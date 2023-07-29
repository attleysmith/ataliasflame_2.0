package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.SIFER;
import static com.asgames.ataliasflame.domain.model.enums.Race.HALFLING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Disabled("May be killed in action")
@SpringBootTest(properties = "booster.experience:true")
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
        assertThat(character.getAttributes().get(LORE), is(0));
        assertThat(character.getAttributes().get(MENTAL_POWER), is(0));
        assertThat(character.getAttributes().get(SPIRITUAL_POWER), is(0));
        // and
        assertThat(character.getAttack(), is(84));
        assertThat(character.getDamageMultiplier(), is(4));
        assertThat(character.getTotalHealth(), is(110));
        assertThat(character.getTotalMagicPoint(), is(5));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(2));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(3));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(4));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(5));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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
        character = upgradeCaste(HERMIT);

        // expect
        assertThat(character.getCaste(), is(HERMIT));

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
        character = addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(7));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(9));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(10));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(11));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(12));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(13));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(14));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(15));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(16));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(17));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(18));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(19));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(INTELLIGENCE, 2);
        addAttributePoints(LORE, 2);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(20));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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
        character = upgradeCaste(DRUID);

        // expect
        assertThat(character.getCaste(), is(DRUID));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(21));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(22));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(23));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(24));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(25));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(26));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(27));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(28));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(29));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(30));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(31));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(32));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(33));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(34));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(35));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        character = addAttributePoints(LORE, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(36));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(37));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(39));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(40));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(41));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(42));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(43));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(45));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(46));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(47));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(48));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(49));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(50));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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
        character = upgradeCaste(ARCHDRUID);

        // expect
        assertThat(character.getCaste(), is(ARCHDRUID));

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(51));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(52));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(53));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(54));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(55));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(56));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(57));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(58));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(59));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(60));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(61));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(62));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(63));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(64));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(65));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(66));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(67));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(68));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(69));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(70));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(71));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(72));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(73));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(74));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(75));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(76));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(77));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(78));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(79));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(80));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(81));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(82));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(83));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(84));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(85));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(86));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(87));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(88));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(89));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(90));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(91));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(92));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(93));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(94));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(95));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(96));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(97));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(98));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(99));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(DEXTERITY, 1);
        addAttributePoints(AGILITY, 1);
        addAttributePoints(INTELLIGENCE, 1);
        addAttributePoints(LORE, 1);
        character = addAttributePoints(MENTAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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

        // when
        character = combatUntilNextLevel();

        // expect
        assertThat(character.getLevel(), is(100));
        assertThat(character.getAttributePoints(), is(5));

        // then
        addAttributePoints(STRENGTH, 1);
        addAttributePoints(CONSTITUTION, 1);
        addAttributePoints(LORE, 1);
        addAttributePoints(MENTAL_POWER, 1);
        character = addAttributePoints(SPIRITUAL_POWER, 1);

        // expect
        assertThat(character.getAttributePoints(), is(0));
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
        character = upgradeCaste(ATALIAS_PRIEST);

        // expect
        assertThat(character.getCaste(), is(ATALIAS_PRIEST));
        // and
        assertThat(character.getAttack(), is(240));
        assertThat(character.getDamageMultiplier(), is(139));
        assertThat(character.getTotalHealth(), is(740));
        assertThat(character.getTotalMagicPoint(), is(1508));
    }
}
