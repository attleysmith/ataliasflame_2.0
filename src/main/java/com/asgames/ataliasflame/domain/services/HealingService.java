package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.Food;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.HEALING_EFFECT_OF_SLEEP;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.HealthRecoveryEvent.healthRecovery;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionHealingEvent.companionHealing;

@Service
public class HealingService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    public void sleep(Character character) {
        heal(character, HEALING_EFFECT_OF_SLEEP);
    }

    public void eat(Character character, Food food) {
        heal(character, food.getHealingEffect());
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
