package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Slf4j
@Service
public class LocationService {

    @Autowired
    private MonsterService monsterService;

    public Location buildLocation() {
        Location location = Location.build();

        List<Monster> monsters = monsterService.populateMonsters(location);
        log.info("Enemies appeared (" + monsters.size() + ")! -> " + monsters.stream().map(Monster::getCode).collect(joining(", ")));

        location.setMonsters(monsters);
        return location;
    }
}
