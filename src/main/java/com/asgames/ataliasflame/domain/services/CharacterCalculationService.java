package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.God;
import com.asgames.ataliasflame.domain.model.enums.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;

@Service
public class CharacterCalculationService {

    @Autowired
    private CalculatorService calculatorService;

    public Character recalculateProperties(Character character) {
        recalculateAttack(character);
        recalculateDefense(character);
        recalculateDamage(character);
        recalculateHealth(character);

        character.setDamage(calculatorService.calculate(CHARACTER_DAMAGE, character.getDamageMultiplier()));

        return character;
    }

    private void recalculateAttack(Character character) {
        character.setAttack(calculatorService.calculate(BASE_ATTACK,
                calculateAttackMultiplier(character, STRENGTH),
                calculateAttackMultiplier(character, DEXTERITY),
                calculateAttackMultiplier(character, CONSTITUTION),
                calculateAttackMultiplier(character, AGILITY),
                calculateAttackMultiplier(character, INTELLIGENCE)));
    }

    private void recalculateDefense(Character character) {
        character.setDefense(calculatorService.calculate(BASE_DEFENSE,
                calculateDefenseMultiplier(character, STRENGTH),
                calculateDefenseMultiplier(character, DEXTERITY),
                calculateDefenseMultiplier(character, CONSTITUTION),
                calculateDefenseMultiplier(character, AGILITY),
                calculateDefenseMultiplier(character, INTELLIGENCE)));
    }

    private void recalculateDamage(Character character) {
        character.setDamageMultiplier(BASE_DAMAGE_MULTIPLIER
                + calculateDamageMultiplier(character, STRENGTH)
                + calculateDamageMultiplier(character, DEXTERITY)
                + calculateDamageMultiplier(character, CONSTITUTION)
                + calculateDamageMultiplier(character, AGILITY)
                + calculateDamageMultiplier(character, INTELLIGENCE));
    }

    private void recalculateHealth(Character character) {
        character.setTotalHealth(calculatorService.calculate(BASE_HEALTH,
                calculateHealthMultiplier(character, STRENGTH),
                calculateHealthMultiplier(character, DEXTERITY),
                calculateHealthMultiplier(character, CONSTITUTION),
                calculateHealthMultiplier(character, AGILITY),
                calculateHealthMultiplier(character, INTELLIGENCE)));
    }

    private int calculateAttackMultiplier(Character character, Attribute attribute) {
        return MODIFIERS.get(attribute.name()).getAttackMultiplier()
                * calculateAttribute(character, attribute);
    }

    private int calculateDefenseMultiplier(Character character, Attribute attribute) {
        return MODIFIERS.get(attribute.name()).getDefenseMultiplier()
                * calculateAttribute(character, attribute);
    }

    private int calculateDamageMultiplier(Character character, Attribute attribute) {
        return MODIFIERS.get(attribute.name()).getDamageMultiplier()
                * calculateAttribute(character, attribute);
    }

    private int calculateHealthMultiplier(Character character, Attribute attribute) {
        return MODIFIERS.get(attribute.name()).getHealthMultiplier()
                * calculateAttribute(character, attribute);
    }

    private int calculateAttribute(Character character, Attribute attribute) {
        return calculateBoosterEffect(attribute, character.getAttributes().get(attribute), character.getRace(), character.getDefensiveGod());
    }

    private int calculateBoosterEffect(Attribute attribute, Integer baseValue, Race race, God god) {
        return calculatorService.calculate(
                baseValue,
                BOOSTERS.get(race.name()).getEffects().get(attribute),
                BOOSTERS.get(god.name()).getEffects().get(attribute));
    }
}
