package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Item;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.HEALING_EFFECT_OF_SLEEP;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

@Slf4j
@Service
public class HealingService {

    @Autowired
    private StoryLineLogger storyLineLogger;

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
            return;
        }

        character.getHealth().recover(healingEffect);
        storyLineLogger.event(INFO, "Healing -> " + character.getHealth().actualValue());
    }
}
