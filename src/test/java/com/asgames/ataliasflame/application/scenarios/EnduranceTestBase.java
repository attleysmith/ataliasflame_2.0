package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.CharacterAdventureService;
import com.asgames.ataliasflame.application.CharacterMaintenanceService;
import com.asgames.ataliasflame.domain.model.dtos.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static com.asgames.ataliasflame.domain.MockConstants.CASTE_DETAILS;
import static com.asgames.ataliasflame.domain.MockConstants.SUMMONING_MAGIC_COST;
import static com.asgames.ataliasflame.domain.model.enums.Caste.TRACKER;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.WANDERER;

public abstract class EnduranceTestBase {

    private static final int TOLERATED_INJURY = 40;

    @Autowired
    protected CharacterAdventureService characterAdventureService;
    @Autowired
    protected CharacterMaintenanceService characterMaintenanceService;

    protected Character character;
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
        while (!character.getHealth().isFull()) {
            character = characterAdventureService.sleep(characterName);
        }

        return characterMaintenanceService.upgradeCaste(characterName, newCaste);
    }

    protected Character combatUntilNextLevel() {
        Character character = characterMaintenanceService.getCharacter(characterName);
        int actualLevel = character.getLevel();

        do {
            character = characterAdventureService.combat(characterName);
            character = healing(character);
            character = prepareToSummon(character);
        } while (character.isAlive() && character.getLevel() == actualLevel);

        return character;
    }

    private Character healing(Character character) {
        if (character.getHealth().tolerateLoss(TOLERATED_INJURY)) {
            return character;
        }
        return characterAdventureService.sleep(characterName);
    }

    private Character prepareToSummon(Character character) {
        if (canSummon(character) &&
                character.getCompanions().size() == 0 &&
                character.getMagic().hasNot(SUMMONING_MAGIC_COST)) {
            return characterAdventureService.sleep(characterName);
        }
        return character;
    }

    private boolean canSummon(Character character) {
        CasteDetails casteDetails = CASTE_DETAILS.get(character.getCaste());
        return casteDetails.getGroup().equals(WANDERER) && !casteDetails.getCaste().equals(TRACKER);
    }
}
