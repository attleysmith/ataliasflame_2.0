package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Slf4j
@Service
public class LocationService {

    @Autowired
    private MonsterService monsterService;
    @Autowired
    private CombatService combatService;
    @Autowired
    private ExperienceService experienceService;

    public Location buildLocation() {
        Location location = Location.build();

        List<Monster> monsters = monsterService.populateMonsters(location);
        log.info("Enemies appeared (" + monsters.size() + ")! -> " + monsters.stream().map(Monster::getCode).collect(joining(", ")));

        location.setMonsters(monsters);
        return location;
    }

    public void seizeLocation(Character character, Location location) {
        List<Monster> monsters = location.getMonsters();

        List<Combatant> characterTeam = new ArrayList<>();
        characterTeam.add(character);
        characterTeam.addAll(character.getCompanions());

        combatService.combat(characterTeam, monsters);
        character.getCompanions().removeIf(Combatant::isDead);

        if (character.isAlive()) {
            character = experienceService.gainExperience(character, monsters);
            log.info("You are the winner! Remaining health: " + character.getHealth().actualValue());
        } else {
            log.info("You are defeated!");
        }
    }

    public void lootLocation(Character character, Location location) {
        location.getMonsters().forEach(monster -> {
            if (monster.isAlive()) {
                throw new IllegalStateException("There are alive enemies on the location. Looting is impossible!");
            }
            monsterService.lootMonster(character, monster);
        });
    }
}
