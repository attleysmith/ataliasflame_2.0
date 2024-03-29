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
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name("Throlf")
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
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name("Iblur")
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
                arguments(HORA, 92, 7, 2, 7, 15, 150, 23),
                arguments(SIFER, 92, 7, 2, 7, 15, 150, 23),
                arguments(GETON, 92, 7, 2, 7, 15, 150, 23),
                arguments(RUNID, 92, 7, 2, 7, 15, 150, 23),
                arguments(ALATE, 92, 7, 2, 7, 15, 150, 23),
                arguments(GINDON, 92, 7, 2, 7, 15, 150, 23)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name("Baunweg")
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
                arguments(HORA, 130, 10, 3, 10, 63, 310, 99),
                arguments(SIFER, 130, 10, 3, 10, 63, 310, 99),
                arguments(GETON, 130, 10, 3, 10, 65, 310, 99),
                arguments(RUNID, 130, 10, 3, 10, 63, 310, 99),
                arguments(ALATE, 132, 10, 3, 10, 64, 310, 99),
                arguments(GINDON, 130, 10, 3, 10, 63, 310, 99)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name("Khalumm")
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
                arguments(HORA, 207, 16, 5, 16, 159, 630, 243),
                arguments(SIFER, 207, 16, 5, 16, 159, 630, 241),
                arguments(GETON, 207, 16, 5, 16, 161, 630, 241),
                arguments(RUNID, 207, 16, 5, 16, 159, 630, 241),
                arguments(ALATE, 209, 16, 5, 16, 160, 630, 241),
                arguments(GINDON, 207, 16, 5, 16, 159, 630, 241)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(god)
                .name("Olk")
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
                arguments(HORA, 332, 25, 8, 25, 315, 1150, 492),
                arguments(SIFER, 332, 25, 8, 25, 315, 1160, 490),
                arguments(GETON, 332, 25, 8, 25, 321, 1150, 490),
                arguments(RUNID, 332, 25, 8, 25, 315, 1150, 490),
                arguments(ALATE, 337, 25, 8, 25, 318, 1150, 490),
                arguments(GINDON, 332, 25, 8, 25, 315, 1150, 490)
        );
    }
}
