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
import static com.asgames.ataliasflame.domain.model.enums.Race.HALFLING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class HalflingCalculationTest extends RaceCalculationTestBase {

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Milo")
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
                arguments(HORA, 84, 22, 2, 6, 4, 110, 5),
                arguments(SIFER, 84, 22, 2, 6, 4, 110, 5),
                arguments(GETON, 84, 22, 2, 6, 4, 110, 5),
                arguments(RUNID, 84, 22, 2, 6, 4, 110, 5),
                arguments(ALATE, 84, 22, 2, 6, 4, 110, 5),
                arguments(GINDON, 84, 22, 2, 6, 4, 110, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("fighterCalculations")
    void fighterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Isembard")
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
                arguments(HORA, 97, 25, 2, 7, 16, 150, 23),
                arguments(SIFER, 97, 25, 2, 7, 16, 150, 23),
                arguments(GETON, 97, 25, 2, 7, 16, 150, 23),
                arguments(RUNID, 97, 25, 2, 7, 16, 150, 23),
                arguments(ALATE, 97, 25, 2, 7, 16, 150, 23),
                arguments(GINDON, 97, 25, 2, 7, 16, 150, 23)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Berno")
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
                arguments(HORA, 143, 34, 3, 9, 58, 280, 99),
                arguments(SIFER, 143, 34, 3, 9, 58, 280, 99),
                arguments(GETON, 143, 34, 3, 10, 60, 280, 99),
                arguments(RUNID, 143, 34, 3, 9, 58, 280, 99),
                arguments(ALATE, 145, 34, 3, 10, 59, 280, 99),
                arguments(GINDON, 143, 34, 3, 9, 58, 280, 99)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Marcus")
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
                arguments(HORA, 238, 54, 5, 15, 145, 550, 253),
                arguments(SIFER, 238, 54, 5, 15, 145, 560, 251),
                arguments(GETON, 238, 55, 5, 15, 149, 550, 251),
                arguments(RUNID, 238, 54, 5, 15, 145, 550, 251),
                arguments(ALATE, 242, 55, 5, 15, 147, 550, 251),
                arguments(GINDON, 238, 54, 5, 15, 145, 550, 251)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Gorhendad")
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
                arguments(HORA, 396, 87, 8, 23, 290, 1000, 512),
                arguments(SIFER, 396, 87, 8, 23, 290, 1010, 510),
                arguments(GETON, 396, 88, 8, 24, 296, 1000, 510),
                arguments(RUNID, 396, 87, 8, 23, 290, 1000, 515),
                arguments(ALATE, 401, 88, 8, 24, 293, 1000, 510),
                arguments(GINDON, 396, 87, 8, 23, 290, 1000, 510)
        );
    }
}
