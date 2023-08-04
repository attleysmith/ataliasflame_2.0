package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.CharacterAdventureService;
import com.asgames.ataliasflame.application.CharacterMaintenanceService;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EnduranceTestBase {

    private static final int TOLERATED_INJURY = 40;

    @Autowired
    protected CharacterAdventureService characterAdventureService;
    @Autowired
    protected CharacterMaintenanceService characterMaintenanceService;

    protected static String characterName;

    @BeforeEach
    void setUp() {
        characterName = "Takemoto";
    }

    @AfterEach
    void tearDown() {
        characterMaintenanceService.removeCharacter(characterName);
    }

    protected Character addAttributePoints(Attribute attribute, int points) {
        return characterMaintenanceService.addAttributePoints(characterName, attribute, points);
    }

    protected Character upgradeCaste(Caste newCaste) {
        return characterMaintenanceService.upgradeCaste(characterName, newCaste);
    }

    protected Character combatUntilNextLevel() {
        Character character = characterMaintenanceService.getCharacter(characterName);
        int actualLevel = character.getLevel();

        do {
            character = characterAdventureService.combat(characterName);
            character = healing(character);
        } while (character.getHealth().hasOne() && character.getLevel() == actualLevel);

        return character;
    }

    private Character healing(Character character) {
        if (character.getHealth().tolerate(TOLERATED_INJURY)) {
            return character;
        }
        return characterAdventureService.sleep(characterName);
    }
}
