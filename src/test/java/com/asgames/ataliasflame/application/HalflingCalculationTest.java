package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.God;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.MockConstants.CASTE_DETAILS;
import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.HALFLING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class HalflingCalculationTest {

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
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        Character character = characterService.createCharacter(characterInput);
        assertThat(character.getCaste(), is(ROGUE));

        character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> rogueCalculations() {
        return Stream.of(
                arguments(HORA, 84, 21, 5, 4, 110),
                arguments(SIFER, 84, 21, 5, 4, 110),
                arguments(GETON, 84, 21, 5, 4, 110),
                arguments(RUNID, 84, 21, 5, 4, 110),
                arguments(ALATE, 84, 21, 5, 4, 110)
        );
    }

    @ParameterizedTest
    @MethodSource("fighterCalculations")
    void fighterTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        Character character = characterService.createCharacter(characterInput);
        upgradeCaste(character.getCaste(), FIGHTER);

        character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> fighterCalculations() {
        return Stream.of(
                arguments(HORA, 97, 23, 6, 16, 150),
                arguments(SIFER, 97, 23, 6, 16, 150),
                arguments(GETON, 97, 23, 6, 16, 150),
                arguments(RUNID, 97, 23, 6, 16, 150),
                arguments(ALATE, 97, 23, 6, 16, 150)
        );
    }

    @ParameterizedTest
    @MethodSource("paladinCalculations")
    void paladinTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        Character character = characterService.createCharacter(characterInput);
        upgradeCaste(character.getCaste(), PALADIN);

        character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> paladinCalculations() {
        return Stream.of(
                arguments(HORA, 143, 33, 8, 58, 280),
                arguments(SIFER, 143, 33, 8, 58, 280),
                arguments(GETON, 143, 33, 8, 60, 280),
                arguments(RUNID, 143, 33, 8, 58, 280),
                arguments(ALATE, 145, 33, 8, 59, 280)
        );
    }

    @ParameterizedTest
    @MethodSource("grandmasterCalculations")
    void grandmasterTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        Character character = characterService.createCharacter(characterInput);
        upgradeCaste(character.getCaste(), GRANDMASTER);

        character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> grandmasterCalculations() {
        return Stream.of(
                arguments(HORA, 238, 52, 12, 145, 550),
                arguments(SIFER, 238, 52, 12, 145, 560),
                arguments(GETON, 238, 52, 12, 149, 550),
                arguments(RUNID, 238, 52, 12, 145, 550),
                arguments(ALATE, 242, 52, 12, 147, 550)
        );
    }

    @ParameterizedTest
    @MethodSource("titanCalculations")
    void titanTest(God god, int attack, int defense, int damage, int damageMultiplier, int health) {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALFLING)
                .gender(MALE)
                .defensiveGod(god)
                .name("Takemoto")
                .build();
        Character character = characterService.createCharacter(characterInput);
        upgradeCaste(character.getCaste(), TITAN);

        character = characterRepository.getCharacter();
        assertThat(character.getAttack(), is(equalTo(attack)));
        assertThat(character.getDefense(), is(equalTo(defense)));
        assertThat(character.getDamage(), is(equalTo(damage)));
        assertThat(character.getDamageMultiplier(), is(equalTo(damageMultiplier)));
        assertThat(character.getTotalHealth(), is(equalTo(health)));
    }

    private static Stream<Arguments> titanCalculations() {
        return Stream.of(
                arguments(HORA, 396, 83, 20, 290, 1000),
                arguments(SIFER, 396, 83, 20, 290, 1010),
                arguments(GETON, 396, 84, 20, 296, 1000),
                arguments(RUNID, 396, 83, 20, 290, 1000),
                arguments(ALATE, 401, 84, 20, 293, 1000)
        );
    }

    private void upgradeCaste(Caste actualCaste, Caste targetCaste) {
        if (actualCaste.equals(targetCaste)) {
            return;
        }
        Caste nextCaste = CASTE_DETAILS.get(actualCaste).getNextCastes().get(0);
        CasteDetails nextCasteDetails = CASTE_DETAILS.get(nextCaste);
        setAttributes(nextCasteDetails.getMinimumAttributes());

        Character character = characterService.upgradeCaste(nextCaste);
        upgradeCaste(character.getCaste(), targetCaste);
    }

    private void setAttributes(Map<Attribute, Integer> targetAttributes) {
        Character character = characterRepository.getCharacter();
        character.getAttributes().put(STRENGTH, targetAttributes.get(STRENGTH));
        character.getAttributes().put(DEXTERITY, targetAttributes.get(DEXTERITY));
        character.getAttributes().put(CONSTITUTION, targetAttributes.get(CONSTITUTION));
        character.getAttributes().put(AGILITY, targetAttributes.get(AGILITY));
        character.getAttributes().put(INTELLIGENCE, targetAttributes.get(INTELLIGENCE));

        characterRepository.save(
                characterCalculationService.recalculateProperties(character)
        );
    }
}
