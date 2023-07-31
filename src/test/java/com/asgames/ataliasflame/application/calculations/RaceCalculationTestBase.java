package com.asgames.ataliasflame.application.calculations;

import com.asgames.ataliasflame.application.CharacterTestBase;
import com.asgames.ataliasflame.domain.model.entities.Character;

import static com.asgames.ataliasflame.domain.MockConstants.WEAPONS;

public abstract class RaceCalculationTestBase extends CharacterTestBase {

    protected void addDagger(String characterName) {
        Character character = characterService.getCharacter(characterName);
        character.setWeapon(WEAPONS.get("DAGGER"));
        character.setShield(null);
        character.setArmor(null);

        characterRepository.save(
                characterCalculationService.recalculateProperties(character)
        );
    }
}
