package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.FIGHTER;
import static com.asgames.ataliasflame.domain.model.enums.Caste.PALADIN;
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

        // and so on...
    }

}
