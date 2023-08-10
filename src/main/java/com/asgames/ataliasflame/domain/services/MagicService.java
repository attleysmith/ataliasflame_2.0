package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Monster;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.ATTACK;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.SUMMON;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.*;
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
        int previousNumberOfCompanions = -1;
        int actualNumberOfCompanions = character.getCompanions().size();
        while (previousNumberOfCompanions < actualNumberOfCompanions) {
            previousNumberOfCompanions = actualNumberOfCompanions;

            usableSpellsOfType(character, SUMMON).stream()
                    .sorted(comparing(Spell::getCost).reversed())
                    .forEach(spell -> {
                        if (character.getMagic().has(spell.getCost())) {
                            castSummoningSpell(character, spell);
                        }
                    });

            actualNumberOfCompanions = character.getCompanions().size();
        }
    }

    private void castSummoningSpell(Character character, Spell spell) {
        Optional<Companion> summoning;
        switch (spell.getGroup()) {
            case SOUL:
                summoning = summonSoulChip(character);
                break;
            case NATURE:
                summoning = summonAnimal(character);
                break;
            case GENERAL:
                summoning = summonGuardianWarrior(character);
                break;
            case ENERGY:
                summoning = summonEnergy(character);
                break;
            case DIVINE:
                summoning = summonDivineGuardian(character);
                break;
            default:
                throw new UnsupportedOperationException(spell.getGroup() + " summoning is not supported!");
        }
        if (summoning.isEmpty()) {
            log.warn("Summoning was unsuccessful!");
            return;
        }
        character.getMagic().use(spell.getCost());
        character.getCompanions().add(summoning.get());
        log.info(summoning.get().getName() + " summoned as companion.");
    }

    private Optional<Companion> summonSoulChip(Character character) {
        List<String> companionNames = character.getCompanions().stream().map(Companion::getName).collect(toList());
        for (SoulChip soulChip : character.getSoulChips()) {
            if (!companionNames.contains(soulChip.getName())) {
                return Optional.of(soulChip.summon());
            }
        }
        return Optional.empty();
    }

    private Optional<Companion> summonAnimal(Character character) {
        int numberOfCompanions = character.getCompanions().size();
        if (numberOfCompanions < 5) {
            return choose(ANIMAL_SELECTOR).map(animalSummoned ->
                    animalSummoned.instance(character, UUID.randomUUID().toString()));
        }
        return Optional.empty();
    }

    private Optional<Companion> summonGuardianWarrior(Character character) {
        int numberOfCompanions = character.getCompanions().size();
        if (numberOfCompanions < 2) {
            return choose(GUARDIAN_WARRIOR_SELECTOR).map(guardianSummoned ->
                    guardianSummoned.instance(character, UUID.randomUUID().toString()));
        }
        return Optional.empty();
    }

    private Optional<Companion> summonEnergy(Character character) {
        int numberOfCompanions = character.getCompanions().size();
        if (numberOfCompanions < 1) {
            Companion projection = Companion.builder()
                    .name("Energy of " + character.getName() + "-" + UUID.randomUUID())
                    .owner(character)
                    .attack(percent(character.getAttack(), ENERGY_PROJECTION_PERCENT))
                    .defense(percent(character.getDefense(), ENERGY_PROJECTION_PERCENT))
                    .minDamage(percent(character.getMinDamage(), ENERGY_PROJECTION_PERCENT))
                    .maxDamage(percent(character.getMaxDamage(), ENERGY_PROJECTION_PERCENT))
                    .health(Energy.withTotal(percent(character.getHealth().totalValue(), ENERGY_PROJECTION_PERCENT)))
                    .initiative(character.getInitiative())
                    .build();
            return Optional.of(projection);
        }
        return Optional.empty();
    }

    private Optional<Companion> summonDivineGuardian(Character character) {
        int numberOfCompanions = character.getCompanions().size();
        if (numberOfCompanions < 1) {
            return choose(DIVINE_GUARDIAN_SELECTOR).map(guardianSummoned ->
                    guardianSummoned.instance(character, UUID.randomUUID().toString()));
        }
        return Optional.empty();
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
