package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.MockConstants.*;

@Slf4j
@Service
public class CharacterInitializer {

    @Autowired
    private AttributeService attributeService;
    @Autowired
    private InventoryService inventoryService;

    public Character initialize(Character character) {
        character.setReference(UUID.randomUUID().toString());
        initializeAttributes(character);
        setStartingCaste(character);
        setStartingInventory(character);
        setStartingLevel(character);
        setStartingAttributes(character);

        validateConstraints(character);

        log.debug("Character initialized: " + character);
        return character;
    }

    private void validateConstraints(Character character) {
        if (CASTE_RACE_PROHIBITION.get(character.getCaste()).contains(character.getRace())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be " + character.getCaste());
        }
        if (CASTE_GOD_PROHIBITION.get(character.getCaste()).contains(character.getDefensiveGod())) {
            throw new IllegalArgumentException("Followers of " + character.getDefensiveGod() + " cannot be " + character.getCaste());
        }
        if (RACE_GENDER_PROHIBITION.get(character.getRace()).contains(character.getGender())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be " + character.getGender());
        }
        if (RACE_GOD_PROHIBITION.get(character.getRace()).contains(character.getDefensiveGod())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be a follower of " + character.getDefensiveGod());
        }
    }

    private void initializeAttributes(Character character) {
        for (Attribute attribute : Attribute.values()) {
            character.getAttributes().put(attribute, 0);
        }
    }

    private void setStartingLevel(Character character) {
        character.setLevel(1);
        character.setExperience(0);
        character.setAttributePoints(LEVEL_ATTRIBUTE_POINTS);
    }

    private void setStartingCaste(Character character) {
        character.setCaste(STARTING_CASTE);
    }

    private void setStartingAttributes(Character character) {
        CasteDetails casteDetails = CASTE_DETAILS.get(character.getCaste());
        for (Attribute attribute : Attribute.values()) {
            attributeService.addAttributePoints(character, attribute, casteDetails.getMinimumAttributes().get(attribute));
        }
    }

    private void setStartingInventory(Character character) {
        inventoryService.setStartingInventory(character);
    }
}
