package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Gender;
import com.asgames.ataliasflame.domain.model.enums.God;
import com.asgames.ataliasflame.domain.model.enums.Race;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.model.enums.Caste.ROGUE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class CharacterServiceTest {

    @Autowired
    private CharacterService characterService;

    @ParameterizedTest
    @MethodSource("characters")
    void characterCreationTest(Race race, Gender gender, God defensiveGod) {
        // given
        String characterName = "Dain";
        // and
        CharacterInput characterInput = CharacterInput.builder()
                .race(race)
                .gender(gender)
                .defensiveGod(defensiveGod)
                .name(characterName)
                .build();

        // when
        Character character = characterService.createCharacter(characterInput);

        // then
        assertThat(character.getRace(), is(equalTo(race)));
        assertThat(character.getGender(), is(equalTo(gender)));
        assertThat(character.getCaste(), is(ROGUE));
        assertThat(character.getLevel(), is(1));
        assertThat(character.getExperience(), is(0));
        assertThat(character.getAttributePoints(), is(0));
    }

    @ParameterizedTest
    @MethodSource("characters")
    void characterQueryTest(Race race, Gender gender, God defensiveGod) {
        // given
        String characterName = "Walt";
        // and
        CharacterInput characterInput = CharacterInput.builder()
                .race(race)
                .gender(gender)
                .defensiveGod(defensiveGod)
                .name(characterName)
                .build();
        characterService.createCharacter(characterInput);

        // when
        Character character = characterService.getCharacter(characterName);

        // then
        assertThat(character.getRace(), is(equalTo(race)));
        assertThat(character.getGender(), is(equalTo(gender)));
        assertThat(character.getCaste(), is(ROGUE));
        assertThat(character.getLevel(), is(1));
        assertThat(character.getExperience(), is(0));
        assertThat(character.getAttributePoints(), is(0));
    }

    @Test
    void deathMatchTest() {
        // given
        String characterName = "Gwatkin";
        // and
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(ALATE)
                .name(characterName)
                .build();
        characterService.createCharacter(characterInput);

        // expect
        Character character;
        do {
            characterService.combat(characterName);
            character = characterService.getCharacter(characterName);
        } while (character.getActualHealth() > 0);
    }

    private static Stream<Arguments> characters() {
        return Stream.of(
                arguments(HUMAN, MALE, SIFER),
                arguments(HUMAN, FEMALE, GETON),
                arguments(HUMAN, MALE, RUNID),
                arguments(HUMAN, FEMALE, ALATE),
                arguments(HUMAN, MALE, HORA),
                arguments(HUMAN, FEMALE, GINDON),
                arguments(ELF, MALE, SIFER),
                arguments(ELF, FEMALE, GETON),
                arguments(ELF, MALE, RUNID),
                arguments(ELF, FEMALE, ALATE),
                arguments(ELF, MALE, HORA),
                arguments(ELF, FEMALE, GINDON),
                arguments(HALF_ELF, MALE, SIFER),
                arguments(HALF_ELF, FEMALE, GETON),
                arguments(HALF_ELF, MALE, RUNID),
                arguments(HALF_ELF, FEMALE, ALATE),
                arguments(HALF_ELF, MALE, HORA),
                arguments(HALF_ELF, FEMALE, GINDON),
                arguments(NIGHT_ELF, MALE, SIFER),
                arguments(NIGHT_ELF, FEMALE, GETON),
                arguments(NIGHT_ELF, MALE, RUNID),
                arguments(NIGHT_ELF, FEMALE, ALATE),
                arguments(NIGHT_ELF, MALE, HORA),
                arguments(NIGHT_ELF, FEMALE, GINDON),
                arguments(HALFLING, MALE, SIFER),
                arguments(HALFLING, FEMALE, GETON),
                arguments(HALFLING, MALE, RUNID),
                arguments(HALFLING, FEMALE, ALATE),
                arguments(HALFLING, MALE, HORA),
                arguments(HALFLING, FEMALE, GINDON)
        );
    }
}