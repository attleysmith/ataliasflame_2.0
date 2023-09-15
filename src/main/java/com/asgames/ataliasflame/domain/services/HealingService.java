package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.Food;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.HealthRecoveryEvent.healthRecovery;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionHealingEvent.companionHealing;

@Service
public class HealingService {

    private static final int HEALING_EFFECT_OF_SLEEP = 20;

    @Autowired
    private StoryLineLogger storyLineLogger;

    public void sleep(Character character) {
        recoverHealth(character, HEALING_EFFECT_OF_SLEEP);
    }

    public void companionSleep(Companion companion) {
        healCompanion(companion, HEALING_EFFECT_OF_SLEEP);
    }

    public void eat(Character character, Food food) {
        recoverHealth(character, food.getHealingEffect());
    }

    public void recoverHealth(Character character, int healingEffect) {
        heal(character, () -> character.getHealth().recover(healingEffect));
    }

    public void replenishHealth(Character character, int healingValue) {
        heal(character, () -> character.getHealth().replenish(healingValue));
    }

    private void heal(Character character, Runnable healingMethod) {
        if (character.getHealth().isFull()) {
            return;
        }

        int oldHealth = character.getHealth().actualValue();
        healingMethod.run();
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
