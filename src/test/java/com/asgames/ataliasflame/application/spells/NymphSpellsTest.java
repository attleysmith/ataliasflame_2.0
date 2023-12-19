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
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.God.SIFER;
import static com.asgames.ataliasflame.domain.model.enums.Race.NYMPH;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class NymphSpellsTest extends RaceSpellsTestBase {

    @Test
    void rogueSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Liberi")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        assertThat(character.getCaste(), is(ROGUE));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellList, containsInAnyOrder(AVAILABLE_SPELLS.get(ROGUE)));
    }

    @Test
    void wizardSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Mellaniphe")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(WIZARD)));
    }

    @Test
    void mageSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Merylle")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(MAGE)));
    }

    @Test
    void witchmasterSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Lametisa")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE, WITCHMASTER));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(WITCHMASTER)));
    }

    @Test
    void avatarSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Cirohsa")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE, WITCHMASTER, AVATAR));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(AVATAR)));
    }

    @Test
    void hermitSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Dione")
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
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Ianisse")
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
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Arethiusei")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(HERMIT, DRUID, ARCHDRUID));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(ARCHDRUID)));
    }

    @Test
    void ataliasPriestSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Arasine")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(HERMIT, DRUID, ARCHDRUID, ATALIAS_PRIEST));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(ATALIAS_PRIEST)));
    }

    @Test
    void monkSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Helia")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(MONK)));
    }

    @Test
    void priestSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Sylphise")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK, PRIEST));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(PRIEST)));
    }

    @Test
    void hierarchSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Akraia")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK, PRIEST, HIERARCH));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(HIERARCH)));
    }

    @Test
    void archangelSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Kimopola")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK, PRIEST, HIERARCH, ARCHANGEL));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(ARCHANGEL)));
    }

    @Test
    void shamanSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Eriphia")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(HERMIT, DRUID, SHAMAN));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(SHAMAN)));
    }

    @Test
    void saintSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Eriphise")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK, PRIEST, SAINT));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(SAINT)));
    }

    @Test
    void witchDoctorSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(NYMPH)
                .gender(FEMALE)
                .defensiveGod(SIFER)
                .name("Eludora")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(HERMIT, DRUID, WITCH_DOCTOR));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(WITCH_DOCTOR)));
    }

    private static final Map<Caste, SpellName[]> AVAILABLE_SPELLS = Map.ofEntries(
            Map.entry(ROGUE, new SpellName[]{}),
            Map.entry(WIZARD, new SpellName[]{BALL_OF_ENERGY, FIREBALL, SPLITTING_WIND, ENERGY_SHIELD, WEAKENING, REGENERATION, WOUND_HEALING}),
            Map.entry(MAGE, new SpellName[]{BALL_OF_ENERGY, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, ENERGY_SHIELD, STRENGTHENING, ENERGY_BLOCKING, WEAKENING, CURE, HEALING_WAVE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(WITCHMASTER, new SpellName[]{BALL_OF_ENERGY, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, ENERGY_SHIELD, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(AVATAR, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(HERMIT, new SpellName[]{PROTECTIVE_HAND_OF_NATURE, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS}),
            Map.entry(DRUID, new SpellName[]{BALL_OF_ENERGY, FIREBALL, SPLITTING_WIND, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, SHACKLE, WEAKENING, CURE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS}),
            Map.entry(ARCHDRUID, new SpellName[]{BALL_OF_ENERGY, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, POWER_DRAIN, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(ATALIAS_PRIEST, new SpellName[]{BALL_OF_ENERGY, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, ENERGY_ABSORPTION, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(MONK, new SpellName[]{DIVINE_PROTECTION, HEALING_WAVE, HEALING_WORD, WOUND_HEALING, FRIEND_IN_NEED}),
            Map.entry(PRIEST, new SpellName[]{BALL_OF_ENERGY, DIVINE_HAMMER, FIREBALL, SPLITTING_WIND, DIVINE_PROTECTION, ENERGY_SHIELD, STRENGTHENING, SHACKLE, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED}),
            Map.entry(HIERARCH, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, LIGHTNING_STRIKE, SPLITTING_WIND, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(ARCHANGEL, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(SHAMAN, new SpellName[]{BALL_OF_ENERGY, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, ENERGY_ABSORPTION, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(SAINT, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(WITCH_DOCTOR, new SpellName[]{BALL_OF_ENERGY, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN})
    );
}