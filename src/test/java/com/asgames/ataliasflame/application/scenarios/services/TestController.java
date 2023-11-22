package com.asgames.ataliasflame.application.scenarios.services;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.interfaces.model.*;

import java.util.List;
import java.util.Map;

public interface TestController {

    CharacterDto createCharacter(CharacterInput characterInput);

    CharacterDto getCharacter(String characterReference);

    void removeCharacter(String characterReference);

    CharacterDto addAttributePoints(String characterReference, Attribute attribute, int points);

    CharacterDto upgradeCaste(String characterReference, Caste newCaste);

    List<SpellDto> listCharacterSpells(String characterReference);

    CharacterDto castSpell(String characterReference, SpellName spellName, Map<String, String> args);

    TargetContextDto castTargetingSpell(String characterReference, SpellName spellName, String targetMonsterReference, Map<String, String> args);

    CharacterDto sleep(String characterReference);

    CharacterDto timePassed(String characterReference);

    CharacterDto switchWeapons(String characterReference);

    CharacterDto switchShields(String characterReference);

    LocationContextDto enterLocation(String characterReference, String locationReference);

    LocationContextDto seizeLocation(String characterReference);

    LocationContextDto useItem(String characterReference, String itemReference);

    LocationContextDto storeItem(String characterReference, String itemReference);

    LocationDto buildLocation(int level);

    LocationDto getLocation(String locationReference);

    WeaponDto getWeapon(String locationReference, String itemReference);

    ShieldDto getShield(String locationReference, String itemReference);

    ArmorDto getArmor(String locationReference, String itemReference);
}
