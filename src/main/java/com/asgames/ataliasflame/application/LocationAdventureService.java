package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.services.LocationService;
import com.asgames.ataliasflame.infrastructure.repositories.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LocationAdventureService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    @Transactional
    public Location buildLocation() {
        return locationRepository.save(locationService.buildLocation());
    }

    @Transactional(readOnly = true)
    public Location getLocation(String locationReference) {
        return locationRepository.findById(locationReference)
                .orElseThrow(() -> new EntityNotFoundException("Location does not exist!"));
    }
}
