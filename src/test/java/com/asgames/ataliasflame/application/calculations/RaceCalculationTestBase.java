package com.asgames.ataliasflame.application.calculations;

import com.asgames.ataliasflame.application.CharacterTestBase;
import com.asgames.ataliasflame.domain.model.entities.Character;

import static com.asgames.ataliasflame.domain.model.enums.ArmorType.BODY_ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.HELMET;
import static com.asgames.ataliasflame.domain.model.enums.WeaponTemplate.DAGGER;

public abstract class RaceCalculationTestBase extends CharacterTestBase {

    protected void addDagger(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        character.setWeapon(DAGGER.instance());
        character.setShield(null);
        character.getCover().drop(HELMET);
        character.getCover().drop(BODY_ARMOR);

        characterCalculationService.recalculateProperties(character);
        characterRepository.save(character);
    }
}
