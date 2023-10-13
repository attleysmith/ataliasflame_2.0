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
        try {
            String path = "/characters";
            return restTemplate.postForObject(path, characterInput, CharacterDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected CharacterDto getCharacter(String characterReference) {
        try {
            String path = "/characters/{characterReference}";
            return restTemplate.getForObject(path, CharacterDto.class, characterReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void removeCharacter(String characterReference) {
        try {
            String path = "/characters/{characterReference}";
            restTemplate.delete(path, characterReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected CharacterDto addAttributePoints(String characterReference, Attribute attribute, int points) {
        try {
            String path = "/characters/{characterReference}/attributes/{attribute}/add?points={points}";
            return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, attribute, points);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected CharacterDto upgradeCaste(String characterReference, Caste newCaste) {
        try {
            String path = "/characters/{characterReference}/caste/upgrade?newCaste={newCaste}";
            return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, newCaste);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected List<SpellDto> listCharacterSpells(String characterReference) {
        try {
            String path = "/characters/{characterReference}/spells";
            return List.of(requireNonNull(restTemplate.getForEntity(path, SpellDto[].class, characterReference).getBody()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected CharacterDto castSpell(String characterReference, SpellName spellName) {
        try {
            String path = "/characters/{characterReference}/spells/{spellName}/cast";
            return restTemplate.postForObject(path, null, CharacterDto.class, characterReference, spellName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected TargetContextDto castTargetingSpell(String characterReference, SpellName spellName, String targetMonsterReference) {
        try {
            String path = "/characters/{characterReference}/spells/{spellName}/cast?target={targetMonsterReference}";
            return restTemplate.postForObject(path, null, TargetContextDto.class, characterReference, spellName, targetMonsterReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected CharacterDto sleep(String characterReference) {
        try {
            String path = "/characters/{characterReference}/sleep";
            return restTemplate.postForObject(path, null, CharacterDto.class, characterReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected CharacterDto timePassed(String characterReference) {
        try {
            String path = "/characters/{characterReference}/time-passed";
            return restTemplate.postForObject(path, null, CharacterDto.class, characterReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected LocationContextDto enterLocation(String characterReference, String locationReference) {
        try {
            String path = "/characters/{characterReference}/location/enter?location={locationReference}";
            return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference, locationReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected LocationContextDto seizeLocation(String characterReference) {
        try {
            String path = "/characters/{characterReference}/location/seize";
            return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected LocationContextDto useItem(String characterReference, String itemReference) {
        try {
            String path = "/characters/{characterReference}/location/items/{itemReference}/use";
            return restTemplate.postForObject(path, null, LocationContextDto.class, characterReference, itemReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected LocationDto buildLocation(int level) {
        try {
            String path = "/locations?level={level}";
            return restTemplate.postForObject(path, null, LocationDto.class, level);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected LocationDto getLocation(String locationReference) {
        try {
            String path = "/locations/{locationReference}";
            return restTemplate.getForObject(path, LocationDto.class, locationReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected WeaponDto getWeapon(String locationReference, String itemReference) {
        try {
            String path = "/locations/{locationReference}/weapons/{itemReference}";
            return restTemplate.getForObject(path, WeaponDto.class, locationReference, itemReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected ShieldDto getShield(String locationReference, String itemReference) {
        try {
            String path = "/locations/{locationReference}/shields/{itemReference}";
            return restTemplate.getForObject(path, ShieldDto.class, locationReference, itemReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected ArmorDto getArmor(String locationReference, String itemReference) {
        try {
            String path = "/locations/{locationReference}/armors/{itemReference}";
            return restTemplate.getForObject(path, ArmorDto.class, locationReference, itemReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
