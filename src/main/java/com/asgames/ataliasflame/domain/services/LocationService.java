package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.CharacterReportCause.DIED_OF_DEFEAT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.CharacterReportCause.WIN;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.characterReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.LocationEvents.MonstersAppearEvent.monstersAppear;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final StoryLineLogger storyLineLogger;
    private final MonsterService monsterService;
    private final CombatService combatService;
    private final ExperienceService experienceService;

    public Location buildLocation(int level) {
        Location location = Location.build(level);
        List<Monster> monsters = monsterService.populateMonsters(location);
        location.setMonsters(monsters);
        storyLineLogger.event(monstersAppear(location));
        return location;
    }

    public void enterLocation(Character character, Location location) {
        character.setLocation(location);
    }

    public void seizeLocation(Character character) {
        Location location = character.getLocation();
        if (location.isSeized()) {
            throw new IllegalStateException("Location is already seized!");
        }

        List<Combatant> characterTeam = new ArrayList<>();
        characterTeam.add(character);
        characterTeam.addAll(character.getCompanions());

        List<Monster> monsters = location.getMonsters();
        combatService.combat(characterTeam, monsters);
        character.getCompanions().removeIf(Combatant::isDead);
        monsterService.processMonsters(monsters);

        if (character.isAlive()) {
            experienceService.gainExperience(character, monsters);
            location.setSeized(true);
            storyLineLogger.event(characterReport(character, WIN));
        } else {
            storyLineLogger.event(characterReport(character, DIED_OF_DEFEAT));
        }
    }
}
