package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.CasteGroup;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.asgames.ataliasflame.domain.model.enums.Caste.ATALIAS_PRIEST;
import static com.asgames.ataliasflame.domain.model.enums.Caste.TRACKER;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.WANDERER;
import static com.asgames.ataliasflame.domain.model.enums.Race.ARIMASPI;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.CharacterReportCause.DIED_OF_TRAUMA;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.characterReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.NewCasteEvent.newCaste;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.NewSoulChipEvent.newSoulChip;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll100;

@RequiredArgsConstructor
@Service
public class CasteService {

    private final StoryLineLogger storyLineLogger;
    private final CharacterCalculationService characterCalculationService;
    private final SoulChipService soulChipService;

    public void upgradeCaste(Character character, Caste newCaste) {
        validateConstraints(character, newCaste);
        validateAvailability(character, newCaste);
        validateAttributes(character, newCaste);

        ripOutSoulChip(character, newCaste);

        Caste oldCaste = character.getCaste();
        character.setCaste(newCaste);
        storyLineLogger.event(newCaste(character, oldCaste));

        characterCalculationService.recalculateProperties(character);
    }

    private void validateConstraints(Character character, Caste newCaste) {
        for (CasteGroup groupTag : newCaste.groupTags) {
            if (character.getRace().prohibitedCasteGroups.contains(groupTag)) {
                throw new IllegalArgumentException(character.getRace() + " cannot be " + newCaste);
            }
            if (character.getDefensiveGod().prohibitedCasteGroups.contains(groupTag)) {
                throw new IllegalArgumentException("Followers of " + character.getDefensiveGod() + " cannot be " + newCaste);
            }
        }
        if (newCaste.equals(ATALIAS_PRIEST) && character.getRace().equals(ARIMASPI)) {
            throw new IllegalArgumentException("Arimaspos cannot be Atalia's priests!");
        }
    }

    private void validateAvailability(Character character, Caste newCaste) {
        Set<Caste> nextCastes = character.getCaste().nextCastes;
        if (!nextCastes.contains(newCaste)) {
            throw new IllegalArgumentException("New caste is not available! Available castes: " + nextCastes);
        }
    }

    private void validateAttributes(Character character, Caste newCaste) {
        newCaste.minimumAttributes.forEach((attribute, requiredValue) -> {
            int actualValue = character.getAttributes().get(attribute);
            if (actualValue < requiredValue) {
                throw new IllegalArgumentException(attribute + " is lower than required (" + requiredValue + ")! Actual: " + actualValue);
            }
        });
    }

    private void ripOutSoulChip(Character character, Caste newCaste) {
        if (newCaste != TRACKER && newCaste.groupTags.contains(WANDERER)) {
            int percent = roll100();

            SoulChip soulChip = soulChipService.getSoulChip(character, percent);
            character.getHealth().trauma(percent);
            character.getSoulChips().add(soulChip);

            storyLineLogger.event(newSoulChip(soulChip));
            if (character.isDead()) {
                storyLineLogger.event(characterReport(character, DIED_OF_TRAUMA));
            }
        }
    }
}
