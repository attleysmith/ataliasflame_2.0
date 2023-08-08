package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.CharacterAdventureService;
import com.asgames.ataliasflame.application.CharacterMaintenanceService;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.SUMMON;
import static java.util.stream.Collectors.toList;

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
        if (character.getCompanions().isEmpty()
                && !summoningSpells(character).isEmpty()
                && usableSummoningSpells(character).isEmpty()) {
            return characterAdventureService.sleep(characterName);
        }
        return character;
    }

    private List<Spell> summoningSpells(Character character) {
        return SPELLS.values().stream()
                .filter(spell -> spell.getType().equals(SUMMON))
                .filter(spell -> !CASTE_SPELL_PROHIBITION.get(character.getCaste())
                        .contains(spell.getName()))
                .filter(spell -> !RACE_SPELL_PROHIBITION.get(character.getRace())
                        .contains(spell.getName()))
                .collect(toList());
    }

    private List<Spell> usableSummoningSpells(Character character) {
        return summoningSpells(character).stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .collect(toList());
    }
}
