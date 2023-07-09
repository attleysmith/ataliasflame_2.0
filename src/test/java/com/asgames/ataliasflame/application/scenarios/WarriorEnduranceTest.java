package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.ALATE;
import static com.asgames.ataliasflame.domain.model.enums.Race.NIGHT_ELF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled("May be killed in action")
@SpringBootTest
public class WarriorEnduranceTest extends EnduranceTestBase {

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(NIGHT_ELF)
                .gender(MALE)
                .defensiveGod(ALATE)
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
        // and
        assertThat(character.getAttack(), is(82));
        assertThat(character.getDamageMultiplier(), is(3));
        assertThat(character.getTotalHealth(), is(110));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> upgradeCaste(FIGHTER));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(2));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        // and
        assertThat(character.getAttack(), is(87));
        assertThat(character.getDamageMultiplier(), is(5));
        assertThat(character.getTotalHealth(), is(120));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> upgradeCaste(FIGHTER));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(3));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(8));
        assertThat(character.getTotalHealth(), is(130));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> upgradeCaste(FIGHTER));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(4));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 3);
        character = addAttributePoints(CONSTITUTION, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(14));
        assertThat(character.getTotalHealth(), is(150));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> upgradeCaste(FIGHTER));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(5));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(3));
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getTotalHealth(), is(150));

        // then
        character = upgradeCaste(FIGHTER);

        // expect
        assertThat(character.getCaste(), is(FIGHTER));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(6));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(96));
        assertThat(character.getDamageMultiplier(), is(17));
        assertThat(character.getTotalHealth(), is(160));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(6));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(101));
        assertThat(character.getDamageMultiplier(), is(19));
        assertThat(character.getTotalHealth(), is(170));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(7));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(106));
        assertThat(character.getDamageMultiplier(), is(23));
        assertThat(character.getTotalHealth(), is(170));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(8));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(112));
        assertThat(character.getDamageMultiplier(), is(26));
        assertThat(character.getTotalHealth(), is(180));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(9));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(114));
        assertThat(character.getDamageMultiplier(), is(29));
        assertThat(character.getTotalHealth(), is(200));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(10));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(9));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(11));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(117));
        assertThat(character.getDamageMultiplier(), is(34));
        assertThat(character.getTotalHealth(), is(210));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(11));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(119));
        assertThat(character.getDamageMultiplier(), is(37));
        assertThat(character.getTotalHealth(), is(220));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(12));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(122));
        assertThat(character.getDamageMultiplier(), is(40));
        assertThat(character.getTotalHealth(), is(240));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(13));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(124));
        assertThat(character.getDamageMultiplier(), is(43));
        assertThat(character.getTotalHealth(), is(260));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(14));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(126));
        assertThat(character.getDamageMultiplier(), is(48));
        assertThat(character.getTotalHealth(), is(270));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(15));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(129));
        assertThat(character.getDamageMultiplier(), is(53));
        assertThat(character.getTotalHealth(), is(280));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(16));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 3);
        character = addAttributePoints(CONSTITUTION, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(129));
        assertThat(character.getDamageMultiplier(), is(59));
        assertThat(character.getTotalHealth(), is(300));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(17));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        // and
        assertThat(character.getAttack(), is(133));
        assertThat(character.getDamageMultiplier(), is(63));
        assertThat(character.getTotalHealth(), is(300));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(18));
        assertThat(character.getAttributePoints(), is(5));

        // then
        character = addAttributePoints(INTELLIGENCE, 2);

        // expect
        assertThat(character.getAttributePoints(), is(3));
        assertThat(character.getAttributes().get(STRENGTH), is(20));
        assertThat(character.getAttributes().get(DEXTERITY), is(20));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        // and
        assertThat(character.getAttack(), is(133));
        assertThat(character.getDamageMultiplier(), is(63));
        assertThat(character.getTotalHealth(), is(300));

        // then
        character = upgradeCaste(PALADIN);

        // expect
        assertThat(character.getCaste(), is(PALADIN));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        character = addAttributePoints(CONSTITUTION, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(21));
        assertThat(character.getAttributes().get(DEXTERITY), is(21));
        assertThat(character.getAttributes().get(CONSTITUTION), is(21));
        assertThat(character.getAttributes().get(AGILITY), is(20));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        // and
        assertThat(character.getAttack(), is(134));
        assertThat(character.getDamageMultiplier(), is(66));
        assertThat(character.getTotalHealth(), is(310));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(19));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(22));
        assertThat(character.getAttributes().get(DEXTERITY), is(22));
        assertThat(character.getAttributes().get(CONSTITUTION), is(22));
        assertThat(character.getAttributes().get(AGILITY), is(22));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        // and
        assertThat(character.getAttack(), is(138));
        assertThat(character.getDamageMultiplier(), is(69));
        assertThat(character.getTotalHealth(), is(320));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(20));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(23));
        assertThat(character.getAttributes().get(DEXTERITY), is(23));
        assertThat(character.getAttributes().get(CONSTITUTION), is(24));
        assertThat(character.getAttributes().get(AGILITY), is(23));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        // and
        assertThat(character.getAttack(), is(140));
        assertThat(character.getDamageMultiplier(), is(72));
        assertThat(character.getTotalHealth(), is(340));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(21));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(24));
        assertThat(character.getAttributes().get(DEXTERITY), is(25));
        assertThat(character.getAttributes().get(CONSTITUTION), is(25));
        assertThat(character.getAttributes().get(AGILITY), is(24));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        // and
        assertThat(character.getAttack(), is(144));
        assertThat(character.getDamageMultiplier(), is(76));
        assertThat(character.getTotalHealth(), is(350));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(22));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(25));
        assertThat(character.getAttributes().get(DEXTERITY), is(26));
        assertThat(character.getAttributes().get(CONSTITUTION), is(26));
        assertThat(character.getAttributes().get(AGILITY), is(25));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        // and
        assertThat(character.getAttack(), is(146));
        assertThat(character.getDamageMultiplier(), is(79));
        assertThat(character.getTotalHealth(), is(360));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(23));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(27));
        assertThat(character.getAttributes().get(DEXTERITY), is(27));
        assertThat(character.getAttributes().get(CONSTITUTION), is(27));
        assertThat(character.getAttributes().get(AGILITY), is(26));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        // and
        assertThat(character.getAttack(), is(150));
        assertThat(character.getDamageMultiplier(), is(85));
        assertThat(character.getTotalHealth(), is(370));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(24));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(28));
        assertThat(character.getAttributes().get(DEXTERITY), is(28));
        assertThat(character.getAttributes().get(CONSTITUTION), is(28));
        assertThat(character.getAttributes().get(AGILITY), is(28));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        // and
        assertThat(character.getAttack(), is(154));
        assertThat(character.getDamageMultiplier(), is(88));
        assertThat(character.getTotalHealth(), is(380));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(25));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(29));
        assertThat(character.getAttributes().get(DEXTERITY), is(29));
        assertThat(character.getAttributes().get(CONSTITUTION), is(30));
        assertThat(character.getAttributes().get(AGILITY), is(29));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        // and
        assertThat(character.getAttack(), is(156));
        assertThat(character.getDamageMultiplier(), is(91));
        assertThat(character.getTotalHealth(), is(400));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(26));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(30));
        assertThat(character.getAttributes().get(DEXTERITY), is(30));
        assertThat(character.getAttributes().get(CONSTITUTION), is(31));
        assertThat(character.getAttributes().get(AGILITY), is(30));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        // and
        assertThat(character.getAttack(), is(158));
        assertThat(character.getDamageMultiplier(), is(94));
        assertThat(character.getTotalHealth(), is(410));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(27));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(31));
        assertThat(character.getAttributes().get(DEXTERITY), is(32));
        assertThat(character.getAttributes().get(CONSTITUTION), is(32));
        assertThat(character.getAttributes().get(AGILITY), is(31));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        // and
        assertThat(character.getAttack(), is(162));
        assertThat(character.getDamageMultiplier(), is(98));
        assertThat(character.getTotalHealth(), is(420));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(28));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(33));
        assertThat(character.getAttributes().get(DEXTERITY), is(33));
        assertThat(character.getAttributes().get(CONSTITUTION), is(33));
        assertThat(character.getAttributes().get(AGILITY), is(32));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        // and
        assertThat(character.getAttack(), is(165));
        assertThat(character.getDamageMultiplier(), is(103));
        assertThat(character.getTotalHealth(), is(430));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(29));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(34));
        assertThat(character.getAttributes().get(DEXTERITY), is(34));
        assertThat(character.getAttributes().get(CONSTITUTION), is(34));
        assertThat(character.getAttributes().get(AGILITY), is(34));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        // and
        assertThat(character.getAttack(), is(168));
        assertThat(character.getDamageMultiplier(), is(106));
        assertThat(character.getTotalHealth(), is(440));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(30));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(35));
        assertThat(character.getAttributes().get(DEXTERITY), is(35));
        assertThat(character.getAttributes().get(CONSTITUTION), is(35));
        assertThat(character.getAttributes().get(AGILITY), is(35));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        // and
        assertThat(character.getAttack(), is(172));
        assertThat(character.getDamageMultiplier(), is(110));
        assertThat(character.getTotalHealth(), is(450));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(31));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(36));
        assertThat(character.getAttributes().get(DEXTERITY), is(36));
        assertThat(character.getAttributes().get(CONSTITUTION), is(37));
        assertThat(character.getAttributes().get(AGILITY), is(36));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        // and
        assertThat(character.getAttack(), is(174));
        assertThat(character.getDamageMultiplier(), is(113));
        assertThat(character.getTotalHealth(), is(470));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(32));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(37));
        assertThat(character.getAttributes().get(DEXTERITY), is(38));
        assertThat(character.getAttributes().get(CONSTITUTION), is(38));
        assertThat(character.getAttributes().get(AGILITY), is(37));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        // and
        assertThat(character.getAttack(), is(178));
        assertThat(character.getDamageMultiplier(), is(117));
        assertThat(character.getTotalHealth(), is(480));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(33));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(39));
        assertThat(character.getAttributes().get(DEXTERITY), is(39));
        assertThat(character.getAttributes().get(CONSTITUTION), is(39));
        assertThat(character.getAttributes().get(AGILITY), is(38));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        // and
        assertThat(character.getAttack(), is(181));
        assertThat(character.getDamageMultiplier(), is(122));
        assertThat(character.getTotalHealth(), is(490));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(34));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(40));
        assertThat(character.getAttributes().get(DEXTERITY), is(40));
        assertThat(character.getAttributes().get(CONSTITUTION), is(40));
        assertThat(character.getAttributes().get(AGILITY), is(40));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        // and
        assertThat(character.getAttack(), is(184));
        assertThat(character.getDamageMultiplier(), is(125));
        assertThat(character.getTotalHealth(), is(500));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(35));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(41));
        assertThat(character.getAttributes().get(DEXTERITY), is(41));
        assertThat(character.getAttributes().get(CONSTITUTION), is(41));
        assertThat(character.getAttributes().get(AGILITY), is(41));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        // and
        assertThat(character.getAttack(), is(186));
        assertThat(character.getDamageMultiplier(), is(128));
        assertThat(character.getTotalHealth(), is(510));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(36));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(42));
        assertThat(character.getAttributes().get(DEXTERITY), is(42));
        assertThat(character.getAttributes().get(CONSTITUTION), is(43));
        assertThat(character.getAttributes().get(AGILITY), is(42));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        // and
        assertThat(character.getAttack(), is(189));
        assertThat(character.getDamageMultiplier(), is(131));
        assertThat(character.getTotalHealth(), is(530));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(37));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(43));
        assertThat(character.getAttributes().get(DEXTERITY), is(44));
        assertThat(character.getAttributes().get(CONSTITUTION), is(44));
        assertThat(character.getAttributes().get(AGILITY), is(43));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        // and
        assertThat(character.getAttack(), is(194));
        assertThat(character.getDamageMultiplier(), is(136));
        assertThat(character.getTotalHealth(), is(540));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(38));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(45));
        assertThat(character.getAttributes().get(DEXTERITY), is(45));
        assertThat(character.getAttributes().get(CONSTITUTION), is(45));
        assertThat(character.getAttributes().get(AGILITY), is(44));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        // and
        assertThat(character.getAttack(), is(197));
        assertThat(character.getDamageMultiplier(), is(141));
        assertThat(character.getTotalHealth(), is(550));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(39));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(46));
        assertThat(character.getAttributes().get(DEXTERITY), is(46));
        assertThat(character.getAttributes().get(CONSTITUTION), is(46));
        assertThat(character.getAttributes().get(AGILITY), is(45));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        // and
        assertThat(character.getAttack(), is(199));
        assertThat(character.getDamageMultiplier(), is(144));
        assertThat(character.getTotalHealth(), is(560));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(40));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(47));
        assertThat(character.getAttributes().get(DEXTERITY), is(47));
        assertThat(character.getAttributes().get(CONSTITUTION), is(47));
        assertThat(character.getAttributes().get(AGILITY), is(47));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        // and
        assertThat(character.getAttack(), is(202));
        assertThat(character.getDamageMultiplier(), is(147));
        assertThat(character.getTotalHealth(), is(570));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(41));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(48));
        assertThat(character.getAttributes().get(DEXTERITY), is(48));
        assertThat(character.getAttributes().get(CONSTITUTION), is(48));
        assertThat(character.getAttributes().get(AGILITY), is(48));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(13));
        // and
        assertThat(character.getAttack(), is(205));
        assertThat(character.getDamageMultiplier(), is(150));
        assertThat(character.getTotalHealth(), is(580));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(42));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(49));
        assertThat(character.getAttributes().get(DEXTERITY), is(49));
        assertThat(character.getAttributes().get(CONSTITUTION), is(49));
        assertThat(character.getAttributes().get(AGILITY), is(49));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(14));
        // and
        assertThat(character.getAttack(), is(207));
        assertThat(character.getDamageMultiplier(), is(153));
        assertThat(character.getTotalHealth(), is(590));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(43));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(50));
        assertThat(character.getAttributes().get(DEXTERITY), is(50));
        assertThat(character.getAttributes().get(CONSTITUTION), is(50));
        assertThat(character.getAttributes().get(AGILITY), is(50));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(15));
        // and
        assertThat(character.getAttack(), is(211));
        assertThat(character.getDamageMultiplier(), is(157));
        assertThat(character.getTotalHealth(), is(600));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(44));
        assertThat(character.getAttributePoints(), is(5));

        // then
        character = addAttributePoints(INTELLIGENCE, 5);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(50));
        assertThat(character.getAttributes().get(DEXTERITY), is(50));
        assertThat(character.getAttributes().get(CONSTITUTION), is(50));
        assertThat(character.getAttributes().get(AGILITY), is(50));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        // and
        assertThat(character.getAttack(), is(211));
        assertThat(character.getDamageMultiplier(), is(157));
        assertThat(character.getTotalHealth(), is(600));

        // then
        character = upgradeCaste(GRANDMASTER);

        // expect
        assertThat(character.getCaste(), is(GRANDMASTER));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(45));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(51));
        assertThat(character.getAttributes().get(DEXTERITY), is(51));
        assertThat(character.getAttributes().get(CONSTITUTION), is(52));
        assertThat(character.getAttributes().get(AGILITY), is(51));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        // and
        assertThat(character.getAttack(), is(214));
        assertThat(character.getDamageMultiplier(), is(160));
        assertThat(character.getTotalHealth(), is(610));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(46));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(52));
        assertThat(character.getAttributes().get(DEXTERITY), is(53));
        assertThat(character.getAttributes().get(CONSTITUTION), is(53));
        assertThat(character.getAttributes().get(AGILITY), is(52));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        // and
        assertThat(character.getAttack(), is(218));
        assertThat(character.getDamageMultiplier(), is(164));
        assertThat(character.getTotalHealth(), is(620));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(47));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(54));
        assertThat(character.getAttributes().get(DEXTERITY), is(54));
        assertThat(character.getAttributes().get(CONSTITUTION), is(54));
        assertThat(character.getAttributes().get(AGILITY), is(53));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        // and
        assertThat(character.getAttack(), is(220));
        assertThat(character.getDamageMultiplier(), is(169));
        assertThat(character.getTotalHealth(), is(630));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(48));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(55));
        assertThat(character.getAttributes().get(DEXTERITY), is(55));
        assertThat(character.getAttributes().get(CONSTITUTION), is(55));
        assertThat(character.getAttributes().get(AGILITY), is(55));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(20));
        // and
        assertThat(character.getAttack(), is(223));
        assertThat(character.getDamageMultiplier(), is(172));
        assertThat(character.getTotalHealth(), is(640));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(49));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(56));
        assertThat(character.getAttributes().get(DEXTERITY), is(56));
        assertThat(character.getAttributes().get(CONSTITUTION), is(56));
        assertThat(character.getAttributes().get(AGILITY), is(56));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        // and
        assertThat(character.getAttack(), is(226));
        assertThat(character.getDamageMultiplier(), is(175));
        assertThat(character.getTotalHealth(), is(650));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(50));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(57));
        assertThat(character.getAttributes().get(DEXTERITY), is(57));
        assertThat(character.getAttributes().get(CONSTITUTION), is(58));
        assertThat(character.getAttributes().get(AGILITY), is(57));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        // and
        assertThat(character.getAttack(), is(228));
        assertThat(character.getDamageMultiplier(), is(178));
        assertThat(character.getTotalHealth(), is(670));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(51));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(58));
        assertThat(character.getAttributes().get(DEXTERITY), is(59));
        assertThat(character.getAttributes().get(CONSTITUTION), is(59));
        assertThat(character.getAttributes().get(AGILITY), is(58));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(21));
        // and
        assertThat(character.getAttack(), is(234));
        assertThat(character.getDamageMultiplier(), is(183));
        assertThat(character.getTotalHealth(), is(680));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(52));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(59));
        assertThat(character.getAttributes().get(DEXTERITY), is(60));
        assertThat(character.getAttributes().get(CONSTITUTION), is(60));
        assertThat(character.getAttributes().get(AGILITY), is(59));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        // and
        assertThat(character.getAttack(), is(236));
        assertThat(character.getDamageMultiplier(), is(186));
        assertThat(character.getTotalHealth(), is(690));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(53));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(61));
        assertThat(character.getAttributes().get(DEXTERITY), is(61));
        assertThat(character.getAttributes().get(CONSTITUTION), is(61));
        assertThat(character.getAttributes().get(AGILITY), is(60));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        // and
        assertThat(character.getAttack(), is(238));
        assertThat(character.getDamageMultiplier(), is(191));
        assertThat(character.getTotalHealth(), is(700));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(54));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(62));
        assertThat(character.getAttributes().get(DEXTERITY), is(62));
        assertThat(character.getAttributes().get(CONSTITUTION), is(62));
        assertThat(character.getAttributes().get(AGILITY), is(62));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        // and
        assertThat(character.getAttack(), is(242));
        assertThat(character.getDamageMultiplier(), is(194));
        assertThat(character.getTotalHealth(), is(710));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(55));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(63));
        assertThat(character.getAttributes().get(DEXTERITY), is(63));
        assertThat(character.getAttributes().get(CONSTITUTION), is(64));
        assertThat(character.getAttributes().get(AGILITY), is(63));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        // and
        assertThat(character.getAttack(), is(244));
        assertThat(character.getDamageMultiplier(), is(197));
        assertThat(character.getTotalHealth(), is(730));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(56));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(64));
        assertThat(character.getAttributes().get(DEXTERITY), is(65));
        assertThat(character.getAttributes().get(CONSTITUTION), is(65));
        assertThat(character.getAttributes().get(AGILITY), is(64));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(22));
        // and
        assertThat(character.getAttack(), is(248));
        assertThat(character.getDamageMultiplier(), is(201));
        assertThat(character.getTotalHealth(), is(740));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(57));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(65));
        assertThat(character.getAttributes().get(DEXTERITY), is(66));
        assertThat(character.getAttributes().get(CONSTITUTION), is(66));
        assertThat(character.getAttributes().get(AGILITY), is(65));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        // and
        assertThat(character.getAttack(), is(252));
        assertThat(character.getDamageMultiplier(), is(205));
        assertThat(character.getTotalHealth(), is(750));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(58));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(67));
        assertThat(character.getAttributes().get(DEXTERITY), is(67));
        assertThat(character.getAttributes().get(CONSTITUTION), is(67));
        assertThat(character.getAttributes().get(AGILITY), is(66));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        // and
        assertThat(character.getAttack(), is(254));
        assertThat(character.getDamageMultiplier(), is(210));
        assertThat(character.getTotalHealth(), is(760));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(59));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(68));
        assertThat(character.getAttributes().get(DEXTERITY), is(68));
        assertThat(character.getAttributes().get(CONSTITUTION), is(68));
        assertThat(character.getAttributes().get(AGILITY), is(68));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(23));
        // and
        assertThat(character.getAttack(), is(258));
        assertThat(character.getDamageMultiplier(), is(213));
        assertThat(character.getTotalHealth(), is(770));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(60));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(69));
        assertThat(character.getAttributes().get(DEXTERITY), is(69));
        assertThat(character.getAttributes().get(CONSTITUTION), is(69));
        assertThat(character.getAttributes().get(AGILITY), is(69));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(24));
        // and
        assertThat(character.getAttack(), is(260));
        assertThat(character.getDamageMultiplier(), is(216));
        assertThat(character.getTotalHealth(), is(780));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(61));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(70));
        assertThat(character.getAttributes().get(DEXTERITY), is(70));
        assertThat(character.getAttributes().get(CONSTITUTION), is(70));
        assertThat(character.getAttributes().get(AGILITY), is(70));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        // and
        assertThat(character.getAttack(), is(262));
        assertThat(character.getDamageMultiplier(), is(219));
        assertThat(character.getTotalHealth(), is(790));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(62));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(71));
        assertThat(character.getAttributes().get(DEXTERITY), is(71));
        assertThat(character.getAttributes().get(CONSTITUTION), is(72));
        assertThat(character.getAttributes().get(AGILITY), is(71));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        // and
        assertThat(character.getAttack(), is(265));
        assertThat(character.getDamageMultiplier(), is(222));
        assertThat(character.getTotalHealth(), is(810));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(63));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(72));
        assertThat(character.getAttributes().get(DEXTERITY), is(73));
        assertThat(character.getAttributes().get(CONSTITUTION), is(73));
        assertThat(character.getAttributes().get(AGILITY), is(72));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(25));
        // and
        assertThat(character.getAttack(), is(269));
        assertThat(character.getDamageMultiplier(), is(226));
        assertThat(character.getTotalHealth(), is(820));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(64));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(73));
        assertThat(character.getAttributes().get(DEXTERITY), is(74));
        assertThat(character.getAttributes().get(CONSTITUTION), is(74));
        assertThat(character.getAttributes().get(AGILITY), is(73));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        // and
        assertThat(character.getAttack(), is(273));
        assertThat(character.getDamageMultiplier(), is(230));
        assertThat(character.getTotalHealth(), is(830));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(65));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(75));
        assertThat(character.getAttributes().get(DEXTERITY), is(75));
        assertThat(character.getAttributes().get(CONSTITUTION), is(75));
        assertThat(character.getAttributes().get(AGILITY), is(74));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        // and
        assertThat(character.getAttack(), is(275));
        assertThat(character.getDamageMultiplier(), is(235));
        assertThat(character.getTotalHealth(), is(840));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(66));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(76));
        assertThat(character.getAttributes().get(DEXTERITY), is(76));
        assertThat(character.getAttributes().get(CONSTITUTION), is(76));
        assertThat(character.getAttributes().get(AGILITY), is(76));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(26));
        // and
        assertThat(character.getAttack(), is(278));
        assertThat(character.getDamageMultiplier(), is(238));
        assertThat(character.getTotalHealth(), is(850));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(67));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(77));
        assertThat(character.getAttributes().get(DEXTERITY), is(77));
        assertThat(character.getAttributes().get(CONSTITUTION), is(77));
        assertThat(character.getAttributes().get(AGILITY), is(77));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        // and
        assertThat(character.getAttack(), is(281));
        assertThat(character.getDamageMultiplier(), is(241));
        assertThat(character.getTotalHealth(), is(860));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(68));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(78));
        assertThat(character.getAttributes().get(DEXTERITY), is(78));
        assertThat(character.getAttributes().get(CONSTITUTION), is(79));
        assertThat(character.getAttributes().get(AGILITY), is(78));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        // and
        assertThat(character.getAttack(), is(283));
        assertThat(character.getDamageMultiplier(), is(244));
        assertThat(character.getTotalHealth(), is(880));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(69));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(79));
        assertThat(character.getAttributes().get(DEXTERITY), is(80));
        assertThat(character.getAttributes().get(CONSTITUTION), is(80));
        assertThat(character.getAttributes().get(AGILITY), is(79));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        // and
        assertThat(character.getAttack(), is(287));
        assertThat(character.getDamageMultiplier(), is(248));
        assertThat(character.getTotalHealth(), is(890));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(70));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(81));
        assertThat(character.getAttributes().get(DEXTERITY), is(81));
        assertThat(character.getAttributes().get(CONSTITUTION), is(81));
        assertThat(character.getAttributes().get(AGILITY), is(80));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        // and
        assertThat(character.getAttack(), is(291));
        assertThat(character.getDamageMultiplier(), is(254));
        assertThat(character.getTotalHealth(), is(900));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(71));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(82));
        assertThat(character.getAttributes().get(DEXTERITY), is(82));
        assertThat(character.getAttributes().get(CONSTITUTION), is(82));
        assertThat(character.getAttributes().get(AGILITY), is(82));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(27));
        // and
        assertThat(character.getAttack(), is(294));
        assertThat(character.getDamageMultiplier(), is(257));
        assertThat(character.getTotalHealth(), is(910));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(72));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(83));
        assertThat(character.getAttributes().get(DEXTERITY), is(83));
        assertThat(character.getAttributes().get(CONSTITUTION), is(83));
        assertThat(character.getAttributes().get(AGILITY), is(83));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        // and
        assertThat(character.getAttack(), is(297));
        assertThat(character.getDamageMultiplier(), is(260));
        assertThat(character.getTotalHealth(), is(920));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(73));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(84));
        assertThat(character.getAttributes().get(DEXTERITY), is(84));
        assertThat(character.getAttributes().get(CONSTITUTION), is(85));
        assertThat(character.getAttributes().get(AGILITY), is(84));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        // and
        assertThat(character.getAttack(), is(299));
        assertThat(character.getDamageMultiplier(), is(263));
        assertThat(character.getTotalHealth(), is(940));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(74));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 2);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(85));
        assertThat(character.getAttributes().get(DEXTERITY), is(86));
        assertThat(character.getAttributes().get(CONSTITUTION), is(86));
        assertThat(character.getAttributes().get(AGILITY), is(85));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        // and
        assertThat(character.getAttack(), is(303));
        assertThat(character.getDamageMultiplier(), is(267));
        assertThat(character.getTotalHealth(), is(950));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(75));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(87));
        assertThat(character.getAttributes().get(DEXTERITY), is(87));
        assertThat(character.getAttributes().get(CONSTITUTION), is(87));
        assertThat(character.getAttributes().get(AGILITY), is(86));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        // and
        assertThat(character.getAttack(), is(306));
        assertThat(character.getDamageMultiplier(), is(272));
        assertThat(character.getTotalHealth(), is(960));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(76));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(88));
        assertThat(character.getAttributes().get(DEXTERITY), is(88));
        assertThat(character.getAttributes().get(CONSTITUTION), is(88));
        assertThat(character.getAttributes().get(AGILITY), is(88));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(28));
        // and
        assertThat(character.getAttack(), is(309));
        assertThat(character.getDamageMultiplier(), is(275));
        assertThat(character.getTotalHealth(), is(970));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(77));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(89));
        assertThat(character.getAttributes().get(DEXTERITY), is(89));
        assertThat(character.getAttributes().get(CONSTITUTION), is(89));
        assertThat(character.getAttributes().get(AGILITY), is(89));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(29));
        // and
        assertThat(character.getAttack(), is(313));
        assertThat(character.getDamageMultiplier(), is(279));
        assertThat(character.getTotalHealth(), is(980));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(78));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(90));
        assertThat(character.getAttributes().get(DEXTERITY), is(90));
        assertThat(character.getAttributes().get(CONSTITUTION), is(90));
        assertThat(character.getAttributes().get(AGILITY), is(90));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(30));
        // and
        assertThat(character.getAttack(), is(315));
        assertThat(character.getDamageMultiplier(), is(282));
        assertThat(character.getTotalHealth(), is(990));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(79));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(91));
        assertThat(character.getAttributes().get(DEXTERITY), is(91));
        assertThat(character.getAttributes().get(CONSTITUTION), is(91));
        assertThat(character.getAttributes().get(AGILITY), is(91));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(31));
        // and
        assertThat(character.getAttack(), is(318));
        assertThat(character.getDamageMultiplier(), is(285));
        assertThat(character.getTotalHealth(), is(1000));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(80));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(92));
        assertThat(character.getAttributes().get(DEXTERITY), is(92));
        assertThat(character.getAttributes().get(CONSTITUTION), is(92));
        assertThat(character.getAttributes().get(AGILITY), is(92));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(32));
        // and
        assertThat(character.getAttack(), is(320));
        assertThat(character.getDamageMultiplier(), is(288));
        assertThat(character.getTotalHealth(), is(1010));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(81));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(93));
        assertThat(character.getAttributes().get(DEXTERITY), is(93));
        assertThat(character.getAttributes().get(CONSTITUTION), is(93));
        assertThat(character.getAttributes().get(AGILITY), is(93));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(33));
        // and
        assertThat(character.getAttack(), is(322));
        assertThat(character.getDamageMultiplier(), is(291));
        assertThat(character.getTotalHealth(), is(1020));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(82));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(94));
        assertThat(character.getAttributes().get(DEXTERITY), is(94));
        assertThat(character.getAttributes().get(CONSTITUTION), is(94));
        assertThat(character.getAttributes().get(AGILITY), is(94));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(34));
        // and
        assertThat(character.getAttack(), is(325));
        assertThat(character.getDamageMultiplier(), is(294));
        assertThat(character.getTotalHealth(), is(1030));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(83));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(95));
        assertThat(character.getAttributes().get(DEXTERITY), is(95));
        assertThat(character.getAttributes().get(CONSTITUTION), is(95));
        assertThat(character.getAttributes().get(AGILITY), is(95));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(35));
        // and
        assertThat(character.getAttack(), is(327));
        assertThat(character.getDamageMultiplier(), is(297));
        assertThat(character.getTotalHealth(), is(1040));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(84));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(96));
        assertThat(character.getAttributes().get(DEXTERITY), is(96));
        assertThat(character.getAttributes().get(CONSTITUTION), is(96));
        assertThat(character.getAttributes().get(AGILITY), is(96));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(36));
        // and
        assertThat(character.getAttack(), is(330));
        assertThat(character.getDamageMultiplier(), is(300));
        assertThat(character.getTotalHealth(), is(1050));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(85));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(97));
        assertThat(character.getAttributes().get(DEXTERITY), is(97));
        assertThat(character.getAttributes().get(CONSTITUTION), is(97));
        assertThat(character.getAttributes().get(AGILITY), is(97));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(37));
        // and
        assertThat(character.getAttack(), is(334));
        assertThat(character.getDamageMultiplier(), is(304));
        assertThat(character.getTotalHealth(), is(1060));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(86));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(98));
        assertThat(character.getAttributes().get(DEXTERITY), is(98));
        assertThat(character.getAttributes().get(CONSTITUTION), is(98));
        assertThat(character.getAttributes().get(AGILITY), is(98));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(38));
        // and
        assertThat(character.getAttack(), is(336));
        assertThat(character.getDamageMultiplier(), is(307));
        assertThat(character.getTotalHealth(), is(1070));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(87));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(99));
        assertThat(character.getAttributes().get(DEXTERITY), is(99));
        assertThat(character.getAttributes().get(CONSTITUTION), is(99));
        assertThat(character.getAttributes().get(AGILITY), is(99));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(39));
        // and
        assertThat(character.getAttack(), is(338));
        assertThat(character.getDamageMultiplier(), is(310));
        assertThat(character.getTotalHealth(), is(1080));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(88));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(100));
        assertThat(character.getAttributes().get(DEXTERITY), is(100));
        assertThat(character.getAttributes().get(CONSTITUTION), is(100));
        assertThat(character.getAttributes().get(AGILITY), is(100));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(40));
        // and
        assertThat(character.getAttack(), is(341));
        assertThat(character.getDamageMultiplier(), is(313));
        assertThat(character.getTotalHealth(), is(1090));

        // then
        character = upgradeCaste(TITAN);

        // expect
        assertThat(character.getCaste(), is(TITAN));

        // and so on...
    }

}
