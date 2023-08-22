package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.CASTE_DETAILS;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.WANDERER;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll100;

@Slf4j
@Service
public class CasteService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public Character upgradeCaste(Character character, Caste newCaste) {
        validateConstraints(character, newCaste);
        validateAvailability(character, newCaste);
        validateAttributes(character, newCaste);

        ripOutSoulChip(character);

        character.setCaste(newCaste);
        storyLineLogger.event(INFO, "New caste: " + newCaste);

        return characterCalculationService.recalculateProperties(character);
    }

    private void validateConstraints(Character character, Caste newCaste) {
        if (character.getRace().prohibitedCastes.contains(newCaste)) {
            throw new IllegalArgumentException(character.getRace() + " cannot be " + newCaste);
        }
        if (character.getDefensiveGod().prohibitedCastes.contains(newCaste)) {
            throw new IllegalArgumentException("Followers of " + character.getDefensiveGod() + " cannot be " + newCaste);
        }
    }

    private void validateAvailability(Character character, Caste newCaste) {
        CasteDetails actualCasteDetails = CASTE_DETAILS.get(character.getCaste());
        if (!actualCasteDetails.getNextCastes().contains(newCaste)) {
            throw new IllegalArgumentException("New caste is not available! Available castes: " + actualCasteDetails.getNextCastes());
        }
    }

    private void validateAttributes(Character character, Caste newCaste) {
        CasteDetails newCasteDetails = CASTE_DETAILS.get(newCaste);
        newCasteDetails.getMinimumAttributes().keySet().forEach(attribute ->
                validateAttribute(attribute, character, newCasteDetails));
    }

    private void validateAttribute(Attribute attribute, Character character, CasteDetails newCasteDetails) {
        int actualValue = character.getAttributes().get(attribute);
        int requiredValue = newCasteDetails.getMinimumAttributes().get(attribute);
        if (actualValue < requiredValue) {
            throw new IllegalArgumentException(attribute + " is lower than required (" + requiredValue + ")! Actual: " + actualValue);
        }
    }

    private void ripOutSoulChip(Character character) {
        CasteDetails casteDetails = CASTE_DETAILS.get(character.getCaste());
        if (casteDetails.getGroup().equals(WANDERER)) {
            int percent = roll100();

            character.getHealth().trauma(percent);
            character.getSoulChips().add(SoulChipFactory.getSoulChip(character, percent));

            storyLineLogger.event(INFO, "Ripping out a soul chip! " + percent + " percent");
            if (character.isDead()) {
                storyLineLogger.event(INFO, "You died of trauma!");
            }
        }
    }
}
