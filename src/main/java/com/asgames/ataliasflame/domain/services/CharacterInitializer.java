package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.MockConstants.CASTE_DETAILS;
import static com.asgames.ataliasflame.domain.MockConstants.LEVEL_ATTRIBUTE_POINTS;
import static com.asgames.ataliasflame.domain.model.enums.Caste.ROGUE;
import static com.asgames.ataliasflame.domain.model.enums.God.ATALIA;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.CharacterReportCause.INIT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.characterReport;

@Service
public class CharacterInitializer {

    @Autowired
    private StoryLineLogger storyLineLogger;

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

        storyLineLogger.event(characterReport(character, INIT));
        return character;
    }

    private void validateConstraints(Character character) {
        if (character.getDefensiveGod().equals(ATALIA)) {
            throw new IllegalArgumentException("Calling ATALIA as defensive god is forbidden!");
        }
        if (character.getRace().prohibitedCastes.contains(character.getCaste())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be " + character.getCaste());
        }
        if (character.getRace().prohibitedGenders.contains(character.getGender())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be " + character.getGender());
        }
        if (character.getRace().prohibitedGods.contains(character.getDefensiveGod())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be a follower of " + character.getDefensiveGod());
        }
        if (character.getDefensiveGod().prohibitedCastes.contains(character.getCaste())) {
            throw new IllegalArgumentException("Followers of " + character.getDefensiveGod() + " cannot be " + character.getCaste());
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
        character.setCaste(ROGUE);
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
