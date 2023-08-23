package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.LocationContext;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.services.LocationService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
    public LocationContext lootLocation(String characterReference, String locationReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Location location = locationAdventureService.getLocation(locationReference);

        locationService.lootLocation(character, location);

        return LocationContext.builder()
                .character(characterRepository.save(character))
                .location(locationRepository.save(location))
                .build();
    }
}
