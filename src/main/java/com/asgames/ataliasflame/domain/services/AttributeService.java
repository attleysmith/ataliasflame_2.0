package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public Character addAttributePoints(Character character, Attribute attribute, int points) {
        if (points > character.getAttributePoints()) {
            throw new IllegalArgumentException("Attribute spent on increase is more then it is available! Spent: "
                    + points + "; Available: " + character.getAttributePoints());
        }
        character.getAttributes().put(attribute, character.getAttributes().get(attribute) + points);
        character.setAttributePoints(character.getAttributePoints() - points);

        return characterCalculationService.recalculateProperties(character);
    }
}
