package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.LEVELS;
import static com.asgames.ataliasflame.domain.MockConstants.LEVEL_ATTRIBUTE_POINTS;

@Slf4j
@Service
public class ExperienceService {

    public Character gainExperience(Character character, int experience) {
        character.setExperience(character.getExperience() + experience);
        log.info("Experience gained: " + experience);
        log.info("Total experience: " + character.getExperience());
        return levelUp(character);
    }

    public Character levelUp(Character character) {
        while (LEVELS.get(character.getLevel()).isPresent()
                && LEVELS.get(character.getLevel()).get() <= character.getExperience()) {
            character.setLevel(character.getLevel() + 1);
            character.setAttributePoints(character.getAttributePoints() + LEVEL_ATTRIBUTE_POINTS);
            log.info("Leveling up!");
            log.info("Level: " + character.getLevel());
            log.info("Attribute points: " + character.getAttributePoints());
        }
        return character;
    }
}
