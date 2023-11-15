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
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.NIGHT_ELF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Disabled("May be killed in action")
public class WarriorToSorcererEnduranceTest extends EnduranceTestBase {

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(NIGHT_ELF)
                .gender(FEMALE)
                .defensiveGod(GETON)
                .name("Linili")
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

        } while (isAlive(character) && character.getLevel() < DUAL_CASTE_MAX_LEVEL);

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
    }

    private void level2Upgrade() {
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
    }

    private void level5Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(1));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));

        // then
        upgradeCaste(FIGHTER);

        // expect
        assertThat(character.getCaste(), is(FIGHTER));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getTotalHealth(), is(150));
        assertThat(character.getTotalMagicPoint(), is(23));
    }

    private void level6Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(1));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level7Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(AGILITY, 2);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(2));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level8Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(AGILITY, 2);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(2));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level9Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(11));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(11));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(2));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level10Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(9));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(9));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(2));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
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
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level12Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level13Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(13));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level14Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level15Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(15));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(15));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level16Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level17Upgrade() {
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
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(3));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level18Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(18));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(19));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(3));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level19Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(6));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(4));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
    }

    private void level20Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));

        // then
        upgradeCaste(PALADIN);

        // expect
        assertThat(character.getCaste(), is(PALADIN));
        // and
        assertThat(character.getAttack(), is(131));
        assertThat(character.getDamageMultiplier(), is(64));
        assertThat(character.getTotalHealth(), is(300));
        assertThat(character.getTotalMagicPoint(), is(99));
    }

    private void level21Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level22Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 3);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level23Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 2);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(17));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level24Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 2);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(23));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(18));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level25Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level26Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level27Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level28Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(27));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level29Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level30Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(28));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(28));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level31Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(29));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level32Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(30));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level33Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(30));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(11));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level34Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(31));
        assertThat(character.getAttributes().get(CONSTITUTION), is(31));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level35Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(27));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(32));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level36Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(27));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(33));
        assertThat(character.getAttributes().get(AGILITY), is(32));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(13));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level37Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(33));
        assertThat(character.getAttributes().get(AGILITY), is(33));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level38Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(34));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
    }

    private void level39Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(35));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(30));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(15));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
    }

    private void level40Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(35));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
    }

    private void level41Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(36));
        assertThat(character.getAttributes().get(CONSTITUTION), is(36));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(16));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
    }

    private void level42Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(36));
        assertThat(character.getAttributes().get(CONSTITUTION), is(37));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(32));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(17));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
    }

    private void level43Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(32));
        assertThat(character.getAttributes().get(DEXTERITY), is(37));
        assertThat(character.getAttributes().get(CONSTITUTION), is(37));
        assertThat(character.getAttributes().get(AGILITY), is(37));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
    }

    private void level44Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(33));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(38));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
    }

    private void level45Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(33));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(39));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(34));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(19));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
    }

    private void level46Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(34));
        assertThat(character.getAttributes().get(DEXTERITY), is(39));
        assertThat(character.getAttributes().get(CONSTITUTION), is(39));
        assertThat(character.getAttributes().get(AGILITY), is(39));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
    }

    private void level47Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(40));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
    }

    private void level48Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(36));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(21));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
    }

    private void level49Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(41));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
    }

    private void level50Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(42));
        assertThat(character.getAttributes().get(AGILITY), is(42));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
    }

    private void level51Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(43));
        assertThat(character.getAttributes().get(AGILITY), is(42));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(23));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
    }

    private void level52Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(38));
        assertThat(character.getAttributes().get(DEXTERITY), is(43));
        assertThat(character.getAttributes().get(CONSTITUTION), is(43));
        assertThat(character.getAttributes().get(AGILITY), is(43));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
    }

    private void level53Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(43));
        assertThat(character.getAttributes().get(CONSTITUTION), is(44));
        assertThat(character.getAttributes().get(AGILITY), is(43));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
    }

    private void level54Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(44));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(44));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
    }

    private void level55Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));

        // then
        upgradeCaste(SAGE);

        // expect
        assertThat(character.getCaste(), is(SAGE));
        // and
        assertThat(character.getAttack(), is(196));
        assertThat(character.getDamageMultiplier(), is(132));
        assertThat(character.getTotalHealth(), is(550));
        assertThat(character.getTotalMagicPoint(), is(500));
    }

    private void level56Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(30));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level57Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(30));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(30));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level58Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(30));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(35));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level59Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(35));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(35));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level60Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(35));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(40));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level61Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(40));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(40));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level62Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(40));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level63Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(45));
        assertThat(character.getAttributes().get(LORE), is(40));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level64Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(45));
        assertThat(character.getAttributes().get(LORE), is(45));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level65Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(45));
        assertThat(character.getAttributes().get(LORE), is(45));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(50));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level66Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(50));
        assertThat(character.getAttributes().get(LORE), is(45));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(50));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level67Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(50));
        assertThat(character.getAttributes().get(LORE), is(50));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(50));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level68Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(50));
        assertThat(character.getAttributes().get(LORE), is(50));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(55));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level69Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(55));
        assertThat(character.getAttributes().get(LORE), is(50));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(55));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level70Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(55));
        assertThat(character.getAttributes().get(LORE), is(55));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(55));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level71Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(55));
        assertThat(character.getAttributes().get(LORE), is(55));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(60));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level72Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(60));
        assertThat(character.getAttributes().get(LORE), is(55));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(60));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level73Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(60));
        assertThat(character.getAttributes().get(LORE), is(60));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(60));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level74Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(60));
        assertThat(character.getAttributes().get(LORE), is(60));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(65));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level75Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(65));
        assertThat(character.getAttributes().get(LORE), is(60));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(65));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level76Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(65));
        assertThat(character.getAttributes().get(LORE), is(65));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(65));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level77Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(65));
        assertThat(character.getAttributes().get(LORE), is(65));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(70));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level78Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(70));
        assertThat(character.getAttributes().get(LORE), is(65));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(70));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level79Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(70));
        assertThat(character.getAttributes().get(LORE), is(70));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(70));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level80Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(70));
        assertThat(character.getAttributes().get(LORE), is(70));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(75));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level81Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(75));
        assertThat(character.getAttributes().get(LORE), is(70));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(75));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level82Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(75));
        assertThat(character.getAttributes().get(LORE), is(75));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(75));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level83Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(75));
        assertThat(character.getAttributes().get(LORE), is(75));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(80));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level84Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(80));
        assertThat(character.getAttributes().get(LORE), is(75));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(80));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level85Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(80));
        assertThat(character.getAttributes().get(LORE), is(80));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(80));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level86Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(80));
        assertThat(character.getAttributes().get(LORE), is(80));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(85));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level87Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(85));
        assertThat(character.getAttributes().get(LORE), is(80));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(85));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level88Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(LORE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(85));
        assertThat(character.getAttributes().get(LORE), is(85));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(85));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level89Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(MENTAL_POWER, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(85));
        assertThat(character.getAttributes().get(LORE), is(85));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(90));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
    }

    private void level90Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(90));
        assertThat(character.getAttributes().get(LORE), is(85));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(90));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(196));
        assertThat(character.getDamageMultiplier(), is(132));
        assertThat(character.getTotalHealth(), is(550));
        assertThat(character.getTotalMagicPoint(), is(1500));
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
            Map.entry(90, this::level90Upgrade)
    );
}
