package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Booster;
import com.asgames.ataliasflame.domain.model.dtos.Modifier;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.utils.CalculatorUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.ATALIAS_PRIEST;
import static com.asgames.ataliasflame.domain.model.enums.God.ATALIA;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
public class CharacterCalculationService {

    public Character recalculateProperties(Character character, Booster blessing) {
        recalculateAttack(character, blessing);
        recalculateDefense(character, blessing);
        recalculateDamage(character, blessing);
        recalculateHealth(character, blessing);
        recalculateMagic(character, blessing);

        character.setMinDamage(calculate(character.getWeapon().getMinDamage(), character.getDamageMultiplier()));
        character.setMaxDamage(calculate(character.getWeapon().getMaxDamage(), character.getDamageMultiplier()));
        character.setInitiative(character.getWeapon().getInitiative());

        return character;
    }

    public Character recalculateProperties(Character character) {
        return recalculateProperties(character, null);
    }

    private void recalculateAttack(Character character, Booster blessing) {
        List<Integer> attackMultipliers = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute, blessing).getAttackMultiplier())
                .collect(toList());
        character.setAttack(calculate(BASE_ATTACK, attackMultipliers));
    }

    private void recalculateDefense(Character character, Booster blessing) {
        List<Integer> defenseMultipliers = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute, blessing).getDefenseMultiplier())
                .collect(toList());
        character.setDefense(calculate(actualDefense(character), defenseMultipliers));
    }

    private void recalculateDamage(Character character, Booster blessing) {
        Integer damageMultiplier = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute, blessing).getDamageMultiplier())
                .reduce(BASE_DAMAGE_MULTIPLIER, Integer::sum);
        character.setDamageMultiplier(damageMultiplier);
    }

    private void recalculateHealth(Character character, Booster blessing) {
        List<Integer> healthMultipliers = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute, blessing).getHealthMultiplier())
                .collect(toList());
        int healthValue = calculate(BASE_HEALTH, healthMultipliers);
        character.getHealth().set(healthValue);
    }

    private void recalculateMagic(Character character, Booster blessing) {
        Integer magicPoint = stream(Attribute.values())
                .map(attribute -> PropertyCalculator.of(character, attribute, blessing).getMagicPoint())
                .reduce(BASE_MAGIC_POINT, Integer::sum);
        character.getMagic().set(magicPoint);
    }

    private int actualDefense(Character character) {
        return BASE_DEFENSE
                + character.getWeapon().getDefense()
                + (character.getShield() == null ? 0 : character.getShield().getDefense())
                + (character.getArmor().isEmpty() ? 0 : character.getArmor().get().getDefense());
    }

    private static class PropertyCalculator {

        private final Modifier attributeModifier;
        private final int calculatedAttributeValue;

        private PropertyCalculator(Character character, Attribute attribute, Booster blessing) {
            this.attributeModifier = MODIFIERS.get(attribute.name());
            this.calculatedAttributeValue = AttributeCalculator.of(character, attribute, blessing).calculate();
        }

        public static PropertyCalculator of(Character character, Attribute attribute, Booster blessing) {
            return new PropertyCalculator(character, attribute, blessing);
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

        private AttributeCalculator(Character character, Attribute attribute, Booster blessing) {
            this.baseValue = character.getAttributes().get(attribute);
            this.multipliers.add(BOOSTERS.get(character.getRace().name()).getEffects().get(attribute));
            this.multipliers.add(BOOSTERS.get(character.getDefensiveGod().name()).getEffects().get(attribute));
            if (character.getCaste().equals(ATALIAS_PRIEST)) {
                this.multipliers.add(BOOSTERS.get(ATALIA.name()).getEffects().get(attribute));
            }
            if (blessing != null) {
                this.multipliers.add(blessing.getEffects().get(attribute));
            }
        }

        public static AttributeCalculator of(Character character, Attribute attribute, Booster blessing) {
            return new AttributeCalculator(character, attribute, blessing);
        }

        public int calculate() {
            return CalculatorUtils.calculate(this.baseValue, this.multipliers);
        }
    }

}
