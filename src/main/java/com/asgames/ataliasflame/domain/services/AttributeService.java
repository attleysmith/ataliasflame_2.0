package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.MAX_ATTRIBUTE_POINTS;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;

@Slf4j
@Service
public class AttributeService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public Character addAttributePoints(Character character, Attribute attribute, int points) {
        if (points > character.getAttributePoints()) {
            throw new IllegalArgumentException("Attribute spent on increase is more then it is available! Spent: "
                    + points + "; Available: " + character.getAttributePoints());
        }

        int oldValue = character.getAttributes().get(attribute);
        int newValue = oldValue + points;
        if (newValue > MAX_ATTRIBUTE_POINTS) {
            throw new IllegalArgumentException("The maximum of one attribute is " + MAX_ATTRIBUTE_POINTS + "!");
        }

        character.getAttributes().put(attribute, newValue);
        character.setAttributePoints(character.getAttributePoints() - points);
        storyLineLogger.event(DEBUG, attribute.name() + ": " + oldValue + " >> " + newValue);

        return characterCalculationService.recalculateProperties(character);
    }
}
