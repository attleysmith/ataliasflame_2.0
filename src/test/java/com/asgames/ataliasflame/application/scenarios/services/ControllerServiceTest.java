package com.asgames.ataliasflame.application.scenarios.services;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.interfaces.CharacterRestController;
import com.asgames.ataliasflame.interfaces.LocationRestController;
import com.asgames.ataliasflame.interfaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@ConditionalOnProperty(name = "test.mode.controller", havingValue = "service", matchIfMissing = true)
@Service
public class ControllerServiceTest implements ControllerTest {

    @Autowired
    private CharacterRestController characterController;
    @Autowired
    private LocationRestController locationController;

    @Override
    public CharacterDto createCharacter(CharacterInput characterInput) {
        return characterController.createCharacter(characterInput);
    }

    @Override
    public CharacterDto getCharacter(String characterReference) {
        return characterController.getCharacter(characterReference);
    }

    @Override
    public void removeCharacter(String characterReference) {
        characterController.removeCharacter(characterReference);
    }

    @Override
    public CharacterDto addAttributePoints(String characterReference, Attribute attribute, int points) {
        return characterController.addAttributePoints(characterReference, attribute, points);
    }

    @Override
    public CharacterDto upgradeCaste(String characterReference, Caste newCaste) {
        return characterController.upgradeCaste(characterReference, newCaste);
    }

    @Override
    public List<SpellDto> listCharacterSpells(String characterReference) {
        return characterController.listCharacterSpells(characterReference);
    }

    @Override
    public CharacterDto castSpell(String characterReference, SpellName spellName) {
        return characterController.castSpell(characterReference, spellName);
    }

    @Override
    public TargetContextDto castTargetingSpell(String characterReference, SpellName spellName, String targetMonsterReference) {
        return characterController.castTargetingSpell(characterReference, spellName, targetMonsterReference);
    }

    @Override
    public CharacterDto sleep(String characterReference) {
        return characterController.sleep(characterReference);
    }

    @Override
    public CharacterDto timePassed(String characterReference) {
        return characterController.timePassed(characterReference);
    }

    @Override
    public CharacterDto switchWeapons(String characterReference) {
        return characterController.switchWeapons(characterReference);
    }

    @Override
    public CharacterDto switchShields(String characterReference) {
        return characterController.switchShields(characterReference);
    }

    @Override
    public LocationContextDto enterLocation(String characterReference, String locationReference) {
        return characterController.enterLocation(characterReference, locationReference);
    }

    @Override
    public LocationContextDto seizeLocation(String characterReference) {
        return characterController.seizeLocation(characterReference);
    }

    @Override
    public LocationContextDto useItem(String characterReference, String itemReference) {
        return characterController.useItem(characterReference, itemReference);
    }

    @Override
    public LocationContextDto storeItem(String characterReference, String itemReference) {
        return characterController.storeItem(characterReference, itemReference);
    }

    @Override
    public LocationDto buildLocation(int level) {
        return locationController.buildLocation(level);
    }

    @Override
    public LocationDto getLocation(String locationReference) {
        return locationController.getLocation(locationReference);
    }

    @Override
    public WeaponDto getWeapon(String locationReference, String itemReference) {
        return locationController.getWeapon(locationReference, itemReference);
    }

    @Override
    public ShieldDto getShield(String locationReference, String itemReference) {
        return locationController.getShield(locationReference, itemReference);
    }

    @Override
    public ArmorDto getArmor(String locationReference, String itemReference) {
        return locationController.getArmor(locationReference, itemReference);
    }
}
