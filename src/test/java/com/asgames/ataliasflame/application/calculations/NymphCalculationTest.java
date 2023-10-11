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
    void rogueTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name("Castalia")
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
                arguments(RUNID, 82, 6, 2, 6, 3, 110, 5),
                arguments(GINDON, 82, 6, 2, 6, 3, 110, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("wizardCalculations")
    void wizardTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name("Kahliste")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD));
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

    private static Stream<Arguments> wizardCalculations() {
        return Stream.of(
                arguments(HORA, 87, 6, 2, 6, 7, 120, 93),
                arguments(SIFER, 87, 6, 2, 6, 7, 120, 91),
                arguments(RUNID, 87, 6, 2, 6, 7, 120, 91),
                arguments(GINDON, 87, 6, 2, 6, 7, 120, 91)
        );
    }

    @ParameterizedTest
    @MethodSource("mageCalculations")
    void mageTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name("Sylphise")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE));
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

    private static Stream<Arguments> mageCalculations() {
        return Stream.of(
                arguments(HORA, 107, 8, 2, 7, 22, 160, 398),
                arguments(SIFER, 107, 8, 2, 7, 22, 160, 396),
                arguments(RUNID, 107, 8, 2, 7, 22, 160, 396),
                arguments(GINDON, 107, 8, 2, 7, 22, 160, 396)
        );
    }

    @ParameterizedTest
    @MethodSource("witchmasterCalculations")
    void witchmasterTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name("Melita")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE, WITCHMASTER));
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

    private static Stream<Arguments> witchmasterCalculations() {
        return Stream.of(
                arguments(HORA, 138, 10, 3, 9, 57, 260, 996),
                arguments(SIFER, 138, 10, 3, 9, 57, 260, 992),
                arguments(RUNID, 138, 10, 3, 9, 57, 260, 997),
                arguments(GINDON, 138, 10, 3, 9, 57, 260, 1002)
        );
    }

    @ParameterizedTest
    @MethodSource("avatarCalculations")
    void avatarTest(God god, int attack, int defense, int minDamage, int maxDamage, int damageMultiplier, int health, int magic) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(god)
                .name("Gatalea")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE, WITCHMASTER, AVATAR));
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

    private static Stream<Arguments> avatarCalculations() {
        return Stream.of(
                arguments(HORA, 188, 14, 4, 13, 117, 460, 1988),
                arguments(SIFER, 188, 14, 4, 13, 117, 460, 1978),
                arguments(RUNID, 188, 14, 4, 13, 117, 460, 1988),
                arguments(GINDON, 188, 14, 4, 13, 117, 460, 1988)
        );
    }
}
