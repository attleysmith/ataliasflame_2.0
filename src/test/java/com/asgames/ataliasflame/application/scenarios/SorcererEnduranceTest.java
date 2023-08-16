package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.HORA;
import static com.asgames.ataliasflame.domain.model.enums.Race.ELF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Disabled("May be killed in action")
@SpringBootTest(properties = "booster.experience:true")
public class SorcererEnduranceTest extends EnduranceTestBase {

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
                .gender(MALE)
                .defensiveGod(HORA)
                .name("Plato")
                .build();
        character = characterMaintenanceService.createCharacter(characterInput);

        // expect
        level1Upgrade();

        do {
            // given
            int startingLevel = character.getLevel();

            // when
            character = combatUntilNextLevel();

            // expect
            assertThat(character.getLevel(), is(greaterThan(startingLevel)));

            // when
            for (int upgradeLevel = startingLevel + 1; upgradeLevel <= character.getLevel(); upgradeLevel++) {
                UPGRADES.get(upgradeLevel).run();
            }

            // expect
            assertThat(character.getAttributePoints(), is(0));

        } while (character.isAlive() && character.getLevel() < 100);
    }

    private void level1Upgrade() {
        // expect
        assertThat(character.getLevel(), is(1));
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(1));
        assertThat(character.getAttributes().get(CONSTITUTION), is(1));
        assertThat(character.getAttributes().get(AGILITY), is(1));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        assertThat(character.getAttributes().get(LORE), is(0));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(0));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(82));
        assertThat(character.getDamageMultiplier(), is(3));
        assertThat(character.getHealth().totalValue(), is(110));
        assertThat(character.getMagic().totalValue(), is(5));
    }

    private void level2Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(2));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(1));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(1));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(84));
        assertThat(character.getDamageMultiplier(), is(4));
        assertThat(character.getHealth().totalValue(), is(120));
        assertThat(character.getMagic().totalValue(), is(22));
    }

    private void level3Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(2));
        assertThat(character.getAttributes().get(DEXTERITY), is(2));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(2));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(2));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(85));
        assertThat(character.getDamageMultiplier(), is(4));
        assertThat(character.getHealth().totalValue(), is(120));
        assertThat(character.getMagic().totalValue(), is(39));
    }

    private void level4Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(2));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(3));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(89));
        assertThat(character.getDamageMultiplier(), is(6));
        assertThat(character.getHealth().totalValue(), is(120));
        assertThat(character.getMagic().totalValue(), is(56));
    }

    private void level5Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 2);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(2));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(4));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(89));
        assertThat(character.getDamageMultiplier(), is(6));
        assertThat(character.getHealth().totalValue(), is(120));
        assertThat(character.getMagic().totalValue(), is(76));

        // then
        character = upgradeCaste(WIZARD);

        // expect
        assertThat(character.getCaste(), is(WIZARD));
    }

    private void level6Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(2));
        assertThat(character.getAttributes().get(DEXTERITY), is(4));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(4));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(6));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(91));
        assertThat(character.getDamageMultiplier(), is(7));
        assertThat(character.getHealth().totalValue(), is(120));
        assertThat(character.getMagic().totalValue(), is(103));
    }

    private void level7Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(4));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(4));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(91));
        assertThat(character.getDamageMultiplier(), is(9));
        assertThat(character.getHealth().totalValue(), is(130));
        assertThat(character.getMagic().totalValue(), is(120));
    }

    private void level8Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(10));
        assertThat(character.getHealth().totalValue(), is(130));
        assertThat(character.getMagic().totalValue(), is(137));
    }

    private void level9Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(96));
        assertThat(character.getDamageMultiplier(), is(11));
        assertThat(character.getHealth().totalValue(), is(130));
        assertThat(character.getMagic().totalValue(), is(154));
    }

    private void level10Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(7));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(98));
        assertThat(character.getDamageMultiplier(), is(12));
        assertThat(character.getHealth().totalValue(), is(130));
        assertThat(character.getMagic().totalValue(), is(174));
    }

    private void level11Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(7));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(98));
        assertThat(character.getDamageMultiplier(), is(14));
        assertThat(character.getHealth().totalValue(), is(140));
        assertThat(character.getMagic().totalValue(), is(191));
    }

    private void level12Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(7));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(11));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(102));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getHealth().totalValue(), is(140));
        assertThat(character.getMagic().totalValue(), is(208));
    }

    private void level13Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(102));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getHealth().totalValue(), is(140));
        assertThat(character.getMagic().totalValue(), is(226));
    }

    private void level14Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(9));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(14));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(13));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(104));
        assertThat(character.getDamageMultiplier(), is(17));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(243));
    }

    private void level15Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(9));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(105));
        assertThat(character.getDamageMultiplier(), is(19));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(260));
    }

    private void level16Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(16));
        assertThat(character.getAttributes().get(LORE), is(16));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(15));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(106));
        assertThat(character.getDamageMultiplier(), is(20));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(288));
    }

    private void level17Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(11));
        assertThat(character.getAttributes().get(CONSTITUTION), is(6));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(17));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(108));
        assertThat(character.getDamageMultiplier(), is(21));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(305));
    }

    private void level18Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(18));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(17));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(110));
        assertThat(character.getDamageMultiplier(), is(22));
        assertThat(character.getHealth().totalValue(), is(160));
        assertThat(character.getMagic().totalValue(), is(322));
    }

    private void level19Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(110));
        assertThat(character.getDamageMultiplier(), is(22));
        assertThat(character.getHealth().totalValue(), is(160));
        assertThat(character.getMagic().totalValue(), is(339));
    }

    private void level20Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 2);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(110));
        assertThat(character.getDamageMultiplier(), is(22));
        assertThat(character.getHealth().totalValue(), is(160));
        assertThat(character.getMagic().totalValue(), is(367));

        // then
        character = upgradeCaste(MAGE);

        // expect
        assertThat(character.getCaste(), is(MAGE));
    }

    private void level21Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(21));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(110));
        assertThat(character.getDamageMultiplier(), is(24));
        assertThat(character.getHealth().totalValue(), is(170));
        assertThat(character.getMagic().totalValue(), is(384));
    }

    private void level22Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(114));
        assertThat(character.getDamageMultiplier(), is(26));
        assertThat(character.getHealth().totalValue(), is(170));
        assertThat(character.getMagic().totalValue(), is(402));
    }

    private void level23Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(9));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(23));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(114));
        assertThat(character.getDamageMultiplier(), is(26));
        assertThat(character.getHealth().totalValue(), is(180));
        assertThat(character.getMagic().totalValue(), is(420));
    }

    private void level24Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(9));
        assertThat(character.getAttributes().get(AGILITY), is(11));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(116));
        assertThat(character.getDamageMultiplier(), is(27));
        assertThat(character.getHealth().totalValue(), is(180));
        assertThat(character.getMagic().totalValue(), is(437));
    }

    private void level25Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(11));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(116));
        assertThat(character.getDamageMultiplier(), is(29));
        assertThat(character.getHealth().totalValue(), is(190));
        assertThat(character.getMagic().totalValue(), is(469));
    }

    private void level26Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        assertThat(character.getAttributes().get(LORE), is(26));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(26));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(30));
        assertThat(character.getHealth().totalValue(), is(190));
        assertThat(character.getMagic().totalValue(), is(486));
    }

    private void level27Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(11));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(27));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(27));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(120));
        assertThat(character.getDamageMultiplier(), is(31));
        assertThat(character.getHealth().totalValue(), is(200));
        assertThat(character.getMagic().totalValue(), is(503));
    }

    private void level28Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(9));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(11));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        assertThat(character.getAttributes().get(LORE), is(28));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(28));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(121));
        assertThat(character.getDamageMultiplier(), is(31));
        assertThat(character.getHealth().totalValue(), is(200));
        assertThat(character.getMagic().totalValue(), is(520));
    }

    private void level29Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(29));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(29));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(121));
        assertThat(character.getDamageMultiplier(), is(33));
        assertThat(character.getHealth().totalValue(), is(210));
        assertThat(character.getMagic().totalValue(), is(537));
    }

    private void level30Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(30));
        assertThat(character.getAttributes().get(LORE), is(30));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(30));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(123));
        assertThat(character.getDamageMultiplier(), is(34));
        assertThat(character.getHealth().totalValue(), is(210));
        assertThat(character.getMagic().totalValue(), is(556));
    }

    private void level31Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(13));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(31));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(31));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(123));
        assertThat(character.getDamageMultiplier(), is(34));
        assertThat(character.getHealth().totalValue(), is(220));
        assertThat(character.getMagic().totalValue(), is(574));
    }

    private void level32Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(13));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(32));
        assertThat(character.getAttributes().get(LORE), is(32));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(32));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(126));
        assertThat(character.getDamageMultiplier(), is(38));
        assertThat(character.getHealth().totalValue(), is(220));
        assertThat(character.getMagic().totalValue(), is(591));
    }

    private void level33Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(33));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(33));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(127));
        assertThat(character.getDamageMultiplier(), is(38));
        assertThat(character.getHealth().totalValue(), is(230));
        assertThat(character.getMagic().totalValue(), is(608));
    }

    private void level34Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(34));
        assertThat(character.getAttributes().get(LORE), is(34));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(34));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(130));
        assertThat(character.getDamageMultiplier(), is(39));
        assertThat(character.getHealth().totalValue(), is(230));
        assertThat(character.getMagic().totalValue(), is(625));
    }

    private void level35Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(35));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(35));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(130));
        assertThat(character.getDamageMultiplier(), is(39));
        assertThat(character.getHealth().totalValue(), is(230));
        assertThat(character.getMagic().totalValue(), is(653));
    }

    private void level36Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(15));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(36));
        assertThat(character.getAttributes().get(LORE), is(36));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(36));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(131));
        assertThat(character.getDamageMultiplier(), is(40));
        assertThat(character.getHealth().totalValue(), is(240));
        assertThat(character.getMagic().totalValue(), is(670));
    }

    private void level37Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(13));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(15));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(37));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(37));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(132));
        assertThat(character.getDamageMultiplier(), is(42));
        assertThat(character.getHealth().totalValue(), is(240));
        assertThat(character.getMagic().totalValue(), is(687));
    }

    private void level38Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(13));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(38));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(38));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(132));
        assertThat(character.getDamageMultiplier(), is(42));
        assertThat(character.getHealth().totalValue(), is(240));
        assertThat(character.getMagic().totalValue(), is(705));
    }

    private void level39Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(39));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(39));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(134));
        assertThat(character.getDamageMultiplier(), is(45));
        assertThat(character.getHealth().totalValue(), is(240));
        assertThat(character.getMagic().totalValue(), is(722));
    }

    private void level40Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(40));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(40));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(134));
        assertThat(character.getDamageMultiplier(), is(45));
        assertThat(character.getHealth().totalValue(), is(240));
        assertThat(character.getMagic().totalValue(), is(740));
    }

    private void level41Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(41));
        assertThat(character.getAttributes().get(LORE), is(41));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(41));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(136));
        assertThat(character.getDamageMultiplier(), is(46));
        assertThat(character.getHealth().totalValue(), is(250));
        assertThat(character.getMagic().totalValue(), is(757));
    }

    private void level42Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(15));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(42));
        assertThat(character.getAttributes().get(LORE), is(42));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(42));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(137));
        assertThat(character.getDamageMultiplier(), is(48));
        assertThat(character.getHealth().totalValue(), is(250));
        assertThat(character.getMagic().totalValue(), is(774));
    }

    private void level43Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(43));
        assertThat(character.getAttributes().get(LORE), is(43));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(43));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(137));
        assertThat(character.getDamageMultiplier(), is(48));
        assertThat(character.getHealth().totalValue(), is(250));
        assertThat(character.getMagic().totalValue(), is(792));
    }

    private void level44Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(44));
        assertThat(character.getAttributes().get(LORE), is(44));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(44));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(13));
        // and
        assertThat(character.getAttack(), is(137));
        assertThat(character.getDamageMultiplier(), is(48));
        assertThat(character.getHealth().totalValue(), is(260));
        assertThat(character.getMagic().totalValue(), is(810));
    }

    private void level45Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(45));
        assertThat(character.getAttributes().get(LORE), is(45));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(13));
        // and
        assertThat(character.getAttack(), is(141));
        assertThat(character.getDamageMultiplier(), is(50));
        assertThat(character.getHealth().totalValue(), is(260));
        assertThat(character.getMagic().totalValue(), is(837));
    }

    private void level46Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(19));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(46));
        assertThat(character.getAttributes().get(LORE), is(46));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(46));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(14));
        // and
        assertThat(character.getAttack(), is(141));
        assertThat(character.getDamageMultiplier(), is(50));
        assertThat(character.getHealth().totalValue(), is(270));
        assertThat(character.getMagic().totalValue(), is(855));
    }

    private void level47Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(17));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(19));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(47));
        assertThat(character.getAttributes().get(LORE), is(47));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(47));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(14));
        // and
        assertThat(character.getAttack(), is(142));
        assertThat(character.getDamageMultiplier(), is(53));
        assertThat(character.getHealth().totalValue(), is(270));
        assertThat(character.getMagic().totalValue(), is(872));
    }

    private void level48Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(17));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(19));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(48));
        assertThat(character.getAttributes().get(LORE), is(48));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(48));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(143));
        assertThat(character.getDamageMultiplier(), is(53));
        assertThat(character.getHealth().totalValue(), is(270));
        assertThat(character.getMagic().totalValue(), is(890));
    }

    private void level49Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(17));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(49));
        assertThat(character.getAttributes().get(LORE), is(49));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(49));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(145));
        assertThat(character.getDamageMultiplier(), is(54));
        assertThat(character.getHealth().totalValue(), is(280));
        assertThat(character.getMagic().totalValue(), is(907));
    }

    private void level50Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(18));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(50));
        assertThat(character.getAttributes().get(LORE), is(50));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(50));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(146));
        assertThat(character.getDamageMultiplier(), is(56));
        assertThat(character.getHealth().totalValue(), is(280));
        assertThat(character.getMagic().totalValue(), is(926));

        // then
        character = upgradeCaste(WITCHMASTER);

        // expect
        assertThat(character.getCaste(), is(WITCHMASTER));
    }

    private void level51Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(51));
        assertThat(character.getAttributes().get(LORE), is(51));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(51));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(146));
        assertThat(character.getDamageMultiplier(), is(56));
        assertThat(character.getHealth().totalValue(), is(290));
        assertThat(character.getMagic().totalValue(), is(943));
    }

    private void level52Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(52));
        assertThat(character.getAttributes().get(LORE), is(52));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(52));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(146));
        assertThat(character.getDamageMultiplier(), is(58));
        assertThat(character.getHealth().totalValue(), is(290));
        assertThat(character.getMagic().totalValue(), is(960));
    }

    private void level53Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(53));
        assertThat(character.getAttributes().get(LORE), is(53));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(53));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(147));
        assertThat(character.getDamageMultiplier(), is(58));
        assertThat(character.getHealth().totalValue(), is(300));
        assertThat(character.getMagic().totalValue(), is(977));
    }

    private void level54Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(54));
        assertThat(character.getAttributes().get(LORE), is(54));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(54));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(148));
        assertThat(character.getDamageMultiplier(), is(60));
        assertThat(character.getHealth().totalValue(), is(300));
        assertThat(character.getMagic().totalValue(), is(994));
    }

    private void level55Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(23));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(55));
        assertThat(character.getAttributes().get(LORE), is(55));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(55));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(148));
        assertThat(character.getDamageMultiplier(), is(60));
        assertThat(character.getHealth().totalValue(), is(310));
        assertThat(character.getMagic().totalValue(), is(1022));
    }

    private void level56Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(23));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(56));
        assertThat(character.getAttributes().get(LORE), is(56));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(56));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(150));
        assertThat(character.getDamageMultiplier(), is(61));
        assertThat(character.getHealth().totalValue(), is(310));
        assertThat(character.getMagic().totalValue(), is(1039));
    }

    private void level57Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(57));
        assertThat(character.getAttributes().get(LORE), is(57));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(57));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(150));
        assertThat(character.getDamageMultiplier(), is(61));
        assertThat(character.getHealth().totalValue(), is(320));
        assertThat(character.getMagic().totalValue(), is(1056));
    }

    private void level58Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(58));
        assertThat(character.getAttributes().get(LORE), is(58));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(58));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(152));
        assertThat(character.getDamageMultiplier(), is(64));
        assertThat(character.getHealth().totalValue(), is(320));
        assertThat(character.getMagic().totalValue(), is(1073));
    }

    private void level59Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(59));
        assertThat(character.getAttributes().get(LORE), is(59));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(59));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(153));
        assertThat(character.getDamageMultiplier(), is(64));
        assertThat(character.getHealth().totalValue(), is(330));
        assertThat(character.getMagic().totalValue(), is(1090));
    }

    private void level60Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(60));
        assertThat(character.getAttributes().get(LORE), is(60));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(60));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(17));
        // and
        assertThat(character.getAttack(), is(153));
        assertThat(character.getDamageMultiplier(), is(66));
        assertThat(character.getHealth().totalValue(), is(330));
        assertThat(character.getMagic().totalValue(), is(1108));
    }

    private void level61Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(61));
        assertThat(character.getAttributes().get(LORE), is(61));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(61));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(17));
        // and
        assertThat(character.getAttack(), is(153));
        assertThat(character.getDamageMultiplier(), is(68));
        assertThat(character.getHealth().totalValue(), is(330));
        assertThat(character.getMagic().totalValue(), is(1125));
    }

    private void level62Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(28));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(28));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(62));
        assertThat(character.getAttributes().get(LORE), is(62));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(62));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(17));
        // and
        assertThat(character.getAttack(), is(157));
        assertThat(character.getDamageMultiplier(), is(70));
        assertThat(character.getHealth().totalValue(), is(330));
        assertThat(character.getMagic().totalValue(), is(1142));
    }

    private void level63Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(27));
        assertThat(character.getAttributes().get(AGILITY), is(28));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(63));
        assertThat(character.getAttributes().get(LORE), is(63));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(63));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(17));
        // and
        assertThat(character.getAttack(), is(158));
        assertThat(character.getDamageMultiplier(), is(71));
        assertThat(character.getHealth().totalValue(), is(340));
        assertThat(character.getMagic().totalValue(), is(1159));
    }

    private void level64Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(27));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(64));
        assertThat(character.getAttributes().get(LORE), is(64));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(64));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(17));
        // and
        assertThat(character.getAttack(), is(159));
        assertThat(character.getDamageMultiplier(), is(71));
        assertThat(character.getHealth().totalValue(), is(340));
        assertThat(character.getMagic().totalValue(), is(1176));
    }

    private void level65Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(65));
        assertThat(character.getAttributes().get(LORE), is(65));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(65));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(159));
        assertThat(character.getDamageMultiplier(), is(71));
        assertThat(character.getHealth().totalValue(), is(350));
        assertThat(character.getMagic().totalValue(), is(1204));
    }

    private void level66Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(66));
        assertThat(character.getAttributes().get(LORE), is(66));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(66));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(162));
        assertThat(character.getDamageMultiplier(), is(72));
        assertThat(character.getHealth().totalValue(), is(350));
        assertThat(character.getMagic().totalValue(), is(1221));
    }

    private void level67Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(27));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(29));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(67));
        assertThat(character.getAttributes().get(LORE), is(67));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(67));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(162));
        assertThat(character.getDamageMultiplier(), is(74));
        assertThat(character.getHealth().totalValue(), is(360));
        assertThat(character.getMagic().totalValue(), is(1238));
    }

    private void level68Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(31));
        assertThat(character.getAttributes().get(CONSTITUTION), is(29));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(68));
        assertThat(character.getAttributes().get(LORE), is(68));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(68));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(163));
        assertThat(character.getDamageMultiplier(), is(77));
        assertThat(character.getHealth().totalValue(), is(360));
        assertThat(character.getMagic().totalValue(), is(1255));
    }

    private void level69Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(31));
        assertThat(character.getAttributes().get(CONSTITUTION), is(30));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(69));
        assertThat(character.getAttributes().get(LORE), is(69));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(69));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(164));
        assertThat(character.getDamageMultiplier(), is(77));
        assertThat(character.getHealth().totalValue(), is(370));
        assertThat(character.getMagic().totalValue(), is(1272));
    }

    private void level70Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(31));
        assertThat(character.getAttributes().get(CONSTITUTION), is(30));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(70));
        assertThat(character.getAttributes().get(LORE), is(70));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(70));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(19));
        // and
        assertThat(character.getAttack(), is(164));
        assertThat(character.getDamageMultiplier(), is(77));
        assertThat(character.getHealth().totalValue(), is(370));
        assertThat(character.getMagic().totalValue(), is(1292));
    }

    private void level71Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(31));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(71));
        assertThat(character.getAttributes().get(LORE), is(71));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(71));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(19));
        // and
        assertThat(character.getAttack(), is(166));
        assertThat(character.getDamageMultiplier(), is(78));
        assertThat(character.getHealth().totalValue(), is(380));
        assertThat(character.getMagic().totalValue(), is(1309));
    }

    private void level72Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(31));
        assertThat(character.getAttributes().get(AGILITY), is(32));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(72));
        assertThat(character.getAttributes().get(LORE), is(72));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(72));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(19));
        // and
        assertThat(character.getAttack(), is(166));
        assertThat(character.getDamageMultiplier(), is(80));
        assertThat(character.getHealth().totalValue(), is(380));
        assertThat(character.getMagic().totalValue(), is(1326));
    }

    private void level73Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(32));
        assertThat(character.getAttributes().get(AGILITY), is(32));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(73));
        assertThat(character.getAttributes().get(LORE), is(73));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(73));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(19));
        // and
        assertThat(character.getAttack(), is(170));
        assertThat(character.getDamageMultiplier(), is(82));
        assertThat(character.getHealth().totalValue(), is(390));
        assertThat(character.getMagic().totalValue(), is(1343));
    }

    private void level74Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(32));
        assertThat(character.getAttributes().get(AGILITY), is(33));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(74));
        assertThat(character.getAttributes().get(LORE), is(74));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(74));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(19));
        // and
        assertThat(character.getAttack(), is(170));
        assertThat(character.getDamageMultiplier(), is(84));
        assertThat(character.getHealth().totalValue(), is(390));
        assertThat(character.getMagic().totalValue(), is(1360));
    }

    private void level75Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(33));
        assertThat(character.getAttributes().get(AGILITY), is(33));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(75));
        assertThat(character.getAttributes().get(LORE), is(75));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(75));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(170));
        assertThat(character.getDamageMultiplier(), is(84));
        assertThat(character.getHealth().totalValue(), is(400));
        assertThat(character.getMagic().totalValue(), is(1393));
    }

    private void level76Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(33));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(76));
        assertThat(character.getAttributes().get(LORE), is(76));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(76));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(173));
        assertThat(character.getDamageMultiplier(), is(85));
        assertThat(character.getHealth().totalValue(), is(400));
        assertThat(character.getMagic().totalValue(), is(1410));
    }

    private void level77Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(32));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(34));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(77));
        assertThat(character.getAttributes().get(LORE), is(77));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(77));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(173));
        assertThat(character.getDamageMultiplier(), is(85));
        assertThat(character.getHealth().totalValue(), is(410));
        assertThat(character.getMagic().totalValue(), is(1427));
    }

    private void level78Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(32));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(34));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(78));
        assertThat(character.getAttributes().get(LORE), is(78));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(78));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(175));
        assertThat(character.getDamageMultiplier(), is(86));
        assertThat(character.getHealth().totalValue(), is(410));
        assertThat(character.getMagic().totalValue(), is(1444));
    }

    private void level79Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(33));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(35));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(79));
        assertThat(character.getAttributes().get(LORE), is(79));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(79));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(175));
        assertThat(character.getDamageMultiplier(), is(88));
        assertThat(character.getHealth().totalValue(), is(420));
        assertThat(character.getMagic().totalValue(), is(1461));
    }

    private void level80Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(34));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(35));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(80));
        assertThat(character.getAttributes().get(LORE), is(80));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(80));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(21));
        // and
        assertThat(character.getAttack(), is(175));
        assertThat(character.getDamageMultiplier(), is(90));
        assertThat(character.getHealth().totalValue(), is(420));
        assertThat(character.getMagic().totalValue(), is(1479));
    }

    private void level81Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(36));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(81));
        assertThat(character.getAttributes().get(LORE), is(81));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(81));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(21));
        // and
        assertThat(character.getAttack(), is(175));
        assertThat(character.getDamageMultiplier(), is(92));
        assertThat(character.getHealth().totalValue(), is(420));
        assertThat(character.getMagic().totalValue(), is(1496));
    }

    private void level82Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(36));
        assertThat(character.getAttributes().get(CONSTITUTION), is(36));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(82));
        assertThat(character.getAttributes().get(LORE), is(82));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(82));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(21));
        // and
        assertThat(character.getAttack(), is(178));
        assertThat(character.getDamageMultiplier(), is(93));
        assertThat(character.getHealth().totalValue(), is(420));
        assertThat(character.getMagic().totalValue(), is(1513));
    }

    private void level83Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(37));
        assertThat(character.getAttributes().get(CONSTITUTION), is(37));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(83));
        assertThat(character.getAttributes().get(LORE), is(83));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(83));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(21));
        // and
        assertThat(character.getAttack(), is(179));
        assertThat(character.getDamageMultiplier(), is(94));
        assertThat(character.getHealth().totalValue(), is(430));
        assertThat(character.getMagic().totalValue(), is(1530));
    }

    private void level84Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(37));
        assertThat(character.getAttributes().get(CONSTITUTION), is(37));
        assertThat(character.getAttributes().get(AGILITY), is(37));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(84));
        assertThat(character.getAttributes().get(LORE), is(84));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(84));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(21));
        // and
        assertThat(character.getAttack(), is(180));
        assertThat(character.getDamageMultiplier(), is(94));
        assertThat(character.getHealth().totalValue(), is(430));
        assertThat(character.getMagic().totalValue(), is(1547));
    }

    private void level85Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(37));
        assertThat(character.getAttributes().get(CONSTITUTION), is(38));
        assertThat(character.getAttributes().get(AGILITY), is(37));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(85));
        assertThat(character.getAttributes().get(LORE), is(85));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(85));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(180));
        assertThat(character.getDamageMultiplier(), is(94));
        assertThat(character.getHealth().totalValue(), is(440));
        assertThat(character.getMagic().totalValue(), is(1575));
    }

    private void level86Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(38));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(86));
        assertThat(character.getAttributes().get(LORE), is(86));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(86));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(184));
        assertThat(character.getDamageMultiplier(), is(96));
        assertThat(character.getHealth().totalValue(), is(440));
        assertThat(character.getMagic().totalValue(), is(1592));
    }

    private void level87Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(39));
        assertThat(character.getAttributes().get(CONSTITUTION), is(39));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(87));
        assertThat(character.getAttributes().get(LORE), is(87));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(87));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(186));
        assertThat(character.getDamageMultiplier(), is(97));
        assertThat(character.getHealth().totalValue(), is(450));
        assertThat(character.getMagic().totalValue(), is(1609));
    }

    private void level88Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(39));
        assertThat(character.getAttributes().get(CONSTITUTION), is(39));
        assertThat(character.getAttributes().get(AGILITY), is(39));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(88));
        assertThat(character.getAttributes().get(LORE), is(88));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(88));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(186));
        assertThat(character.getDamageMultiplier(), is(99));
        assertThat(character.getHealth().totalValue(), is(450));
        assertThat(character.getMagic().totalValue(), is(1626));
    }

    private void level89Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(40));
        assertThat(character.getAttributes().get(AGILITY), is(39));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(89));
        assertThat(character.getAttributes().get(LORE), is(89));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(89));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(188));
        assertThat(character.getDamageMultiplier(), is(100));
        assertThat(character.getHealth().totalValue(), is(460));
        assertThat(character.getMagic().totalValue(), is(1643));
    }

    private void level90Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(40));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(90));
        assertThat(character.getAttributes().get(LORE), is(90));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(90));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(189));
        assertThat(character.getDamageMultiplier(), is(100));
        assertThat(character.getHealth().totalValue(), is(460));
        assertThat(character.getMagic().totalValue(), is(1663));
    }

    private void level91Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(38));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(91));
        assertThat(character.getAttributes().get(LORE), is(91));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(91));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(189));
        assertThat(character.getDamageMultiplier(), is(102));
        assertThat(character.getHealth().totalValue(), is(470));
        assertThat(character.getMagic().totalValue(), is(1680));
    }

    private void level92Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(41));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(92));
        assertThat(character.getAttributes().get(LORE), is(92));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(92));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(190));
        assertThat(character.getDamageMultiplier(), is(103));
        assertThat(character.getHealth().totalValue(), is(470));
        assertThat(character.getMagic().totalValue(), is(1697));
    }

    private void level93Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(41));
        assertThat(character.getAttributes().get(CONSTITUTION), is(42));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(93));
        assertThat(character.getAttributes().get(LORE), is(93));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(93));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(191));
        assertThat(character.getDamageMultiplier(), is(103));
        assertThat(character.getHealth().totalValue(), is(480));
        assertThat(character.getMagic().totalValue(), is(1714));
    }

    private void level94Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(42));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(94));
        assertThat(character.getAttributes().get(LORE), is(94));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(94));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(193));
        assertThat(character.getDamageMultiplier(), is(106));
        assertThat(character.getHealth().totalValue(), is(480));
        assertThat(character.getMagic().totalValue(), is(1731));
    }

    private void level95Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(43));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(95));
        assertThat(character.getAttributes().get(LORE), is(95));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(95));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(24));
        // and
        assertThat(character.getAttack(), is(193));
        assertThat(character.getDamageMultiplier(), is(106));
        assertThat(character.getHealth().totalValue(), is(490));
        assertThat(character.getMagic().totalValue(), is(1759));
    }

    private void level96Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(43));
        assertThat(character.getAttributes().get(CONSTITUTION), is(43));
        assertThat(character.getAttributes().get(AGILITY), is(42));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(96));
        assertThat(character.getAttributes().get(LORE), is(96));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(96));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(24));
        // and
        assertThat(character.getAttack(), is(197));
        assertThat(character.getDamageMultiplier(), is(108));
        assertThat(character.getHealth().totalValue(), is(490));
        assertThat(character.getMagic().totalValue(), is(1776));
    }

    private void level97Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(43));
        assertThat(character.getAttributes().get(CONSTITUTION), is(44));
        assertThat(character.getAttributes().get(AGILITY), is(43));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(97));
        assertThat(character.getAttributes().get(LORE), is(97));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(97));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(24));
        // and
        assertThat(character.getAttack(), is(198));
        assertThat(character.getDamageMultiplier(), is(108));
        assertThat(character.getHealth().totalValue(), is(500));
        assertThat(character.getMagic().totalValue(), is(1793));
    }

    private void level98Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(44));
        assertThat(character.getAttributes().get(CONSTITUTION), is(44));
        assertThat(character.getAttributes().get(AGILITY), is(44));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(98));
        assertThat(character.getAttributes().get(LORE), is(98));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(98));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(24));
        // and
        assertThat(character.getAttack(), is(200));
        assertThat(character.getDamageMultiplier(), is(109));
        assertThat(character.getHealth().totalValue(), is(500));
        assertThat(character.getMagic().totalValue(), is(1810));
    }

    private void level99Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(44));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(99));
        assertThat(character.getAttributes().get(LORE), is(99));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(99));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(24));
        // and
        assertThat(character.getAttack(), is(202));
        assertThat(character.getDamageMultiplier(), is(110));
        assertThat(character.getHealth().totalValue(), is(510));
        assertThat(character.getMagic().totalValue(), is(1827));
    }

    private void level100Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(100));
        assertThat(character.getAttributes().get(LORE), is(100));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(100));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(25));
        // and
        assertThat(character.getAttack(), is(202));
        assertThat(character.getDamageMultiplier(), is(110));
        assertThat(character.getHealth().totalValue(), is(510));
        assertThat(character.getMagic().totalValue(), is(1845));

        // then
        character = upgradeCaste(AVATAR);

        // expect
        assertThat(character.getCaste(), is(AVATAR));
    }

    private final Map<Integer, Runnable> UPGRADES = Map.ofEntries(
            Map.entry(2, this::level2Upgrade),
            Map.entry(3, this::level3Upgrade),
            Map.entry(4, this::level4Upgrade),
            Map.entry(5, this::level5Upgrade),
            Map.entry(6, this::level6Upgrade),
            Map.entry(7, this::level7Upgrade),
            Map.entry(8, this::level8Upgrade),
            Map.entry(9, this::level9Upgrade),
            Map.entry(10, this::level10Upgrade),
            Map.entry(11, this::level11Upgrade),
            Map.entry(12, this::level12Upgrade),
            Map.entry(13, this::level13Upgrade),
            Map.entry(14, this::level14Upgrade),
            Map.entry(15, this::level15Upgrade),
            Map.entry(16, this::level16Upgrade),
            Map.entry(17, this::level17Upgrade),
            Map.entry(18, this::level18Upgrade),
            Map.entry(19, this::level19Upgrade),
            Map.entry(20, this::level20Upgrade),
            Map.entry(21, this::level21Upgrade),
            Map.entry(22, this::level22Upgrade),
            Map.entry(23, this::level23Upgrade),
            Map.entry(24, this::level24Upgrade),
            Map.entry(25, this::level25Upgrade),
            Map.entry(26, this::level26Upgrade),
            Map.entry(27, this::level27Upgrade),
            Map.entry(28, this::level28Upgrade),
            Map.entry(29, this::level29Upgrade),
            Map.entry(30, this::level30Upgrade),
            Map.entry(31, this::level31Upgrade),
            Map.entry(32, this::level32Upgrade),
            Map.entry(33, this::level33Upgrade),
            Map.entry(34, this::level34Upgrade),
            Map.entry(35, this::level35Upgrade),
            Map.entry(36, this::level36Upgrade),
            Map.entry(37, this::level37Upgrade),
            Map.entry(38, this::level38Upgrade),
            Map.entry(39, this::level39Upgrade),
            Map.entry(40, this::level40Upgrade),
            Map.entry(41, this::level41Upgrade),
            Map.entry(42, this::level42Upgrade),
            Map.entry(43, this::level43Upgrade),
            Map.entry(44, this::level44Upgrade),
            Map.entry(45, this::level45Upgrade),
            Map.entry(46, this::level46Upgrade),
            Map.entry(47, this::level47Upgrade),
            Map.entry(48, this::level48Upgrade),
            Map.entry(49, this::level49Upgrade),
            Map.entry(50, this::level50Upgrade),
            Map.entry(51, this::level51Upgrade),
            Map.entry(52, this::level52Upgrade),
            Map.entry(53, this::level53Upgrade),
            Map.entry(54, this::level54Upgrade),
            Map.entry(55, this::level55Upgrade),
            Map.entry(56, this::level56Upgrade),
            Map.entry(57, this::level57Upgrade),
            Map.entry(58, this::level58Upgrade),
            Map.entry(59, this::level59Upgrade),
            Map.entry(60, this::level60Upgrade),
            Map.entry(61, this::level61Upgrade),
            Map.entry(62, this::level62Upgrade),
            Map.entry(63, this::level63Upgrade),
            Map.entry(64, this::level64Upgrade),
            Map.entry(65, this::level65Upgrade),
            Map.entry(66, this::level66Upgrade),
            Map.entry(67, this::level67Upgrade),
            Map.entry(68, this::level68Upgrade),
            Map.entry(69, this::level69Upgrade),
            Map.entry(70, this::level70Upgrade),
            Map.entry(71, this::level71Upgrade),
            Map.entry(72, this::level72Upgrade),
            Map.entry(73, this::level73Upgrade),
            Map.entry(74, this::level74Upgrade),
            Map.entry(75, this::level75Upgrade),
            Map.entry(76, this::level76Upgrade),
            Map.entry(77, this::level77Upgrade),
            Map.entry(78, this::level78Upgrade),
            Map.entry(79, this::level79Upgrade),
            Map.entry(80, this::level80Upgrade),
            Map.entry(81, this::level81Upgrade),
            Map.entry(82, this::level82Upgrade),
            Map.entry(83, this::level83Upgrade),
            Map.entry(84, this::level84Upgrade),
            Map.entry(85, this::level85Upgrade),
            Map.entry(86, this::level86Upgrade),
            Map.entry(87, this::level87Upgrade),
            Map.entry(88, this::level88Upgrade),
            Map.entry(89, this::level89Upgrade),
            Map.entry(90, this::level90Upgrade),
            Map.entry(91, this::level91Upgrade),
            Map.entry(92, this::level92Upgrade),
            Map.entry(93, this::level93Upgrade),
            Map.entry(94, this::level94Upgrade),
            Map.entry(95, this::level95Upgrade),
            Map.entry(96, this::level96Upgrade),
            Map.entry(97, this::level97Upgrade),
            Map.entry(98, this::level98Upgrade),
            Map.entry(99, this::level99Upgrade),
            Map.entry(100, this::level100Upgrade)
    );
}
