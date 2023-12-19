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
import static com.asgames.ataliasflame.domain.model.enums.Race.DWARF;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class DwarfSpellsTest extends RaceSpellsTestBase {

    @Test
    void rogueSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Thrasgraeth")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Orred")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Dorafrag")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Arraem")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Thratmuki")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Aramdeag")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Sissamri")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Gaghoug")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Thafrus")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Fordrous")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Hestaeg")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Mozum")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Durigrolir")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Turroick")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Dosgred")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Snakerlig")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Dakkoc")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK, PRIEST, HIERARCH, ARCHANGEL));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(ARCHANGEL)));
    }

    @Test
    void championSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Darevuck")
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
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Daloggatum")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(TRACKER, RANGER, NOMAD));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(NOMAD)));
    }

    @Test
    void shamanSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Kotmoc")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(HERMIT, DRUID, SHAMAN));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(SHAMAN)));
    }

    @Test
    void apostleSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Torelgrarlum")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(TRACKER, RANGER, APOSTLE));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(APOSTLE)));
    }

    @Test
    void crusaderSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Doukerlig")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK, PRIEST, CRUSADER));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(CRUSADER)));
    }

    @Test
    void barbarianSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(DWARF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Skatmuil")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN, BARBARIAN));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(BARBARIAN)));
    }

    private static final Map<Caste, SpellName[]> AVAILABLE_SPELLS = Map.ofEntries(
            Map.entry(ROGUE, new SpellName[]{}),
            Map.entry(FIGHTER, new SpellName[]{STRENGTHENING, WOUND_HEALING}),
            Map.entry(PALADIN, new SpellName[]{FIREBALL, STRENGTHENING, CURE, REGENERATION, WOUND_HEALING}),
            Map.entry(GRANDMASTER, new SpellName[]{BLADES_OF_JUDGEMENT, FIREBALL, LIGHTNING_STRIKE, STRENGTHENING, WEAKENING, CURE, HEALING_WAVE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, SUMMON_GUARDIAN}),
            Map.entry(TITAN, new SpellName[]{BLADES_OF_JUDGEMENT, FIREBALL, LIGHTNING_STRIKE, SPLITTING_WIND, STRENGTHENING, SHACKLE, WEAKENING, CURE, HEALING_WAVE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, SUMMON_GUARDIAN}),
            Map.entry(TRACKER, new SpellName[]{POWER_OF_NATURE, WOUND_HEALING, CALLING_ANIMALS}),
            Map.entry(RANGER, new SpellName[]{FIREBALL, SOUL_OUTBURST, PROTECTIVE_HAND_OF_NATURE, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS}),
            Map.entry(PILGRIM, new SpellName[]{FIREBALL, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, SOUL_STRIKE, CURE, HEALING_WAVE, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS}),
            Map.entry(FREE_SOUL, new SpellName[]{BLADES_OF_JUDGEMENT, FIREBALL, INFERNO, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, WRATH_OF_NATURE, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SHACKLE, SOUL_STRIKE, CURE, HEALING_WAVE, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS, SUMMON_GUARDIAN}),
            Map.entry(HERMIT, new SpellName[]{PROTECTIVE_HAND_OF_NATURE, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS}),
            Map.entry(DRUID, new SpellName[]{FIREBALL, SPLITTING_WIND, PROTECTIVE_HAND_OF_NATURE, SHACKLE, WEAKENING, CURE, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS}),
            Map.entry(ARCHDRUID, new SpellName[]{FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, PROTECTIVE_HAND_OF_NATURE, POWER_DRAIN, SHACKLE, WEAKENING, CURE, HEALING_WAVE, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, SUMMON_GUARDIAN}),
            Map.entry(ATALIAS_PRIEST, new SpellName[]{DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, POWER_DRAIN, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, SUMMON_GUARDIAN}),
            Map.entry(MONK, new SpellName[]{DIVINE_PROTECTION, HEALING_WAVE, HEALING_WORD, WOUND_HEALING, FRIEND_IN_NEED}),
            Map.entry(PRIEST, new SpellName[]{DIVINE_HAMMER, FIREBALL, SPLITTING_WIND, DIVINE_PROTECTION, STRENGTHENING, SHACKLE, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED}),
            Map.entry(HIERARCH, new SpellName[]{BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, LIGHTNING_STRIKE, SPLITTING_WIND, DIVINE_PROTECTION, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, SUMMON_GUARDIAN}),
            Map.entry(ARCHANGEL, new SpellName[]{BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, SUMMON_GUARDIAN}),
            Map.entry(CHAMPION, new SpellName[]{BLADES_OF_JUDGEMENT, FIREBALL, INFERNO, LIGHTNING_STRIKE, SOUL_OUTBURST, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SOUL_STRIKE, WEAKENING, CURE, HEALING_WAVE, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS}),
            Map.entry(NOMAD, new SpellName[]{FIREBALL, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, WRATH_OF_NATURE, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SHACKLE, SOUL_STRIKE, WEAKENING, CURE, HEALING_WAVE, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS}),
            Map.entry(SHAMAN, new SpellName[]{DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, POWER_DRAIN, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, SUMMON_GUARDIAN}),
            Map.entry(APOSTLE, new SpellName[]{DIVINE_HAMMER, FIREBALL, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SHACKLE, SOUL_STRIKE, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS, FRIEND_IN_NEED, SUMMON_GUARDIAN}),
            Map.entry(CRUSADER, new SpellName[]{BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, DIVINE_PROTECTION, STRENGTHENING, SHACKLE, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED}),
            Map.entry(BARBARIAN, new SpellName[]{BLADES_OF_JUDGEMENT, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, WEAKENING, CURE, HEALING_WAVE, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS})
    );
}