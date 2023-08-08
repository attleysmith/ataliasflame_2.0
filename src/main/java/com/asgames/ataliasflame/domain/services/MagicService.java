package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Monster;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.ATTACK;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.SUMMON;
import static com.asgames.ataliasflame.domain.model.enums.SummonType.SOUL_CHIP;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class MagicService {

    public void sleep(Character character) {
        recover(character, MAGIC_RECOVERY_EFFECT_OF_SLEEP);
    }

    private void recover(Character character, int recoveryEffect) {
        if (character.getMagic().isFull()) {
            log.debug("Unnecessary magic recovery!");
            return;
        }

        character.getMagic().recover(recoveryEffect);
        log.info("Recovering to " + character.getMagic().actualValue() + " magic point!");
    }

    public void castSummoningMagic(Character character) {
        selectSummoningSpell(character)
                .ifPresent(spell -> summonSoulChip(character, spell));
    }

    private Optional<Spell> selectSummoningSpell(Character character) {
        List<Spell> summoningSpells = usableSpellsOfType(character, SUMMON);
        switch (summoningSpells.size()) {
            case 0:
                return Optional.empty();
            case 1:
                return Optional.of(summoningSpells.get(0));
            default:
                return summoningSpells.stream()
                        .min(comparing(Spell::getCost));
        }
    }

    private void summonSoulChip(Character character, Spell spell) {
        if (!spell.getSummonType().equals(SOUL_CHIP)) {
            throw new UnsupportedOperationException("Only soul chip summoning is supported!");
        }

        SoulChip summonedSoulChip = null;
        List<String> companionNames = character.getCompanions().stream().map(Companion::getName).collect(toList());
        for (SoulChip soulChip : character.getSoulChips()) {
            if (!companionNames.contains(soulChip.getName())) {
                summonedSoulChip = soulChip;
                break;
            }
        }
        if (summonedSoulChip == null) {
            log.warn("Soul chip summoning was unsuccessful!");
            return;
        }

        character.getMagic().use(spell.getCost());
        character.getCompanions().add(summonedSoulChip.summon());
        log.info(summonedSoulChip.getName() + " summoned as companion.");
    }

    public void castAttackMagic(Character character, List<Monster> monsters) {
        if (character.getCompanions().isEmpty() && !spellsOfType(character, SUMMON).isEmpty()) {
            return;
        }
        monsters.forEach(monster -> castAttackMagic(character, monster));
    }

    public void castAttackMagic(Character character, Monster monster) {
        AtomicBoolean tryToAttack = new AtomicBoolean(true);
        while (tryToAttack.get() && monster.isAlive()) {
            selectAttackingSpell(character).ifPresentOrElse(
                    spell -> castAttackSpell(character, monster, spell),
                    () -> tryToAttack.set(false)
            );
        }
    }

    private Optional<Spell> selectAttackingSpell(Character character) {
        List<Spell> attackingSpells = usableSpellsOfType(character, ATTACK);
        switch (attackingSpells.size()) {
            case 0:
                return Optional.empty();
            case 1:
                return Optional.of(attackingSpells.get(0));
            default:
                return attackingSpells.stream()
                        .max(comparing(Spell::getCost));
        }
    }

    private void castAttackSpell(Character character, Monster monster, Spell spell) {
        if (monster.isDead()) {
            log.warn("Unnecessary use of attack spell!");
            return;
        }
        character.getMagic().use(spell.getCost());
        int damage = pointOut(spell.getMinDamage(), spell.getMaxDamage());
        monster.getHealth().damage(damage);
        log.info(spell.getName() + " deals " + damage + " damage to " + monster.getCode());
    }

    private List<Spell> spellsOfType(Character character, MagicType magicType) {
        return SPELLS.values().stream()
                .filter(spell -> spell.getType().equals(magicType))
                .filter(spell -> !CASTE_SPELL_PROHIBITION.get(character.getCaste())
                        .contains(spell.getName()))
                .filter(spell -> !RACE_SPELL_PROHIBITION.get(character.getRace())
                        .contains(spell.getName()))
                .collect(toList());
    }

    private List<Spell> usableSpellsOfType(Character character, MagicType magicType) {
        return spellsOfType(character, magicType).stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .collect(toList());
    }
}
