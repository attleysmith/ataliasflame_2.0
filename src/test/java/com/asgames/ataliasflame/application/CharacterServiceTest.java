package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Gender;
import com.asgames.ataliasflame.domain.model.enums.God;
import com.asgames.ataliasflame.domain.model.enums.Race;
import com.github.javafaker.ElderScrolls;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.FIGHTER;
import static com.asgames.ataliasflame.domain.model.enums.Caste.ROGUE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class CharacterServiceTest {

    @Autowired
    private CharacterService characterService;

    @ParameterizedTest
    @MethodSource("characters")
    void characterCreationTest(Race race, Gender gender, God defensiveGod, String name) {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(race)
                .gender(gender)
                .defensiveGod(defensiveGod)
                .name(name)
                .build();

        // when
        Character character = characterService.createCharacter(characterInput);

        // then
        assertThat(character.getRace(), is(equalTo(race)));
        assertThat(character.getGender(), is(equalTo(gender)));
        assertThat(character.getName(), is(equalTo(name)));
        assertThat(character.getCaste(), is(ROGUE));
        assertThat(character.getLevel(), is(1));
        assertThat(character.getExperience(), is(0));
        assertThat(character.getAttributePoints(), is(0));
    }

    @ParameterizedTest
    @MethodSource("characters")
    void characterQueryTest(Race race, Gender gender, God defensiveGod, String name) {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(race)
                .gender(gender)
                .defensiveGod(defensiveGod)
                .name(name)
                .build();
        characterService.createCharacter(characterInput);

        // when
        Character character = characterService.getCharacter();

        // then
        assertThat(character.getRace(), is(equalTo(race)));
        assertThat(character.getGender(), is(equalTo(gender)));
        assertThat(character.getName(), is(equalTo(name)));
        assertThat(character.getCaste(), is(ROGUE));
        assertThat(character.getLevel(), is(1));
        assertThat(character.getExperience(), is(0));
        assertThat(character.getAttributePoints(), is(0));
    }

    @Test
    void combatTest() {
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
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(DEXTERITY, 2);
        characterService.addAttributePoints(AGILITY, 2);
        character = characterService.addAttributePoints(CONSTITUTION, 1);

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
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(DEXTERITY, 2);
        characterService.addAttributePoints(AGILITY, 2);
        character = characterService.addAttributePoints(CONSTITUTION, 1);

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
        assertThat(character.getAttributePoints(), is(5));

        // then
        characterService.addAttributePoints(CONSTITUTION, 2);
        character = characterService.addAttributePoints(STRENGTH, 3);

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
    }

    @Test
    void deathMatchTest() {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(ALATE)
                .name("Takemoto")
                .build();
        characterService.createCharacter(characterInput);

        // expect
        Character character;
        do {
            characterService.combat();
            character = characterService.getCharacter();
        } while (character.getActualHealth() > 0);
    }

    private static Stream<Arguments> characters() {
        ElderScrolls elderScrollsFaker = Faker.instance().elderScrolls();

        return Stream.of(
                arguments(HUMAN, MALE, SIFER, elderScrollsFaker.firstName()),
                arguments(HUMAN, FEMALE, GETON, elderScrollsFaker.firstName()),
                arguments(HUMAN, MALE, RUNID, elderScrollsFaker.firstName()),
                arguments(HUMAN, FEMALE, ALATE, elderScrollsFaker.firstName()),
                arguments(HUMAN, MALE, HORA, elderScrollsFaker.firstName()),
                arguments(HUMAN, FEMALE, GINDON, elderScrollsFaker.firstName()),
                arguments(ELF, MALE, SIFER, elderScrollsFaker.firstName()),
                arguments(ELF, FEMALE, GETON, elderScrollsFaker.firstName()),
                arguments(ELF, MALE, RUNID, elderScrollsFaker.firstName()),
                arguments(ELF, FEMALE, ALATE, elderScrollsFaker.firstName()),
                arguments(ELF, MALE, HORA, elderScrollsFaker.firstName()),
                arguments(ELF, FEMALE, GINDON, elderScrollsFaker.firstName()),
                arguments(HALF_ELF, MALE, SIFER, elderScrollsFaker.firstName()),
                arguments(HALF_ELF, FEMALE, GETON, elderScrollsFaker.firstName()),
                arguments(HALF_ELF, MALE, RUNID, elderScrollsFaker.firstName()),
                arguments(HALF_ELF, FEMALE, ALATE, elderScrollsFaker.firstName()),
                arguments(HALF_ELF, MALE, HORA, elderScrollsFaker.firstName()),
                arguments(HALF_ELF, FEMALE, GINDON, elderScrollsFaker.firstName()),
                arguments(NIGHT_ELF, MALE, SIFER, elderScrollsFaker.firstName()),
                arguments(NIGHT_ELF, FEMALE, GETON, elderScrollsFaker.firstName()),
                arguments(NIGHT_ELF, MALE, RUNID, elderScrollsFaker.firstName()),
                arguments(NIGHT_ELF, FEMALE, ALATE, elderScrollsFaker.firstName()),
                arguments(NIGHT_ELF, MALE, HORA, elderScrollsFaker.firstName()),
                arguments(NIGHT_ELF, FEMALE, GINDON, elderScrollsFaker.firstName()),
                arguments(HALFLING, MALE, SIFER, elderScrollsFaker.firstName()),
                arguments(HALFLING, FEMALE, GETON, elderScrollsFaker.firstName()),
                arguments(HALFLING, MALE, RUNID, elderScrollsFaker.firstName()),
                arguments(HALFLING, FEMALE, ALATE, elderScrollsFaker.firstName()),
                arguments(HALFLING, MALE, HORA, elderScrollsFaker.firstName()),
                arguments(HALFLING, FEMALE, GINDON, elderScrollsFaker.firstName())
        );
    }
}