package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.God;
import com.asgames.ataliasflame.domain.model.enums.Race;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
public class CharacterCalculationService {

    public Character recalculateProperties(Character character) {
        recalculateAttack(character);
        recalculateDefense(character);
        recalculateDamage(character);
        recalculateHealth(character);
        recalculateMagic(character);

        character.setMinDamage(calculate(character.getWeapon().getMinDamage(), character.getDamageMultiplier()));
        character.setMaxDamage(calculate(character.getWeapon().getMaxDamage(), character.getDamageMultiplier()));
        character.setInitiative(character.getWeapon().getInitiative());

        return character;
    }

    private void recalculateAttack(Character character) {
        List<Integer> attackMultipliers = stream(Attribute.values())
                .map(attribute -> calculateAttackMultiplier(character, attribute))
                .collect(toList());
        character.setAttack(calculate(BASE_ATTACK, attackMultipliers));
    }

    private void recalculateDefense(Character character) {
        List<Integer> defenseMultipliers = stream(Attribute.values())
                .map(attribute -> calculateDefenseMultiplier(character, attribute))
                .collect(toList());
        character.setDefense(calculate(actualDefense(character), defenseMultipliers));
    }

    private void recalculateDamage(Character character) {
        Integer damageMultiplier = stream(Attribute.values())
                .map(attribute -> calculateDamageMultiplier(character, attribute))
                .reduce(BASE_DAMAGE_MULTIPLIER, Integer::sum);
        character.setDamageMultiplier(damageMultiplier);
    }

    private void recalculateHealth(Character character) {
        List<Integer> healthMultipliers = stream(Attribute.values())
                .map(attribute -> calculateHealthMultiplier(character, attribute))
                .collect(toList());
        character.setTotalHealth(calculate(BASE_HEALTH, healthMultipliers));
    }

    private void recalculateMagic(Character character) {
        Integer magicPoint = stream(Attribute.values())
                .map(attribute -> calculateMagicPoint(character, attribute))
                .reduce(BASE_MAGIC_POINT, Integer::sum);
        character.setTotalMagicPoint(magicPoint);
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

    private int calculateMagicPoint(Character character, Attribute attribute) {
        return MODIFIERS.get(attribute.name()).getMagicPoint()
                * calculateAttribute(character, attribute);
    }

    private int calculateAttribute(Character character, Attribute attribute) {
        return calculateBoosterEffect(attribute, character.getAttributes().get(attribute), character.getRace(), character.getDefensiveGod());
    }

    private int calculateBoosterEffect(Attribute attribute, Integer baseValue, Race race, God god) {
        return calculate(baseValue, List.of(
                BOOSTERS.get(race.name()).getEffects().get(attribute),
                BOOSTERS.get(god.name()).getEffects().get(attribute)));
    }

    private int actualDefense(Character character) {
        return BASE_DEFENSE
                + character.getWeapon().getDefense()
                + (character.getShield() == null ? 0 : character.getShield().getDefense())
                + (character.getArmor() == null ? 0 : character.getArmor().getDefense());
    }
}
