package com.asgames.ataliasflame.interfaces;

import com.asgames.ataliasflame.application.CharacterAdventureService;
import com.asgames.ataliasflame.application.CharacterMagicService;
import com.asgames.ataliasflame.application.CharacterMaintenanceService;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.interfaces.mappers.CharacterDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.DefensiveGodConversionCodeDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.MonsterDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.SpellDtoMapper;
import com.asgames.ataliasflame.interfaces.model.CharacterDto;
import com.asgames.ataliasflame.interfaces.model.DefensiveGodConversionCodeDto;
import com.asgames.ataliasflame.interfaces.model.SpellDto;
import com.asgames.ataliasflame.interfaces.model.TargetContextDto;
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
    private MonsterDtoMapper monsterDtoMapper;

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    private CharacterAdventureService characterAdventureService;
    @Autowired
    private CharacterMagicService characterMagicService;

    @PostMapping
    public CharacterDto createCharacter(@RequestBody CharacterInput characterInput) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.createCharacter(characterInput));
    }

    @GetMapping("/{reference}")
    public CharacterDto getCharacter(@PathVariable String reference) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.getCharacter(reference));
    }

    @DeleteMapping("/{reference}")
    public void removeCharacter(@PathVariable String reference) {
        characterMaintenanceService.removeCharacter(reference);
    }

    @PostMapping("/{reference}/attributes/{attribute}/add")
    public CharacterDto addAttributePoints(@PathVariable String reference, @PathVariable Attribute attribute, @RequestParam int points) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.addAttributePoints(reference, attribute, points));
    }

    @PostMapping("/{reference}/caste/upgrade")
    public CharacterDto upgradeCaste(@PathVariable String reference, @RequestParam Caste newCaste) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.upgradeCaste(reference, newCaste));
    }

    @PostMapping("/{reference}/sleep")
    public CharacterDto sleep(@PathVariable String reference) {
        return characterDtoMapper.toCharacterDto(characterAdventureService.sleep(reference));
    }

    @PostMapping("/{reference}/time-passed")
    public CharacterDto timePassed(@PathVariable String reference) {
        return characterDtoMapper.toCharacterDto(characterAdventureService.timePassed(reference));
    }

    @PostMapping("/{reference}/defensive-god/convert")
    public CharacterDto convertDefensiveGod(@PathVariable String reference, @RequestParam String conversionCode) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.convertDefensiveGod(reference, conversionCode));
    }

    @PostMapping("/{reference}/defensive-god/conversion-code/produce")
    public DefensiveGodConversionCodeDto produceDefensiveGodConversionCode(@PathVariable String reference) {
        return defensiveGodConversionCodeDtoMapper.toDto(characterAdventureService.produceDefensiveGodConversionCode(reference));
    }

    @GetMapping("/{reference}/spells")
    public List<SpellDto> listCharacterSpells(@PathVariable String reference) {
        return spellDtoMapper.toSpellDtoList(characterMagicService.listCharacterSpells(reference));
    }

    @PostMapping(value = "/{reference}/spells/{spellName}/cast", params = "!target")
    public CharacterDto castSpell(@PathVariable String reference, @PathVariable SpellName spellName) {
        return characterDtoMapper.toCharacterDto(characterMagicService.castSpell(reference, spellName));
    }

    @PostMapping(value = "/{reference}/spells/{spellName}/cast", params = "target")
    public TargetContextDto castTargetingSpell(@PathVariable String reference, @PathVariable SpellName spellName, @RequestParam(name = "target") String monsterReference) {
        return monsterDtoMapper.toTargetContextDto(characterMagicService.castTargetingSpell(reference, spellName, monsterReference));
    }
}
