package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.CasteGroup;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    @Autowired
    private LocationService locationService;

    public Character initialize(Character character) {
        character.setReference(UUID.randomUUID().toString());
        initializeAttributes(character);
        setStartingLevel(character);
        setStartingCaste(character);
        setStartingInventory(character);
        setStartingAttributes(character);
        setStartingLocation(character);

        validateConstraints(character);

        storyLineLogger.event(characterReport(character, INIT));
        return character;
    }

    private void validateConstraints(Character character) {
        if (character.getDefensiveGod().equals(ATALIA)) {
            throw new IllegalArgumentException("Calling ATALIA as defensive god is forbidden!");
        }
        if (character.getRace().prohibitedGenders.contains(character.getGender())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be " + character.getGender());
        }
        if (character.getRace().prohibitedGods.contains(character.getDefensiveGod())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be a follower of " + character.getDefensiveGod());
        }
        for (CasteGroup groupTag : character.getCaste().groupTags) {
            if (character.getRace().prohibitedCasteGroups.contains(groupTag)) {
                throw new IllegalArgumentException(character.getRace() + " cannot be " + character.getCaste());
            }
            if (character.getDefensiveGod().prohibitedCasteGroups.contains(groupTag)) {
                throw new IllegalArgumentException("Followers of " + character.getDefensiveGod() + " cannot be " + character.getCaste());
            }
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
    }

    private void setStartingCaste(Character character) {
        character.setCaste(ROGUE);
    }

    private void setStartingAttributes(Character character) {
        for (Attribute attribute : Attribute.values()) {
            int attributeValue = character.getCaste().minimumAttributes.get(attribute);
            character.setAttributePoints(attributeValue);
            attributeService.addAttributePoints(character, attribute, attributeValue);
        }
    }

    private void setStartingInventory(Character character) {
        inventoryService.setStartingInventory(character);
    }

    private void setStartingLocation(Character character) {
        Location location = locationService.buildLocation(character.getLevel());
        locationService.enterLocation(character, location);
    }
}
