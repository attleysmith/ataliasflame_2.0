package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.application.CharacterMaintenanceService;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.asgames.ataliasflame.domain.MockConstants.MAX_ATTRIBUTE_POINTS;
import static com.asgames.ataliasflame.domain.MockConstants.WEAPONS;
import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.ALATE;
import static com.asgames.ataliasflame.domain.model.enums.Race.HUMAN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class AttributeServiceTest {

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    private CharacterCalculationService characterCalculationService;
    @Autowired
    private AttributeService attributeService;

    private static Character character;

    @Test
    @Order(1)
    void addStrengthTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(ALATE)
                .name("Hugo")
                .build();
        character = characterMaintenanceService.createCharacter(characterInput);
        addDagger();

        // and
        character.setAttributePoints(4);

        // when
        attributeService.addAttributePoints(character, STRENGTH, 4);

        // then
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttack(), is(82));
        assertThat(character.getDefense(), is(22));
        assertThat(character.getMinDamage(), is(2));
        assertThat(character.getMaxDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(11));
        assertThat(character.getHealth().totalValue(), is(110));
        assertThat(character.getMagic().totalValue(), is(5));
    }

    @Test
    @Order(2)
    void addDexterityTest() {
        // given
        character.setAttributePoints(4);

        // when
        attributeService.addAttributePoints(character, DEXTERITY, 4);

        // then
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttack(), is(89));
        assertThat(character.getDefense(), is(23));
        assertThat(character.getMinDamage(), is(2));
        assertThat(character.getMaxDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(15));
        assertThat(character.getHealth().totalValue(), is(110));
        assertThat(character.getMagic().totalValue(), is(5));
    }

    @Test
    @Order(3)
    void addConstitutionTest() {
        // given
        character.setAttributePoints(4);

        // when
        attributeService.addAttributePoints(character, CONSTITUTION, 4);

        // then
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttack(), is(89));
        assertThat(character.getDefense(), is(23));
        assertThat(character.getMinDamage(), is(2));
        assertThat(character.getMaxDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(15));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(5));
    }

    @Test
    @Order(4)
    void addAgilityTest() {
        // given
        character.setAttributePoints(4);

        // when
        attributeService.addAttributePoints(character, AGILITY, 4);

        // then
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDefense(), is(24));
        assertThat(character.getMinDamage(), is(2));
        assertThat(character.getMaxDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(15));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(5));
    }

    @Test
    @Order(5)
    void addIntelligenceTest() {
        // given
        character.setAttributePoints(1);

        // when
        attributeService.addAttributePoints(character, INTELLIGENCE, 1);

        // then
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDefense(), is(24));
        assertThat(character.getMinDamage(), is(2));
        assertThat(character.getMaxDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(15));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(10));
    }

    @Test
    @Order(6)
    void addLoreTest() {
        // given
        character.setAttributePoints(1);

        // when
        attributeService.addAttributePoints(character, LORE, 1);

        // then
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDefense(), is(24));
        assertThat(character.getMinDamage(), is(2));
        assertThat(character.getMaxDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(15));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(12));
    }

    @Test
    @Order(7)
    void addMentalPowerTest() {
        // given
        character.setAttributePoints(1);

        // when
        attributeService.addAttributePoints(character, MENTAL_POWER, 1);

        // then
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDefense(), is(24));
        assertThat(character.getMinDamage(), is(2));
        assertThat(character.getMaxDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(15));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(22));
    }

    @Test
    @Order(8)
    void addSpiritualPowerTest() {
        // given
        character.setAttributePoints(1);

        // when
        attributeService.addAttributePoints(character, SPIRITUAL_POWER, 1);

        // then
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDefense(), is(24));
        assertThat(character.getMinDamage(), is(2));
        assertThat(character.getMaxDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(15));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(23));
    }

    @Test
    @Order(9)
    void maxAttributeValueTest() {
        // given
        int allowedAdditionalStrength = MAX_ATTRIBUTE_POINTS - character.getAttributes().get(STRENGTH);
        // and
        character.setAttributePoints(allowedAdditionalStrength + 1);

        // when
        attributeService.addAttributePoints(character, STRENGTH, allowedAdditionalStrength);

        // expect
        assertThrows(IllegalArgumentException.class,
                () -> attributeService.addAttributePoints(character, STRENGTH, 1));
    }

    private void addDagger() {
        WEAPONS.get("DAGGER").instance().belongsTo(character);
        character.setArmor(null);
        character.setShield(null);

        characterCalculationService.recalculateProperties(character);
    }

}