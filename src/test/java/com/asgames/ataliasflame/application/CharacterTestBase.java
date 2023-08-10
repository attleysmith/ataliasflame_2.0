package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.asgames.ataliasflame.domain.MockConstants.CASTE_DETAILS;
import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;

public abstract class CharacterTestBase {

    protected String characterName(String name) {
        return name + UUID.randomUUID();
    }

    @Autowired
    protected CharacterAdventureService characterAdventureService;
    @Autowired
    protected CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    protected CharacterRepository characterRepository;
    @Autowired
    protected CharacterCalculationService characterCalculationService;

    protected void upgradeCaste(String characterName, List<Caste> upgradePath) {
        if (upgradePath.isEmpty()) {
            return;
        }

        ArrayList<Caste> pathForward = new ArrayList<>(upgradePath);
        Caste nextCaste = pathForward.remove(0);
        setAttributes(characterName, CASTE_DETAILS.get(nextCaste).getMinimumAttributes());

        characterMaintenanceService.upgradeCaste(characterName, nextCaste);
        upgradeCaste(characterName, pathForward);
    }

    private void setAttributes(String characterName, Map<Attribute, Integer> targetAttributes) {
        Character character = characterMaintenanceService.getCharacter(characterName);
        character.getAttributes().put(STRENGTH, targetAttributes.get(STRENGTH));
        character.getAttributes().put(DEXTERITY, targetAttributes.get(DEXTERITY));
        character.getAttributes().put(CONSTITUTION, targetAttributes.get(CONSTITUTION));
        character.getAttributes().put(AGILITY, targetAttributes.get(AGILITY));
        character.getAttributes().put(INTELLIGENCE, targetAttributes.get(INTELLIGENCE));
        character.getAttributes().put(LORE, targetAttributes.get(LORE));
        character.getAttributes().put(MENTAL_POWER, targetAttributes.get(MENTAL_POWER));
        character.getAttributes().put(SPIRITUAL_POWER, targetAttributes.get(SPIRITUAL_POWER));

        characterRepository.save(
                characterCalculationService.recalculateProperties(character)
        );
    }
}
