package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.dtos.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.HEALING_EFFECT_OF_SLEEP;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;

@Slf4j
@Service
public class HealingService {

    public void sleep(Character character) {
        heal(character, HEALING_EFFECT_OF_SLEEP);
    }

    public void eat(Character character, Item item) {
        if (!item.getType().equals(FOOD)) {
            throw new IllegalArgumentException("Only food can be eaten!");
        }
        heal(character, item.getHealingEffect());
    }

    private void heal(Character character, int healingEffect) {
        if (character.getHealth().isFull()) {
            log.debug("Unnecessary healing!");
            return;
        }

        character.getHealth().recover(healingEffect);
        log.info("Healing to " + character.getHealth().actualValue() + "!");
    }
}
