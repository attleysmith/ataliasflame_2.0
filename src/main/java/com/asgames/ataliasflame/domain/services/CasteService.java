package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.*;

@Slf4j
@Service
public class CasteService {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public Character upgradeCaste(Character character, Caste newCaste) {
        validateConstraints(character, newCaste);
        validateAvailability(character, newCaste);
        validateAttributes(character, newCaste);

        log.info("New caste: " + newCaste);
        character.setCaste(newCaste);
        return characterCalculationService.recalculateProperties(character);
    }

    private void validateConstraints(Character character, Caste newCaste) {
        if (CASTE_RACE_PROHIBITION.get(newCaste).contains(character.getRace())) {
            throw new IllegalArgumentException(character.getRace() + " cannot be " + newCaste);
        }
        if (CASTE_GOD_PROHIBITION.get(newCaste).contains(character.getDefensiveGod())) {
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

}
