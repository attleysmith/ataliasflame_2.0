package com.asgames.ataliasflame.application.calculations;

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
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Remon")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        assertThat(character.getCaste(), is(ROGUE));
        addDagger(characterReference);

        character = characterMaintenanceService.getCharacter(characterReference);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> rogueCalculations() {
        return Stream.of(
                arguments(HORA, 82, 22, 2, 6, 3, 110, 5),
                arguments(SIFER, 82, 22, 2, 6, 3, 110, 5),
                arguments(GETON, 82, 22, 2, 6, 3, 110, 5),
                arguments(RUNID, 82, 22, 2, 6, 3, 110, 5),
                arguments(ALATE, 82, 22, 2, 6, 3, 110, 5),
                arguments(GINDON, 82, 22, 2, 6, 3, 110, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("fighterCalculations")
    void fighterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("John")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER));
        addDagger(characterReference);

        character = characterMaintenanceService.getCharacter(characterReference);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> fighterCalculations() {
        return Stream.of(
                arguments(HORA, 92, 24, 2, 7, 15, 150, 23),
                arguments(SIFER, 92, 24, 2, 7, 15, 150, 23),
                arguments(GETON, 92, 24, 2, 7, 15, 150, 23),
                arguments(RUNID, 92, 24, 2, 7, 15, 150, 23),
                arguments(ALATE, 92, 24, 2, 7, 15, 150, 23),
                arguments(GINDON, 92, 24, 2, 7, 15, 150, 23)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Dandy")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN));
        addDagger(characterReference);

        character = characterMaintenanceService.getCharacter(characterReference);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> paladinCalculations() {
        return Stream.of(
                arguments(HORA, 128, 34, 3, 10, 60, 300, 99),
                arguments(SIFER, 128, 34, 3, 10, 60, 300, 99),
                arguments(GETON, 128, 34, 3, 10, 62, 300, 99),
                arguments(RUNID, 128, 34, 3, 10, 60, 300, 99),
                arguments(ALATE, 130, 34, 3, 10, 61, 300, 99),
                arguments(GINDON, 128, 34, 3, 10, 60, 300, 99)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Estevot")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN, GRANDMASTER));
        addDagger(characterReference);

        character = characterMaintenanceService.getCharacter(characterReference);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> grandmasterCalculations() {
        return Stream.of(
                arguments(HORA, 200, 53, 5, 15, 150, 600, 253),
                arguments(SIFER, 200, 53, 5, 15, 150, 610, 251),
                arguments(GETON, 200, 53, 5, 15, 154, 600, 251),
                arguments(RUNID, 200, 53, 5, 15, 150, 600, 251),
                arguments(ALATE, 203, 53, 5, 15, 152, 600, 251),
                arguments(GINDON, 200, 53, 5, 15, 150, 600, 251)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Ludovicus")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN, GRANDMASTER, TITAN));
        addDagger(characterReference);

        character = characterMaintenanceService.getCharacter(characterReference);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> titanCalculations() {
        return Stream.of(
                arguments(HORA, 320, 84, 8, 24, 300, 1100, 512),
                arguments(SIFER, 320, 84, 8, 24, 300, 1110, 510),
                arguments(GETON, 320, 85, 8, 24, 306, 1100, 510),
                arguments(RUNID, 320, 84, 8, 24, 300, 1100, 515),
                arguments(ALATE, 325, 85, 8, 24, 303, 1100, 510),
                arguments(GINDON, 320, 84, 8, 24, 300, 1100, 510)
        );
    }
}
