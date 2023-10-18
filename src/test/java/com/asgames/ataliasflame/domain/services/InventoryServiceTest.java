package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.application.CharacterMaintenanceService;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.entities.Weapon;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.GETON;
import static com.asgames.ataliasflame.domain.model.enums.God.SIFER;
import static com.asgames.ataliasflame.domain.model.enums.Race.HUMAN;
import static com.asgames.ataliasflame.domain.model.enums.Race.ORC;
import static com.asgames.ataliasflame.domain.model.enums.ShieldTemplate.BUCKLER;
import static com.asgames.ataliasflame.domain.model.enums.WeaponTemplate.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class InventoryServiceTest {

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    private InventoryService inventoryService;

    @ParameterizedTest
    @MethodSource("weaponSource")
    void switchSingleWeaponsTest(Weapon primaryWeapon, Weapon secondaryWeapon) {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Ramon")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        // and
        character.setPrimaryWeapon(primaryWeapon);
        character.setSecondaryWeapon(secondaryWeapon);

        // when
        inventoryService.switchWeapons(character);

        // then
        assertThat(character.getPrimaryWeapon().orElse(null), is(secondaryWeapon));
        assertThat(character.getSecondaryWeapon().orElse(null), is(primaryWeapon));
    }

    @ParameterizedTest
    @MethodSource("weaponSourceWithShield")
    void switchWeaponsWithShieldTest(Weapon primaryWeapon, Weapon secondaryWeapon, Shield shield, String expectedShieldCode) {
        // given
        CharacterInput characterInput = CharacterInput.builder()
                .race(ORC)
                .gender(FEMALE)
                .defensiveGod(GETON)
                .name("Shiva")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        // and
        character.setPrimaryWeapon(primaryWeapon);
        character.setSecondaryWeapon(secondaryWeapon);
        character.setShield(shield);

        // when
        inventoryService.switchWeapons(character);

        // then
        assertThat(character.getPrimaryWeapon().orElse(null), is(secondaryWeapon));
        assertThat(character.getSecondaryWeapon().orElse(null), is(primaryWeapon));
        assertThat(character.getShield().map(Shield::getCode).orElse("NONE"), is(expectedShieldCode));
    }

    private static Stream<Arguments> weaponSource() {
        return Stream.of(
                arguments(null, null),
                arguments(DAGGER.instance(), null),
                arguments(null, DAGGER.instance()),
                arguments(DAGGER.instance(), SWORD.instance())
        );
    }

    private static Stream<Arguments> weaponSourceWithShield() {
        return Stream.of(
                arguments(DAGGER.instance(), SPEAR.instance(), BUCKLER.instance(), "NONE"),
                arguments(DAGGER.instance(), SWORD.instance(), BUCKLER.instance(), BUCKLER.name())
        );
    }
}