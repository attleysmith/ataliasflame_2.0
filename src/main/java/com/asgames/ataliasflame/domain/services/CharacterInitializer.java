package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;

@Service
public class CharacterInitializer {

    @Autowired
    private AttributeService attributeService;

    public Character initialize(Character character) {
        initializeAttributes(character);
        setStartingLevel(character);
        setStartingCaste(character);
        setStartingAttributes(character);
        return character;
    }

    private void initializeAttributes(Character character) {
        character.getAttributes().put(STRENGTH, 0);
        character.getAttributes().put(DEXTERITY, 0);
        character.getAttributes().put(CONSTITUTION, 0);
        character.getAttributes().put(AGILITY, 0);
        character.getAttributes().put(INTELLIGENCE, 0);
    }

    private void setStartingCaste(Character character) {
        character.setCaste(STARTING_CASTE);
    }

    private void setStartingAttributes(Character character) {
        attributeService.addAttributePoints(character, STRENGTH, STARTING_STRENGTH);
        attributeService.addAttributePoints(character, DEXTERITY, STARTING_DEXTERITY);
        attributeService.addAttributePoints(character, CONSTITUTION, STARTING_CONSTITUTION);
        attributeService.addAttributePoints(character, AGILITY, STARTING_AGILITY);
        attributeService.addAttributePoints(character, INTELLIGENCE, STARTING_INTELLIGENCE);
    }

    private void setStartingLevel(Character character) {
        character.setLevel(1);
        character.setExperience(0);
        character.setAttributePoints(LEVEL_ATTRIBUTE_POINTS);
    }
}
