package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.infrastructure.repositories.LevelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.LEVEL_ATTRIBUTE_POINTS;

@Slf4j
@Service
public class ExperienceService implements InitializingBean {

    @Autowired
    private LevelRepository levelRepository;

    private final Map<Integer, Optional<Integer>> levels = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        levelRepository.findAll().forEach(level ->
                levels.put(level.getId(), Optional.ofNullable(level.getNextLevelExperience()))
        );
    }

    public Character gainExperience(Character character, int experience) {
        character.setExperience(character.getExperience() + experience);
        log.info("Experience gained: " + experience);
        log.info("Total experience: " + character.getExperience());
        return levelUp(character);
    }

    public Character levelUp(Character character) {
        while (levels.get(character.getLevel()).isPresent()
                && levels.get(character.getLevel()).get() <= character.getExperience()) {
            character.setLevel(character.getLevel() + 1);
            character.setAttributePoints(character.getAttributePoints() + LEVEL_ATTRIBUTE_POINTS);
            log.info("Leveling up!");
            log.info("Level: " + character.getLevel());
            log.info("Attribute points: " + character.getAttributePoints());
        }
        return character;
    }
}
