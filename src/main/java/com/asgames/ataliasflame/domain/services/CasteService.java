package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.CASTE_DETAILS;

@Service
public class CasteService {

    public Character upgradeCaste(Character character, Caste newCaste) {
        validateAvailability(character, newCaste);
        validateAttributes(character, newCaste);

        character.setCaste(newCaste);
        return character;
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

}
