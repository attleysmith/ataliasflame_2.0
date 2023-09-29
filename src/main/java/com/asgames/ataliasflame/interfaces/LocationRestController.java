package com.asgames.ataliasflame.interfaces;

import com.asgames.ataliasflame.application.LocationAdventureService;
import com.asgames.ataliasflame.interfaces.mappers.CharacterDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.LocationDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.MonsterDtoMapper;
import com.asgames.ataliasflame.interfaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class LocationRestController {

    @Autowired
    private LocationDtoMapper locationDtoMapper;
    @Autowired
    private MonsterDtoMapper monsterDtoMapper;
    @Autowired
    private CharacterDtoMapper characterDtoMapper;

    @Autowired
    private LocationAdventureService locationAdventureService;

    @PostMapping("/locations")
    public LocationDto buildLocation(@RequestParam int level) {
        return locationDtoMapper.toLocationDto(locationAdventureService.buildLocation(level));
    }

    @GetMapping("/locations/{locationReference}")
    public LocationDto getLocation(@PathVariable String locationReference) {
        return locationDtoMapper.toLocationDto(locationAdventureService.getLocation(locationReference));
    }

    @GetMapping("/monsters/{monsterReference}")
    public MonsterDto getMonster(@PathVariable String monsterReference) {
        return monsterDtoMapper.toMonsterDto(locationAdventureService.getMonster(monsterReference));
    }

    @GetMapping("/weapons/{itemReference}")
    public WeaponDto getWeapon(@PathVariable String itemReference) {
        return characterDtoMapper.toWeaponDto(locationAdventureService.getWeapon(itemReference));
    }

    @GetMapping("/shields/{itemReference}")
    public ShieldDto getShield(@PathVariable String itemReference) {
        return characterDtoMapper.toShieldDto(locationAdventureService.getShield(itemReference));
    }

    @GetMapping("/armors/{itemReference}")
    public ArmorDto getArmor(@PathVariable String itemReference) {
        return characterDtoMapper.toArmorDto(locationAdventureService.getArmor(itemReference));
    }
}