package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.interfaces.CharacterLevelProvider;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.LEVEL_ATTRIBUTE_POINTS;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.CharacterReportCause.LEVEL_UP;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.characterReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ExperienceEvent.experienceGain;

@Service
public class ExperienceService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private CharacterLevelProvider characterLevelProvider;

    @Value("${booster.experience:false}")
    private boolean experienceBooster;

    public void gainExperience(Character character, List<Monster> monsters) {
        int sumExperience = monsters.stream().filter(Combatant::isDead).map(Monster::getExperience).reduce(0, Integer::sum);
        gainExperience(character, sumExperience);
    }

    private void gainExperience(Character character, int experience) {
        int gainedExperience = experienceBooster ? character.getLevel() * experience : experience;
        character.setExperience(character.getExperience() + gainedExperience);
        storyLineLogger.event(experienceGain(character, gainedExperience));
        levelUp(character);
    }

    private void levelUp(Character character) {
        while (nextLevelReached(character)) {
            character.setLevel(character.getLevel() + 1);
            character.setAttributePoints(character.getAttributePoints() + LEVEL_ATTRIBUTE_POINTS);
            storyLineLogger.event(characterReport(character, LEVEL_UP));
        }
    }

    private boolean nextLevelReached(Character character) {
        return characterLevelProvider.getNextLevelExperience(character.getLevel())
                .filter(nextLevelExperience -> nextLevelExperience <= character.getExperience())
                .isPresent();
    }
}
