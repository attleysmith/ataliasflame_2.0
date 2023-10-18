package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.interfaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = "booster.experience:true")
public abstract class WebTestBase {

    @Autowired
    private TestRestTemplate restTemplate;

    protected CharacterDto createCharacter(CharacterInput characterInput) {
        String path = "/characters";
        return restTemplate.postForObject(path, characterInput, CharacterDto.class);
    }

    protected CharacterDto getCharacter(String characterReference) {
        String path = "/characters/{characterReference}";
        return restTemplate.getForObject(path, CharacterDto.class, characterReference);
    }

    protected void removeCharacter(String characterReference) {
        String path = "/characters/{characterReference}";
        restTemplate.delete(path, characterReference);
    }

    protected CharacterDto addAttributePoints(String characterReference, Attribute attribute, int points) {
        String path = "/characters/{characterReference}/attributes/{attribute}/add?points={points}";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, attribute, points);
    }

    protected CharacterDto upgradeCaste(String characterReference, Caste newCaste) {
        String path = "/characters/{characterReference}/caste/upgrade?newCaste={newCaste}";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, newCaste);
    }

    protected List<SpellDto> listCharacterSpells(String characterReference) {
        String path = "/characters/{characterReference}/spells";
        return List.of(requireNonNull(restTemplate.getForEntity(path, SpellDto[].class, characterReference).getBody()));
    }

    protected CharacterDto castSpell(String characterReference, SpellName spellName) {
        String path = "/characters/{characterReference}/spells/{spellName}/cast";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, spellName);
    }

    protected TargetContextDto castTargetingSpell(String characterReference, SpellName spellName, String targetMonsterReference) {
        String path = "/characters/{characterReference}/spells/{spellName}/cast?target={targetMonsterReference}";
        return restTemplate.postForObject(path, null, TargetContextDto.class, characterReference, spellName, targetMonsterReference);
    }

    protected CharacterDto sleep(String characterReference) {
        String path = "/characters/{characterReference}/sleep";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference);
    }

    protected CharacterDto timePassed(String characterReference) {
        String path = "/characters/{characterReference}/time-passed";
        return restTemplate.postForObject(path, null, CharacterDto.class, characterReference);
    }

    protected LocationContextDto enterLocation(String characterReference, String locationReference) {
        String path = "/characters/{characterReference}/location/enter?location={locationReference}";
        return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference, locationReference);
    }

    protected LocationContextDto seizeLocation(String characterReference) {
        String path = "/characters/{characterReference}/location/seize";
        return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference);
    }

    protected LocationContextDto useItem(String characterReference, String itemReference) {
        String path = "/characters/{characterReference}/location/items/{itemReference}/use";
        return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference, itemReference);
    }

    protected LocationContextDto storeItem(String characterReference, String itemReference) {
        String path = "/characters/{characterReference}/location/items/{itemReference}/store";
        return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference, itemReference);
    }

    protected LocationDto buildLocation(int level) {
        String path = "/locations?level={level}";
        return restTemplate.postForObject(path, null, LocationDto.class, level);
    }

    protected LocationDto getLocation(String locationReference) {
        String path = "/locations/{locationReference}";
        return restTemplate.getForObject(path, LocationDto.class, locationReference);
    }

    protected WeaponDto getWeapon(String locationReference, String itemReference) {
        String path = "/locations/{locationReference}/weapons/{itemReference}";
        return restTemplate.getForObject(path, WeaponDto.class, locationReference, itemReference);
    }

    protected ShieldDto getShield(String locationReference, String itemReference) {
        String path = "/locations/{locationReference}/shields/{itemReference}";
        return restTemplate.getForObject(path, ShieldDto.class, locationReference, itemReference);
    }

    protected ArmorDto getArmor(String locationReference, String itemReference) {
        String path = "/locations/{locationReference}/armors/{itemReference}";
        return restTemplate.getForObject(path, ArmorDto.class, locationReference, itemReference);
    }

}
