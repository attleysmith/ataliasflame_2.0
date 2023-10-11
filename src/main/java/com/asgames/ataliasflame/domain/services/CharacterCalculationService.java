package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.utils.CalculatorUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.Caste.ATALIAS_PRIEST;
import static com.asgames.ataliasflame.domain.model.enums.God.ATALIA;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static java.util.Arrays.stream;

@Service
public class CharacterCalculationService {

    private static final int BASE_HEALTH = 100;
    private static final int BASE_ATTACK = 80;
    private static final int BASE_DEFENSE = 5;
    private static final int BASE_DAMAGE_MULTIPLIER = 0;
    private static final int BASE_MAGIC_POINT = 0;

    private static final int FIST_MIN_DAMAGE = 1;
    private static final int FIST_MAX_DAMAGE = 2;
    private static final int FIST_INITIATIVE = 1;

    public void recalculateProperties(Character character) {
        recalculateAttack(character);
        recalculateDefense(character);
        recalculateDamage(character);
        recalculateHealth(character);
        recalculateMagic(character);

        character.getWeapon().ifPresentOrElse(weapon -> {
                    character.setMinDamage(calculate(weapon.getMinDamage(), character.getDamageMultiplier()));
                    character.setMaxDamage(calculate(weapon.getMaxDamage(), character.getDamageMultiplier()));
                    character.setInitiative(weapon.getInitiative());
                },
                () -> {
                    character.setMinDamage(calculate(FIST_MIN_DAMAGE, character.getDamageMultiplier()));
                    character.setMaxDamage(calculate(FIST_MAX_DAMAGE, character.getDamageMultiplier()));
                    character.setInitiative(FIST_INITIATIVE);
                });
    }

    private void recalculateAttack(Character character) {
        List<Integer> attackMultipliers = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute).getAttackMultiplier())
                .toList();
        character.setAttack(calculate(BASE_ATTACK, attackMultipliers));
    }

    private void recalculateDefense(Character character) {
        List<Integer> defenseMultipliers = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute).getDefenseMultiplier())
                .toList();
        character.setDefense(calculate(actualDefense(character), defenseMultipliers));
    }

    private void recalculateDamage(Character character) {
        Integer damageMultiplier = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute).getDamageMultiplier())
                .reduce(BASE_DAMAGE_MULTIPLIER, Integer::sum);
        character.setDamageMultiplier(damageMultiplier);
    }

    private void recalculateHealth(Character character) {
        List<Integer> healthMultipliers = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute).getHealthMultiplier())
                .toList();
        int healthValue = calculate(BASE_HEALTH, healthMultipliers);
        character.getHealth().set(healthValue);
    }

    private void recalculateMagic(Character character) {
        Integer magicPoint = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute).getMagicPoint())
                .reduce(BASE_MAGIC_POINT, Integer::sum);
        character.getMagic().set(magicPoint);
    }

    private int actualDefense(Character character) {
        return BASE_DEFENSE
                + character.getWeapon().map(Weapon::getDefense).orElse(0)
                + character.getShield().map(Shield::getDefense).orElse(0)
                + character.getCover().getEnergyArmor().map(Armor::getDefense).orElse(0)
                + character.getCover().getHelmet().map(Armor::getDefense).orElse(0)
                + character.getCover().getBodyArmor().map(Armor::getDefense).orElse(0)
                + character.getCover().getDivineArmor().map(Armor::getDefense).orElse(0);
    }

    private static class PropertyCalculator {

        private final Attribute attribute;
        private final int calculatedAttributeValue;

        private PropertyCalculator(Character character, Attribute attribute) {
            this.attribute = attribute;
            this.calculatedAttributeValue = AttributeCalculator.of(character, attribute).calculate();
        }

        public static PropertyCalculator of(Character character, Attribute attribute) {
            return new PropertyCalculator(character, attribute);
        }

        public int getAttackMultiplier() {
            return calculatedAttributeValue * attribute.attackMultiplier;
        }

        public int getDefenseMultiplier() {
            return calculatedAttributeValue * attribute.defenseMultiplier;
        }

        public int getDamageMultiplier() {
            return calculatedAttributeValue * attribute.damageMultiplier;
        }

        public int getHealthMultiplier() {
            return calculatedAttributeValue * attribute.healthMultiplier;
        }

        public int getMagicPoint() {
            return calculatedAttributeValue * attribute.magicPoint;
        }
    }

    private static class AttributeCalculator {

        private final Integer baseValue;
        private final List<Integer> multipliers = new ArrayList<>();

        private AttributeCalculator(Character character, Attribute attribute) {
            this.baseValue = character.getAttributes().get(attribute);
            this.multipliers.add(character.getRace().booster.effects.get(attribute));
            this.multipliers.add(character.getDefensiveGod().booster.effects.get(attribute));
            if (character.getCaste().equals(ATALIAS_PRIEST)) {
                this.multipliers.add(ATALIA.booster.effects.get(attribute));
            }
            character.getBlessings().forEach(blessing -> {
                int bonusEffect = Optional.ofNullable(blessing.getSource())
                        .map(SoulChip::getEffectiveness)
                        .orElse(0);
                this.multipliers.add(
                        CalculatorUtils.calculate(blessing.getBooster().effects.get(attribute), bonusEffect)
                );
            });
        }

        public static AttributeCalculator of(Character character, Attribute attribute) {
            return new AttributeCalculator(character, attribute);
        }

        public int calculate() {
            return CalculatorUtils.calculate(this.baseValue, this.multipliers);
        }
    }

}
