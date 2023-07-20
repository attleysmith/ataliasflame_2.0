package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static java.lang.Math.max;

@Slf4j
@Service
public class MagicService {

    @Autowired
    private CombatService combatService;

    public void sleep(Character character) {
        recover(character, MAGIC_RECOVERY_EFFECT_OF_SLEEP);
    }

    private void recover(Character character, int recoveryEffect) {
        if (character.getUsedMagicPoint() == 0) {
            log.debug("Unnecessary magic recovery!");
            return;
        }

        int recoveryValue = percent(character.getTotalMagicPoint(), recoveryEffect);
        int remainingUsedMagicPoint = max(0, character.getUsedMagicPoint() - recoveryValue);

        character.setUsedMagicPoint(remainingUsedMagicPoint);
        log.info("Recovering to " + character.getActualMagicPoint() + " magic point!");
    }

    public void castMagic(Character character, Monster monster) {
        while (character.getActualMagicPoint() >= FIREBALL_MAGIC_COST
                && monster.getActualHealth() > 0) {
            castFireBall(character, monster);
        }
    }

    private void castFireBall(Character character, Monster monster) {
        if (monster.getActualHealth() == 0) {
            log.debug("Unnecessary use of fireball!");
            return;
        }
        if (character.getActualMagicPoint() < FIREBALL_MAGIC_COST) {
            throw new IllegalArgumentException("Character doesn't have enough magic for casting a fireball!");
        }
        character.setUsedMagicPoint(character.getUsedMagicPoint() + FIREBALL_MAGIC_COST);
        combatService.dealDamage(monster, FIREBALL_MAGIC_DAMAGE);
        log.info("Fireball used on " + monster.getCode());
    }
}
