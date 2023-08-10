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
import static com.asgames.ataliasflame.domain.model.enums.Race.MINOTAUR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class MinotaurCalculationTest extends RaceCalculationTestBase {

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        String characterName = characterName("Sirzeus");
        CharacterInput characterInput = CharacterInput.builder()
                .race(MINOTAUR)
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
        String characterName = characterName("Grudir");
        CharacterInput characterInput = CharacterInput.builder()
                .race(MINOTAUR)
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
                arguments(SIFER, 92, 24, 2, 7, 17, 160, 23),
                arguments(GETON, 92, 24, 2, 7, 17, 160, 23),
                arguments(RUNID, 92, 24, 2, 7, 17, 160, 23),
                arguments(ALATE, 92, 24, 2, 7, 17, 160, 23),
                arguments(GINDON, 92, 24, 2, 7, 17, 160, 23)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        String characterName = characterName("Rahdnur");
        CharacterInput characterInput = CharacterInput.builder()
                .race(MINOTAUR)
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
                arguments(SIFER, 123, 34, 3, 10, 66, 340, 89),
                arguments(GETON, 123, 34, 3, 10, 68, 340, 89),
                arguments(RUNID, 123, 34, 3, 10, 66, 340, 89),
                arguments(ALATE, 125, 34, 3, 10, 67, 340, 89),
                arguments(GINDON, 123, 34, 3, 10, 66, 340, 89)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        String characterName = characterName("Gararius");
        CharacterInput characterInput = CharacterInput.builder()
                .race(MINOTAUR)
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
                arguments(SIFER, 188, 53, 5, 16, 165, 710, 231),
                arguments(GETON, 188, 53, 5, 16, 169, 700, 231),
                arguments(RUNID, 188, 53, 5, 16, 165, 700, 231),
                arguments(ALATE, 191, 53, 5, 16, 167, 700, 231),
                arguments(GINDON, 188, 53, 5, 16, 165, 700, 231)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        String characterName = characterName("Nohrmud");
        CharacterInput characterInput = CharacterInput.builder()
                .race(MINOTAUR)
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
                arguments(SIFER, 296, 84, 9, 26, 330, 1310, 460),
                arguments(GETON, 296, 85, 9, 26, 336, 1300, 460),
                arguments(RUNID, 296, 84, 9, 26, 330, 1300, 465),
                arguments(ALATE, 301, 85, 9, 26, 333, 1300, 460),
                arguments(GINDON, 296, 84, 9, 26, 330, 1300, 460)
        );
    }
}
