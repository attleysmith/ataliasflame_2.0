package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.HEALING_EFFECT_OF_SLEEP;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.HealthRecoveryEvent.healthRecovery;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionHealingEvent.companionHealing;

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

    public void heal(Character character, int healingEffect) {
        if (character.getHealth().isFull()) {
            return;
        }

        int oldHealth = character.getHealth().actualValue();
        character.getHealth().recover(healingEffect);
        storyLineLogger.event(healthRecovery(character, oldHealth));
    }

    public void healCompanion(Companion companion, int healingEffect) {
        if (companion.getHealth().isFull()) {
            return;
        }

        int oldHealth = companion.getHealth().actualValue();
        companion.getHealth().recover(healingEffect);
        storyLineLogger.event(companionHealing(companion, oldHealth));
    }
}
