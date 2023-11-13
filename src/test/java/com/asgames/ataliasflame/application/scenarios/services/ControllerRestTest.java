package com.asgames.ataliasflame.application.scenarios.services;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.interfaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;

@ConditionalOnProperty(name = "test.mode.controller", havingValue = "rest")
@Service
public class ControllerRestTest implements ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    public CharacterDto createCharacter(CharacterInput characterInput) {
        String path = "/characters";
        return restTemplate.postForObject(path, characterInput, CharacterDto.class);
    }

    public CharacterDto getCharacter(String characterReference) {
        String path = "/characters/{characterReference}";
        return restTemplate.getForObject(path, CharacterDto.class, characterReference);
    }

    public void removeCharacter(String characterReference) {
        String path = "/characters/{characterReference}";
        restTemplate.delete(path, characterReference);
    }

    public CharacterDto addAttributePoints(String characterReference, Attribute attribute, int points) {
        String path = "/characters/{characterReference}/attributes/{attribute}/add?points={points}";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, attribute, points);
    }

    public CharacterDto upgradeCaste(String characterReference, Caste newCaste) {
        String path = "/characters/{characterReference}/caste/upgrade?newCaste={newCaste}";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, newCaste);
    }

    public List<SpellDto> listCharacterSpells(String characterReference) {
        String path = "/characters/{characterReference}/spells";
        return List.of(requireNonNull(restTemplate.getForEntity(path, SpellDto[].class, characterReference).getBody()));
    }

    public CharacterDto castSpell(String characterReference, SpellName spellName) {
        String path = "/characters/{characterReference}/spells/{spellName}/cast";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, spellName);
    }

    public TargetContextDto castTargetingSpell(String characterReference, SpellName spellName, String targetMonsterReference) {
        String path = "/characters/{characterReference}/spells/{spellName}/cast?target={targetMonsterReference}";
        return restTemplate.postForObject(path, null, TargetContextDto.class, characterReference, spellName, targetMonsterReference);
    }

    public CharacterDto sleep(String characterReference) {
        String path = "/characters/{characterReference}/sleep";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference);
    }

    public CharacterDto timePassed(String characterReference) {
        String path = "/characters/{characterReference}/time-passed";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference);
    }

    public CharacterDto switchWeapons(String characterReference) {
        String path = "/characters/{characterReference}/weapons/switch";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference);
    }

    public CharacterDto switchShields(String characterReference) {
        String path = "/characters/{characterReference}/shields/switch";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference);
    }

    public LocationContextDto enterLocation(String characterReference, String locationReference) {
        String path = "/characters/{characterReference}/location/enter?location={locationReference}";
        return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference, locationReference);
    }

    public LocationContextDto seizeLocation(String characterReference) {
        String path = "/characters/{characterReference}/location/seize";
        return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference);
    }

    public LocationContextDto useItem(String characterReference, String itemReference) {
        String path = "/characters/{characterReference}/location/items/{itemReference}/use";
        return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference, itemReference);
    }

    public LocationContextDto storeItem(String characterReference, String itemReference) {
        String path = "/characters/{characterReference}/location/items/{itemReference}/store";
        return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference, itemReference);
    }

    public LocationDto buildLocation(int level) {
        String path = "/locations?level={level}";
        return restTemplate.postForObject(path, null, LocationDto.class, level);
    }

    public LocationDto getLocation(String locationReference) {
        String path = "/locations/{locationReference}";
        return restTemplate.getForObject(path, LocationDto.class, locationReference);
    }

    public WeaponDto getWeapon(String locationReference, String itemReference) {
        String path = "/locations/{locationReference}/weapons/{itemReference}";
        return restTemplate.getForObject(path, WeaponDto.class, locationReference, itemReference);
    }

    public ShieldDto getShield(String locationReference, String itemReference) {
        String path = "/locations/{locationReference}/shields/{itemReference}";
        return restTemplate.getForObject(path, ShieldDto.class, locationReference, itemReference);
    }

    public ArmorDto getArmor(String locationReference, String itemReference) {
        String path = "/locations/{locationReference}/armors/{itemReference}";
        return restTemplate.getForObject(path, ArmorDto.class, locationReference, itemReference);
    }
}
