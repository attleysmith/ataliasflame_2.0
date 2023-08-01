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
import static com.asgames.ataliasflame.domain.model.enums.Race.ARIMASPI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class ArimaspiCalculationTest extends RaceCalculationTestBase {

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Throlf";
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        assertThat(character.getCaste(), is(ROGUE));
        addDagger(characterName);

        character = characterMaintenanceService.getCharacter(characterName);
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
        String characterName = "Iblur";
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterMaintenanceService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(FIGHTER));
        addDagger(characterName);

        Character character = characterMaintenanceService.getCharacter(characterName);
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
        String characterName = "Baunweg";
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterMaintenanceService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(FIGHTER, PALADIN));
        addDagger(characterName);

        Character character = characterMaintenanceService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> paladinCalculations() {
        return Stream.of(
                arguments(HORA, 130, 34, 3, 10, 63, 310),
                arguments(SIFER, 130, 34, 3, 10, 63, 310),
                arguments(GETON, 130, 34, 3, 10, 65, 310),
                arguments(RUNID, 130, 34, 3, 10, 63, 310),
                arguments(ALATE, 132, 34, 3, 10, 64, 310),
                arguments(GINDON, 130, 34, 3, 10, 63, 310)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Khalumm";
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterMaintenanceService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(FIGHTER, PALADIN, GRANDMASTER));
        addDagger(characterName);

        Character character = characterMaintenanceService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> grandmasterCalculations() {
        return Stream.of(
                arguments(HORA, 207, 54, 5, 16, 159, 630),
                arguments(SIFER, 207, 54, 5, 16, 159, 630),
                arguments(GETON, 207, 55, 5, 16, 161, 630),
                arguments(RUNID, 207, 54, 5, 16, 159, 630),
                arguments(ALATE, 209, 55, 5, 16, 160, 630),
                arguments(GINDON, 207, 54, 5, 16, 159, 630)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Olk";
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterMaintenanceService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(FIGHTER, PALADIN, GRANDMASTER, TITAN));
        addDagger(characterName);

        Character character = characterMaintenanceService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> titanCalculations() {
        return Stream.of(
                arguments(HORA, 332, 87, 8, 25, 315, 1150),
                arguments(SIFER, 332, 87, 8, 25, 315, 1160),
                arguments(GETON, 332, 88, 8, 25, 321, 1150),
                arguments(RUNID, 332, 87, 8, 25, 315, 1150),
                arguments(ALATE, 337, 88, 8, 25, 318, 1150),
                arguments(GINDON, 332, 87, 8, 25, 315, 1150)
        );
    }
}
