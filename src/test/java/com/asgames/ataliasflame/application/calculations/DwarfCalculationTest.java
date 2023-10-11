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
import static com.asgames.ataliasflame.domain.model.enums.Race.DWARF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class DwarfCalculationTest extends RaceCalculationTestBase {

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Thangrar")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Forbik")
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
                arguments(SIFER, 91, 7, 2, 7, 17, 160, 23),
                arguments(GETON, 91, 7, 2, 7, 17, 160, 23),
                arguments(RUNID, 91, 7, 2, 7, 17, 160, 23),
                arguments(ALATE, 91, 7, 2, 7, 17, 160, 23),
                arguments(GINDON, 91, 7, 2, 7, 17, 160, 23)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Anmum")
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
                arguments(SIFER, 122, 9, 3, 10, 62, 340, 89),
                arguments(GETON, 122, 9, 3, 10, 64, 340, 89),
                arguments(RUNID, 122, 9, 3, 10, 62, 340, 89),
                arguments(ALATE, 123, 9, 3, 10, 63, 340, 89),
                arguments(GINDON, 122, 9, 3, 10, 62, 340, 89)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Gronmomi")
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
                arguments(SIFER, 184, 14, 5, 15, 155, 710, 231),
                arguments(GETON, 184, 15, 5, 16, 159, 700, 231),
                arguments(RUNID, 184, 14, 5, 15, 155, 700, 231),
                arguments(ALATE, 187, 15, 5, 15, 157, 700, 231),
                arguments(GINDON, 184, 14, 5, 15, 155, 700, 231)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(god)
                .name("Kirronlim")
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
                arguments(SIFER, 288, 23, 8, 25, 310, 1310, 470),
                arguments(GETON, 288, 23, 8, 25, 316, 1300, 470),
                arguments(RUNID, 288, 23, 8, 25, 310, 1300, 475),
                arguments(ALATE, 293, 23, 8, 25, 313, 1300, 470),
                arguments(GINDON, 288, 23, 8, 25, 310, 1300, 480)
        );
    }
}
