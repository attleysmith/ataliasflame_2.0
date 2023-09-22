package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Modifier;
import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.utils.CalculatorUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.MODIFIERS;
import static com.asgames.ataliasflame.domain.model.enums.Caste.ATALIAS_PRIEST;
import static com.asgames.ataliasflame.domain.model.enums.God.ATALIA;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static java.util.Arrays.stream;

@Service
public class CharacterCalculationService {

    private static final int BASE_HEALTH = 100;
    private static final int BASE_ATTACK = 80;
    private static final int BASE_DEFENSE = 20;
    private static final int BASE_DAMAGE_MULTIPLIER = 0;
    private static final int BASE_MAGIC_POINT = 0;

    public void recalculateProperties(Character character) {
        recalculateAttack(character);
        recalculateDefense(character);
        recalculateDamage(character);
        recalculateHealth(character);
        recalculateMagic(character);

        character.setMinDamage(calculate(character.getWeapon().getMinDamage(), character.getDamageMultiplier()));
        character.setMaxDamage(calculate(character.getWeapon().getMaxDamage(), character.getDamageMultiplier()));
        character.setInitiative(character.getWeapon().getInitiative());
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
                + character.getWeapon().getDefense()
                + character.getShield().map(Shield::getDefense).orElse(0)
                + character.getCover().getEnergyArmor().map(Armor::getDefense).orElse(0)
                + character.getCover().getPhysicalArmor().map(Armor::getDefense).orElse(0)
                + character.getCover().getDivineArmor().map(Armor::getDefense).orElse(0);
    }

    private static class PropertyCalculator {

        private final Modifier attributeModifier;
        private final int calculatedAttributeValue;

        private PropertyCalculator(Character character, Attribute attribute) {
            this.attributeModifier = MODIFIERS.get(attribute.name());
            this.calculatedAttributeValue = AttributeCalculator.of(character, attribute).calculate();
        }

        public static PropertyCalculator of(Character character, Attribute attribute) {
            return new PropertyCalculator(character, attribute);
        }

        public int getAttackMultiplier() {
            return calculatedAttributeValue * attributeModifier.getAttackMultiplier();
        }

        public int getDefenseMultiplier() {
            return calculatedAttributeValue * attributeModifier.getDefenseMultiplier();
        }

        public int getDamageMultiplier() {
            return calculatedAttributeValue * attributeModifier.getDamageMultiplier();
        }

        public int getHealthMultiplier() {
            return calculatedAttributeValue * attributeModifier.getHealthMultiplier();
        }

        public int getMagicPoint() {
            return calculatedAttributeValue * attributeModifier.getMagicPoint();
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
