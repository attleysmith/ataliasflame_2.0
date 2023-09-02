package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.LocationContext;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.InventoryService;
import com.asgames.ataliasflame.domain.services.LocationService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
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
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("Referenced item is not at the location!"));

        inventoryService.use(character, item);
        location.getItems().remove(item);

        return LocationContext.builder()
                .character(characterRepository.save(character))
                .location(locationRepository.save(location))
                .build();
    }
}
