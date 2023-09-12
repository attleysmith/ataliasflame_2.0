package com.asgames.ataliasflame.application.calculations;

import com.asgames.ataliasflame.application.CharacterTestBase;
import com.asgames.ataliasflame.domain.model.entities.Character;

import static com.asgames.ataliasflame.domain.MockConstants.WEAPONS;
import static com.asgames.ataliasflame.domain.model.enums.ItemCode.DAGGER;

public abstract class RaceCalculationTestBase extends CharacterTestBase {

    protected void addDagger(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        WEAPONS.get(DAGGER).instance().belongsTo(character);
        character.setShield(null);
        character.setArmor(null);

        characterCalculationService.recalculateProperties(character);
        characterRepository.save(character);
    }
}
