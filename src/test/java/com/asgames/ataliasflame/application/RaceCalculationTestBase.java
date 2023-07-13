package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.domain.model.entities.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.asgames.ataliasflame.domain.MockConstants.CASTE_DETAILS;
import static com.asgames.ataliasflame.domain.MockConstants.WEAPONS;
import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;

public abstract class RaceCalculationTestBase {

    @Autowired
    protected CharacterService characterService;
    @Autowired
    protected CharacterRepository characterRepository;
    @Autowired
    protected CharacterCalculationService characterCalculationService;

    protected void upgradeCaste(String characterName, Caste actualCaste, Caste targetCaste) {
        if (actualCaste.equals(targetCaste)) {
            return;
        }
        Caste nextCaste = CASTE_DETAILS.get(actualCaste).getNextCastes().get(0);
        CasteDetails nextCasteDetails = CASTE_DETAILS.get(nextCaste);
        setAttributes(characterName, nextCasteDetails.getMinimumAttributes());

        Character character = characterService.upgradeCaste(characterName, nextCaste);
        upgradeCaste(characterName, character.getCaste(), targetCaste);
    }

    private void setAttributes(String characterName, Map<Attribute, Integer> targetAttributes) {
        Character character = characterService.getCharacter(characterName);
        character.getAttributes().put(STRENGTH, targetAttributes.get(STRENGTH));
        character.getAttributes().put(DEXTERITY, targetAttributes.get(DEXTERITY));
        character.getAttributes().put(CONSTITUTION, targetAttributes.get(CONSTITUTION));
        character.getAttributes().put(AGILITY, targetAttributes.get(AGILITY));
        character.getAttributes().put(INTELLIGENCE, targetAttributes.get(INTELLIGENCE));

        characterRepository.save(
                characterCalculationService.recalculateProperties(character)
        );
    }

    protected void addDagger(String characterName) {
        Character character = characterService.getCharacter(characterName);
        character.setWeapon(WEAPONS.get("DAGGER"));
        character.setShield(null);
        character.setArmor(null);

        characterRepository.save(
                characterCalculationService.recalculateProperties(character)
        );
    }
}
