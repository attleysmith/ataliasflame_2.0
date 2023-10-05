package com.asgames.ataliasflame.interfaces;

import com.asgames.ataliasflame.application.CharacterAdventureService;
import com.asgames.ataliasflame.application.CharacterLocationService;
import com.asgames.ataliasflame.application.CharacterMagicService;
import com.asgames.ataliasflame.application.CharacterMaintenanceService;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.interfaces.mappers.CharacterDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.DefensiveGodConversionCodeDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.LocationDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.SpellDtoMapper;
import com.asgames.ataliasflame.interfaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/characters")
public class CharacterRestController {

    @Autowired
    private CharacterDtoMapper characterDtoMapper;
    @Autowired
    private DefensiveGodConversionCodeDtoMapper defensiveGodConversionCodeDtoMapper;
    @Autowired
    private SpellDtoMapper spellDtoMapper;
    @Autowired
    private LocationDtoMapper locationDtoMapper;

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    private CharacterAdventureService characterAdventureService;
    @Autowired
    private CharacterMagicService characterMagicService;
    @Autowired
    private CharacterLocationService characterLocationService;

    @PostMapping()
    public CharacterDto createCharacter(@RequestBody CharacterInput characterInput) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.createCharacter(characterInput));
    }

    @GetMapping("/{characterReference}")
    public CharacterDto getCharacter(@PathVariable String characterReference) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.getCharacter(characterReference));
    }

    @DeleteMapping("/{characterReference}")
    public void removeCharacter(@PathVariable String characterReference) {
        characterMaintenanceService.removeCharacter(characterReference);
    }

    @PostMapping("/{characterReference}/attributes/{attribute}/add")
    public CharacterDto addAttributePoints(@PathVariable String characterReference, @PathVariable Attribute attribute, @RequestParam int points) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.addAttributePoints(characterReference, attribute, points));
    }

    @PostMapping("/{characterReference}/caste/upgrade")
    public CharacterDto upgradeCaste(@PathVariable String characterReference, @RequestParam Caste newCaste) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.upgradeCaste(characterReference, newCaste));
    }

    @PostMapping("/{characterReference}/sleep")
    public CharacterDto sleep(@PathVariable String characterReference) {
        return characterDtoMapper.toCharacterDto(characterAdventureService.sleep(characterReference));
    }

    @PostMapping("/{characterReference}/time-passed")
    public CharacterDto timePassed(@PathVariable String characterReference) {
        return characterDtoMapper.toCharacterDto(characterAdventureService.timePassed(characterReference));
    }

    @PostMapping("/{characterReference}/defensive-god/convert")
    public CharacterDto convertDefensiveGod(@PathVariable String characterReference, @RequestParam String conversionCode) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.convertDefensiveGod(characterReference, conversionCode));
    }

    @PostMapping("/{characterReference}/defensive-god/conversion-code/produce")
    public DefensiveGodConversionCodeDto produceDefensiveGodConversionCode(@PathVariable String characterReference) {
        return defensiveGodConversionCodeDtoMapper.toDto(characterAdventureService.produceDefensiveGodConversionCode(characterReference));
    }

    @GetMapping("/{characterReference}/spells")
    public List<SpellDto> listCharacterSpells(@PathVariable String characterReference) {
        return spellDtoMapper.toSpellDtoList(characterMagicService.listCharacterSpells(characterReference));
    }

    @PostMapping(value = "/{characterReference}/spells/{spellName}/cast", params = "!target")
    public CharacterDto castSpell(@PathVariable String characterReference, @PathVariable SpellName spellName) {
        return characterDtoMapper.toCharacterDto(characterMagicService.castSpell(characterReference, spellName));
    }

    @PostMapping(value = "/{characterReference}/spells/{spellName}/cast", params = "target")
    public TargetContextDto castTargetingSpell(@PathVariable String characterReference, @PathVariable SpellName spellName, @RequestParam(name = "target") String monsterReference) {
        return locationDtoMapper.toTargetContextDto(characterMagicService.castTargetingSpell(characterReference, spellName, monsterReference));
    }

    @PostMapping(value = "/{characterReference}/location/enter")
    public LocationContextDto enterLocation(@PathVariable String characterReference, @RequestParam("location") String locationReference) {
        return locationDtoMapper.toLocationContextDto(characterLocationService.enterLocation(characterReference, locationReference));
    }

    @PostMapping(value = "/{characterReference}/location/seize")
    public LocationContextDto seizeLocation(@PathVariable String characterReference) {
        return locationDtoMapper.toLocationContextDto(characterLocationService.seizeLocation(characterReference));
    }

    @PostMapping(value = "/{characterReference}/location/items/{itemReference}/use")
    public LocationContextDto useItem(@PathVariable String characterReference, @PathVariable String itemReference) {
        return locationDtoMapper.toLocationContextDto(characterLocationService.useItem(characterReference, itemReference));
    }

    @PostMapping(value = "/{characterReference}/items/{itemReference}/drop")
    public LocationContextDto dropItem(@PathVariable String characterReference, @PathVariable String itemReference) {
        return locationDtoMapper.toLocationContextDto(characterLocationService.dropItem(characterReference, itemReference));
    }
}
