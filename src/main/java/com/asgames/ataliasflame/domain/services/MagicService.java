package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Food;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.SpellRegistry;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.CharacterReportCause.DIED_OF_BLESSING_EXPIRY;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.characterReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.MagicRecoveryEvent.magicRecovery;

@Service
public class MagicService {

    private static final int MAGIC_RECOVERY_EFFECT_OF_SLEEP = 50;

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private SpellRegistry spellRegistry;

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public void sleep(Character character) {
        recover(character, MAGIC_RECOVERY_EFFECT_OF_SLEEP);
    }

    public void eat(Character character, Food food) {
        recover(character, food.getMagicEffect());
    }

    private void recover(Character character, int recoveryEffect) {
        if (character.getMagic().isFull()) {
            return;
        }

        int oldMagic = character.getMagic().actualValue();
        character.getMagic().recover(recoveryEffect);
        storyLineLogger.event(magicRecovery(character, oldMagic));
    }

    public void castSpell(Character character, Spell spell, @Nullable Monster targetMonster) {
        if (!character.getMagic().has(spell.getCost())) {
            throw new IllegalArgumentException("Character does not have enough magic to cast spell!");
        }

        spellRegistry
                .get(spell.getName())
                .enforce(character, targetMonster);
    }

    public void removeBlessingMagic(Character character) {
        character.setBlessings(null);
        characterCalculationService.recalculateProperties(character);
        if (character.getHealth().isEmpty()) {
            storyLineLogger.event(characterReport(character, DIED_OF_BLESSING_EXPIRY));
        }
    }
}
