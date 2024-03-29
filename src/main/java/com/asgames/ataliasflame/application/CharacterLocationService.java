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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CharacterLocationService {

    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;
    private final ItemRepository itemRepository;
    private final CharacterMaintenanceService characterMaintenanceService;
    private final LocationAdventureService locationAdventureService;
    private final LocationService locationService;
    private final InventoryService inventoryService;

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

    @Transactional
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

        switch (item.getType()) {
            case WEAPON -> inventoryService.storeWeapon(character, (Weapon) item);
            case SHIELD -> inventoryService.storeShield(character, (Shield) item);
            default -> throw new IllegalArgumentException("Not supported item storage: " + item.getType());
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
                    case USED_WEAPON -> inventoryService.dropWeapon(character);
                    case SPARE_WEAPON -> inventoryService.dropSpareWeapon(character);
                    case USED_SHIELD -> inventoryService.dropShield(character);
                    case SPARE_SHIELD -> inventoryService.dropSpareShield(character);
                    case USED_HELMET, USED_BODY_ARMOR ->
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
