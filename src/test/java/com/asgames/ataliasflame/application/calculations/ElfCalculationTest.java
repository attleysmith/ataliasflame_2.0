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
import static com.asgames.ataliasflame.domain.model.enums.Race.ELF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class ElfCalculationTest extends RaceCalculationTestBase {

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        String characterName = characterName("Illithor");
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
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
        String characterName = characterName("Vamir");
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
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
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> fighterCalculations() {
        return Stream.of(
                arguments(HORA, 94, 24, 2, 7, 14, 150, 23),
                arguments(SIFER, 94, 24, 2, 7, 14, 150, 23),
                arguments(GETON, 94, 24, 2, 7, 14, 150, 23),
                arguments(RUNID, 94, 24, 2, 7, 14, 150, 23),
                arguments(ALATE, 94, 24, 2, 7, 14, 150, 23),
                arguments(GINDON, 94, 24, 2, 7, 14, 150, 23)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        String characterName = characterName("Althidon");
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
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
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> paladinCalculations() {
        return Stream.of(
                arguments(HORA, 134, 33, 3, 9, 52, 280, 109),
                arguments(SIFER, 134, 33, 3, 9, 52, 280, 109),
                arguments(GETON, 134, 33, 3, 9, 54, 280, 109),
                arguments(RUNID, 134, 33, 3, 9, 52, 280, 109),
                arguments(ALATE, 136, 33, 3, 9, 53, 280, 109),
                arguments(GINDON, 134, 33, 3, 9, 52, 280, 109)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        String characterName = characterName("Arbelladon");
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
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
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> grandmasterCalculations() {
        return Stream.of(
                arguments(HORA, 216, 51, 5, 14, 130, 550, 263),
                arguments(SIFER, 216, 51, 5, 14, 130, 560, 261),
                arguments(GETON, 216, 52, 5, 14, 134, 550, 261),
                arguments(RUNID, 216, 51, 5, 14, 130, 550, 266),
                arguments(ALATE, 219, 52, 5, 14, 132, 550, 261),
                arguments(GINDON, 216, 51, 5, 14, 130, 550, 261)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        String characterName = characterName("Morthil");
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
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
        assertThat(character.getHealth().totalValue(), is(equalTo(health)));
        assertThat(character.getMagic().totalValue(), is(equalTo(magic)));
    }

    private static Stream<Arguments> titanCalculations() {
        return Stream.of(
                arguments(HORA, 352, 82, 7, 22, 260, 1000, 547),
                arguments(SIFER, 352, 82, 7, 22, 260, 1010, 545),
                arguments(GETON, 352, 83, 7, 22, 266, 1000, 545),
                arguments(RUNID, 352, 82, 7, 22, 260, 1000, 550),
                arguments(ALATE, 357, 83, 7, 22, 263, 1000, 545),
                arguments(GINDON, 352, 82, 7, 22, 260, 1000, 545)
        );
    }
}
