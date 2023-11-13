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
import static com.asgames.ataliasflame.domain.model.enums.God.ALATE;
import static com.asgames.ataliasflame.domain.model.enums.Race.NIGHT_ELF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Disabled("May be killed in action")
public class WarriorEnduranceTest extends EnduranceTestBase {

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(NIGHT_ELF)
                .gender(MALE)
                .defensiveGod(ALATE)
                .name("Bhakri")
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
        assertThat(character.getAttack(), is(82));
        assertThat(character.getDamageMultiplier(), is(3));
        assertThat(character.getTotalHealth(), is(110));
        assertThat(character.getTotalMagicPoint(), is(5));
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
        assertThat(character.getTotalHealth(), is(120));
        assertThat(character.getTotalMagicPoint(), is(5));
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
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(8));
        assertThat(character.getTotalHealth(), is(130));
        assertThat(character.getTotalMagicPoint(), is(5));
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
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(14));
        assertThat(character.getTotalHealth(), is(150));
        assertThat(character.getTotalMagicPoint(), is(5));
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
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getTotalHealth(), is(150));
        assertThat(character.getTotalMagicPoint(), is(23));

        // then
        upgradeCaste(FIGHTER);

        // expect
        assertThat(character.getCaste(), is(FIGHTER));
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
        // and
        assertThat(character.getAttack(), is(96));
        assertThat(character.getDamageMultiplier(), is(19));
        assertThat(character.getTotalHealth(), is(170));
        assertThat(character.getTotalMagicPoint(), is(23));
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
        // and
        assertThat(character.getAttack(), is(101));
        assertThat(character.getDamageMultiplier(), is(21));
        assertThat(character.getTotalHealth(), is(170));
        assertThat(character.getTotalMagicPoint(), is(25));
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
        // and
        assertThat(character.getAttack(), is(106));
        assertThat(character.getDamageMultiplier(), is(23));
        assertThat(character.getTotalHealth(), is(170));
        assertThat(character.getTotalMagicPoint(), is(35));
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
        // and
        assertThat(character.getAttack(), is(108));
        assertThat(character.getDamageMultiplier(), is(26));
        assertThat(character.getTotalHealth(), is(180));
        assertThat(character.getTotalMagicPoint(), is(40));
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
        // and
        assertThat(character.getAttack(), is(112));
        assertThat(character.getDamageMultiplier(), is(32));
        assertThat(character.getTotalHealth(), is(190));
        assertThat(character.getTotalMagicPoint(), is(40));
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
        // and
        assertThat(character.getAttack(), is(114));
        assertThat(character.getDamageMultiplier(), is(35));
        assertThat(character.getTotalHealth(), is(200));
        assertThat(character.getTotalMagicPoint(), is(42));
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
        // and
        assertThat(character.getAttack(), is(117));
        assertThat(character.getDamageMultiplier(), is(38));
        assertThat(character.getTotalHealth(), is(220));
        assertThat(character.getTotalMagicPoint(), is(42));
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
        // and
        assertThat(character.getAttack(), is(119));
        assertThat(character.getDamageMultiplier(), is(41));
        assertThat(character.getTotalHealth(), is(230));
        assertThat(character.getTotalMagicPoint(), is(47));
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
        // and
        assertThat(character.getAttack(), is(122));
        assertThat(character.getDamageMultiplier(), is(46));
        assertThat(character.getTotalHealth(), is(240));
        assertThat(character.getTotalMagicPoint(), is(47));
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
        // and
        assertThat(character.getAttack(), is(124));
        assertThat(character.getDamageMultiplier(), is(49));
        assertThat(character.getTotalHealth(), is(250));
        assertThat(character.getTotalMagicPoint(), is(49));
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
        // and
        assertThat(character.getAttack(), is(126));
        assertThat(character.getDamageMultiplier(), is(52));
        assertThat(character.getTotalHealth(), is(270));
        assertThat(character.getTotalMagicPoint(), is(49));
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
        // and
        assertThat(character.getAttack(), is(129));
        assertThat(character.getDamageMultiplier(), is(55));
        assertThat(character.getTotalHealth(), is(280));
        assertThat(character.getTotalMagicPoint(), is(59));
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
        // and
        assertThat(character.getAttack(), is(133));
        assertThat(character.getDamageMultiplier(), is(59));
        assertThat(character.getTotalHealth(), is(290));
        assertThat(character.getTotalMagicPoint(), is(64));
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
        // and
        assertThat(character.getAttack(), is(133));
        assertThat(character.getDamageMultiplier(), is(61));
        assertThat(character.getTotalHealth(), is(300));
        assertThat(character.getTotalMagicPoint(), is(81));
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
        // and
        assertThat(character.getAttack(), is(133));
        assertThat(character.getDamageMultiplier(), is(63));
        assertThat(character.getTotalHealth(), is(300));
        assertThat(character.getTotalMagicPoint(), is(99));

        // then
        upgradeCaste(PALADIN);

        // expect
        assertThat(character.getCaste(), is(PALADIN));
    }

    private void level21Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(135));
        assertThat(character.getDamageMultiplier(), is(66));
        assertThat(character.getTotalHealth(), is(310));
        assertThat(character.getTotalMagicPoint(), is(104));
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
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(138));
        assertThat(character.getDamageMultiplier(), is(69));
        assertThat(character.getTotalHealth(), is(320));
        assertThat(character.getTotalMagicPoint(), is(114));
    }

    private void level23Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(23));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(140));
        assertThat(character.getDamageMultiplier(), is(72));
        assertThat(character.getTotalHealth(), is(330));
        assertThat(character.getTotalMagicPoint(), is(119));
    }

    private void level24Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(142));
        assertThat(character.getDamageMultiplier(), is(75));
        assertThat(character.getTotalHealth(), is(340));
        assertThat(character.getTotalMagicPoint(), is(121));
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
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(145));
        assertThat(character.getDamageMultiplier(), is(78));
        assertThat(character.getTotalHealth(), is(350));
        assertThat(character.getTotalMagicPoint(), is(126));
    }

    private void level26Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(26));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(147));
        assertThat(character.getDamageMultiplier(), is(81));
        assertThat(character.getTotalHealth(), is(360));
        assertThat(character.getTotalMagicPoint(), is(136));
    }

    private void level27Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(27));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(27));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(151));
        assertThat(character.getDamageMultiplier(), is(85));
        assertThat(character.getTotalHealth(), is(370));
        assertThat(character.getTotalMagicPoint(), is(141));
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
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(28));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(28));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(154));
        assertThat(character.getDamageMultiplier(), is(88));
        assertThat(character.getTotalHealth(), is(380));
        assertThat(character.getTotalMagicPoint(), is(142));
    }

    private void level29Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(29));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(156));
        assertThat(character.getDamageMultiplier(), is(91));
        assertThat(character.getTotalHealth(), is(390));
        assertThat(character.getTotalMagicPoint(), is(144));
    }

    private void level30Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(30));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(158));
        assertThat(character.getDamageMultiplier(), is(94));
        assertThat(character.getTotalHealth(), is(400));
        assertThat(character.getTotalMagicPoint(), is(149));
    }

    private void level31Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(31));
        assertThat(character.getAttributes().get(CONSTITUTION), is(31));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(161));
        assertThat(character.getDamageMultiplier(), is(97));
        assertThat(character.getTotalHealth(), is(410));
        assertThat(character.getTotalMagicPoint(), is(159));
    }

    private void level32Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(32));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(32));
        assertThat(character.getAttributes().get(AGILITY), is(32));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(163));
        assertThat(character.getDamageMultiplier(), is(100));
        assertThat(character.getTotalHealth(), is(420));
        assertThat(character.getTotalMagicPoint(), is(164));
    }

    private void level33Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(33));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(33));
        assertThat(character.getAttributes().get(AGILITY), is(33));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(166));
        assertThat(character.getDamageMultiplier(), is(103));
        assertThat(character.getTotalHealth(), is(430));
        assertThat(character.getTotalMagicPoint(), is(166));
    }

    private void level34Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(34));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(34));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(14));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(168));
        assertThat(character.getDamageMultiplier(), is(106));
        assertThat(character.getTotalHealth(), is(440));
        assertThat(character.getTotalMagicPoint(), is(171));
    }

    private void level35Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(35));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(14));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(172));
        assertThat(character.getDamageMultiplier(), is(110));
        assertThat(character.getTotalHealth(), is(450));
        assertThat(character.getTotalMagicPoint(), is(181));
    }

    private void level36Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(36));
        assertThat(character.getAttributes().get(CONSTITUTION), is(36));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(174));
        assertThat(character.getDamageMultiplier(), is(113));
        assertThat(character.getTotalHealth(), is(460));
        assertThat(character.getTotalMagicPoint(), is(186));
    }

    private void level37Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(37));
        assertThat(character.getAttributes().get(CONSTITUTION), is(37));
        assertThat(character.getAttributes().get(AGILITY), is(37));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(177));
        assertThat(character.getDamageMultiplier(), is(116));
        assertThat(character.getTotalHealth(), is(470));
        assertThat(character.getTotalMagicPoint(), is(188));
    }

    private void level38Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(38));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(38));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(179));
        assertThat(character.getDamageMultiplier(), is(119));
        assertThat(character.getTotalHealth(), is(480));
        assertThat(character.getTotalMagicPoint(), is(198));
    }

    private void level39Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(39));
        assertThat(character.getAttributes().get(CONSTITUTION), is(39));
        assertThat(character.getAttributes().get(AGILITY), is(39));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(16));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(182));
        assertThat(character.getDamageMultiplier(), is(122));
        assertThat(character.getTotalHealth(), is(490));
        assertThat(character.getTotalMagicPoint(), is(203));
    }

    private void level40Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(40));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(16));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(184));
        assertThat(character.getDamageMultiplier(), is(125));
        assertThat(character.getTotalHealth(), is(500));
        assertThat(character.getTotalMagicPoint(), is(204));
    }

    private void level41Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(41));
        assertThat(character.getAttributes().get(DEXTERITY), is(41));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(17));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(186));
        assertThat(character.getDamageMultiplier(), is(128));
        assertThat(character.getTotalHealth(), is(510));
        assertThat(character.getTotalMagicPoint(), is(209));
    }

    private void level42Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(42));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(42));
        assertThat(character.getAttributes().get(AGILITY), is(42));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(17));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(189));
        assertThat(character.getDamageMultiplier(), is(131));
        assertThat(character.getTotalHealth(), is(520));
        assertThat(character.getTotalMagicPoint(), is(211));
    }

    private void level43Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(43));
        assertThat(character.getAttributes().get(DEXTERITY), is(43));
        assertThat(character.getAttributes().get(CONSTITUTION), is(43));
        assertThat(character.getAttributes().get(AGILITY), is(43));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(18));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(193));
        assertThat(character.getDamageMultiplier(), is(135));
        assertThat(character.getTotalHealth(), is(530));
        assertThat(character.getTotalMagicPoint(), is(216));
    }

    private void level44Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(44));
        assertThat(character.getAttributes().get(DEXTERITY), is(44));
        assertThat(character.getAttributes().get(CONSTITUTION), is(44));
        assertThat(character.getAttributes().get(AGILITY), is(44));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(18));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(11));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(195));
        assertThat(character.getDamageMultiplier(), is(138));
        assertThat(character.getTotalHealth(), is(540));
        assertThat(character.getTotalMagicPoint(), is(226));
    }

    private void level45Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(45));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(11));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(198));
        assertThat(character.getDamageMultiplier(), is(141));
        assertThat(character.getTotalHealth(), is(550));
        assertThat(character.getTotalMagicPoint(), is(231));
    }

    private void level46Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(46));
        assertThat(character.getAttributes().get(DEXTERITY), is(46));
        assertThat(character.getAttributes().get(CONSTITUTION), is(46));
        assertThat(character.getAttributes().get(AGILITY), is(46));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(11));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(200));
        assertThat(character.getDamageMultiplier(), is(144));
        assertThat(character.getTotalHealth(), is(560));
        assertThat(character.getTotalMagicPoint(), is(233));
    }

    private void level47Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(47));
        assertThat(character.getAttributes().get(DEXTERITY), is(47));
        assertThat(character.getAttributes().get(CONSTITUTION), is(47));
        assertThat(character.getAttributes().get(AGILITY), is(47));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(202));
        assertThat(character.getDamageMultiplier(), is(147));
        assertThat(character.getTotalHealth(), is(570));
        assertThat(character.getTotalMagicPoint(), is(243));
    }

    private void level48Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(48));
        assertThat(character.getAttributes().get(DEXTERITY), is(48));
        assertThat(character.getAttributes().get(CONSTITUTION), is(48));
        assertThat(character.getAttributes().get(AGILITY), is(48));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(205));
        assertThat(character.getDamageMultiplier(), is(150));
        assertThat(character.getTotalHealth(), is(580));
        assertThat(character.getTotalMagicPoint(), is(248));
    }

    private void level49Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(49));
        assertThat(character.getAttributes().get(DEXTERITY), is(49));
        assertThat(character.getAttributes().get(CONSTITUTION), is(49));
        assertThat(character.getAttributes().get(AGILITY), is(49));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(207));
        assertThat(character.getDamageMultiplier(), is(153));
        assertThat(character.getTotalHealth(), is(590));
        assertThat(character.getTotalMagicPoint(), is(250));
    }

    private void level50Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(50));
        assertThat(character.getAttributes().get(DEXTERITY), is(50));
        assertThat(character.getAttributes().get(CONSTITUTION), is(50));
        assertThat(character.getAttributes().get(AGILITY), is(50));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(211));
        assertThat(character.getDamageMultiplier(), is(157));
        assertThat(character.getTotalHealth(), is(600));
        assertThat(character.getTotalMagicPoint(), is(251));

        // then
        upgradeCaste(GRANDMASTER);

        // expect
        assertThat(character.getCaste(), is(GRANDMASTER));
    }

    private void level51Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(51));
        assertThat(character.getAttributes().get(DEXTERITY), is(51));
        assertThat(character.getAttributes().get(CONSTITUTION), is(51));
        assertThat(character.getAttributes().get(AGILITY), is(51));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(214));
        assertThat(character.getDamageMultiplier(), is(160));
        assertThat(character.getTotalHealth(), is(600));
        assertThat(character.getTotalMagicPoint(), is(256));
    }

    private void level52Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(52));
        assertThat(character.getAttributes().get(DEXTERITY), is(52));
        assertThat(character.getAttributes().get(CONSTITUTION), is(52));
        assertThat(character.getAttributes().get(AGILITY), is(52));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(13));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(216));
        assertThat(character.getDamageMultiplier(), is(163));
        assertThat(character.getTotalHealth(), is(610));
        assertThat(character.getTotalMagicPoint(), is(266));
    }

    private void level53Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(53));
        assertThat(character.getAttributes().get(DEXTERITY), is(53));
        assertThat(character.getAttributes().get(CONSTITUTION), is(53));
        assertThat(character.getAttributes().get(AGILITY), is(53));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(13));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(218));
        assertThat(character.getDamageMultiplier(), is(166));
        assertThat(character.getTotalHealth(), is(620));
        assertThat(character.getTotalMagicPoint(), is(271));
    }

    private void level54Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(54));
        assertThat(character.getAttributes().get(DEXTERITY), is(54));
        assertThat(character.getAttributes().get(CONSTITUTION), is(54));
        assertThat(character.getAttributes().get(AGILITY), is(54));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(221));
        assertThat(character.getDamageMultiplier(), is(169));
        assertThat(character.getTotalHealth(), is(630));
        assertThat(character.getTotalMagicPoint(), is(281));
    }

    private void level55Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(55));
        assertThat(character.getAttributes().get(DEXTERITY), is(55));
        assertThat(character.getAttributes().get(CONSTITUTION), is(55));
        assertThat(character.getAttributes().get(AGILITY), is(55));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(223));
        assertThat(character.getDamageMultiplier(), is(172));
        assertThat(character.getTotalHealth(), is(640));
        assertThat(character.getTotalMagicPoint(), is(283));
    }

    private void level56Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(56));
        assertThat(character.getAttributes().get(DEXTERITY), is(56));
        assertThat(character.getAttributes().get(CONSTITUTION), is(56));
        assertThat(character.getAttributes().get(AGILITY), is(56));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(226));
        assertThat(character.getDamageMultiplier(), is(175));
        assertThat(character.getTotalHealth(), is(650));
        assertThat(character.getTotalMagicPoint(), is(288));
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
        assertThat(character.getAttributes().get(STRENGTH), is(57));
        assertThat(character.getAttributes().get(DEXTERITY), is(57));
        assertThat(character.getAttributes().get(CONSTITUTION), is(57));
        assertThat(character.getAttributes().get(AGILITY), is(57));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(228));
        assertThat(character.getDamageMultiplier(), is(178));
        assertThat(character.getTotalHealth(), is(660));
        assertThat(character.getTotalMagicPoint(), is(289));
    }

    private void level58Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(58));
        assertThat(character.getAttributes().get(DEXTERITY), is(58));
        assertThat(character.getAttributes().get(CONSTITUTION), is(58));
        assertThat(character.getAttributes().get(AGILITY), is(58));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(15));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(232));
        assertThat(character.getDamageMultiplier(), is(182));
        assertThat(character.getTotalHealth(), is(670));
        assertThat(character.getTotalMagicPoint(), is(299));
    }

    private void level59Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(59));
        assertThat(character.getAttributes().get(DEXTERITY), is(59));
        assertThat(character.getAttributes().get(CONSTITUTION), is(59));
        assertThat(character.getAttributes().get(AGILITY), is(59));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(15));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(234));
        assertThat(character.getDamageMultiplier(), is(185));
        assertThat(character.getTotalHealth(), is(680));
        assertThat(character.getTotalMagicPoint(), is(304));
    }

    private void level60Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(60));
        assertThat(character.getAttributes().get(DEXTERITY), is(60));
        assertThat(character.getAttributes().get(CONSTITUTION), is(60));
        assertThat(character.getAttributes().get(AGILITY), is(60));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(15));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(237));
        assertThat(character.getDamageMultiplier(), is(188));
        assertThat(character.getTotalHealth(), is(690));
        assertThat(character.getTotalMagicPoint(), is(306));
    }

    private void level61Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(61));
        assertThat(character.getAttributes().get(DEXTERITY), is(61));
        assertThat(character.getAttributes().get(CONSTITUTION), is(61));
        assertThat(character.getAttributes().get(AGILITY), is(61));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(15));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(239));
        assertThat(character.getDamageMultiplier(), is(191));
        assertThat(character.getTotalHealth(), is(700));
        assertThat(character.getTotalMagicPoint(), is(311));
    }

    private void level62Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(62));
        assertThat(character.getAttributes().get(DEXTERITY), is(62));
        assertThat(character.getAttributes().get(CONSTITUTION), is(62));
        assertThat(character.getAttributes().get(AGILITY), is(62));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(242));
        assertThat(character.getDamageMultiplier(), is(194));
        assertThat(character.getTotalHealth(), is(710));
        assertThat(character.getTotalMagicPoint(), is(321));
    }

    private void level63Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(63));
        assertThat(character.getAttributes().get(DEXTERITY), is(63));
        assertThat(character.getAttributes().get(CONSTITUTION), is(63));
        assertThat(character.getAttributes().get(AGILITY), is(63));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(244));
        assertThat(character.getDamageMultiplier(), is(197));
        assertThat(character.getTotalHealth(), is(720));
        assertThat(character.getTotalMagicPoint(), is(326));
    }

    private void level64Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(64));
        assertThat(character.getAttributes().get(DEXTERITY), is(64));
        assertThat(character.getAttributes().get(CONSTITUTION), is(64));
        assertThat(character.getAttributes().get(AGILITY), is(64));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        assertThat(character.getAttributes().get(LORE), is(16));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(246));
        assertThat(character.getDamageMultiplier(), is(200));
        assertThat(character.getTotalHealth(), is(730));
        assertThat(character.getTotalMagicPoint(), is(328));
    }

    private void level65Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(65));
        assertThat(character.getAttributes().get(DEXTERITY), is(65));
        assertThat(character.getAttributes().get(CONSTITUTION), is(65));
        assertThat(character.getAttributes().get(AGILITY), is(65));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(16));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(249));
        assertThat(character.getDamageMultiplier(), is(203));
        assertThat(character.getTotalHealth(), is(740));
        assertThat(character.getTotalMagicPoint(), is(333));
    }

    private void level66Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(66));
        assertThat(character.getAttributes().get(DEXTERITY), is(66));
        assertThat(character.getAttributes().get(CONSTITUTION), is(66));
        assertThat(character.getAttributes().get(AGILITY), is(66));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(16));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(17));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(253));
        assertThat(character.getDamageMultiplier(), is(207));
        assertThat(character.getTotalHealth(), is(750));
        assertThat(character.getTotalMagicPoint(), is(333));
    }

    private void level67Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(67));
        assertThat(character.getAttributes().get(DEXTERITY), is(67));
        assertThat(character.getAttributes().get(CONSTITUTION), is(67));
        assertThat(character.getAttributes().get(AGILITY), is(67));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        assertThat(character.getAttributes().get(LORE), is(16));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(17));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(255));
        assertThat(character.getDamageMultiplier(), is(210));
        assertThat(character.getTotalHealth(), is(760));
        assertThat(character.getTotalMagicPoint(), is(338));
    }

    private void level68Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(68));
        assertThat(character.getAttributes().get(DEXTERITY), is(68));
        assertThat(character.getAttributes().get(CONSTITUTION), is(68));
        assertThat(character.getAttributes().get(AGILITY), is(68));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(17));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(258));
        assertThat(character.getDamageMultiplier(), is(213));
        assertThat(character.getTotalHealth(), is(770));
        assertThat(character.getTotalMagicPoint(), is(340));
    }

    private void level69Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(69));
        assertThat(character.getAttributes().get(DEXTERITY), is(69));
        assertThat(character.getAttributes().get(CONSTITUTION), is(69));
        assertThat(character.getAttributes().get(AGILITY), is(69));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(17));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(260));
        assertThat(character.getDamageMultiplier(), is(216));
        assertThat(character.getTotalHealth(), is(780));
        assertThat(character.getTotalMagicPoint(), is(345));
    }

    private void level70Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(70));
        assertThat(character.getAttributes().get(DEXTERITY), is(70));
        assertThat(character.getAttributes().get(CONSTITUTION), is(70));
        assertThat(character.getAttributes().get(AGILITY), is(70));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(262));
        assertThat(character.getDamageMultiplier(), is(219));
        assertThat(character.getTotalHealth(), is(790));
        assertThat(character.getTotalMagicPoint(), is(355));
    }

    private void level71Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(71));
        assertThat(character.getAttributes().get(DEXTERITY), is(71));
        assertThat(character.getAttributes().get(CONSTITUTION), is(71));
        assertThat(character.getAttributes().get(AGILITY), is(71));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(30));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(265));
        assertThat(character.getDamageMultiplier(), is(222));
        assertThat(character.getTotalHealth(), is(800));
        assertThat(character.getTotalMagicPoint(), is(360));
    }

    private void level72Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(72));
        assertThat(character.getAttributes().get(DEXTERITY), is(72));
        assertThat(character.getAttributes().get(CONSTITUTION), is(72));
        assertThat(character.getAttributes().get(AGILITY), is(72));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(30));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(267));
        assertThat(character.getDamageMultiplier(), is(225));
        assertThat(character.getTotalHealth(), is(810));
        assertThat(character.getTotalMagicPoint(), is(362));
    }

    private void level73Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(73));
        assertThat(character.getAttributes().get(DEXTERITY), is(73));
        assertThat(character.getAttributes().get(CONSTITUTION), is(73));
        assertThat(character.getAttributes().get(AGILITY), is(73));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(270));
        assertThat(character.getDamageMultiplier(), is(228));
        assertThat(character.getTotalHealth(), is(820));
        assertThat(character.getTotalMagicPoint(), is(367));
    }

    private void level74Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(74));
        assertThat(character.getAttributes().get(DEXTERITY), is(74));
        assertThat(character.getAttributes().get(CONSTITUTION), is(74));
        assertThat(character.getAttributes().get(AGILITY), is(74));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(19));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(274));
        assertThat(character.getDamageMultiplier(), is(232));
        assertThat(character.getTotalHealth(), is(830));
        assertThat(character.getTotalMagicPoint(), is(377));
    }

    private void level75Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(75));
        assertThat(character.getAttributes().get(DEXTERITY), is(75));
        assertThat(character.getAttributes().get(CONSTITUTION), is(75));
        assertThat(character.getAttributes().get(AGILITY), is(75));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(32));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(19));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(276));
        assertThat(character.getDamageMultiplier(), is(235));
        assertThat(character.getTotalHealth(), is(840));
        assertThat(character.getTotalMagicPoint(), is(382));
    }

    private void level76Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(76));
        assertThat(character.getAttributes().get(DEXTERITY), is(76));
        assertThat(character.getAttributes().get(CONSTITUTION), is(76));
        assertThat(character.getAttributes().get(AGILITY), is(76));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(32));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(19));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(278));
        assertThat(character.getDamageMultiplier(), is(238));
        assertThat(character.getTotalHealth(), is(850));
        assertThat(character.getTotalMagicPoint(), is(384));
    }

    private void level77Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(77));
        assertThat(character.getAttributes().get(DEXTERITY), is(77));
        assertThat(character.getAttributes().get(CONSTITUTION), is(77));
        assertThat(character.getAttributes().get(AGILITY), is(77));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(32));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(281));
        assertThat(character.getDamageMultiplier(), is(241));
        assertThat(character.getTotalHealth(), is(860));
        assertThat(character.getTotalMagicPoint(), is(394));
    }

    private void level78Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(78));
        assertThat(character.getAttributes().get(DEXTERITY), is(78));
        assertThat(character.getAttributes().get(CONSTITUTION), is(78));
        assertThat(character.getAttributes().get(AGILITY), is(78));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(283));
        assertThat(character.getDamageMultiplier(), is(244));
        assertThat(character.getTotalHealth(), is(870));
        assertThat(character.getTotalMagicPoint(), is(399));
    }

    private void level79Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(79));
        assertThat(character.getAttributes().get(DEXTERITY), is(79));
        assertThat(character.getAttributes().get(CONSTITUTION), is(79));
        assertThat(character.getAttributes().get(AGILITY), is(79));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(6));
        // and
        assertThat(character.getAttack(), is(286));
        assertThat(character.getDamageMultiplier(), is(247));
        assertThat(character.getTotalHealth(), is(880));
        assertThat(character.getTotalMagicPoint(), is(401));
    }

    private void level80Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(80));
        assertThat(character.getAttributes().get(DEXTERITY), is(80));
        assertThat(character.getAttributes().get(CONSTITUTION), is(80));
        assertThat(character.getAttributes().get(AGILITY), is(80));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(288));
        assertThat(character.getDamageMultiplier(), is(250));
        assertThat(character.getTotalHealth(), is(890));
        assertThat(character.getTotalMagicPoint(), is(402));
    }

    private void level81Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(81));
        assertThat(character.getAttributes().get(DEXTERITY), is(81));
        assertThat(character.getAttributes().get(CONSTITUTION), is(81));
        assertThat(character.getAttributes().get(AGILITY), is(81));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(34));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(292));
        assertThat(character.getDamageMultiplier(), is(254));
        assertThat(character.getTotalHealth(), is(900));
        assertThat(character.getTotalMagicPoint(), is(407));
    }

    private void level82Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(82));
        assertThat(character.getAttributes().get(DEXTERITY), is(82));
        assertThat(character.getAttributes().get(CONSTITUTION), is(82));
        assertThat(character.getAttributes().get(AGILITY), is(82));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(34));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(21));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(294));
        assertThat(character.getDamageMultiplier(), is(257));
        assertThat(character.getTotalHealth(), is(910));
        assertThat(character.getTotalMagicPoint(), is(417));
    }

    private void level83Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(83));
        assertThat(character.getAttributes().get(DEXTERITY), is(83));
        assertThat(character.getAttributes().get(CONSTITUTION), is(83));
        assertThat(character.getAttributes().get(AGILITY), is(83));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(21));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(297));
        assertThat(character.getDamageMultiplier(), is(260));
        assertThat(character.getTotalHealth(), is(920));
        assertThat(character.getTotalMagicPoint(), is(422));
    }

    private void level84Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(84));
        assertThat(character.getAttributes().get(DEXTERITY), is(84));
        assertThat(character.getAttributes().get(CONSTITUTION), is(84));
        assertThat(character.getAttributes().get(AGILITY), is(84));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(21));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(299));
        assertThat(character.getDamageMultiplier(), is(263));
        assertThat(character.getTotalHealth(), is(930));
        assertThat(character.getTotalMagicPoint(), is(424));
    }

    private void level85Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(85));
        assertThat(character.getAttributes().get(DEXTERITY), is(85));
        assertThat(character.getAttributes().get(CONSTITUTION), is(85));
        assertThat(character.getAttributes().get(AGILITY), is(85));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(302));
        assertThat(character.getDamageMultiplier(), is(266));
        assertThat(character.getTotalHealth(), is(940));
        assertThat(character.getTotalMagicPoint(), is(434));
    }

    private void level86Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(86));
        assertThat(character.getAttributes().get(DEXTERITY), is(86));
        assertThat(character.getAttributes().get(CONSTITUTION), is(86));
        assertThat(character.getAttributes().get(AGILITY), is(86));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(36));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(304));
        assertThat(character.getDamageMultiplier(), is(269));
        assertThat(character.getTotalHealth(), is(950));
        assertThat(character.getTotalMagicPoint(), is(439));
    }

    private void level87Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(87));
        assertThat(character.getAttributes().get(DEXTERITY), is(87));
        assertThat(character.getAttributes().get(CONSTITUTION), is(87));
        assertThat(character.getAttributes().get(AGILITY), is(87));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(306));
        assertThat(character.getDamageMultiplier(), is(272));
        assertThat(character.getTotalHealth(), is(960));
        assertThat(character.getTotalMagicPoint(), is(444));
    }

    private void level88Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(88));
        assertThat(character.getAttributes().get(DEXTERITY), is(88));
        assertThat(character.getAttributes().get(CONSTITUTION), is(88));
        assertThat(character.getAttributes().get(AGILITY), is(88));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(309));
        assertThat(character.getDamageMultiplier(), is(275));
        assertThat(character.getTotalHealth(), is(970));
        assertThat(character.getTotalMagicPoint(), is(446));
    }

    private void level89Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(89));
        assertThat(character.getAttributes().get(DEXTERITY), is(89));
        assertThat(character.getAttributes().get(CONSTITUTION), is(89));
        assertThat(character.getAttributes().get(AGILITY), is(89));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(23));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(313));
        assertThat(character.getDamageMultiplier(), is(279));
        assertThat(character.getTotalHealth(), is(980));
        assertThat(character.getTotalMagicPoint(), is(456));
    }

    private void level90Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(90));
        assertThat(character.getAttributes().get(DEXTERITY), is(90));
        assertThat(character.getAttributes().get(CONSTITUTION), is(90));
        assertThat(character.getAttributes().get(AGILITY), is(90));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(23));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(315));
        assertThat(character.getDamageMultiplier(), is(282));
        assertThat(character.getTotalHealth(), is(990));
        assertThat(character.getTotalMagicPoint(), is(461));
    }

    private void level91Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(91));
        assertThat(character.getAttributes().get(DEXTERITY), is(91));
        assertThat(character.getAttributes().get(CONSTITUTION), is(91));
        assertThat(character.getAttributes().get(AGILITY), is(91));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(23));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(7));
        // and
        assertThat(character.getAttack(), is(318));
        assertThat(character.getDamageMultiplier(), is(285));
        assertThat(character.getTotalHealth(), is(1000));
        assertThat(character.getTotalMagicPoint(), is(463));
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
        assertThat(character.getAttributes().get(STRENGTH), is(92));
        assertThat(character.getAttributes().get(DEXTERITY), is(92));
        assertThat(character.getAttributes().get(CONSTITUTION), is(92));
        assertThat(character.getAttributes().get(AGILITY), is(92));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(23));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(320));
        assertThat(character.getDamageMultiplier(), is(288));
        assertThat(character.getTotalHealth(), is(1010));
        assertThat(character.getTotalMagicPoint(), is(464));
    }

    private void level93Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(93));
        assertThat(character.getAttributes().get(DEXTERITY), is(93));
        assertThat(character.getAttributes().get(CONSTITUTION), is(93));
        assertThat(character.getAttributes().get(AGILITY), is(93));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(322));
        assertThat(character.getDamageMultiplier(), is(291));
        assertThat(character.getTotalHealth(), is(1020));
        assertThat(character.getTotalMagicPoint(), is(474));
    }

    private void level94Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(94));
        assertThat(character.getAttributes().get(DEXTERITY), is(94));
        assertThat(character.getAttributes().get(CONSTITUTION), is(94));
        assertThat(character.getAttributes().get(AGILITY), is(94));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(325));
        assertThat(character.getDamageMultiplier(), is(294));
        assertThat(character.getTotalHealth(), is(1030));
        assertThat(character.getTotalMagicPoint(), is(479));
    }

    private void level95Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(95));
        assertThat(character.getAttributes().get(DEXTERITY), is(95));
        assertThat(character.getAttributes().get(CONSTITUTION), is(95));
        assertThat(character.getAttributes().get(AGILITY), is(95));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(8));
        // and
        assertThat(character.getAttack(), is(327));
        assertThat(character.getDamageMultiplier(), is(297));
        assertThat(character.getTotalHealth(), is(1040));
        assertThat(character.getTotalMagicPoint(), is(481));
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
        assertThat(character.getAttributes().get(STRENGTH), is(96));
        assertThat(character.getAttributes().get(DEXTERITY), is(96));
        assertThat(character.getAttributes().get(CONSTITUTION), is(96));
        assertThat(character.getAttributes().get(AGILITY), is(96));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(330));
        assertThat(character.getDamageMultiplier(), is(300));
        assertThat(character.getTotalHealth(), is(1050));
        assertThat(character.getTotalMagicPoint(), is(482));
    }

    private void level97Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(97));
        assertThat(character.getAttributes().get(DEXTERITY), is(97));
        assertThat(character.getAttributes().get(CONSTITUTION), is(97));
        assertThat(character.getAttributes().get(AGILITY), is(97));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(334));
        assertThat(character.getDamageMultiplier(), is(304));
        assertThat(character.getTotalHealth(), is(1060));
        assertThat(character.getTotalMagicPoint(), is(492));
    }

    private void level98Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(98));
        assertThat(character.getAttributes().get(DEXTERITY), is(98));
        assertThat(character.getAttributes().get(CONSTITUTION), is(98));
        assertThat(character.getAttributes().get(AGILITY), is(98));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(336));
        assertThat(character.getDamageMultiplier(), is(307));
        assertThat(character.getTotalHealth(), is(1070));
        assertThat(character.getTotalMagicPoint(), is(497));
    }

    private void level99Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(99));
        assertThat(character.getAttributes().get(DEXTERITY), is(99));
        assertThat(character.getAttributes().get(CONSTITUTION), is(99));
        assertThat(character.getAttributes().get(AGILITY), is(99));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(9));
        // and
        assertThat(character.getAttack(), is(338));
        assertThat(character.getDamageMultiplier(), is(310));
        assertThat(character.getTotalHealth(), is(1080));
        assertThat(character.getTotalMagicPoint(), is(499));
    }

    private void level100Upgrade() {
        assertThat(character.getAttributePoints(), is(greaterThanOrEqualTo(5)));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributes().get(STRENGTH), is(100));
        assertThat(character.getAttributes().get(DEXTERITY), is(100));
        assertThat(character.getAttributes().get(CONSTITUTION), is(100));
        assertThat(character.getAttributes().get(AGILITY), is(100));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(10));
        // and
        assertThat(character.getAttack(), is(341));
        assertThat(character.getDamageMultiplier(), is(313));
        assertThat(character.getTotalHealth(), is(1090));
        assertThat(character.getTotalMagicPoint(), is(500));

        // then
        upgradeCaste(TITAN);

        // expect
        assertThat(character.getCaste(), is(TITAN));
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
