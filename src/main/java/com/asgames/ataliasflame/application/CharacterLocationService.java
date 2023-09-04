package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.LocationContext;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.InventoryService;
import com.asgames.ataliasflame.domain.services.LocationService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.ItemRepository;
import com.asgames.ataliasflame.infrastructure.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterLocationService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    private LocationAdventureService locationAdventureService;

    @Autowired
    private LocationService locationService;
    @Autowired
    private InventoryService inventoryService;

    @Transactional
    public LocationContext seizeLocation(String characterReference, String locationReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Location location = locationAdventureService.getLocation(locationReference);

        locationService.seizeLocation(character, location);

        return LocationContext.builder()
                .character(characterRepository.save(character))
                .location(locationRepository.save(location))
                .build();
    }

    @Transactional
    public LocationContext useItem(String characterReference, String locationReference, String itemReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Location location = locationAdventureService.getLocation(locationReference);

        if (location.getMonsters().stream().anyMatch(Combatant::isAlive)) {
            throw new IllegalStateException("There are alive enemies on the location. Looting is impossible!");
        }

        Item item = location.getItems().stream()
                .filter(locationItem -> locationItem.getReference().equals(itemReference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Referenced item is not at the location!"));
        location.getItems().remove(item);

        switch (item.getType()) {
            case FOOD -> {
                inventoryService.eatFood(character, (Food) item);
                itemRepository.delete(item);
            }
            case WEAPON -> inventoryService.takeWeapon(character, (Weapon) item);
            case SHIELD -> inventoryService.takeShield(character, (Shield) item);
            case ARMOR -> inventoryService.takeArmor(character, (Armor) item);
            default -> throw new UnsupportedOperationException("Not supported item usage: " + item.getType());
        }

        return LocationContext.builder()
                .character(characterRepository.save(character))
                .location(locationRepository.save(location))
                .build();
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
