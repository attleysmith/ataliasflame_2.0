package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.services.LocationService;
import com.asgames.ataliasflame.infrastructure.repositories.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;

@RequiredArgsConstructor
@Service
public class LocationAdventureService {

    private final LocationRepository locationRepository;
    private final LocationService locationService;

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
    public Weapon getWeapon(String locationReference, String itemReference) {
        Location location = getLocation(locationReference);
        Item item = location.getItems().stream()
                .filter(locationItem -> locationItem.getReference().equals(itemReference))
                .filter(locationItem -> locationItem.getType().equals(WEAPON))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Referenced weapon is not at the location!"));
        return (Weapon) item;
    }

    @Transactional(readOnly = true)
    public Shield getShield(String locationReference, String itemReference) {
        Location location = getLocation(locationReference);
        Item item = location.getItems().stream()
                .filter(locationItem -> locationItem.getReference().equals(itemReference))
                .filter(locationItem -> locationItem.getType().equals(SHIELD))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Referenced shield is not at the location!"));
        return (Shield) item;
    }

    @Transactional(readOnly = true)
    public Armor getArmor(String locationReference, String itemReference) {
        Location location = getLocation(locationReference);
        Item item = location.getItems().stream()
                .filter(locationItem -> locationItem.getReference().equals(itemReference))
                .filter(locationItem -> locationItem.getType().equals(ARMOR))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Referenced armor is not at the location!"));
        return (Armor) item;
    }
}
