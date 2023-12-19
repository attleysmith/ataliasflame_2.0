package com.asgames.ataliasflame.application.spells;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.SIFER;
import static com.asgames.ataliasflame.domain.model.enums.Race.ARIMASPI;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class ArimaspiSpellsTest extends RaceSpellsTestBase {

    @Test
    void rogueSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Wukug")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        assertThat(character.getCaste(), is(ROGUE));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellList, containsInAnyOrder(AVAILABLE_SPELLS.get(ROGUE)));
    }

    @Test
    void fighterSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Takur")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(FIGHTER)));
    }

    @Test
    void paladinSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Vurg")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(PALADIN)));
    }

    @Test
    void grandmasterSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Kag")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN, GRANDMASTER));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(GRANDMASTER)));
    }

    @Test
    void titanSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Oruk")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN, GRANDMASTER, TITAN));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(TITAN)));
    }

    @Test
    void trackerSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Katerg")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(TRACKER));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(TRACKER)));
    }

    @Test
    void rangerSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Taig")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(TRACKER, RANGER));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(RANGER)));
    }

    @Test
    void pilgrimSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Yakokur")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(TRACKER, RANGER, PILGRIM));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(PILGRIM)));
    }

    @Test
    void freeSoulSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Braokor")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(TRACKER, RANGER, PILGRIM, FREE_SOUL));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(FREE_SOUL)));
    }

    @Test
    void hermitSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Zimukig")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(HERMIT));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(HERMIT)));
    }

    @Test
    void druidSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Worth")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(HERMIT, DRUID));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(DRUID)));
    }

    @Test
    void archdruidSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Klikor")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(HERMIT, DRUID, ARCHDRUID));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(ARCHDRUID)));
    }

    @Test
    void championSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Yerob")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN, CHAMPION));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(CHAMPION)));
    }

    @Test
    void nomadSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Xorok")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(TRACKER, RANGER, NOMAD));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(NOMAD)));
    }

    @Test
    void barbarianSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(ARIMASPI)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Kizir")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN, BARBARIAN));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(BARBARIAN)));
    }

    private static final Map<Caste, SpellName[]> AVAILABLE_SPELLS = Map.ofEntries(
            Map.entry(ROGUE, new SpellName[]{}),
            Map.entry(FIGHTER, new SpellName[]{ENERGY_SHIELD, STRENGTHENING, WOUND_HEALING}),
            Map.entry(PALADIN, new SpellName[]{BALL_OF_ENERGY, FIREBALL, ENERGY_SHIELD, STRENGTHENING, CURE, REGENERATION, WOUND_HEALING}),
            Map.entry(GRANDMASTER, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, FIREBALL, LIGHTNING_STRIKE, ENERGY_SHIELD, STRENGTHENING, WEAKENING, CURE, HEALING_WAVE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(TITAN, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, FIREBALL, LIGHTNING_STRIKE, SPLITTING_WIND, ENERGY_SHIELD, STRENGTHENING, ENERGY_BLOCKING, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(TRACKER, new SpellName[]{POWER_OF_NATURE, WOUND_HEALING, CALLING_ANIMALS}),
            Map.entry(RANGER, new SpellName[]{FIREBALL, SOUL_OUTBURST, PROTECTIVE_HAND_OF_NATURE, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS}),
            Map.entry(PILGRIM, new SpellName[]{FIREBALL, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, SOUL_STRIKE, CURE, HEALING_WAVE, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS}),
            Map.entry(FREE_SOUL, new SpellName[]{BLADES_OF_JUDGEMENT, FIREBALL, INFERNO, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, WRATH_OF_NATURE, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SHACKLE, SOUL_STRIKE, CURE, HEALING_WAVE, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS, SUMMON_GUARDIAN}),
            Map.entry(HERMIT, new SpellName[]{PROTECTIVE_HAND_OF_NATURE, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS}),
            Map.entry(DRUID, new SpellName[]{BALL_OF_ENERGY, FIREBALL, SPLITTING_WIND, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, SHACKLE, WEAKENING, CURE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS}),
            Map.entry(ARCHDRUID, new SpellName[]{BALL_OF_ENERGY, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, POWER_DRAIN, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(CHAMPION, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, FIREBALL, INFERNO, LIGHTNING_STRIKE, SOUL_OUTBURST, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SOUL_STRIKE, WEAKENING, CURE, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS, PROJECTION_OF_ENERGY}),
            Map.entry(NOMAD, new SpellName[]{BALL_OF_ENERGY, FIREBALL, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SHACKLE, SOUL_STRIKE, WEAKENING, CURE, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS}),
            Map.entry(BARBARIAN, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY})
    );
}