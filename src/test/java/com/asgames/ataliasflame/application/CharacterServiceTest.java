package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.application.model.DefensiveGodConversionCode;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Gender;
import com.asgames.ataliasflame.domain.model.enums.God;
import com.asgames.ataliasflame.domain.model.enums.Race;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class CharacterServiceTest extends CharacterTestBase {

    @ParameterizedTest
    @MethodSource("characters")
    void characterCreationTest(Race race, Gender gender, God defensiveGod) {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(race)
                .gender(gender)
                .defensiveGod(defensiveGod)
                .name("Dain")
                .build();

        // when
        Character character = characterMaintenanceService.createCharacter(characterInput);

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
        CharacterInput characterInput = CharacterInput.builder()
                .race(race)
                .gender(gender)
                .defensiveGod(defensiveGod)
                .name("Walt")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);

        // when
        character = characterMaintenanceService.getCharacter(character.getReference());

        // then
        assertThat(character.getRace(), is(equalTo(race)));
        assertThat(character.getGender(), is(equalTo(gender)));
        assertThat(character.getCaste(), is(ROGUE));
        assertThat(character.getLevel(), is(1));
        assertThat(character.getExperience(), is(0));
        assertThat(character.getAttributePoints(), is(0));
    }

    @Test
    void defensiveGodConversionTest() {
        // given
        CharacterInput clericInput = CharacterInput.builder()
                .race(ORC)
                .gender(MALE)
                .defensiveGod(GETON)
                .name("Guag")
                .build();
        Character cleric = characterMaintenanceService.createCharacter(clericInput);
        // and
        String clericReference = cleric.getReference();
        upgradeCaste(clericReference, List.of(MONK, PRIEST));
        // and
        CharacterInput fighterInput = CharacterInput.builder()
                .race(HALF_ELF)
                .gender(FEMALE)
                .defensiveGod(GINDON)
                .name("Janadiane")
                .build();
        Character fighter = characterMaintenanceService.createCharacter(fighterInput);
        // and
        String fighterReference = fighter.getReference();
        upgradeCaste(fighterReference, List.of(FIGHTER, PALADIN, GRANDMASTER, TITAN));

        // then
        cleric = characterMaintenanceService.getCharacter(clericReference);
        fighter = characterMaintenanceService.getCharacter(fighterReference);
        assertThat(cleric.getDefensiveGod(), is(not(equalTo(fighter.getDefensiveGod()))));
        // and
        assertThat(fighter.getDamageMultiplier(), is(280));
        assertThat(fighter.getMagic().totalValue(), is(530));

        // when
        DefensiveGodConversionCode conversionCode = characterAdventureService.getDefensiveGodConversionCode(clericReference);

        // then
        assertThat(conversionCode.getClericReference(), is(cleric.getReference()));
        assertThat(conversionCode.getClericName(), is(cleric.getName()));
        assertThat(conversionCode.getGod(), is(cleric.getDefensiveGod()));

        // when
        fighter = characterMaintenanceService.convertDefensiveGod(fighterReference, conversionCode.getCode());

        // expect
        assertThat(cleric.getDefensiveGod(), is(equalTo(fighter.getDefensiveGod())));
        assertThat(fighter.getDefensiveGod(), is(GETON));
        // and
        assertThat(fighter.getDamageMultiplier(), is(286));
        assertThat(fighter.getMagic().totalValue(), is(520));

        // and
        assertThrows(IllegalArgumentException.class,
                () -> characterMaintenanceService.convertDefensiveGod(fighterReference, conversionCode.getCode()));
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