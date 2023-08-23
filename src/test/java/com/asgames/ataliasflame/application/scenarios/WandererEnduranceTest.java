package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.GETON;
import static com.asgames.ataliasflame.domain.model.enums.Race.HUMAN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Disabled("May be killed in action")
@SpringBootTest(properties = "booster.experience:true")
public class WandererEnduranceTest extends EnduranceTestBase {

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(GETON)
                .name("Takemoto")
                .build();
        createCharacter(characterInput);

        // expect
        level1Upgrade();

        do {
            // given
            int startingLevel = character.getLevel();

            // when
            combatUntilNextLevel();

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

    public void level2Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        assertThat(character.getAttributes().get(LORE), is(0));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(0));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(87));
        assertThat(character.getDamageMultiplier(), is(5));
        assertThat(character.getHealth().totalValue(), is(120));
        assertThat(character.getMagic().totalValue(), is(5));
    }

    private void level3Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        assertThat(character.getAttributes().get(LORE), is(0));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(0));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDamageMultiplier(), is(7));
        assertThat(character.getHealth().totalValue(), is(130));
        assertThat(character.getMagic().totalValue(), is(5));
    }

    private void level4Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 3);
        addAttributePoints(CONSTITUTION, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        assertThat(character.getAttributes().get(LORE), is(0));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(0));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDamageMultiplier(), is(13));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(5));
    }

    private void level5Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(1));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDamageMultiplier(), is(13));
        assertThat(character.getHealth().totalValue(), is(150));
        assertThat(character.getMagic().totalValue(), is(24));

        // then
        upgradeCaste(TRACKER);

        // expect
        assertThat(character.getCaste(), is(TRACKER));
        assertThat(character.getSoulChips().size(), is(0));
    }

    private void level6Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(6));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(2));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getHealth().totalValue(), is(160));
        assertThat(character.getMagic().totalValue(), is(26));
    }

    private void level7Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(7));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(7));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(97));
        assertThat(character.getDamageMultiplier(), is(17));
        assertThat(character.getHealth().totalValue(), is(170));
        assertThat(character.getMagic().totalValue(), is(29));
    }

    private void level8Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(99));
        assertThat(character.getDamageMultiplier(), is(20));
        assertThat(character.getHealth().totalValue(), is(180));
        assertThat(character.getMagic().totalValue(), is(30));
    }

    private void level9Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(9));
        assertThat(character.getAttributes().get(CONSTITUTION), is(9));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(102));
        assertThat(character.getDamageMultiplier(), is(21));
        assertThat(character.getHealth().totalValue(), is(190));
        assertThat(character.getMagic().totalValue(), is(36));
    }

    private void level10Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(104));
        assertThat(character.getDamageMultiplier(), is(24));
        assertThat(character.getHealth().totalValue(), is(200));
        assertThat(character.getMagic().totalValue(), is(37));
    }

    private void level11Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(11));
        assertThat(character.getAttributes().get(CONSTITUTION), is(11));
        assertThat(character.getAttributes().get(AGILITY), is(11));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(106));
        assertThat(character.getDamageMultiplier(), is(27));
        assertThat(character.getHealth().totalValue(), is(210));
        assertThat(character.getMagic().totalValue(), is(39));
    }

    private void level12Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(109));
        assertThat(character.getDamageMultiplier(), is(28));
        assertThat(character.getHealth().totalValue(), is(220));
        assertThat(character.getMagic().totalValue(), is(45));
    }

    private void level13Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(9));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(13));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(111));
        assertThat(character.getDamageMultiplier(), is(31));
        assertThat(character.getHealth().totalValue(), is(230));
        assertThat(character.getMagic().totalValue(), is(46));
    }

    private void level14Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(114));
        assertThat(character.getDamageMultiplier(), is(34));
        assertThat(character.getHealth().totalValue(), is(240));
        assertThat(character.getMagic().totalValue(), is(48));
    }

    private void level15Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(15));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(116));
        assertThat(character.getDamageMultiplier(), is(37));
        assertThat(character.getHealth().totalValue(), is(250));
        assertThat(character.getMagic().totalValue(), is(53));
    }

    private void level16Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(40));
        assertThat(character.getHealth().totalValue(), is(260));
        assertThat(character.getMagic().totalValue(), is(54));
    }

    private void level17Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(13));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(121));
        assertThat(character.getDamageMultiplier(), is(43));
        assertThat(character.getHealth().totalValue(), is(270));
        assertThat(character.getMagic().totalValue(), is(55));
    }

    private void level18Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(122));
        assertThat(character.getDamageMultiplier(), is(46));
        assertThat(character.getHealth().totalValue(), is(280));
        assertThat(character.getMagic().totalValue(), is(58));
    }

    private void level19Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(15));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(19));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(6));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(123));
        assertThat(character.getDamageMultiplier(), is(48));
        assertThat(character.getHealth().totalValue(), is(290));
        assertThat(character.getMagic().totalValue(), is(64));
    }

    private void level20Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(123));
        assertThat(character.getDamageMultiplier(), is(50));
        assertThat(character.getHealth().totalValue(), is(300));
        assertThat(character.getMagic().totalValue(), is(81));

        // then
        upgradeCaste(RANGER);

        // expect
        assertThat(character.getCaste(), is(RANGER));
        assertThat(character.getSoulChips().size(), is(1));
    }

    private void level21Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(17));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(3));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(126));
        assertThat(character.getDamageMultiplier(), is(55));
        assertThat(character.getHealth().totalValue(), is(310));
        assertThat(character.getMagic().totalValue(), is(91));
    }

    private void level22Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(18));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(4));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(128));
        assertThat(character.getDamageMultiplier(), is(58));
        assertThat(character.getHealth().totalValue(), is(320));
        assertThat(character.getMagic().totalValue(), is(101));
    }

    private void level23Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(18));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(23));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(13));
        // and
        assertThat(character.getAttack(), is(130));
        assertThat(character.getDamageMultiplier(), is(59));
        assertThat(character.getHealth().totalValue(), is(330));
        assertThat(character.getMagic().totalValue(), is(112));
    }

    private void level24Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(14));
        // and
        assertThat(character.getAttack(), is(133));
        assertThat(character.getDamageMultiplier(), is(62));
        assertThat(character.getHealth().totalValue(), is(340));
        assertThat(character.getMagic().totalValue(), is(113));
    }

    private void level25Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(134));
        assertThat(character.getDamageMultiplier(), is(64));
        assertThat(character.getHealth().totalValue(), is(340));
        assertThat(character.getMagic().totalValue(), is(121));
    }

    private void level26Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(136));
        assertThat(character.getDamageMultiplier(), is(65));
        assertThat(character.getHealth().totalValue(), is(350));
        assertThat(character.getMagic().totalValue(), is(127));
    }

    private void level27Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(17));
        // and
        assertThat(character.getAttack(), is(138));
        assertThat(character.getDamageMultiplier(), is(68));
        assertThat(character.getHealth().totalValue(), is(360));
        assertThat(character.getMagic().totalValue(), is(128));
    }

    private void level28Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(27));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(141));
        assertThat(character.getDamageMultiplier(), is(71));
        assertThat(character.getHealth().totalValue(), is(370));
        assertThat(character.getMagic().totalValue(), is(129));
    }

    private void level29Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(19));
        // and
        assertThat(character.getAttack(), is(143));
        assertThat(character.getDamageMultiplier(), is(72));
        assertThat(character.getHealth().totalValue(), is(380));
        assertThat(character.getMagic().totalValue(), is(132));
    }

    private void level30Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(28));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(144));
        assertThat(character.getDamageMultiplier(), is(74));
        assertThat(character.getHealth().totalValue(), is(380));
        assertThat(character.getMagic().totalValue(), is(140));
    }

    private void level31Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(29));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(21));
        // and
        assertThat(character.getAttack(), is(146));
        assertThat(character.getDamageMultiplier(), is(77));
        assertThat(character.getHealth().totalValue(), is(390));
        assertThat(character.getMagic().totalValue(), is(141));
    }

    private void level32Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(28));
        assertThat(character.getAttributes().get(CONSTITUTION), is(30));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(149));
        assertThat(character.getDamageMultiplier(), is(78));
        assertThat(character.getHealth().totalValue(), is(400));
        assertThat(character.getMagic().totalValue(), is(147));
    }

    private void level33Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(31));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(151));
        assertThat(character.getDamageMultiplier(), is(81));
        assertThat(character.getHealth().totalValue(), is(410));
        assertThat(character.getMagic().totalValue(), is(148));
    }

    private void level34Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(32));
        assertThat(character.getAttributes().get(AGILITY), is(32));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(24));
        // and
        assertThat(character.getAttack(), is(154));
        assertThat(character.getDamageMultiplier(), is(84));
        assertThat(character.getHealth().totalValue(), is(420));
        assertThat(character.getMagic().totalValue(), is(149));
    }

    private void level35Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(32));
        assertThat(character.getAttributes().get(AGILITY), is(33));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(25));
        // and
        assertThat(character.getAttack(), is(154));
        assertThat(character.getDamageMultiplier(), is(84));
        assertThat(character.getHealth().totalValue(), is(420));
        assertThat(character.getMagic().totalValue(), is(162));
    }

    private void level36Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(27));
        assertThat(character.getAttributes().get(DEXTERITY), is(31));
        assertThat(character.getAttributes().get(CONSTITUTION), is(33));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(26));
        // and
        assertThat(character.getAttack(), is(157));
        assertThat(character.getDamageMultiplier(), is(87));
        assertThat(character.getHealth().totalValue(), is(430));
        assertThat(character.getMagic().totalValue(), is(163));
    }

    private void level37Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(34));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(27));
        // and
        assertThat(character.getAttack(), is(159));
        assertThat(character.getDamageMultiplier(), is(90));
        assertThat(character.getHealth().totalValue(), is(440));
        assertThat(character.getMagic().totalValue(), is(164));
    }

    private void level38Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(35));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(28));
        // and
        assertThat(character.getAttack(), is(162));
        assertThat(character.getDamageMultiplier(), is(91));
        assertThat(character.getHealth().totalValue(), is(450));
        assertThat(character.getMagic().totalValue(), is(167));
    }

    private void level39Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(36));
        assertThat(character.getAttributes().get(AGILITY), is(37));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(29));
        // and
        assertThat(character.getAttack(), is(164));
        assertThat(character.getDamageMultiplier(), is(94));
        assertThat(character.getHealth().totalValue(), is(460));
        assertThat(character.getMagic().totalValue(), is(168));
    }

    private void level40Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(36));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(14));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(30));
        // and
        assertThat(character.getAttack(), is(165));
        assertThat(character.getDamageMultiplier(), is(96));
        assertThat(character.getHealth().totalValue(), is(460));
        assertThat(character.getMagic().totalValue(), is(176));
    }

    private void level41Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(37));
        assertThat(character.getAttributes().get(AGILITY), is(39));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(31));
        // and
        assertThat(character.getAttack(), is(167));
        assertThat(character.getDamageMultiplier(), is(97));
        assertThat(character.getHealth().totalValue(), is(470));
        assertThat(character.getMagic().totalValue(), is(182));
    }

    private void level42Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(36));
        assertThat(character.getAttributes().get(CONSTITUTION), is(38));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(32));
        // and
        assertThat(character.getAttack(), is(170));
        assertThat(character.getDamageMultiplier(), is(100));
        assertThat(character.getHealth().totalValue(), is(480));
        assertThat(character.getMagic().totalValue(), is(183));
    }

    private void level43Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(32));
        assertThat(character.getAttributes().get(DEXTERITY), is(37));
        assertThat(character.getAttributes().get(CONSTITUTION), is(39));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(33));
        // and
        assertThat(character.getAttack(), is(172));
        assertThat(character.getDamageMultiplier(), is(103));
        assertThat(character.getHealth().totalValue(), is(490));
        assertThat(character.getMagic().totalValue(), is(184));
    }

    private void level44Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(32));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(40));
        assertThat(character.getAttributes().get(AGILITY), is(42));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(34));
        // and
        assertThat(character.getAttack(), is(174));
        assertThat(character.getDamageMultiplier(), is(104));
        assertThat(character.getHealth().totalValue(), is(500));
        assertThat(character.getMagic().totalValue(), is(187));
    }

    private void level45Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(33));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(40));
        assertThat(character.getAttributes().get(AGILITY), is(43));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(16));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(35));
        // and
        assertThat(character.getAttack(), is(175));
        assertThat(character.getDamageMultiplier(), is(106));
        assertThat(character.getHealth().totalValue(), is(500));
        assertThat(character.getMagic().totalValue(), is(195));
    }

    private void level46Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(34));
        assertThat(character.getAttributes().get(DEXTERITY), is(39));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(44));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(16));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(36));
        // and
        assertThat(character.getAttack(), is(178));
        assertThat(character.getDamageMultiplier(), is(109));
        assertThat(character.getHealth().totalValue(), is(510));
        assertThat(character.getMagic().totalValue(), is(196));
    }

    private void level47Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(34));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(42));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(17));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(37));
        // and
        assertThat(character.getAttack(), is(180));
        assertThat(character.getDamageMultiplier(), is(110));
        assertThat(character.getHealth().totalValue(), is(520));
        assertThat(character.getMagic().totalValue(), is(202));
    }

    private void level48Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(41));
        assertThat(character.getAttributes().get(CONSTITUTION), is(43));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(18));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(38));
        // and
        assertThat(character.getAttack(), is(182));
        assertThat(character.getDamageMultiplier(), is(113));
        assertThat(character.getHealth().totalValue(), is(530));
        assertThat(character.getMagic().totalValue(), is(208));
    }

    private void level49Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(44));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(16));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(39));
        // and
        assertThat(character.getAttack(), is(183));
        assertThat(character.getDamageMultiplier(), is(114));
        assertThat(character.getHealth().totalValue(), is(540));
        assertThat(character.getMagic().totalValue(), is(216));
    }

    private void level50Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 2);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(40));
        // and
        assertThat(character.getAttack(), is(183));
        assertThat(character.getDamageMultiplier(), is(114));
        assertThat(character.getHealth().totalValue(), is(550));
        assertThat(character.getMagic().totalValue(), is(226));

        // then
        upgradeCaste(PILGRIM);

        // expect
        assertThat(character.getCaste(), is(PILGRIM));
        assertThat(character.getSoulChips().size(), is(2));
    }

    private void level51Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(46));
        assertThat(character.getAttributes().get(AGILITY), is(46));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(41));
        // and
        assertThat(character.getAttack(), is(184));
        assertThat(character.getDamageMultiplier(), is(116));
        assertThat(character.getHealth().totalValue(), is(560));
        assertThat(character.getMagic().totalValue(), is(237));
    }

    private void level52Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(47));
        assertThat(character.getAttributes().get(AGILITY), is(47));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(42));
        // and
        assertThat(character.getAttack(), is(185));
        assertThat(character.getDamageMultiplier(), is(118));
        assertThat(character.getHealth().totalValue(), is(570));
        assertThat(character.getMagic().totalValue(), is(248));
    }

    private void level53Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(38));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(48));
        assertThat(character.getAttributes().get(AGILITY), is(48));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(43));
        // and
        assertThat(character.getAttack(), is(186));
        assertThat(character.getDamageMultiplier(), is(120));
        assertThat(character.getHealth().totalValue(), is(580));
        assertThat(character.getMagic().totalValue(), is(259));
    }

    private void level54Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(49));
        assertThat(character.getAttributes().get(AGILITY), is(49));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(44));
        // and
        assertThat(character.getAttack(), is(186));
        assertThat(character.getDamageMultiplier(), is(122));
        assertThat(character.getHealth().totalValue(), is(590));
        assertThat(character.getMagic().totalValue(), is(270));
    }

    private void level55Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(43));
        assertThat(character.getAttributes().get(CONSTITUTION), is(49));
        assertThat(character.getAttributes().get(AGILITY), is(49));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(46));
        // and
        assertThat(character.getAttack(), is(188));
        assertThat(character.getDamageMultiplier(), is(123));
        assertThat(character.getHealth().totalValue(), is(590));
        assertThat(character.getMagic().totalValue(), is(287));
    }

    private void level56Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(44));
        assertThat(character.getAttributes().get(CONSTITUTION), is(50));
        assertThat(character.getAttributes().get(AGILITY), is(50));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(47));
        // and
        assertThat(character.getAttack(), is(190));
        assertThat(character.getDamageMultiplier(), is(126));
        assertThat(character.getHealth().totalValue(), is(600));
        assertThat(character.getMagic().totalValue(), is(288));
    }

    private void level57Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(41));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(51));
        assertThat(character.getAttributes().get(AGILITY), is(51));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(48));
        // and
        assertThat(character.getAttack(), is(193));
        assertThat(character.getDamageMultiplier(), is(129));
        assertThat(character.getHealth().totalValue(), is(610));
        assertThat(character.getMagic().totalValue(), is(289));
    }

    private void level58Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(42));
        assertThat(character.getAttributes().get(DEXTERITY), is(46));
        assertThat(character.getAttributes().get(CONSTITUTION), is(52));
        assertThat(character.getAttributes().get(AGILITY), is(52));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(49));
        // and
        assertThat(character.getAttack(), is(195));
        assertThat(character.getDamageMultiplier(), is(132));
        assertThat(character.getHealth().totalValue(), is(620));
        assertThat(character.getMagic().totalValue(), is(290));
    }

    private void level59Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(43));
        assertThat(character.getAttributes().get(DEXTERITY), is(47));
        assertThat(character.getAttributes().get(CONSTITUTION), is(53));
        assertThat(character.getAttributes().get(AGILITY), is(53));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(50));
        // and
        assertThat(character.getAttack(), is(198));
        assertThat(character.getDamageMultiplier(), is(135));
        assertThat(character.getHealth().totalValue(), is(630));
        assertThat(character.getMagic().totalValue(), is(291));
    }

    private void level60Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(43));
        assertThat(character.getAttributes().get(DEXTERITY), is(47));
        assertThat(character.getAttributes().get(CONSTITUTION), is(53));
        assertThat(character.getAttributes().get(AGILITY), is(53));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(52));
        // and
        assertThat(character.getAttack(), is(198));
        assertThat(character.getDamageMultiplier(), is(135));
        assertThat(character.getHealth().totalValue(), is(630));
        assertThat(character.getMagic().totalValue(), is(305));
    }

    private void level61Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(44));
        assertThat(character.getAttributes().get(DEXTERITY), is(48));
        assertThat(character.getAttributes().get(CONSTITUTION), is(54));
        assertThat(character.getAttributes().get(AGILITY), is(54));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(53));
        // and
        assertThat(character.getAttack(), is(200));
        assertThat(character.getDamageMultiplier(), is(138));
        assertThat(character.getHealth().totalValue(), is(640));
        assertThat(character.getMagic().totalValue(), is(306));
    }

    private void level62Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(45));
        assertThat(character.getAttributes().get(DEXTERITY), is(49));
        assertThat(character.getAttributes().get(CONSTITUTION), is(55));
        assertThat(character.getAttributes().get(AGILITY), is(55));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(54));
        // and
        assertThat(character.getAttack(), is(202));
        assertThat(character.getDamageMultiplier(), is(141));
        assertThat(character.getHealth().totalValue(), is(650));
        assertThat(character.getMagic().totalValue(), is(307));
    }

    private void level63Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(46));
        assertThat(character.getAttributes().get(DEXTERITY), is(50));
        assertThat(character.getAttributes().get(CONSTITUTION), is(56));
        assertThat(character.getAttributes().get(AGILITY), is(56));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(55));
        // and
        assertThat(character.getAttack(), is(205));
        assertThat(character.getDamageMultiplier(), is(144));
        assertThat(character.getHealth().totalValue(), is(660));
        assertThat(character.getMagic().totalValue(), is(308));
    }

    private void level64Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(47));
        assertThat(character.getAttributes().get(DEXTERITY), is(51));
        assertThat(character.getAttributes().get(CONSTITUTION), is(57));
        assertThat(character.getAttributes().get(AGILITY), is(57));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(56));
        // and
        assertThat(character.getAttack(), is(207));
        assertThat(character.getDamageMultiplier(), is(147));
        assertThat(character.getHealth().totalValue(), is(670));
        assertThat(character.getMagic().totalValue(), is(309));
    }

    private void level65Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(47));
        assertThat(character.getAttributes().get(DEXTERITY), is(51));
        assertThat(character.getAttributes().get(CONSTITUTION), is(57));
        assertThat(character.getAttributes().get(AGILITY), is(57));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(58));
        // and
        assertThat(character.getAttack(), is(207));
        assertThat(character.getDamageMultiplier(), is(147));
        assertThat(character.getHealth().totalValue(), is(670));
        assertThat(character.getMagic().totalValue(), is(323));
    }

    private void level66Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(48));
        assertThat(character.getAttributes().get(DEXTERITY), is(52));
        assertThat(character.getAttributes().get(CONSTITUTION), is(58));
        assertThat(character.getAttributes().get(AGILITY), is(58));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(59));
        // and
        assertThat(character.getAttack(), is(210));
        assertThat(character.getDamageMultiplier(), is(150));
        assertThat(character.getHealth().totalValue(), is(680));
        assertThat(character.getMagic().totalValue(), is(324));
    }

    private void level67Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(49));
        assertThat(character.getAttributes().get(DEXTERITY), is(53));
        assertThat(character.getAttributes().get(CONSTITUTION), is(59));
        assertThat(character.getAttributes().get(AGILITY), is(59));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(60));
        // and
        assertThat(character.getAttack(), is(212));
        assertThat(character.getDamageMultiplier(), is(153));
        assertThat(character.getHealth().totalValue(), is(690));
        assertThat(character.getMagic().totalValue(), is(325));
    }

    private void level68Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(50));
        assertThat(character.getAttributes().get(DEXTERITY), is(54));
        assertThat(character.getAttributes().get(CONSTITUTION), is(60));
        assertThat(character.getAttributes().get(AGILITY), is(60));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(61));
        // and
        assertThat(character.getAttack(), is(214));
        assertThat(character.getDamageMultiplier(), is(158));
        assertThat(character.getHealth().totalValue(), is(700));
        assertThat(character.getMagic().totalValue(), is(326));
    }

    private void level69Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(51));
        assertThat(character.getAttributes().get(DEXTERITY), is(55));
        assertThat(character.getAttributes().get(CONSTITUTION), is(61));
        assertThat(character.getAttributes().get(AGILITY), is(61));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(62));
        // and
        assertThat(character.getAttack(), is(217));
        assertThat(character.getDamageMultiplier(), is(161));
        assertThat(character.getHealth().totalValue(), is(710));
        assertThat(character.getMagic().totalValue(), is(327));
    }

    private void level70Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(51));
        assertThat(character.getAttributes().get(DEXTERITY), is(55));
        assertThat(character.getAttributes().get(CONSTITUTION), is(61));
        assertThat(character.getAttributes().get(AGILITY), is(61));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(64));
        // and
        assertThat(character.getAttack(), is(217));
        assertThat(character.getDamageMultiplier(), is(161));
        assertThat(character.getHealth().totalValue(), is(710));
        assertThat(character.getMagic().totalValue(), is(341));
    }

    private void level71Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(52));
        assertThat(character.getAttributes().get(DEXTERITY), is(56));
        assertThat(character.getAttributes().get(CONSTITUTION), is(62));
        assertThat(character.getAttributes().get(AGILITY), is(62));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(65));
        // and
        assertThat(character.getAttack(), is(219));
        assertThat(character.getDamageMultiplier(), is(164));
        assertThat(character.getHealth().totalValue(), is(720));
        assertThat(character.getMagic().totalValue(), is(342));
    }

    private void level72Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(53));
        assertThat(character.getAttributes().get(DEXTERITY), is(57));
        assertThat(character.getAttributes().get(CONSTITUTION), is(63));
        assertThat(character.getAttributes().get(AGILITY), is(63));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(66));
        // and
        assertThat(character.getAttack(), is(222));
        assertThat(character.getDamageMultiplier(), is(167));
        assertThat(character.getHealth().totalValue(), is(730));
        assertThat(character.getMagic().totalValue(), is(343));
    }

    private void level73Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(54));
        assertThat(character.getAttributes().get(DEXTERITY), is(58));
        assertThat(character.getAttributes().get(CONSTITUTION), is(64));
        assertThat(character.getAttributes().get(AGILITY), is(64));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(67));
        // and
        assertThat(character.getAttack(), is(224));
        assertThat(character.getDamageMultiplier(), is(170));
        assertThat(character.getHealth().totalValue(), is(740));
        assertThat(character.getMagic().totalValue(), is(344));
    }

    private void level74Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(55));
        assertThat(character.getAttributes().get(DEXTERITY), is(59));
        assertThat(character.getAttributes().get(CONSTITUTION), is(65));
        assertThat(character.getAttributes().get(AGILITY), is(65));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(68));
        // and
        assertThat(character.getAttack(), is(226));
        assertThat(character.getDamageMultiplier(), is(173));
        assertThat(character.getHealth().totalValue(), is(750));
        assertThat(character.getMagic().totalValue(), is(345));
    }

    private void level75Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(55));
        assertThat(character.getAttributes().get(DEXTERITY), is(59));
        assertThat(character.getAttributes().get(CONSTITUTION), is(65));
        assertThat(character.getAttributes().get(AGILITY), is(65));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(70));
        // and
        assertThat(character.getAttack(), is(226));
        assertThat(character.getDamageMultiplier(), is(173));
        assertThat(character.getHealth().totalValue(), is(750));
        assertThat(character.getMagic().totalValue(), is(359));
    }

    private void level76Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(56));
        assertThat(character.getAttributes().get(DEXTERITY), is(60));
        assertThat(character.getAttributes().get(CONSTITUTION), is(66));
        assertThat(character.getAttributes().get(AGILITY), is(66));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(71));
        // and
        assertThat(character.getAttack(), is(229));
        assertThat(character.getDamageMultiplier(), is(176));
        assertThat(character.getHealth().totalValue(), is(760));
        assertThat(character.getMagic().totalValue(), is(360));
    }

    private void level77Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(57));
        assertThat(character.getAttributes().get(DEXTERITY), is(61));
        assertThat(character.getAttributes().get(CONSTITUTION), is(67));
        assertThat(character.getAttributes().get(AGILITY), is(67));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(72));
        // and
        assertThat(character.getAttack(), is(231));
        assertThat(character.getDamageMultiplier(), is(179));
        assertThat(character.getHealth().totalValue(), is(770));
        assertThat(character.getMagic().totalValue(), is(361));
    }

    private void level78Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(58));
        assertThat(character.getAttributes().get(DEXTERITY), is(62));
        assertThat(character.getAttributes().get(CONSTITUTION), is(68));
        assertThat(character.getAttributes().get(AGILITY), is(68));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(73));
        // and
        assertThat(character.getAttack(), is(234));
        assertThat(character.getDamageMultiplier(), is(182));
        assertThat(character.getHealth().totalValue(), is(780));
        assertThat(character.getMagic().totalValue(), is(362));
    }

    private void level79Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(59));
        assertThat(character.getAttributes().get(DEXTERITY), is(63));
        assertThat(character.getAttributes().get(CONSTITUTION), is(69));
        assertThat(character.getAttributes().get(AGILITY), is(69));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(74));
        // and
        assertThat(character.getAttack(), is(236));
        assertThat(character.getDamageMultiplier(), is(185));
        assertThat(character.getHealth().totalValue(), is(790));
        assertThat(character.getMagic().totalValue(), is(363));
    }

    private void level80Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(59));
        assertThat(character.getAttributes().get(DEXTERITY), is(63));
        assertThat(character.getAttributes().get(CONSTITUTION), is(69));
        assertThat(character.getAttributes().get(AGILITY), is(69));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(76));
        // and
        assertThat(character.getAttack(), is(236));
        assertThat(character.getDamageMultiplier(), is(185));
        assertThat(character.getHealth().totalValue(), is(790));
        assertThat(character.getMagic().totalValue(), is(377));
    }

    private void level81Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(60));
        assertThat(character.getAttributes().get(DEXTERITY), is(64));
        assertThat(character.getAttributes().get(CONSTITUTION), is(70));
        assertThat(character.getAttributes().get(AGILITY), is(70));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(77));
        // and
        assertThat(character.getAttack(), is(238));
        assertThat(character.getDamageMultiplier(), is(188));
        assertThat(character.getHealth().totalValue(), is(800));
        assertThat(character.getMagic().totalValue(), is(378));
    }

    private void level82Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(61));
        assertThat(character.getAttributes().get(DEXTERITY), is(65));
        assertThat(character.getAttributes().get(CONSTITUTION), is(71));
        assertThat(character.getAttributes().get(AGILITY), is(71));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(78));
        // and
        assertThat(character.getAttack(), is(241));
        assertThat(character.getDamageMultiplier(), is(191));
        assertThat(character.getHealth().totalValue(), is(810));
        assertThat(character.getMagic().totalValue(), is(379));
    }

    private void level83Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(62));
        assertThat(character.getAttributes().get(DEXTERITY), is(66));
        assertThat(character.getAttributes().get(CONSTITUTION), is(72));
        assertThat(character.getAttributes().get(AGILITY), is(72));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(79));
        // and
        assertThat(character.getAttack(), is(243));
        assertThat(character.getDamageMultiplier(), is(194));
        assertThat(character.getHealth().totalValue(), is(820));
        assertThat(character.getMagic().totalValue(), is(380));
    }

    private void level84Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(63));
        assertThat(character.getAttributes().get(DEXTERITY), is(67));
        assertThat(character.getAttributes().get(CONSTITUTION), is(73));
        assertThat(character.getAttributes().get(AGILITY), is(73));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(80));
        // and
        assertThat(character.getAttack(), is(246));
        assertThat(character.getDamageMultiplier(), is(197));
        assertThat(character.getHealth().totalValue(), is(830));
        assertThat(character.getMagic().totalValue(), is(381));
    }

    private void level85Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(63));
        assertThat(character.getAttributes().get(DEXTERITY), is(67));
        assertThat(character.getAttributes().get(CONSTITUTION), is(73));
        assertThat(character.getAttributes().get(AGILITY), is(73));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(82));
        // and
        assertThat(character.getAttack(), is(246));
        assertThat(character.getDamageMultiplier(), is(197));
        assertThat(character.getHealth().totalValue(), is(830));
        assertThat(character.getMagic().totalValue(), is(395));
    }

    private void level86Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(64));
        assertThat(character.getAttributes().get(DEXTERITY), is(68));
        assertThat(character.getAttributes().get(CONSTITUTION), is(74));
        assertThat(character.getAttributes().get(AGILITY), is(74));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(83));
        // and
        assertThat(character.getAttack(), is(248));
        assertThat(character.getDamageMultiplier(), is(200));
        assertThat(character.getHealth().totalValue(), is(840));
        assertThat(character.getMagic().totalValue(), is(396));
    }

    private void level87Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(65));
        assertThat(character.getAttributes().get(DEXTERITY), is(69));
        assertThat(character.getAttributes().get(CONSTITUTION), is(75));
        assertThat(character.getAttributes().get(AGILITY), is(75));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(84));
        // and
        assertThat(character.getAttack(), is(250));
        assertThat(character.getDamageMultiplier(), is(203));
        assertThat(character.getHealth().totalValue(), is(850));
        assertThat(character.getMagic().totalValue(), is(397));
    }

    private void level88Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(66));
        assertThat(character.getAttributes().get(DEXTERITY), is(70));
        assertThat(character.getAttributes().get(CONSTITUTION), is(76));
        assertThat(character.getAttributes().get(AGILITY), is(76));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(85));
        // and
        assertThat(character.getAttack(), is(253));
        assertThat(character.getDamageMultiplier(), is(206));
        assertThat(character.getHealth().totalValue(), is(860));
        assertThat(character.getMagic().totalValue(), is(398));
    }

    private void level89Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(67));
        assertThat(character.getAttributes().get(DEXTERITY), is(71));
        assertThat(character.getAttributes().get(CONSTITUTION), is(77));
        assertThat(character.getAttributes().get(AGILITY), is(77));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(86));
        // and
        assertThat(character.getAttack(), is(255));
        assertThat(character.getDamageMultiplier(), is(209));
        assertThat(character.getHealth().totalValue(), is(870));
        assertThat(character.getMagic().totalValue(), is(399));
    }

    private void level90Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(67));
        assertThat(character.getAttributes().get(DEXTERITY), is(71));
        assertThat(character.getAttributes().get(CONSTITUTION), is(77));
        assertThat(character.getAttributes().get(AGILITY), is(77));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(88));
        // and
        assertThat(character.getAttack(), is(255));
        assertThat(character.getDamageMultiplier(), is(209));
        assertThat(character.getHealth().totalValue(), is(870));
        assertThat(character.getMagic().totalValue(), is(413));
    }

    private void level91Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(68));
        assertThat(character.getAttributes().get(DEXTERITY), is(72));
        assertThat(character.getAttributes().get(CONSTITUTION), is(78));
        assertThat(character.getAttributes().get(AGILITY), is(78));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(89));
        // and
        assertThat(character.getAttack(), is(258));
        assertThat(character.getDamageMultiplier(), is(212));
        assertThat(character.getHealth().totalValue(), is(880));
        assertThat(character.getMagic().totalValue(), is(414));
    }

    private void level92Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(69));
        assertThat(character.getAttributes().get(DEXTERITY), is(73));
        assertThat(character.getAttributes().get(CONSTITUTION), is(79));
        assertThat(character.getAttributes().get(AGILITY), is(79));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(90));
        // and
        assertThat(character.getAttack(), is(260));
        assertThat(character.getDamageMultiplier(), is(215));
        assertThat(character.getHealth().totalValue(), is(890));
        assertThat(character.getMagic().totalValue(), is(415));
    }

    private void level93Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(70));
        assertThat(character.getAttributes().get(DEXTERITY), is(74));
        assertThat(character.getAttributes().get(CONSTITUTION), is(80));
        assertThat(character.getAttributes().get(AGILITY), is(80));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(91));
        // and
        assertThat(character.getAttack(), is(262));
        assertThat(character.getDamageMultiplier(), is(218));
        assertThat(character.getHealth().totalValue(), is(900));
        assertThat(character.getMagic().totalValue(), is(416));
    }

    private void level94Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(71));
        assertThat(character.getAttributes().get(DEXTERITY), is(75));
        assertThat(character.getAttributes().get(CONSTITUTION), is(81));
        assertThat(character.getAttributes().get(AGILITY), is(81));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(92));
        // and
        assertThat(character.getAttack(), is(265));
        assertThat(character.getDamageMultiplier(), is(221));
        assertThat(character.getHealth().totalValue(), is(910));
        assertThat(character.getMagic().totalValue(), is(417));
    }

    private void level95Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(71));
        assertThat(character.getAttributes().get(DEXTERITY), is(76));
        assertThat(character.getAttributes().get(CONSTITUTION), is(81));
        assertThat(character.getAttributes().get(AGILITY), is(81));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(94));
        // and
        assertThat(character.getAttack(), is(266));
        assertThat(character.getDamageMultiplier(), is(222));
        assertThat(character.getHealth().totalValue(), is(910));
        assertThat(character.getMagic().totalValue(), is(429));
    }

    private void level96Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(72));
        assertThat(character.getAttributes().get(DEXTERITY), is(77));
        assertThat(character.getAttributes().get(CONSTITUTION), is(82));
        assertThat(character.getAttributes().get(AGILITY), is(82));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(95));
        // and
        assertThat(character.getAttack(), is(269));
        assertThat(character.getDamageMultiplier(), is(225));
        assertThat(character.getHealth().totalValue(), is(920));
        assertThat(character.getMagic().totalValue(), is(430));
    }

    private void level97Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(73));
        assertThat(character.getAttributes().get(DEXTERITY), is(78));
        assertThat(character.getAttributes().get(CONSTITUTION), is(83));
        assertThat(character.getAttributes().get(AGILITY), is(83));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(96));
        // and
        assertThat(character.getAttack(), is(271));
        assertThat(character.getDamageMultiplier(), is(228));
        assertThat(character.getHealth().totalValue(), is(930));
        assertThat(character.getMagic().totalValue(), is(431));
    }

    private void level98Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(74));
        assertThat(character.getAttributes().get(DEXTERITY), is(79));
        assertThat(character.getAttributes().get(CONSTITUTION), is(84));
        assertThat(character.getAttributes().get(AGILITY), is(84));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(97));
        // and
        assertThat(character.getAttack(), is(274));
        assertThat(character.getDamageMultiplier(), is(231));
        assertThat(character.getHealth().totalValue(), is(940));
        assertThat(character.getMagic().totalValue(), is(432));
    }

    private void level99Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(75));
        assertThat(character.getAttributes().get(DEXTERITY), is(80));
        assertThat(character.getAttributes().get(CONSTITUTION), is(85));
        assertThat(character.getAttributes().get(AGILITY), is(85));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(98));
        // and
        assertThat(character.getAttack(), is(276));
        assertThat(character.getDamageMultiplier(), is(234));
        assertThat(character.getHealth().totalValue(), is(950));
        assertThat(character.getMagic().totalValue(), is(433));
    }

    private void level100Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 3);
        addAttributePoints(SPIRITUAL_POWER, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(75));
        assertThat(character.getAttributes().get(DEXTERITY), is(80));
        assertThat(character.getAttributes().get(CONSTITUTION), is(85));
        assertThat(character.getAttributes().get(AGILITY), is(85));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(100));
        // and
        assertThat(character.getAttack(), is(276));
        assertThat(character.getDamageMultiplier(), is(234));
        assertThat(character.getHealth().totalValue(), is(950));
        assertThat(character.getMagic().totalValue(), is(450));

        // then
        upgradeCaste(FREE_SOUL);

        // expect
        assertThat(character.getCaste(), is(FREE_SOUL));
        assertThat(character.getSoulChips().size(), is(3));
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
