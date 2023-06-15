package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.CharacterService;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.FIGHTER;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.ALATE;
import static com.asgames.ataliasflame.domain.model.enums.Race.HUMAN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
@SpringBootTest
public class WarriorEnduranceTest {

    @Autowired
    private CharacterService characterService;

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(ALATE)
                .name("Takemoto")
                .build();
        characterService.createCharacter(characterInput);
        // and
        Character character = characterService.getCharacter();

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
        assertThat(character.getDefense(), is(21));
        assertThat(character.getDamage(), is(5));
        assertThat(character.getDamageMultiplier(), is(3));
        assertThat(character.getTotalHealth(), is(110));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> characterService.upgradeCaste(FIGHTER));

        // when
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(2));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(DEXTERITY, 2);
        characterService.addAttributePoints(CONSTITUTION, 1);
        character = characterService.addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(2));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        // and
        assertThat(character.getAttack(), is(87));
        assertThat(character.getDefense(), is(21));
        assertThat(character.getDamage(), is(5));
        assertThat(character.getDamageMultiplier(), is(5));
        assertThat(character.getTotalHealth(), is(120));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> characterService.upgradeCaste(FIGHTER));

        // when
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(3));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(DEXTERITY, 2);
        characterService.addAttributePoints(CONSTITUTION, 1);
        character = characterService.addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(1));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(3));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        // and
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDefense(), is(22));
        assertThat(character.getDamage(), is(5));
        assertThat(character.getDamageMultiplier(), is(7));
        assertThat(character.getTotalHealth(), is(130));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> characterService.upgradeCaste(FIGHTER));

        // when
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(4));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 3);
        character = characterService.addAttributePoints(CONSTITUTION, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(1));
        // and
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDefense(), is(23));
        assertThat(character.getDamage(), is(6));
        assertThat(character.getDamageMultiplier(), is(13));
        assertThat(character.getTotalHealth(), is(150));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> characterService.upgradeCaste(FIGHTER));

        // when
        characterService.combat();
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(5));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 1);
        character = characterService.addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(3));
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(92));
        assertThat(character.getDefense(), is(23));
        assertThat(character.getDamage(), is(6));
        assertThat(character.getDamageMultiplier(), is(15));
        assertThat(character.getTotalHealth(), is(150));

        // then
        character = characterService.upgradeCaste(FIGHTER);

        // expect
        assertThat(character.getCaste(), is(FIGHTER));

        // then
        characterService.addAttributePoints(DEXTERITY, 1);
        characterService.addAttributePoints(CONSTITUTION, 1);
        character = characterService.addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(6));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(94));
        assertThat(character.getDefense(), is(23));
        assertThat(character.getDamage(), is(6));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getTotalHealth(), is(160));

        // when
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(6));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(DEXTERITY, 2);
        characterService.addAttributePoints(CONSTITUTION, 1);
        character = characterService.addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(99));
        assertThat(character.getDefense(), is(24));
        assertThat(character.getDamage(), is(6));
        assertThat(character.getDamageMultiplier(), is(18));
        assertThat(character.getTotalHealth(), is(170));

        // when
        characterService.combat();
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(7));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 1);
        characterService.addAttributePoints(DEXTERITY, 2);
        character = characterService.addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(104));
        assertThat(character.getDefense(), is(25));
        assertThat(character.getDamage(), is(6));
        assertThat(character.getDamageMultiplier(), is(22));
        assertThat(character.getTotalHealth(), is(170));

        // when
        characterService.combat();
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(8));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(DEXTERITY, 2);
        characterService.addAttributePoints(CONSTITUTION, 1);
        character = characterService.addAttributePoints(AGILITY, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(109));
        assertThat(character.getDefense(), is(26));
        assertThat(character.getDamage(), is(6));
        assertThat(character.getDamageMultiplier(), is(24));
        assertThat(character.getTotalHealth(), is(180));

        // when
        characterService.combat();
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(9));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 1);
        characterService.addAttributePoints(DEXTERITY, 1);
        characterService.addAttributePoints(CONSTITUTION, 2);
        character = characterService.addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(13));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(13));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(111));
        assertThat(character.getDefense(), is(27));
        assertThat(character.getDamage(), is(6));
        assertThat(character.getDamageMultiplier(), is(27));
        assertThat(character.getTotalHealth(), is(200));

        // when
        characterService.combat();
        characterService.combat();
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(10));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 2);
        characterService.addAttributePoints(DEXTERITY, 1);
        characterService.addAttributePoints(CONSTITUTION, 1);
        character = characterService.addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(9));
        assertThat(character.getAttributes().get(DEXTERITY), is(14));
        assertThat(character.getAttributes().get(CONSTITUTION), is(11));
        assertThat(character.getAttributes().get(AGILITY), is(14));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(114));
        assertThat(character.getDefense(), is(27));
        assertThat(character.getDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(32));
        assertThat(character.getTotalHealth(), is(210));

        // when
        characterService.combat();
        characterService.combat();
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(11));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 1);
        characterService.addAttributePoints(DEXTERITY, 1);
        characterService.addAttributePoints(CONSTITUTION, 1);
        characterService.addAttributePoints(AGILITY, 1);
        character = characterService.addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(10));
        assertThat(character.getAttributes().get(DEXTERITY), is(15));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(15));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(116));
        assertThat(character.getDefense(), is(28));
        assertThat(character.getDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(35));
        assertThat(character.getTotalHealth(), is(220));

        // when
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(12));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 1);
        characterService.addAttributePoints(DEXTERITY, 1);
        characterService.addAttributePoints(CONSTITUTION, 2);
        character = characterService.addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(11));
        assertThat(character.getAttributes().get(DEXTERITY), is(16));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(16));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDefense(), is(29));
        assertThat(character.getDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(38));
        assertThat(character.getTotalHealth(), is(240));

        // when
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();

        // expect
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(13));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 1);
        characterService.addAttributePoints(DEXTERITY, 1);
        characterService.addAttributePoints(CONSTITUTION, 2);
        character = characterService.addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(12));
        assertThat(character.getAttributes().get(DEXTERITY), is(17));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(17));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(122));
        assertThat(character.getDefense(), is(29));
        assertThat(character.getDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(42));
        assertThat(character.getTotalHealth(), is(260));

        // when
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();

        // expect --- occurred to be killed before this point!
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(14));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 2);
        characterService.addAttributePoints(DEXTERITY, 1);
        characterService.addAttributePoints(CONSTITUTION, 1);
        character = characterService.addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(14));
        assertThat(character.getAttributes().get(DEXTERITY), is(18));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(18));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(125));
        assertThat(character.getDefense(), is(30));
        assertThat(character.getDamage(), is(7));
        assertThat(character.getDamageMultiplier(), is(47));
        assertThat(character.getTotalHealth(), is(270));

        // when
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();

        // expect --- can reach, but...
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(15));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 2);
        characterService.addAttributePoints(DEXTERITY, 1);
        characterService.addAttributePoints(CONSTITUTION, 1);
        character = characterService.addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(16));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(18));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(127));
        assertThat(character.getDefense(), is(31));
        assertThat(character.getDamage(), is(8));
        assertThat(character.getDamageMultiplier(), is(52));
        assertThat(character.getTotalHealth(), is(280));

        // when
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();

        // expect --- occurred to be reached somehow :)
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(16));
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(STRENGTH, 3);
        character = characterService.addAttributePoints(CONSTITUTION, 2);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(19));
        assertThat(character.getAttributes().get(DEXTERITY), is(19));
        assertThat(character.getAttributes().get(CONSTITUTION), is(20));
        assertThat(character.getAttributes().get(AGILITY), is(19));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(3));
        // and
        assertThat(character.getAttack(), is(127));
        assertThat(character.getDefense(), is(32));
        assertThat(character.getDamage(), is(8));
        assertThat(character.getDamageMultiplier(), is(58));
        assertThat(character.getTotalHealth(), is(300));

        // when
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();
        characterService.combat();

        // expect --- never made it
        character = characterService.getCharacter();
        assertThat(character.getLevel(), is(17));
        assertThat(character.getAttributePoints(), is(5));
    }
}
