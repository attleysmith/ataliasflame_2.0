package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.entities.Weapon;
import com.asgames.ataliasflame.domain.services.LocationService;
import com.asgames.ataliasflame.infrastructure.repositories.ItemRepository;
import com.asgames.ataliasflame.infrastructure.repositories.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationAdventureService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LocationService locationService;

    @Transactional
    public Location buildLocation(int level) {
        return locationRepository.save(locationService.buildLocation(level));
    }

    @Transactional(readOnly = true)
    public Location getLocation(String locationReference) {
        return locationRepository.findById(locationReference)
                .orElseThrow(() -> new EntityNotFoundException("Location does not exist!"));
    }

    @Transactional(readOnly = true)
    public Weapon getWeapon(String itemReference) {
        return itemRepository.getWeaponByReference(itemReference);
    }

    @Transactional(readOnly = true)
    public Shield getShield(String itemReference) {
        return itemRepository.getShieldByReference(itemReference);
    }

    @Transactional(readOnly = true)
    public Armor getArmor(String itemReference) {
        return itemRepository.getArmorByReference(itemReference);
    }
}
