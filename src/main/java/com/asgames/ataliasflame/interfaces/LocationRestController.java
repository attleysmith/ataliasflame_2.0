package com.asgames.ataliasflame.interfaces;

import com.asgames.ataliasflame.application.LocationAdventureService;
import com.asgames.ataliasflame.interfaces.mappers.CharacterDtoMapper;
import com.asgames.ataliasflame.interfaces.mappers.LocationDtoMapper;
import com.asgames.ataliasflame.interfaces.model.ArmorDto;
import com.asgames.ataliasflame.interfaces.model.LocationDto;
import com.asgames.ataliasflame.interfaces.model.ShieldDto;
import com.asgames.ataliasflame.interfaces.model.WeaponDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/locations")
public class LocationRestController {

    @Autowired
    private LocationDtoMapper locationDtoMapper;
    @Autowired
    private CharacterDtoMapper characterDtoMapper;

    @Autowired
    private LocationAdventureService locationAdventureService;

    @PostMapping()
    public LocationDto buildLocation(@RequestParam int level) {
        return locationDtoMapper.toLocationDto(locationAdventureService.buildLocation(level));
    }

    @GetMapping("/{locationReference}")
    public LocationDto getLocation(@PathVariable String locationReference) {
        return locationDtoMapper.toLocationDto(locationAdventureService.getLocation(locationReference));
    }

    @GetMapping("/{locationReference}/weapons/{itemReference}")
    public WeaponDto getWeapon(@PathVariable String locationReference, @PathVariable String itemReference) {
        return characterDtoMapper.toWeaponDto(locationAdventureService.getWeapon(locationReference, itemReference));
    }

    @GetMapping("/{locationReference}/shields/{itemReference}")
    public ShieldDto getShield(@PathVariable String locationReference, @PathVariable String itemReference) {
        return characterDtoMapper.toShieldDto(locationAdventureService.getShield(locationReference, itemReference));
    }

    @GetMapping("/{locationReference}/armors/{itemReference}")
    public ArmorDto getArmor(@PathVariable String locationReference, @PathVariable String itemReference) {
        return characterDtoMapper.toArmorDto(locationAdventureService.getArmor(locationReference, itemReference));
    }
}
