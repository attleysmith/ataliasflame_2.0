package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.God;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.HUMAN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class HumanCalculationTest extends RaceCalculationTestBase {

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Remon";
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        Character character = characterService.createCharacter(characterInput);
        assertThat(character.getCaste(), is(ROGUE));
        addDagger(characterName);

        character = characterService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> rogueCalculations() {
        return Stream.of(
                arguments(HORA, 82, 22, 2, 6, 3, 110),
                arguments(SIFER, 82, 22, 2, 6, 3, 110),
                arguments(GETON, 82, 22, 2, 6, 3, 110),
                arguments(RUNID, 82, 22, 2, 6, 3, 110),
                arguments(ALATE, 82, 22, 2, 6, 3, 110),
                arguments(GINDON, 82, 22, 2, 6, 3, 110)
        );
    }

    @ParameterizedTest
    @MethodSource("fighterCalculations")
    void fighterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "John";
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(FIGHTER));
        addDagger(characterName);

        Character character = characterService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> fighterCalculations() {
        return Stream.of(
                arguments(HORA, 92, 24, 2, 7, 15, 150),
                arguments(SIFER, 92, 24, 2, 7, 15, 150),
                arguments(GETON, 92, 24, 2, 7, 15, 150),
                arguments(RUNID, 92, 24, 2, 7, 15, 150),
                arguments(ALATE, 92, 24, 2, 7, 15, 150),
                arguments(GINDON, 92, 24, 2, 7, 15, 150)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Dandy";
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(FIGHTER, PALADIN));
        addDagger(characterName);

        Character character = characterService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> paladinCalculations() {
        return Stream.of(
                arguments(HORA, 128, 34, 3, 10, 60, 300),
                arguments(SIFER, 128, 34, 3, 10, 60, 300),
                arguments(GETON, 128, 34, 3, 10, 62, 300),
                arguments(RUNID, 128, 34, 3, 10, 60, 300),
                arguments(ALATE, 130, 34, 3, 10, 61, 300),
                arguments(GINDON, 128, 34, 3, 10, 60, 300)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Estevot";
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(FIGHTER, PALADIN, GRANDMASTER));
        addDagger(characterName);

        Character character = characterService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> grandmasterCalculations() {
        return Stream.of(
                arguments(HORA, 200, 53, 5, 15, 150, 600),
                arguments(SIFER, 200, 53, 5, 15, 150, 610),
                arguments(GETON, 200, 53, 5, 15, 154, 600),
                arguments(RUNID, 200, 53, 5, 15, 150, 600),
                arguments(ALATE, 203, 53, 5, 15, 152, 600),
                arguments(GINDON, 200, 53, 5, 15, 150, 600)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Ludovicus";
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(FIGHTER, PALADIN, GRANDMASTER, TITAN));
        addDagger(characterName);

        Character character = characterService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> titanCalculations() {
        return Stream.of(
                arguments(HORA, 320, 84, 8, 24, 300, 1100),
                arguments(SIFER, 320, 84, 8, 24, 300, 1110),
                arguments(GETON, 320, 85, 8, 24, 306, 1100),
                arguments(RUNID, 320, 84, 8, 24, 300, 1100),
                arguments(ALATE, 325, 85, 8, 24, 303, 1100),
                arguments(GINDON, 320, 84, 8, 24, 300, 1100)
        );
    }
}
