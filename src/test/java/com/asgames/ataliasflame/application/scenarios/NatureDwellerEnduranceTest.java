package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.application.scenarios.helpers.EnduranceTestBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.asgames.ataliasflame.application.scenarios.helpers.HelperUtils.isAlive;
import static com.asgames.ataliasflame.application.scenarios.helpers.HelperUtils.isDead;
import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.SIFER;
import static com.asgames.ataliasflame.domain.model.enums.Race.HALFLING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Disabled("May be killed in action")
public class NatureDwellerEnduranceTest extends EnduranceTestBase {

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Drogo")
                .build();
        initializeCharacter(characterInput);

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

        } while (isAlive(character) && character.getLevel() < 100);

        // then do some more adventuring
        for (int i = 0; i < 1000; i++) {
            doCombat();
            if (isDead(character)) break;
        }
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
        assertThat(character.getAttack(), is(84));
        assertThat(character.getDamageMultiplier(), is(4));
        assertThat(character.getTotalHealth(), is(110));
        assertThat(character.getTotalMagicPoint(), is(5));
    }

    private void level2Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(2));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(2));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        assertThat(character.getAttributes().get(LORE), is(1));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(86));
        assertThat(character.getDamageMultiplier(), is(5));
        assertThat(character.getTotalHealth(), is(120));
        assertThat(character.getTotalMagicPoint(), is(17));
    }

    private void level3Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(2));
        assertThat(character.getAttributes().get(DEXTERITY), is(2));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(2));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(2));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(86));
        assertThat(character.getDamageMultiplier(), is(5));
        assertThat(character.getTotalHealth(), is(130));
        assertThat(character.getTotalMagicPoint(), is(34));
    }

    private void level4Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(90));
        assertThat(character.getDamageMultiplier(), is(9));
        assertThat(character.getTotalHealth(), is(130));
        assertThat(character.getTotalMagicPoint(), is(41));
    }

    private void level5Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(3));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(90));
        assertThat(character.getDamageMultiplier(), is(9));
        assertThat(character.getTotalHealth(), is(140));
        assertThat(character.getTotalMagicPoint(), is(59));

        // then
        upgradeCaste(HERMIT);

        // expect
        assertThat(character.getCaste(), is(HERMIT));
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
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(4));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(4));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(3));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(93));
        assertThat(character.getDamageMultiplier(), is(12));
        assertThat(character.getTotalHealth(), is(150));
        assertThat(character.getTotalMagicPoint(), is(61));
    }

    private void level7Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(4));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(97));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getTotalHealth(), is(150));
        assertThat(character.getTotalMagicPoint(), is(76));
    }

    private void level8Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(6));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(6));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(4));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(99));
        assertThat(character.getDamageMultiplier(), is(17));
        assertThat(character.getTotalHealth(), is(150));
        assertThat(character.getTotalMagicPoint(), is(81));
    }

    private void level9Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(7));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(4));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(102));
        assertThat(character.getDamageMultiplier(), is(21));
        assertThat(character.getTotalHealth(), is(160));
        assertThat(character.getTotalMagicPoint(), is(88));
    }

    private void level10Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(7));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(4));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(105));
        assertThat(character.getDamageMultiplier(), is(22));
        assertThat(character.getTotalHealth(), is(170));
        assertThat(character.getTotalMagicPoint(), is(95));
    }

    private void level11Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(9));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(106));
        assertThat(character.getDamageMultiplier(), is(22));
        assertThat(character.getTotalHealth(), is(180));
        assertThat(character.getTotalMagicPoint(), is(112));
    }

    private void level12Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(106));
        assertThat(character.getDamageMultiplier(), is(22));
        assertThat(character.getTotalHealth(), is(190));
        assertThat(character.getTotalMagicPoint(), is(130));
    }

    private void level13Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(9));
        assertThat(character.getAttributes().get(CONSTITUTION), is(11));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(109));
        assertThat(character.getDamageMultiplier(), is(26));
        assertThat(character.getTotalHealth(), is(200));
        assertThat(character.getTotalMagicPoint(), is(142));
    }

    private void level14Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(9));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(110));
        assertThat(character.getDamageMultiplier(), is(26));
        assertThat(character.getTotalHealth(), is(210));
        assertThat(character.getTotalMagicPoint(), is(159));
    }

    private void level15Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(13));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(111));
        assertThat(character.getDamageMultiplier(), is(27));
        assertThat(character.getTotalHealth(), is(220));
        assertThat(character.getTotalMagicPoint(), is(176));
    }

    private void level16Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(112));
        assertThat(character.getDamageMultiplier(), is(27));
        assertThat(character.getTotalHealth(), is(230));
        assertThat(character.getTotalMagicPoint(), is(193));
    }

    private void level17Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(11));
        assertThat(character.getAttributes().get(CONSTITUTION), is(15));
        assertThat(character.getAttributes().get(AGILITY), is(11));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(14));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(115));
        assertThat(character.getDamageMultiplier(), is(29));
        assertThat(character.getTotalHealth(), is(240));
        assertThat(character.getTotalMagicPoint(), is(200));
    }

    private void level18Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(30));
        assertThat(character.getTotalHealth(), is(250));
        assertThat(character.getTotalMagicPoint(), is(207));
    }

    private void level19Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 2);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(17));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(11));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(30));
        assertThat(character.getTotalHealth(), is(250));
        assertThat(character.getTotalMagicPoint(), is(231));
    }

    private void level20Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(18));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(30));
        assertThat(character.getTotalHealth(), is(250));
        assertThat(character.getTotalMagicPoint(), is(249));

        // then
        upgradeCaste(DRUID);

        // expect
        assertThat(character.getCaste(), is(DRUID));
    }

    private void level21Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(9));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(13));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(121));
        assertThat(character.getDamageMultiplier(), is(32));
        assertThat(character.getTotalHealth(), is(260));
        assertThat(character.getTotalMagicPoint(), is(264));
    }

    private void level22Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(19));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(122));
        assertThat(character.getDamageMultiplier(), is(34));
        assertThat(character.getTotalHealth(), is(270));
        assertThat(character.getTotalMagicPoint(), is(276));
    }

    private void level23Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(15));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(123));
        assertThat(character.getDamageMultiplier(), is(37));
        assertThat(character.getTotalHealth(), is(280));
        assertThat(character.getTotalMagicPoint(), is(291));
    }

    private void level24Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(124));
        assertThat(character.getDamageMultiplier(), is(37));
        assertThat(character.getTotalHealth(), is(290));
        assertThat(character.getTotalMagicPoint(), is(303));
    }

    private void level25Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(13));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(17));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(127));
        assertThat(character.getDamageMultiplier(), is(41));
        assertThat(character.getTotalHealth(), is(300));
        assertThat(character.getTotalMagicPoint(), is(318));
    }

    private void level26Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(23));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(128));
        assertThat(character.getDamageMultiplier(), is(43));
        assertThat(character.getTotalHealth(), is(310));
        assertThat(character.getTotalMagicPoint(), is(330));
    }

    private void level27Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(15));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(19));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(130));
        assertThat(character.getDamageMultiplier(), is(46));
        assertThat(character.getTotalHealth(), is(320));
        assertThat(character.getTotalMagicPoint(), is(345));
    }

    private void level28Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(130));
        assertThat(character.getDamageMultiplier(), is(46));
        assertThat(character.getTotalHealth(), is(330));
        assertThat(character.getTotalMagicPoint(), is(357));
    }

    private void level29Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(21));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(134));
        assertThat(character.getDamageMultiplier(), is(48));
        assertThat(character.getTotalHealth(), is(340));
        assertThat(character.getTotalMagicPoint(), is(373));
    }

    private void level30Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(17));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(27));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(134));
        assertThat(character.getDamageMultiplier(), is(50));
        assertThat(character.getTotalHealth(), is(350));
        assertThat(character.getTotalMagicPoint(), is(385));
    }

    private void level31Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(18));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(23));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(136));
        assertThat(character.getDamageMultiplier(), is(53));
        assertThat(character.getTotalHealth(), is(350));
        assertThat(character.getTotalMagicPoint(), is(400));
    }

    private void level32Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(18));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(29));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(140));
        assertThat(character.getDamageMultiplier(), is(55));
        assertThat(character.getTotalHealth(), is(360));
        assertThat(character.getTotalMagicPoint(), is(412));
    }

    private void level33Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(30));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(141));
        assertThat(character.getDamageMultiplier(), is(55));
        assertThat(character.getTotalHealth(), is(370));
        assertThat(character.getTotalMagicPoint(), is(427));
    }

    private void level34Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(31));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(26));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(142));
        assertThat(character.getDamageMultiplier(), is(56));
        assertThat(character.getTotalHealth(), is(380));
        assertThat(character.getTotalMagicPoint(), is(440));
    }

    private void level35Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(32));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        assertThat(character.getAttributes().get(LORE), is(26));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(26));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(143));
        assertThat(character.getDamageMultiplier(), is(58));
        assertThat(character.getTotalHealth(), is(390));
        assertThat(character.getTotalMagicPoint(), is(447));
    }

    private void level36Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(33));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(27));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(27));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(146));
        assertThat(character.getDamageMultiplier(), is(60));
        assertThat(character.getTotalHealth(), is(400));
        assertThat(character.getTotalMagicPoint(), is(464));
    }

    private void level37Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(34));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        assertThat(character.getAttributes().get(LORE), is(28));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(28));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(146));
        assertThat(character.getDamageMultiplier(), is(62));
        assertThat(character.getTotalHealth(), is(410));
        assertThat(character.getTotalMagicPoint(), is(481));
    }

    private void level38Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(35));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(29));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(29));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(147));
        assertThat(character.getDamageMultiplier(), is(62));
        assertThat(character.getTotalHealth(), is(420));
        assertThat(character.getTotalMagicPoint(), is(498));
    }

    private void level39Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(36));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(30));
        assertThat(character.getAttributes().get(LORE), is(30));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(30));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(149));
        assertThat(character.getDamageMultiplier(), is(63));
        assertThat(character.getTotalHealth(), is(430));
        assertThat(character.getTotalMagicPoint(), is(515));
    }

    private void level40Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(37));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(31));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(31));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(149));
        assertThat(character.getDamageMultiplier(), is(63));
        assertThat(character.getTotalHealth(), is(440));
        assertThat(character.getTotalMagicPoint(), is(532));
    }

    private void level41Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(38));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(32));
        assertThat(character.getAttributes().get(LORE), is(32));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(32));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(150));
        assertThat(character.getDamageMultiplier(), is(63));
        assertThat(character.getTotalHealth(), is(450));
        assertThat(character.getTotalMagicPoint(), is(549));
    }

    private void level42Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(39));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(33));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(33));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(153));
        assertThat(character.getDamageMultiplier(), is(65));
        assertThat(character.getTotalHealth(), is(450));
        assertThat(character.getTotalMagicPoint(), is(566));
    }

    private void level43Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(40));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(34));
        assertThat(character.getAttributes().get(LORE), is(34));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(34));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(153));
        assertThat(character.getDamageMultiplier(), is(67));
        assertThat(character.getTotalHealth(), is(460));
        assertThat(character.getTotalMagicPoint(), is(583));
    }

    private void level44Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(35));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(35));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(154));
        assertThat(character.getDamageMultiplier(), is(67));
        assertThat(character.getTotalHealth(), is(470));
        assertThat(character.getTotalMagicPoint(), is(600));
    }

    private void level45Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(36));
        assertThat(character.getAttributes().get(LORE), is(36));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(36));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(155));
        assertThat(character.getDamageMultiplier(), is(68));
        assertThat(character.getTotalHealth(), is(470));
        assertThat(character.getTotalMagicPoint(), is(618));
    }

    private void level46Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(37));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(37));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(155));
        assertThat(character.getDamageMultiplier(), is(70));
        assertThat(character.getTotalHealth(), is(470));
        assertThat(character.getTotalMagicPoint(), is(636));
    }

    private void level47Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(42));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(38));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(38));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(156));
        assertThat(character.getDamageMultiplier(), is(70));
        assertThat(character.getTotalHealth(), is(480));
        assertThat(character.getTotalMagicPoint(), is(653));
    }

    private void level48Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(43));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(38));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(39));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(159));
        assertThat(character.getDamageMultiplier(), is(72));
        assertThat(character.getTotalHealth(), is(490));
        assertThat(character.getTotalMagicPoint(), is(669));
    }

    private void level49Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(44));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(39));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(40));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(159));
        assertThat(character.getDamageMultiplier(), is(74));
        assertThat(character.getTotalHealth(), is(500));
        assertThat(character.getTotalMagicPoint(), is(682));
    }

    private void level50Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(40));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(40));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(160));
        assertThat(character.getDamageMultiplier(), is(74));
        assertThat(character.getTotalHealth(), is(510));
        assertThat(character.getTotalMagicPoint(), is(690));

        // then
        upgradeCaste(ARCHDRUID);

        // expect
        assertThat(character.getCaste(), is(ARCHDRUID));
    }

    private void level51Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(41));
        assertThat(character.getAttributes().get(LORE), is(41));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(41));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(162));
        assertThat(character.getDamageMultiplier(), is(75));
        assertThat(character.getTotalHealth(), is(510));
        assertThat(character.getTotalMagicPoint(), is(707));
    }

    private void level52Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(46));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(42));
        assertThat(character.getAttributes().get(LORE), is(42));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(42));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(162));
        assertThat(character.getDamageMultiplier(), is(75));
        assertThat(character.getTotalHealth(), is(520));
        assertThat(character.getTotalMagicPoint(), is(724));
    }

    private void level53Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(46));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(43));
        assertThat(character.getAttributes().get(LORE), is(43));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(43));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(166));
        assertThat(character.getDamageMultiplier(), is(77));
        assertThat(character.getTotalHealth(), is(520));
        assertThat(character.getTotalMagicPoint(), is(741));
    }

    private void level54Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(27));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(47));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(44));
        assertThat(character.getAttributes().get(LORE), is(44));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(44));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(166));
        assertThat(character.getDamageMultiplier(), is(79));
        assertThat(character.getTotalHealth(), is(530));
        assertThat(character.getTotalMagicPoint(), is(758));
    }

    private void level55Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(27));
        assertThat(character.getAttributes().get(DEXTERITY), is(28));
        assertThat(character.getAttributes().get(CONSTITUTION), is(47));
        assertThat(character.getAttributes().get(AGILITY), is(28));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(44));
        assertThat(character.getAttributes().get(LORE), is(45));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(169));
        assertThat(character.getDamageMultiplier(), is(80));
        assertThat(character.getTotalHealth(), is(530));
        assertThat(character.getTotalMagicPoint(), is(771));
    }

    private void level56Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(28));
        assertThat(character.getAttributes().get(CONSTITUTION), is(48));
        assertThat(character.getAttributes().get(AGILITY), is(28));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(45));
        assertThat(character.getAttributes().get(LORE), is(46));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(46));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(169));
        assertThat(character.getDamageMultiplier(), is(82));
        assertThat(character.getTotalHealth(), is(540));
        assertThat(character.getTotalMagicPoint(), is(788));
    }

    private void level57Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(48));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(46));
        assertThat(character.getAttributes().get(LORE), is(47));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(47));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(173));
        assertThat(character.getDamageMultiplier(), is(84));
        assertThat(character.getTotalHealth(), is(540));
        assertThat(character.getTotalMagicPoint(), is(805));
    }

    private void level58Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(49));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(47));
        assertThat(character.getAttributes().get(LORE), is(48));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(48));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(173));
        assertThat(character.getDamageMultiplier(), is(84));
        assertThat(character.getTotalHealth(), is(550));
        assertThat(character.getTotalMagicPoint(), is(822));
    }

    private void level59Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(49));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(48));
        assertThat(character.getAttributes().get(LORE), is(49));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(49));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(11));
        // and
        assertThat(character.getAttack(), is(175));
        assertThat(character.getDamageMultiplier(), is(85));
        assertThat(character.getTotalHealth(), is(550));
        assertThat(character.getTotalMagicPoint(), is(839));
    }

    private void level60Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(50));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(48));
        assertThat(character.getAttributes().get(LORE), is(50));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(50));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(175));
        assertThat(character.getDamageMultiplier(), is(87));
        assertThat(character.getTotalHealth(), is(560));
        assertThat(character.getTotalMagicPoint(), is(852));
    }

    private void level61Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(31));
        assertThat(character.getAttributes().get(CONSTITUTION), is(50));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(49));
        assertThat(character.getAttributes().get(LORE), is(51));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(51));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(178));
        assertThat(character.getDamageMultiplier(), is(89));
        assertThat(character.getTotalHealth(), is(560));
        assertThat(character.getTotalMagicPoint(), is(869));
    }

    private void level62Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(31));
        assertThat(character.getAttributes().get(CONSTITUTION), is(51));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(50));
        assertThat(character.getAttributes().get(LORE), is(52));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(52));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(178));
        assertThat(character.getDamageMultiplier(), is(91));
        assertThat(character.getTotalHealth(), is(560));
        assertThat(character.getTotalMagicPoint(), is(886));
    }

    private void level63Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(51));
        assertThat(character.getAttributes().get(AGILITY), is(32));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(51));
        assertThat(character.getAttributes().get(LORE), is(53));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(53));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(181));
        assertThat(character.getDamageMultiplier(), is(92));
        assertThat(character.getTotalHealth(), is(560));
        assertThat(character.getTotalMagicPoint(), is(903));
    }

    private void level64Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(32));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(52));
        assertThat(character.getAttributes().get(AGILITY), is(32));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(52));
        assertThat(character.getAttributes().get(LORE), is(54));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(54));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(12));
        // and
        assertThat(character.getAttack(), is(181));
        assertThat(character.getDamageMultiplier(), is(92));
        assertThat(character.getTotalHealth(), is(570));
        assertThat(character.getTotalMagicPoint(), is(920));
    }

    private void level65Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(32));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(52));
        assertThat(character.getAttributes().get(AGILITY), is(33));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(52));
        assertThat(character.getAttributes().get(LORE), is(55));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(55));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(13));
        // and
        assertThat(character.getAttack(), is(185));
        assertThat(character.getDamageMultiplier(), is(94));
        assertThat(character.getTotalHealth(), is(570));
        assertThat(character.getTotalMagicPoint(), is(933));
    }

    private void level66Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(33));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(53));
        assertThat(character.getAttributes().get(AGILITY), is(33));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(53));
        assertThat(character.getAttributes().get(LORE), is(56));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(56));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(13));
        // and
        assertThat(character.getAttack(), is(185));
        assertThat(character.getDamageMultiplier(), is(96));
        assertThat(character.getTotalHealth(), is(580));
        assertThat(character.getTotalMagicPoint(), is(950));
    }

    private void level67Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(33));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(53));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(54));
        assertThat(character.getAttributes().get(LORE), is(57));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(57));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(13));
        // and
        assertThat(character.getAttack(), is(187));
        assertThat(character.getDamageMultiplier(), is(97));
        assertThat(character.getTotalHealth(), is(580));
        assertThat(character.getTotalMagicPoint(), is(967));
    }

    private void level68Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(34));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(54));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(55));
        assertThat(character.getAttributes().get(LORE), is(58));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(58));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(13));
        // and
        assertThat(character.getAttack(), is(187));
        assertThat(character.getDamageMultiplier(), is(99));
        assertThat(character.getTotalHealth(), is(590));
        assertThat(character.getTotalMagicPoint(), is(984));
    }

    private void level69Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(34));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(54));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(56));
        assertThat(character.getAttributes().get(LORE), is(59));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(59));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(13));
        // and
        assertThat(character.getAttack(), is(191));
        assertThat(character.getDamageMultiplier(), is(101));
        assertThat(character.getTotalHealth(), is(590));
        assertThat(character.getTotalMagicPoint(), is(1001));
    }

    private void level70Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(55));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(56));
        assertThat(character.getAttributes().get(LORE), is(60));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(60));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(14));
        // and
        assertThat(character.getAttack(), is(191));
        assertThat(character.getDamageMultiplier(), is(103));
        assertThat(character.getTotalHealth(), is(600));
        assertThat(character.getTotalMagicPoint(), is(1014));
    }

    private void level71Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(36));
        assertThat(character.getAttributes().get(CONSTITUTION), is(55));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(57));
        assertThat(character.getAttributes().get(LORE), is(61));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(61));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(14));
        // and
        assertThat(character.getAttack(), is(194));
        assertThat(character.getDamageMultiplier(), is(104));
        assertThat(character.getTotalHealth(), is(600));
        assertThat(character.getTotalMagicPoint(), is(1031));
    }

    private void level72Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(36));
        assertThat(character.getAttributes().get(CONSTITUTION), is(56));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(58));
        assertThat(character.getAttributes().get(LORE), is(62));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(62));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(194));
        assertThat(character.getDamageMultiplier(), is(104));
        assertThat(character.getTotalHealth(), is(610));
        assertThat(character.getTotalMagicPoint(), is(1049));
    }

    private void level73Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(37));
        assertThat(character.getAttributes().get(CONSTITUTION), is(56));
        assertThat(character.getAttributes().get(AGILITY), is(37));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(59));
        assertThat(character.getAttributes().get(LORE), is(63));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(63));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(198));
        assertThat(character.getDamageMultiplier(), is(106));
        assertThat(character.getTotalHealth(), is(610));
        assertThat(character.getTotalMagicPoint(), is(1066));
    }

    private void level74Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(37));
        assertThat(character.getAttributes().get(CONSTITUTION), is(57));
        assertThat(character.getAttributes().get(AGILITY), is(37));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(60));
        assertThat(character.getAttributes().get(LORE), is(64));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(64));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(15));
        // and
        assertThat(character.getAttack(), is(198));
        assertThat(character.getDamageMultiplier(), is(106));
        assertThat(character.getTotalHealth(), is(620));
        assertThat(character.getTotalMagicPoint(), is(1083));
    }

    private void level75Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(57));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(60));
        assertThat(character.getAttributes().get(LORE), is(65));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(65));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(200));
        assertThat(character.getDamageMultiplier(), is(107));
        assertThat(character.getTotalHealth(), is(620));
        assertThat(character.getTotalMagicPoint(), is(1096));
    }

    private void level76Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(58));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(61));
        assertThat(character.getAttributes().get(LORE), is(66));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(66));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(200));
        assertThat(character.getDamageMultiplier(), is(109));
        assertThat(character.getTotalHealth(), is(630));
        assertThat(character.getTotalMagicPoint(), is(1113));
    }

    private void level77Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(39));
        assertThat(character.getAttributes().get(CONSTITUTION), is(58));
        assertThat(character.getAttributes().get(AGILITY), is(39));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(62));
        assertThat(character.getAttributes().get(LORE), is(67));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(67));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(16));
        // and
        assertThat(character.getAttack(), is(204));
        assertThat(character.getDamageMultiplier(), is(111));
        assertThat(character.getTotalHealth(), is(630));
        assertThat(character.getTotalMagicPoint(), is(1130));
    }

    private void level78Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(39));
        assertThat(character.getAttributes().get(CONSTITUTION), is(59));
        assertThat(character.getAttributes().get(AGILITY), is(39));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(63));
        assertThat(character.getAttributes().get(LORE), is(68));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(68));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(17));
        // and
        assertThat(character.getAttack(), is(204));
        assertThat(character.getDamageMultiplier(), is(111));
        assertThat(character.getTotalHealth(), is(640));
        assertThat(character.getTotalMagicPoint(), is(1148));
    }

    private void level79Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(59));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(64));
        assertThat(character.getAttributes().get(LORE), is(69));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(69));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(17));
        // and
        assertThat(character.getAttack(), is(206));
        assertThat(character.getDamageMultiplier(), is(112));
        assertThat(character.getTotalHealth(), is(640));
        assertThat(character.getTotalMagicPoint(), is(1165));
    }

    private void level80Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(38));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(60));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(64));
        assertThat(character.getAttributes().get(LORE), is(70));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(70));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(206));
        assertThat(character.getDamageMultiplier(), is(114));
        assertThat(character.getTotalHealth(), is(650));
        assertThat(character.getTotalMagicPoint(), is(1178));
    }

    private void level81Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(38));
        assertThat(character.getAttributes().get(DEXTERITY), is(41));
        assertThat(character.getAttributes().get(CONSTITUTION), is(60));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(65));
        assertThat(character.getAttributes().get(LORE), is(71));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(71));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(210));
        assertThat(character.getDamageMultiplier(), is(116));
        assertThat(character.getTotalHealth(), is(650));
        assertThat(character.getTotalMagicPoint(), is(1195));
    }

    private void level82Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(41));
        assertThat(character.getAttributes().get(CONSTITUTION), is(61));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(66));
        assertThat(character.getAttributes().get(LORE), is(72));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(72));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(210));
        assertThat(character.getDamageMultiplier(), is(116));
        assertThat(character.getTotalHealth(), is(660));
        assertThat(character.getTotalMagicPoint(), is(1212));
    }

    private void level83Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(61));
        assertThat(character.getAttributes().get(AGILITY), is(42));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(67));
        assertThat(character.getAttributes().get(LORE), is(73));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(73));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(18));
        // and
        assertThat(character.getAttack(), is(213));
        assertThat(character.getDamageMultiplier(), is(117));
        assertThat(character.getTotalHealth(), is(660));
        assertThat(character.getTotalMagicPoint(), is(1229));
    }

    private void level84Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(62));
        assertThat(character.getAttributes().get(AGILITY), is(42));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(68));
        assertThat(character.getAttributes().get(LORE), is(74));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(74));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(19));
        // and
        assertThat(character.getAttack(), is(213));
        assertThat(character.getDamageMultiplier(), is(117));
        assertThat(character.getTotalHealth(), is(660));
        assertThat(character.getTotalMagicPoint(), is(1247));
    }

    private void level85Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(43));
        assertThat(character.getAttributes().get(CONSTITUTION), is(62));
        assertThat(character.getAttributes().get(AGILITY), is(43));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(68));
        assertThat(character.getAttributes().get(LORE), is(75));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(75));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(19));
        // and
        assertThat(character.getAttack(), is(217));
        assertThat(character.getDamageMultiplier(), is(121));
        assertThat(character.getTotalHealth(), is(660));
        assertThat(character.getTotalMagicPoint(), is(1259));
    }

    private void level86Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(43));
        assertThat(character.getAttributes().get(CONSTITUTION), is(63));
        assertThat(character.getAttributes().get(AGILITY), is(43));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(69));
        assertThat(character.getAttributes().get(LORE), is(76));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(76));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(217));
        assertThat(character.getDamageMultiplier(), is(121));
        assertThat(character.getTotalHealth(), is(670));
        assertThat(character.getTotalMagicPoint(), is(1277));
    }

    private void level87Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(44));
        assertThat(character.getAttributes().get(CONSTITUTION), is(63));
        assertThat(character.getAttributes().get(AGILITY), is(44));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(70));
        assertThat(character.getAttributes().get(LORE), is(77));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(77));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(219));
        assertThat(character.getDamageMultiplier(), is(122));
        assertThat(character.getTotalHealth(), is(670));
        assertThat(character.getTotalMagicPoint(), is(1294));
    }

    private void level88Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(41));
        assertThat(character.getAttributes().get(DEXTERITY), is(44));
        assertThat(character.getAttributes().get(CONSTITUTION), is(64));
        assertThat(character.getAttributes().get(AGILITY), is(44));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(71));
        assertThat(character.getAttributes().get(LORE), is(78));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(78));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(219));
        assertThat(character.getDamageMultiplier(), is(124));
        assertThat(character.getTotalHealth(), is(680));
        assertThat(character.getTotalMagicPoint(), is(1311));
    }

    private void level89Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(41));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(64));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(72));
        assertThat(character.getAttributes().get(LORE), is(79));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(79));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(20));
        // and
        assertThat(character.getAttack(), is(223));
        assertThat(character.getDamageMultiplier(), is(126));
        assertThat(character.getTotalHealth(), is(680));
        assertThat(character.getTotalMagicPoint(), is(1328));
    }

    private void level90Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(42));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(65));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(72));
        assertThat(character.getAttributes().get(LORE), is(80));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(80));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(21));
        // and
        assertThat(character.getAttack(), is(223));
        assertThat(character.getDamageMultiplier(), is(126));
        assertThat(character.getTotalHealth(), is(690));
        assertThat(character.getTotalMagicPoint(), is(1341));
    }

    private void level91Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(42));
        assertThat(character.getAttributes().get(DEXTERITY), is(46));
        assertThat(character.getAttributes().get(CONSTITUTION), is(65));
        assertThat(character.getAttributes().get(AGILITY), is(46));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(73));
        assertThat(character.getAttributes().get(LORE), is(81));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(81));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(21));
        // and
        assertThat(character.getAttack(), is(226));
        assertThat(character.getDamageMultiplier(), is(127));
        assertThat(character.getTotalHealth(), is(690));
        assertThat(character.getTotalMagicPoint(), is(1358));
    }

    private void level92Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(42));
        assertThat(character.getAttributes().get(DEXTERITY), is(46));
        assertThat(character.getAttributes().get(CONSTITUTION), is(66));
        assertThat(character.getAttributes().get(AGILITY), is(46));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(74));
        assertThat(character.getAttributes().get(LORE), is(82));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(82));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(226));
        assertThat(character.getDamageMultiplier(), is(127));
        assertThat(character.getTotalHealth(), is(700));
        assertThat(character.getTotalMagicPoint(), is(1376));
    }

    private void level93Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(42));
        assertThat(character.getAttributes().get(DEXTERITY), is(47));
        assertThat(character.getAttributes().get(CONSTITUTION), is(66));
        assertThat(character.getAttributes().get(AGILITY), is(47));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(75));
        assertThat(character.getAttributes().get(LORE), is(83));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(83));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(230));
        assertThat(character.getDamageMultiplier(), is(129));
        assertThat(character.getTotalHealth(), is(700));
        assertThat(character.getTotalMagicPoint(), is(1393));
    }

    private void level94Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(43));
        assertThat(character.getAttributes().get(DEXTERITY), is(47));
        assertThat(character.getAttributes().get(CONSTITUTION), is(67));
        assertThat(character.getAttributes().get(AGILITY), is(47));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(76));
        assertThat(character.getAttributes().get(LORE), is(84));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(84));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(22));
        // and
        assertThat(character.getAttack(), is(230));
        assertThat(character.getDamageMultiplier(), is(131));
        assertThat(character.getTotalHealth(), is(710));
        assertThat(character.getTotalMagicPoint(), is(1410));
    }

    private void level95Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(43));
        assertThat(character.getAttributes().get(DEXTERITY), is(48));
        assertThat(character.getAttributes().get(CONSTITUTION), is(67));
        assertThat(character.getAttributes().get(AGILITY), is(48));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(76));
        assertThat(character.getAttributes().get(LORE), is(85));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(85));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(232));
        assertThat(character.getDamageMultiplier(), is(132));
        assertThat(character.getTotalHealth(), is(710));
        assertThat(character.getTotalMagicPoint(), is(1423));
    }

    private void level96Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(44));
        assertThat(character.getAttributes().get(DEXTERITY), is(48));
        assertThat(character.getAttributes().get(CONSTITUTION), is(68));
        assertThat(character.getAttributes().get(AGILITY), is(48));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(77));
        assertThat(character.getAttributes().get(LORE), is(86));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(86));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(232));
        assertThat(character.getDamageMultiplier(), is(134));
        assertThat(character.getTotalHealth(), is(720));
        assertThat(character.getTotalMagicPoint(), is(1440));
    }

    private void level97Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(44));
        assertThat(character.getAttributes().get(DEXTERITY), is(49));
        assertThat(character.getAttributes().get(CONSTITUTION), is(68));
        assertThat(character.getAttributes().get(AGILITY), is(49));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(78));
        assertThat(character.getAttributes().get(LORE), is(87));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(87));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(23));
        // and
        assertThat(character.getAttack(), is(236));
        assertThat(character.getDamageMultiplier(), is(136));
        assertThat(character.getTotalHealth(), is(720));
        assertThat(character.getTotalMagicPoint(), is(1457));
    }

    private void level98Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(44));
        assertThat(character.getAttributes().get(DEXTERITY), is(49));
        assertThat(character.getAttributes().get(CONSTITUTION), is(69));
        assertThat(character.getAttributes().get(AGILITY), is(49));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(79));
        assertThat(character.getAttributes().get(LORE), is(88));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(88));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(24));
        // and
        assertThat(character.getAttack(), is(236));
        assertThat(character.getDamageMultiplier(), is(136));
        assertThat(character.getTotalHealth(), is(730));
        assertThat(character.getTotalMagicPoint(), is(1475));
    }

    private void level99Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(44));
        assertThat(character.getAttributes().get(DEXTERITY), is(50));
        assertThat(character.getAttributes().get(CONSTITUTION), is(69));
        assertThat(character.getAttributes().get(AGILITY), is(50));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(80));
        assertThat(character.getAttributes().get(LORE), is(89));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(89));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(24));
        // and
        assertThat(character.getAttack(), is(238));
        assertThat(character.getDamageMultiplier(), is(137));
        assertThat(character.getTotalHealth(), is(730));
        assertThat(character.getTotalMagicPoint(), is(1492));
    }

    private void level100Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(45));
        assertThat(character.getAttributes().get(DEXTERITY), is(50));
        assertThat(character.getAttributes().get(CONSTITUTION), is(70));
        assertThat(character.getAttributes().get(AGILITY), is(50));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(80));
        assertThat(character.getAttributes().get(LORE), is(90));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(90));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(25));
        // and
        assertThat(character.getAttack(), is(238));
        assertThat(character.getDamageMultiplier(), is(139));
        assertThat(character.getTotalHealth(), is(740));
        assertThat(character.getTotalMagicPoint(), is(1505));

        // then
        upgradeCaste(ATALIAS_PRIEST);

        // expect
        assertThat(character.getCaste(), is(ATALIAS_PRIEST));
        // and
        assertThat(character.getAttack(), is(240));
        assertThat(character.getDamageMultiplier(), is(139));
        assertThat(character.getTotalHealth(), is(740));
        assertThat(character.getTotalMagicPoint(), is(1508));
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
