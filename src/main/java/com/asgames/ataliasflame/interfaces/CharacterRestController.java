package com.asgames.ataliasflame.interfaces;

import com.asgames.ataliasflame.application.CharacterMaintenanceService;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.interfaces.mappers.CharacterDtoMapper;
import com.asgames.ataliasflame.interfaces.model.CharacterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/characters")
public class CharacterRestController {

    @Autowired
    private CharacterDtoMapper characterDtoMapper;

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;

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

    @PostMapping("/{reference}/defensiveGod/convert")
    public CharacterDto convertDefensiveGod(@PathVariable String reference, @RequestParam String conversionCode) {
        return characterDtoMapper.toCharacterDto(characterMaintenanceService.convertDefensiveGod(reference, conversionCode));
    }
}
