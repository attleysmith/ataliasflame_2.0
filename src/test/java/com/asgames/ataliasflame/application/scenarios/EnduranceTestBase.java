package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.CharacterService;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EnduranceTestBase {

    @Autowired
    protected CharacterService characterService;

    protected static String characterName;

    @BeforeAll
    static void beforeAll() {
        characterName = "Takemoto";
    }

    protected Character addAttributePoints(Attribute attribute, int points) {
        return characterService.addAttributePoints(characterName, attribute, points);
    }

    protected Character upgradeCaste(Caste newCaste) {
        return characterService.upgradeCaste(characterName, newCaste);
    }

    protected Character combatUntilNextLevel() {
        Character character = characterService.getCharacter(characterName);
        int actualLevel = character.getLevel();

        do {
            characterService.combat(characterName);
            character = characterService.getCharacter(characterName);
        } while (character.getActualHealth() > 0 && character.getLevel() == actualLevel);

        return character;
    }
}
