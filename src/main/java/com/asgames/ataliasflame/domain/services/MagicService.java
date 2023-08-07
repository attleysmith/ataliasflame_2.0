package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.CasteDetails;
import com.asgames.ataliasflame.domain.model.dtos.Monster;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.TRACKER;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.WANDERER;
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
        if (canSummon(character) && character.getMagic().has(SUMMONING_MAGIC_COST)) {
            List<String> companionNames = character.getCompanions().stream().map(Companion::getName).collect(toList());
            for (SoulChip soulChip : character.getSoulChips()) {
                if (!companionNames.contains(soulChip.getName())) {
                    summonSoulChip(character, soulChip);
                    break;
                }
            }
        }
    }

    private void summonSoulChip(Character character, SoulChip soulChip) {
        if (character.getMagic().hasNot(SUMMONING_MAGIC_COST)) {
            throw new IllegalArgumentException("Character doesn't have enough magic for summoning a soul chip!");
        }
        character.getMagic().use(SUMMONING_MAGIC_COST);
        character.getCompanions().add(soulChip.summon());
        log.info(soulChip.getName() + " summoned as companion.");
    }

    public void castAttackMagic(Character character, List<Monster> monsters) {
        if (canSummon(character) && character.getCompanions().isEmpty()) {
            return;
        }
        monsters.forEach(monster -> castAttackMagic(character, monster));
    }

    public void castAttackMagic(Character character, Monster monster) {
        while (character.getMagic().has(FIREBALL_MAGIC_COST)
                && monster.isAlive()) {
            castFireBall(character, monster);
        }
    }

    private void castFireBall(Character character, Monster monster) {
        if (monster.isDead()) {
            log.debug("Unnecessary use of fireball!");
            return;
        }
        if (character.getMagic().hasNot(FIREBALL_MAGIC_COST)) {
            throw new IllegalArgumentException("Character doesn't have enough magic for casting a fireball!");
        }
        character.getMagic().use(FIREBALL_MAGIC_COST);
        monster.getHealth().damage(FIREBALL_MAGIC_DAMAGE);
        log.info("Fireball used on " + monster.getCode());
    }

    private boolean canSummon(Character character) {
        CasteDetails casteDetails = CASTE_DETAILS.get(character.getCaste());
        return casteDetails.getGroup().equals(WANDERER) && !casteDetails.getCaste().equals(TRACKER);
    }
}
