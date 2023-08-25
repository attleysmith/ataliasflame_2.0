package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;
import static java.util.stream.Collectors.joining;

@Slf4j
@Service
public class LocationService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private MonsterService monsterService;
    @Autowired
    private CombatService combatService;
    @Autowired
    private ExperienceService experienceService;

    public Location buildLocation(int level) {
        Location location = Location.build(level);

        List<Monster> monsters = monsterService.populateMonsters(location);
        storyLineLogger.event(INFO, "Enemies appeared (" + monsters.size() + ")! -> " + monsters.stream().map(Monster::getCode).collect(joining(", ")));

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
        monsterService.processMonsters(location);

        if (character.isAlive()) {
            experienceService.gainExperience(character, monsters);
            storyLineLogger.event(INFO, "You are the winner! Remaining health: " + character.getHealth().actualValue());
        } else {
            storyLineLogger.event(INFO, "You are defeated!");
        }
    }
}
