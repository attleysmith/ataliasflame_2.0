package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Food;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.ArmorType.DIVINE_ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.ENERGY_ARMOR;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.CharacterReportCause.DIED_OF_BLESSING_EXPIRY;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.characterReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.MagicRecoveryEvent.magicRecovery;

@RequiredArgsConstructor
@Service
public class MagicService {

    private static final int MAGIC_RECOVERY_EFFECT_OF_SLEEP = 50;

    private final StoryLineLogger storyLineLogger;
    private final CharacterCalculationService characterCalculationService;

    public void sleep(Character character) {
        recoverMagic(character, MAGIC_RECOVERY_EFFECT_OF_SLEEP);
    }

    public void eat(Character character, Food food) {
        recoverMagic(character, food.getMagicEffect());
    }

    public void recoverMagic(Character character, int recoveryEffect) {
        restoreMagic(character, () -> character.getMagic().recover(recoveryEffect));
    }

    public void replenishMagic(Character character, int replenishValue) {
        restoreMagic(character, () -> character.getMagic().replenish(replenishValue));
    }

    private void restoreMagic(Character character, Runnable restorationMethod) {
        int oldMagic = character.getMagic().actualValue();
        restorationMethod.run();
        storyLineLogger.event(magicRecovery(character, oldMagic));
    }

    public void castSpell(Character character, Spell spell, @Nullable Monster targetMonster, Map<String, String> args) {
        if (!character.getMagic().has(spell.getCost())) {
            throw new IllegalArgumentException("Character does not have enough magic to cast spell!");
        }

        spell.validateArgs(args);
        spell.enforce(character, targetMonster, args);
    }

    public void removeBlessingMagic(Character character) {
        character.getBlessings().clear();
        character.getCover().drop(ENERGY_ARMOR);
        character.getCover().drop(DIVINE_ARMOR);
        characterCalculationService.recalculateProperties(character);
        if (character.getHealth().isEmpty()) {
            storyLineLogger.event(characterReport(character, DIED_OF_BLESSING_EXPIRY));
        }
    }
}
