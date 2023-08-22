package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import com.asgames.ataliasflame.infrastructure.repositories.LevelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.LEVEL_ATTRIBUTE_POINTS;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

@Slf4j
@Service
public class ExperienceService implements InitializingBean {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private LevelRepository levelRepository;

    @Value("${booster.experience:false}")
    private boolean experienceBooster;

    private final Map<Integer, Optional<Integer>> levels = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        levelRepository.findAll().forEach(level ->
                levels.put(level.getId(), Optional.ofNullable(level.getNextLevelExperience()))
        );
    }

    public Character gainExperience(Character character, List<Monster> monsters) {
        int sumExperience = monsters.stream().map(Monster::getExperience).reduce(0, Integer::sum);
        return gainExperience(character, sumExperience);
    }

    public Character gainExperience(Character character, int experience) {
        int gainedExperience = experienceBooster ? character.getLevel() * experience : experience;
        character.setExperience(character.getExperience() + gainedExperience);
        storyLineLogger.event(INFO, "Experience gained: " + gainedExperience + "; Total experience: " + character.getExperience());
        return levelUp(character);
    }

    public Character levelUp(Character character) {
        while (levels.get(character.getLevel()).isPresent()
                && levels.get(character.getLevel()).get() <= character.getExperience()) {
            character.setLevel(character.getLevel() + 1);
            character.setAttributePoints(character.getAttributePoints() + LEVEL_ATTRIBUTE_POINTS);
            storyLineLogger.event(INFO, "Leveling up -> " + character.getLevel());
        }
        storyLineLogger.event(DEBUG, "Attribute points: " + character.getAttributePoints());
        return character;
    }
}
