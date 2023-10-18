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

import static com.asgames.ataliasflame.domain.model.enums.ItemType.WEAPON;

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
    public LocationContext enterLocation(String characterReference, String locationReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Location location = locationAdventureService.getLocation(locationReference);

        locationService.enterLocation(character, location);

        return LocationContext.builder()
                .location(locationRepository.save(location))
                .character(characterRepository.save(character))
                .build();
    }

    @Transactional
    public LocationContext seizeLocation(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        if (character.getLocation() == null) {
            throw new IllegalStateException("Character is not at a location!");
        }

        locationService.seizeLocation(character);

        return LocationContext.builder()
                .location(locationRepository.save(character.getLocation()))
                .character(characterRepository.save(character))
                .build();
    }

    @Transactional
    public LocationContext useItem(String characterReference, String itemReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Location location = character.getLocation();
        if (location == null) {
            throw new IllegalStateException("Character is not at a location!");
        }
        if (location.getMonsters().stream().anyMatch(Combatant::isAlive)) {
            throw new IllegalStateException("There are alive enemies on the location. Looting is impossible!");
        }

        Item item = location.getItems().stream()
                .filter(locationItem -> locationItem.getReference().equals(itemReference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Referenced item is not at the character's location!"));
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
                .location(locationRepository.save(character.getLocation()))
                .character(characterRepository.save(character))
                .build();
    }

    public LocationContext storeItem(String characterReference, String itemReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Location location = character.getLocation();
        if (location == null) {
            throw new IllegalStateException("Character is not at a location!");
        }
        if (location.getMonsters().stream().anyMatch(Combatant::isAlive)) {
            throw new IllegalStateException("There are alive enemies on the location. Looting is impossible!");
        }

        Item item = location.getItems().stream()
                .filter(locationItem -> locationItem.getReference().equals(itemReference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Referenced item is not at the character's location!"));
        location.getItems().remove(item);

        if (item.getType() == WEAPON) {
            inventoryService.storeWeapon(character, (Weapon) item);
        } else {
            throw new UnsupportedOperationException("Not supported item storage: " + item.getType());
        }

        return LocationContext.builder()
                .location(locationRepository.save(character.getLocation()))
                .character(characterRepository.save(character))
                .build();
    }

    @Transactional
    public LocationContext dropItem(String characterReference, String itemReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        if (character.getLocation() == null) {
            throw new IllegalStateException("Character is not at a location!");
        }

        inventoryService.getInventory(character).forEach((inventoryType, characterItem) -> {
            if (characterItem.getReference().equals(itemReference)) {
                switch (inventoryType) {
                    case PRIMARY_WEAPON -> inventoryService.dropPrimaryWeapon(character);
                    case SECONDARY_WEAPON -> inventoryService.dropSecondaryWeapon(character);
                    case SHIELD -> inventoryService.dropShield(character);
                    case HELMET, BODY_ARMOR ->
                            inventoryService.dropArmor(character, ((Armor) characterItem).getArmorType());
                }
            }
        });

        return LocationContext.builder()
                .location(locationRepository.save(character.getLocation()))
                .character(characterRepository.save(character))
                .build();
    }
}
