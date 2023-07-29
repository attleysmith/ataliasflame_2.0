package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.MONK;
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.God.RUNID;
import static com.asgames.ataliasflame.domain.model.enums.Race.DWARF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Disabled("May be killed in action")
@SpringBootTest
public class ClericEnduranceTest extends EnduranceTestBase {

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(FEMALE)
                .defensiveGod(RUNID)
                .name(characterName)
                .build();
        characterService.createCharacter(characterInput);
        // and
        Character character = characterService.getCharacter(characterName);

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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(2));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 2);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(2));
        assertThat(character.getAttributes().get(DEXTERITY), is(1));
        assertThat(character.getAttributes().get(CONSTITUTION), is(1));
        assertThat(character.getAttributes().get(AGILITY), is(1));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        assertThat(character.getAttributes().get(LORE), is(2));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(1));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(82));
        assertThat(character.getDamageMultiplier(), is(5));
        assertThat(character.getTotalHealth(), is(110));
        assertThat(character.getTotalMagicPoint(), is(24));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(3));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(2));
        assertThat(character.getAttributes().get(DEXTERITY), is(2));
        assertThat(character.getAttributes().get(CONSTITUTION), is(1));
        assertThat(character.getAttributes().get(AGILITY), is(2));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        assertThat(character.getAttributes().get(LORE), is(3));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(2));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(85));
        assertThat(character.getDamageMultiplier(), is(6));
        assertThat(character.getTotalHealth(), is(110));
        assertThat(character.getTotalMagicPoint(), is(41));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(4));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(2));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(2));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(4));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(3));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(85));
        assertThat(character.getDamageMultiplier(), is(8));
        assertThat(character.getTotalHealth(), is(120));
        assertThat(character.getTotalMagicPoint(), is(58));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(5));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(5));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(4));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(86));
        assertThat(character.getDamageMultiplier(), is(9));
        assertThat(character.getTotalHealth(), is(120));
        assertThat(character.getTotalMagicPoint(), is(61));

        // then
        character = upgradeCaste(MONK);

        // expect
        assertThat(character.getCaste(), is(MONK));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(6));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(4));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(4));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        assertThat(character.getAttributes().get(LORE), is(6));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(5));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(89));
        assertThat(character.getDamageMultiplier(), is(10));
        assertThat(character.getTotalHealth(), is(140));
        assertThat(character.getTotalMagicPoint(), is(73));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(7));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(4));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(7));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(6));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(90));
        assertThat(character.getDamageMultiplier(), is(11));
        assertThat(character.getTotalHealth(), is(150));
        assertThat(character.getTotalMagicPoint(), is(90));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(8));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        assertThat(character.getAttributes().get(LORE), is(8));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(7));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(91));
        assertThat(character.getDamageMultiplier(), is(13));
        assertThat(character.getTotalHealth(), is(150));
        assertThat(character.getTotalMagicPoint(), is(102));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(9));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(6));
        assertThat(character.getAttributes().get(LORE), is(9));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(8));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDamageMultiplier(), is(13));
        assertThat(character.getTotalHealth(), is(160));
        assertThat(character.getTotalMagicPoint(), is(119));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(10));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(7));
        assertThat(character.getAttributes().get(CONSTITUTION), is(6));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(6));
        assertThat(character.getAttributes().get(LORE), is(10));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(9));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(18));
        assertThat(character.getTotalHealth(), is(170));
        assertThat(character.getTotalMagicPoint(), is(131));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(11));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(7));
        assertThat(character.getAttributes().get(CONSTITUTION), is(6));
        assertThat(character.getAttributes().get(AGILITY), is(7));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        assertThat(character.getAttributes().get(LORE), is(11));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(10));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(20));
        assertThat(character.getTotalHealth(), is(170));
        assertThat(character.getTotalMagicPoint(), is(148));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(12));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(7));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        assertThat(character.getAttributes().get(LORE), is(12));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(11));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(96));
        assertThat(character.getDamageMultiplier(), is(21));
        assertThat(character.getTotalHealth(), is(180));
        assertThat(character.getTotalMagicPoint(), is(155));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(13));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        assertThat(character.getAttributes().get(LORE), is(13));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(12));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(96));
        assertThat(character.getDamageMultiplier(), is(21));
        assertThat(character.getTotalHealth(), is(200));
        assertThat(character.getTotalMagicPoint(), is(172));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(14));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(9));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        assertThat(character.getAttributes().get(LORE), is(14));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(13));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(98));
        assertThat(character.getDamageMultiplier(), is(22));
        assertThat(character.getTotalHealth(), is(200));
        assertThat(character.getTotalMagicPoint(), is(189));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(15));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(9));
        assertThat(character.getAttributes().get(CONSTITUTION), is(9));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        assertThat(character.getAttributes().get(LORE), is(15));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(14));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(98));
        assertThat(character.getDamageMultiplier(), is(24));
        assertThat(character.getTotalHealth(), is(210));
        assertThat(character.getTotalMagicPoint(), is(206));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(16));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        assertThat(character.getAttributes().get(LORE), is(16));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(15));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(100));
        assertThat(character.getDamageMultiplier(), is(25));
        assertThat(character.getTotalHealth(), is(220));
        assertThat(character.getTotalMagicPoint(), is(223));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(17));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        assertThat(character.getAttributes().get(LORE), is(17));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(16));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(101));
        assertThat(character.getDamageMultiplier(), is(27));
        assertThat(character.getTotalHealth(), is(220));
        assertThat(character.getTotalMagicPoint(), is(240));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(18));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(11));
        assertThat(character.getAttributes().get(CONSTITUTION), is(11));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(14));
        assertThat(character.getAttributes().get(LORE), is(18));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(17));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(102));
        assertThat(character.getDamageMultiplier(), is(28));
        assertThat(character.getTotalHealth(), is(230));
        assertThat(character.getTotalMagicPoint(), is(247));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(19));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(11));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(11));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        assertThat(character.getAttributes().get(LORE), is(19));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(1));
        // and
        assertThat(character.getAttack(), is(103));
        assertThat(character.getDamageMultiplier(), is(28));
        assertThat(character.getTotalHealth(), is(240));
        assertThat(character.getTotalMagicPoint(), is(264));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(20));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(16));
        assertThat(character.getAttributes().get(LORE), is(20));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(18));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(106));
        assertThat(character.getDamageMultiplier(), is(29));
        assertThat(character.getTotalHealth(), is(240));
        assertThat(character.getTotalMagicPoint(), is(272));

        // TODO: upgrade caste

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(21));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(9));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(17));
        assertThat(character.getAttributes().get(LORE), is(21));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(19));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(107));
        assertThat(character.getDamageMultiplier(), is(32));
        assertThat(character.getTotalHealth(), is(240));
        assertThat(character.getTotalMagicPoint(), is(289));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(22));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(9));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(13));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(18));
        assertThat(character.getAttributes().get(LORE), is(22));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(20));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(107));
        assertThat(character.getDamageMultiplier(), is(32));
        assertThat(character.getTotalHealth(), is(260));
        assertThat(character.getTotalMagicPoint(), is(306));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(23));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(13));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(19));
        assertThat(character.getAttributes().get(LORE), is(23));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(21));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(109));
        assertThat(character.getDamageMultiplier(), is(35));
        assertThat(character.getTotalHealth(), is(260));
        assertThat(character.getTotalMagicPoint(), is(323));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(24));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        assertThat(character.getAttributes().get(LORE), is(24));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(22));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(110));
        assertThat(character.getDamageMultiplier(), is(35));
        assertThat(character.getTotalHealth(), is(270));
        assertThat(character.getTotalMagicPoint(), is(340));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(25));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        assertThat(character.getAttributes().get(LORE), is(25));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(23));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(111));
        assertThat(character.getDamageMultiplier(), is(38));
        assertThat(character.getTotalHealth(), is(270));
        assertThat(character.getTotalMagicPoint(), is(357));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(26));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(15));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        assertThat(character.getAttributes().get(LORE), is(26));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(24));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(112));
        assertThat(character.getDamageMultiplier(), is(38));
        assertThat(character.getTotalHealth(), is(280));
        assertThat(character.getTotalMagicPoint(), is(364));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(27));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(15));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        assertThat(character.getAttributes().get(LORE), is(27));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(25));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(112));
        assertThat(character.getDamageMultiplier(), is(40));
        assertThat(character.getTotalHealth(), is(280));
        assertThat(character.getTotalMagicPoint(), is(381));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(28));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        assertThat(character.getAttributes().get(LORE), is(28));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(26));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(113));
        assertThat(character.getDamageMultiplier(), is(40));
        assertThat(character.getTotalHealth(), is(290));
        assertThat(character.getTotalMagicPoint(), is(398));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(29));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(13));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        assertThat(character.getAttributes().get(LORE), is(29));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(27));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(114));
        assertThat(character.getDamageMultiplier(), is(43));
        assertThat(character.getTotalHealth(), is(290));
        assertThat(character.getTotalMagicPoint(), is(420));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(30));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(13));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        assertThat(character.getAttributes().get(LORE), is(30));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(28));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(115));
        assertThat(character.getDamageMultiplier(), is(43));
        assertThat(character.getTotalHealth(), is(300));
        assertThat(character.getTotalMagicPoint(), is(437));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(31));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        assertThat(character.getAttributes().get(LORE), is(31));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(29));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(117));
        assertThat(character.getDamageMultiplier(), is(46));
        assertThat(character.getTotalHealth(), is(300));
        assertThat(character.getTotalMagicPoint(), is(454));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(32));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        assertThat(character.getAttributes().get(LORE), is(32));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(30));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(117));
        assertThat(character.getDamageMultiplier(), is(46));
        assertThat(character.getTotalHealth(), is(320));
        assertThat(character.getTotalMagicPoint(), is(471));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(33));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(15));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        assertThat(character.getAttributes().get(LORE), is(33));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(31));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(51));
        assertThat(character.getTotalHealth(), is(320));
        assertThat(character.getTotalMagicPoint(), is(478));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(34));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(15));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(19));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(30));
        assertThat(character.getAttributes().get(LORE), is(34));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(32));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(119));
        assertThat(character.getDamageMultiplier(), is(51));
        assertThat(character.getTotalHealth(), is(330));
        assertThat(character.getTotalMagicPoint(), is(495));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(35));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(19));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        assertThat(character.getAttributes().get(LORE), is(35));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(33));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(121));
        assertThat(character.getDamageMultiplier(), is(54));
        assertThat(character.getTotalHealth(), is(330));
        assertThat(character.getTotalMagicPoint(), is(512));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(36));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(32));
        assertThat(character.getAttributes().get(LORE), is(36));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(34));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(122));
        assertThat(character.getDamageMultiplier(), is(54));
        assertThat(character.getTotalHealth(), is(340));
        assertThat(character.getTotalMagicPoint(), is(529));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(37));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(17));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        assertThat(character.getAttributes().get(LORE), is(37));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(35));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(123));
        assertThat(character.getDamageMultiplier(), is(57));
        assertThat(character.getTotalHealth(), is(340));
        assertThat(character.getTotalMagicPoint(), is(546));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(38));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(17));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(34));
        assertThat(character.getAttributes().get(LORE), is(38));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(36));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(124));
        assertThat(character.getDamageMultiplier(), is(57));
        assertThat(character.getTotalHealth(), is(350));
        assertThat(character.getTotalMagicPoint(), is(563));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(39));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(18));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(21));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        assertThat(character.getAttributes().get(LORE), is(39));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(37));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(126));
        assertThat(character.getDamageMultiplier(), is(60));
        assertThat(character.getTotalHealth(), is(350));
        assertThat(character.getTotalMagicPoint(), is(570));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(40));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(18));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(36));
        assertThat(character.getAttributes().get(LORE), is(40));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(38));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(126));
        assertThat(character.getDamageMultiplier(), is(60));
        assertThat(character.getTotalHealth(), is(360));
        assertThat(character.getTotalMagicPoint(), is(587));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(41));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        assertThat(character.getAttributes().get(LORE), is(41));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(39));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(128));
        assertThat(character.getDamageMultiplier(), is(63));
        assertThat(character.getTotalHealth(), is(360));
        assertThat(character.getTotalMagicPoint(), is(604));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(42));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(23));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        assertThat(character.getAttributes().get(LORE), is(42));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(40));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(128));
        assertThat(character.getDamageMultiplier(), is(63));
        assertThat(character.getTotalHealth(), is(380));
        assertThat(character.getTotalMagicPoint(), is(621));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(43));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(23));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        assertThat(character.getAttributes().get(LORE), is(43));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(41));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(130));
        assertThat(character.getDamageMultiplier(), is(66));
        assertThat(character.getTotalHealth(), is(380));
        assertThat(character.getTotalMagicPoint(), is(638));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(44));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(24));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        assertThat(character.getAttributes().get(LORE), is(44));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(42));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(130));
        assertThat(character.getDamageMultiplier(), is(66));
        assertThat(character.getTotalHealth(), is(390));
        assertThat(character.getTotalMagicPoint(), is(655));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(45));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(41));
        assertThat(character.getAttributes().get(LORE), is(45));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(43));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(132));
        assertThat(character.getDamageMultiplier(), is(69));
        assertThat(character.getTotalHealth(), is(390));
        assertThat(character.getTotalMagicPoint(), is(672));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(46));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(42));
        assertThat(character.getAttributes().get(LORE), is(46));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(44));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(133));
        assertThat(character.getDamageMultiplier(), is(69));
        assertThat(character.getTotalHealth(), is(400));
        assertThat(character.getTotalMagicPoint(), is(679));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(47));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(43));
        assertThat(character.getAttributes().get(LORE), is(47));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(2));
        // and
        assertThat(character.getAttack(), is(133));
        assertThat(character.getDamageMultiplier(), is(71));
        assertThat(character.getTotalHealth(), is(400));
        assertThat(character.getTotalMagicPoint(), is(696));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(48));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(44));
        assertThat(character.getAttributes().get(LORE), is(48));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(3));
        // and
        assertThat(character.getAttack(), is(134));
        assertThat(character.getDamageMultiplier(), is(71));
        assertThat(character.getTotalHealth(), is(410));
        assertThat(character.getTotalMagicPoint(), is(704));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(49));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(45));
        assertThat(character.getAttributes().get(LORE), is(49));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(4));
        // and
        assertThat(character.getAttack(), is(135));
        assertThat(character.getDamageMultiplier(), is(74));
        assertThat(character.getTotalHealth(), is(410));
        assertThat(character.getTotalMagicPoint(), is(712));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(50));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(27));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(45));
        assertThat(character.getAttributes().get(LORE), is(50));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(45));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(5));
        // and
        assertThat(character.getAttack(), is(136));
        assertThat(character.getDamageMultiplier(), is(80));
        assertThat(character.getTotalHealth(), is(410));
        assertThat(character.getTotalMagicPoint(), is(715));
    }
}
