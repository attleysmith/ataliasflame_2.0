package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.CharacterService;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

public abstract class EnduranceTestBase {

    private static final int TOLERATED_INJURY = 40;

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
            character = characterService.combat(characterName);
            character = healing(character);
        } while (character.getActualHealth() > 0 && character.getLevel() == actualLevel);

        return character;
    }

    private Character healing(Character character) {
        int toleratedInjury = percent(character.getTotalHealth(), TOLERATED_INJURY);
        if (character.getInjury() <= toleratedInjury) {
            return character;
        }
        return characterService.sleep(characterName);
    }
}
