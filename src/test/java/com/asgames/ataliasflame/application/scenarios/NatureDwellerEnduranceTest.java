package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.HERMIT;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.SIFER;
import static com.asgames.ataliasflame.domain.model.enums.Race.HALFLING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled("May be killed in action")
@SpringBootTest
public class NatureDwellerEnduranceTest extends EnduranceTestBase {

    @Test
    void enduranceTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(SIFER)
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
        assertThat(character.getAttack(), is(84));
        assertThat(character.getDamageMultiplier(), is(4));
        assertThat(character.getTotalHealth(), is(110));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> upgradeCaste(HERMIT));

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
        assertThat(character.getAttack(), is(90));
        assertThat(character.getDamageMultiplier(), is(7));
        assertThat(character.getTotalHealth(), is(120));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> upgradeCaste(HERMIT));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(3));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 2);
        addAttributePoints(CONSTITUTION, 2);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(2));
        // and
        assertThat(character.getAttack(), is(90));
        assertThat(character.getDamageMultiplier(), is(9));
        assertThat(character.getTotalHealth(), is(140));
        // and
        assertThrows(IllegalArgumentException.class,
                () -> upgradeCaste(HERMIT));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(4));
        assertThat(character.getAttributePoints(), is(5));

        // then
        character = addAttributePoints(INTELLIGENCE, 2);

        // expect
        assertThat(character.getAttributePoints(), is(3));
        assertThat(character.getAttributes().get(STRENGTH), is(3));
        assertThat(character.getAttributes().get(DEXTERITY), is(3));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(3));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        // and
        assertThat(character.getAttack(), is(90));
        assertThat(character.getDamageMultiplier(), is(9));
        assertThat(character.getTotalHealth(), is(140));

        // then
        character = upgradeCaste(HERMIT);

        // expect
        assertThat(character.getCaste(), is(HERMIT));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        character = addAttributePoints(AGILITY, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(4));
        assertThat(character.getAttributes().get(DEXTERITY), is(4));
        assertThat(character.getAttributes().get(CONSTITUTION), is(4));
        assertThat(character.getAttributes().get(AGILITY), is(4));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(4));
        // and
        assertThat(character.getAttack(), is(93));
        assertThat(character.getDamageMultiplier(), is(12));
        assertThat(character.getTotalHealth(), is(140));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(5));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(5));
        assertThat(character.getAttributes().get(DEXTERITY), is(5));
        assertThat(character.getAttributes().get(CONSTITUTION), is(5));
        assertThat(character.getAttributes().get(AGILITY), is(5));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(5));
        // and
        assertThat(character.getAttack(), is(97));
        assertThat(character.getDamageMultiplier(), is(16));
        assertThat(character.getTotalHealth(), is(150));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(6));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(6));
        assertThat(character.getAttributes().get(DEXTERITY), is(6));
        assertThat(character.getAttributes().get(CONSTITUTION), is(6));
        assertThat(character.getAttributes().get(AGILITY), is(6));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(6));
        // and
        assertThat(character.getAttack(), is(99));
        assertThat(character.getDamageMultiplier(), is(17));
        assertThat(character.getTotalHealth(), is(150));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(7));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(7));
        assertThat(character.getAttributes().get(DEXTERITY), is(7));
        assertThat(character.getAttributes().get(CONSTITUTION), is(7));
        assertThat(character.getAttributes().get(AGILITY), is(7));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(7));
        // and
        assertThat(character.getAttack(), is(103));
        assertThat(character.getDamageMultiplier(), is(21));
        assertThat(character.getTotalHealth(), is(160));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(8));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(8));
        assertThat(character.getAttributes().get(CONSTITUTION), is(8));
        assertThat(character.getAttributes().get(AGILITY), is(8));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(8));
        // and
        assertThat(character.getAttack(), is(106));
        assertThat(character.getDamageMultiplier(), is(24));
        assertThat(character.getTotalHealth(), is(170));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(9));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(9));
        assertThat(character.getAttributes().get(CONSTITUTION), is(10));
        assertThat(character.getAttributes().get(AGILITY), is(9));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(9));
        // and
        assertThat(character.getAttack(), is(110));
        assertThat(character.getDamageMultiplier(), is(26));
        assertThat(character.getTotalHealth(), is(190));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(10));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(10));
        assertThat(character.getAttributes().get(CONSTITUTION), is(12));
        assertThat(character.getAttributes().get(AGILITY), is(10));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(10));
        // and
        assertThat(character.getAttack(), is(112));
        assertThat(character.getDamageMultiplier(), is(27));
        assertThat(character.getTotalHealth(), is(210));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(11));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(11));
        assertThat(character.getAttributes().get(CONSTITUTION), is(14));
        assertThat(character.getAttributes().get(AGILITY), is(11));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(11));
        // and
        assertThat(character.getAttack(), is(115));
        assertThat(character.getDamageMultiplier(), is(29));
        assertThat(character.getTotalHealth(), is(230));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(12));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 2);
        addAttributePoints(AGILITY, 1);
        character = addAttributePoints(INTELLIGENCE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(16));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(12));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(30));
        assertThat(character.getTotalHealth(), is(250));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(13));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        character = addAttributePoints(INTELLIGENCE, 4);

        // expect
        assertThat(character.getAttributePoints(), is(0));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(16));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(30));
        assertThat(character.getTotalHealth(), is(250));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(14));
        assertThat(character.getAttributePoints(), is(5));

        // then
        character = addAttributePoints(INTELLIGENCE, 2);

        // expect
        assertThat(character.getAttributePoints(), is(3));
        assertThat(character.getAttributes().get(STRENGTH), is(8));
        assertThat(character.getAttributes().get(DEXTERITY), is(12));
        assertThat(character.getAttributes().get(CONSTITUTION), is(17));
        assertThat(character.getAttributes().get(AGILITY), is(12));
        assertThat(character.getAttributes().get(INTELLIGENCE), is(18));
        // and
        assertThat(character.getAttack(), is(118));
        assertThat(character.getDamageMultiplier(), is(30));
        assertThat(character.getTotalHealth(), is(250));
    }
}
