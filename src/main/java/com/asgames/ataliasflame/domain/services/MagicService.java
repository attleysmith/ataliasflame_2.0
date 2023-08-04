package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.structures.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.*;

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

    public void castMagic(Character character, Monster monster) {
        while (character.getMagic().has(FIREBALL_MAGIC_COST)
                && monster.getHealth().hasOne()) {
            castFireBall(character, monster);
        }
    }

    private void castFireBall(Character character, Monster monster) {
        if (monster.getHealth().isEmpty()) {
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
}
