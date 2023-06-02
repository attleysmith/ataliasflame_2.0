package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.God;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.HUMAN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class HumanCalculationTest {

    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterCalculationService characterCalculationService;

    @ParameterizedTest
    @MethodSource("rogueCalculations")
    void rogueTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        characterService.createCharacter(characterInput);

        Character character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> rogueCalculations() {
        return Stream.of(
                arguments(SIFER, 82, 21, 5, 3, 110),
                arguments(GETON, 82, 21, 5, 3, 110),
                arguments(RUNID, 82, 21, 5, 3, 110),
                arguments(ALATE, 82, 21, 5, 3, 110)
        );
    }

    @ParameterizedTest
    @MethodSource("fighterCalculations")
    void fighterTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        characterService.createCharacter(characterInput);
        setAttributes(5, 5, 5, 5, 2);

        Character character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> fighterCalculations() {
        return Stream.of(
                arguments(SIFER, 92, 23, 6, 15, 150),
                arguments(GETON, 92, 23, 6, 15, 150),
                arguments(RUNID, 92, 23, 6, 15, 150),
                arguments(ALATE, 92, 23, 6, 15, 150)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        characterService.createCharacter(characterInput);
        setAttributes(20, 20, 20, 20, 7);

        Character character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> paladinCalculations() {
        return Stream.of(
                arguments(SIFER, 128, 32, 8, 60, 300),
                arguments(GETON, 128, 32, 8, 62, 300),
                arguments(RUNID, 128, 32, 8, 60, 300),
                arguments(ALATE, 130, 32, 8, 61, 300)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        characterService.createCharacter(characterInput);
        setAttributes(50, 50, 50, 50, 20);

        Character character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> grandmasterCalculations() {
        return Stream.of(
                arguments(SIFER, 200, 50, 13, 150, 610),
                arguments(GETON, 200, 50, 13, 154, 600),
                arguments(RUNID, 200, 50, 13, 150, 600),
                arguments(ALATE, 203, 50, 13, 152, 600)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HUMAN)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        characterService.createCharacter(characterInput);
        setAttributes(100, 100, 100, 100, 40);

        Character character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> titanCalculations() {
        return Stream.of(
                arguments(SIFER, 320, 80, 20, 300, 1110),
                arguments(GETON, 320, 81, 20, 306, 1100),
                arguments(RUNID, 320, 80, 20, 300, 1100),
                arguments(ALATE, 325, 81, 20, 303, 1100)
        );
    }

    private void setAttributes(int strength, int dexterity, int constitution, int agility, int intelligence) {
        Character character = characterRepository.getCharacter();
        character.getAttributes().put(STRENGTH, strength);
        character.getAttributes().put(DEXTERITY, dexterity);
        character.getAttributes().put(CONSTITUTION, constitution);
        character.getAttributes().put(AGILITY, agility);
        character.getAttributes().put(INTELLIGENCE, intelligence);

        characterRepository.save(
                characterCalculationService.recalculateProperties(character)
        );
    }
}
