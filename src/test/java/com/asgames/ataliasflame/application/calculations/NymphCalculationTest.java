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
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.NYMPH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class NymphCalculationTest extends RaceCalculationTestBase {

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Castalia";
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
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
                arguments(RUNID, 82, 22, 2, 6, 3, 110),
                arguments(GINDON, 82, 22, 2, 6, 3, 110)
        );
    }

    @ParameterizedTest
    @MethodSource("wizardCalculations")
    void wizardTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Kahliste";
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterMaintenanceService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(WIZARD));
        addDagger(characterName);

        Character character = characterMaintenanceService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> wizardCalculations() {
        return Stream.of(
                arguments(HORA, 87, 23, 2, 6, 7, 120),
                arguments(SIFER, 87, 23, 2, 6, 7, 120),
                arguments(RUNID, 87, 23, 2, 6, 7, 120),
                arguments(GINDON, 87, 23, 2, 6, 7, 120)
        );
    }

    @ParameterizedTest
    @MethodSource("mageCalculations")
    void mageTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Sylphise";
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterMaintenanceService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(WIZARD, MAGE));
        addDagger(characterName);

        Character character = characterMaintenanceService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> mageCalculations() {
        return Stream.of(
                arguments(HORA, 107, 27, 2, 7, 22, 160),
                arguments(SIFER, 107, 27, 2, 7, 22, 160),
                arguments(RUNID, 107, 27, 2, 7, 22, 160),
                arguments(GINDON, 107, 27, 2, 7, 22, 160)
        );
    }

    @ParameterizedTest
    @MethodSource("witchmasterCalculations")
    void witchmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Melita";
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterMaintenanceService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(WIZARD, MAGE, WITCHMASTER));
        addDagger(characterName);

        Character character = characterMaintenanceService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> witchmasterCalculations() {
        return Stream.of(
                arguments(HORA, 138, 34, 3, 9, 57, 260),
                arguments(SIFER, 138, 34, 3, 9, 57, 260),
                arguments(RUNID, 138, 34, 3, 9, 57, 260),
                arguments(GINDON, 138, 34, 3, 9, 57, 260)
        );
    }

    @ParameterizedTest
    @MethodSource("avatarCalculations")
    void avatarTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health) {
        String characterName = "Gatalea";
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name(characterName)
                .build();
        characterMaintenanceService.createCharacter(characterInput);
        upgradeCaste(characterName, List.of(WIZARD, MAGE, WITCHMASTER, AVATAR));
        addDagger(characterName);

        Character character = characterMaintenanceService.getCharacter(characterName);
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getMinDamage(), is(equalTo(minDamage)));
        assertThat(character.getMaxDamage(), is(equalTo(maxDamage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> avatarCalculations() {
        return Stream.of(
                arguments(HORA, 188, 47, 4, 13, 117, 460),
                arguments(SIFER, 188, 47, 4, 13, 117, 460),
                arguments(RUNID, 188, 47, 4, 13, 117, 460),
                arguments(GINDON, 188, 47, 4, 13, 117, 460)
        );
    }
}
