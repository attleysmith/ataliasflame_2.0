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
import static com.asgames.ataliasflame.domain.model.enums.Race.NIGHT_ELF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class NightElfCalculationTest extends RaceCalculationTestBase {

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NIGHT_ELF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Bhumarth")
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
                arguments(HORA, 82, 6, 2, 6, 3, 110, 5),
                arguments(SIFER, 82, 6, 2, 6, 3, 110, 5),
                arguments(GETON, 82, 6, 2, 6, 3, 110, 5),
                arguments(RUNID, 82, 6, 2, 6, 3, 110, 5),
                arguments(ALATE, 82, 6, 2, 6, 3, 110, 5),
                arguments(GINDON, 82, 6, 2, 6, 3, 110, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("fighterCalculations")
    void fighterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NIGHT_ELF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Mebnina")
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
                arguments(HORA, 94, 7, 2, 7, 16, 150, 23),
                arguments(SIFER, 94, 7, 2, 7, 16, 150, 23),
                arguments(GETON, 94, 7, 2, 7, 16, 150, 23),
                arguments(RUNID, 94, 7, 2, 7, 16, 150, 23),
                arguments(ALATE, 94, 7, 2, 7, 16, 150, 23),
                arguments(GINDON, 94, 7, 2, 7, 16, 150, 23)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NIGHT_ELF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Rhikrod")
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
                arguments(HORA, 131, 10, 3, 10, 62, 300, 99),
                arguments(SIFER, 131, 10, 3, 10, 62, 300, 99),
                arguments(GETON, 131, 10, 3, 10, 64, 300, 99),
                arguments(RUNID, 131, 10, 3, 10, 62, 300, 99),
                arguments(ALATE, 133, 10, 3, 10, 63, 300, 99),
                arguments(GINDON, 131, 10, 3, 10, 62, 300, 99)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NIGHT_ELF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Rhurvud")
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
                arguments(HORA, 208, 15, 5, 15, 155, 600, 253),
                arguments(SIFER, 208, 15, 5, 15, 155, 600, 251),
                arguments(GETON, 208, 15, 5, 16, 159, 600, 251),
                arguments(RUNID, 208, 15, 5, 15, 155, 600, 251),
                arguments(ALATE, 211, 15, 5, 15, 157, 600, 251),
                arguments(GINDON, 208, 15, 5, 15, 155, 600, 251)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NIGHT_ELF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Conuth")
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
                arguments(HORA, 336, 25, 8, 25, 310, 1090, 502),
                arguments(SIFER, 336, 25, 8, 25, 310, 1100, 500),
                arguments(GETON, 336, 25, 8, 25, 316, 1090, 500),
                arguments(RUNID, 336, 25, 8, 25, 310, 1090, 505),
                arguments(ALATE, 341, 25, 8, 25, 313, 1090, 500),
                arguments(GINDON, 336, 25, 8, 25, 310, 1090, 510)
        );
    }
}
