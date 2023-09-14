package com.asgames.ataliasflame.application.calculations;

import com.asgames.ataliasflame.application.CharacterTestBase;
import com.asgames.ataliasflame.domain.model.entities.Character;

import static com.asgames.ataliasflame.domain.model.enums.WeaponTemplate.DAGGER;

public abstract class RaceCalculationTestBase extends CharacterTestBase {

    protected void addDagger(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        DAGGER.instance().belongsTo(character);
        character.setShield(null);
        character.getCover().setPhysicalArmor(null);

        characterCalculationService.recalculateProperties(character);
        characterRepository.save(character);
    }
}
