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
import static com.asgames.ataliasflame.domain.model.enums.Race.ELF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class ElfCalculationTest {

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
                .race(ELF)
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
                .race(ELF)
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
                arguments(SIFER, 94, 23, 6, 14, 150),
                arguments(GETON, 94, 23, 6, 14, 150),
                arguments(RUNID, 94, 23, 6, 14, 150),
                arguments(ALATE, 94, 23, 6, 14, 150)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
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
                arguments(SIFER, 134, 32, 8, 52, 280),
                arguments(GETON, 134, 32, 8, 54, 280),
                arguments(RUNID, 134, 32, 8, 52, 280),
                arguments(ALATE, 136, 32, 8, 53, 280)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
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
                arguments(SIFER, 216, 49, 12, 130, 560),
                arguments(GETON, 216, 49, 12, 134, 550),
                arguments(RUNID, 216, 49, 12, 130, 550),
                arguments(ALATE, 219, 49, 12, 132, 550)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ELF)
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
                arguments(SIFER, 352, 78, 18, 260, 1010),
                arguments(GETON, 352, 79, 18, 266, 1000),
                arguments(RUNID, 352, 78, 18, 260, 1000),
                arguments(ALATE, 357, 79, 18, 263, 1000)
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
