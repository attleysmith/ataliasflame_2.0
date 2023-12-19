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
import static com.asgames.ataliasflame.domain.model.enums.Race.HALF_ELF;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class HalfElfSpellsTest extends RaceSpellsTestBase {

    @Test
    void rogueSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Dranaith")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Reymaris")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Nigeverel")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Gilnelis")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Friluin")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE, WITCHMASTER, AVATAR));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(AVATAR)));
    }

    @Test
    void fighterSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Gilhice")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Colgotin")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Urimaer")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Hemdal")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Umejeon")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Zyllanann")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Shapeiros")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Kevreth")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Umeroris")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Hulion")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Gilzumin")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Teodrith")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Sylvalur")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Vicalath")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Adven")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Presmaer")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK, PRIEST, HIERARCH, ARCHANGEL));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(ARCHANGEL)));
    }

    @Test
    void sageSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Vicsaran")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE, SAGE));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(SAGE)));
    }

    @Test
    void championSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Urixisys")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Iancoril")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Petcyne")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Havezaphir")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(MONK, PRIEST, SAINT));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(SAINT)));
    }

    @Test
    void charlatanSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Charfarin")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(WIZARD, MAGE, CHARLATAN));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(CHARLATAN)));
    }

    @Test
    void apostleSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Glynlynn")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Iarqirelle")
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
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Hummar")
                .build();
        Character character = characterMaintenanceService.createCharacter(characterInput);
        String characterReference = character.getReference();
        upgradeCaste(characterReference, List.of(FIGHTER, PALADIN, BARBARIAN));

        List<Spell> spellList = characterMagicService.listCharacterSpells(characterReference);
        assertThat(spellNamesOf(spellList), containsInAnyOrder(AVAILABLE_SPELLS.get(BARBARIAN)));
    }

    @Test
    void witchDoctorSpells() {
        CharacterInput characterInput = CharacterInput.builder()
                .race(HALF_ELF)
                .gender(MALE)
                .defensiveGod(SIFER)
                .name("Perdal")
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
            Map.entry(ATALIAS_PRIEST, new SpellName[]{BALL_OF_ENERGY, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, ENERGY_ABSORPTION, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(MONK, new SpellName[]{DIVINE_PROTECTION, HEALING_WAVE, HEALING_WORD, WOUND_HEALING, FRIEND_IN_NEED}),
            Map.entry(PRIEST, new SpellName[]{BALL_OF_ENERGY, DIVINE_HAMMER, FIREBALL, SPLITTING_WIND, DIVINE_PROTECTION, ENERGY_SHIELD, STRENGTHENING, SHACKLE, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED}),
            Map.entry(HIERARCH, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, LIGHTNING_STRIKE, SPLITTING_WIND, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(ARCHANGEL, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(SAGE, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(CHAMPION, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, FIREBALL, INFERNO, LIGHTNING_STRIKE, SOUL_OUTBURST, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SOUL_STRIKE, WEAKENING, CURE, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS, PROJECTION_OF_ENERGY}),
            Map.entry(NOMAD, new SpellName[]{BALL_OF_ENERGY, FIREBALL, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SHACKLE, SOUL_STRIKE, WEAKENING, CURE, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS}),
            Map.entry(SHAMAN, new SpellName[]{BALL_OF_ENERGY, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, ENERGY_ABSORPTION, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(SAINT, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, SHACKLE, WEAKENING, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(CHARLATAN, new SpellName[]{BALL_OF_ENERGY, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, SOUL_STRIKE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN}),
            Map.entry(APOSTLE, new SpellName[]{BALL_OF_ENERGY, DIVINE_HAMMER, FIREBALL, LIGHTNING_STRIKE, SOUL_OUTBURST, SPLITTING_WIND, WRATH_OF_NATURE, DIVINE_PROTECTION, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, SOUL_CONNECTION, STRENGTHENING, SHACKLE, SOUL_STRIKE, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, SOUL_POWER, WOUND_HEALING, CALLING_ANIMALS, CALLING_THE_SOULS, FRIEND_IN_NEED, SUMMON_GUARDIAN}),
            Map.entry(CRUSADER, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, DIVINE_HAMMER, FIREBALL, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, DIVINE_PROTECTION, ENERGY_SHIELD, STRENGTHENING, SHACKLE, BREATH_OF_GOD, CURE, HEALING_WAVE, HEALING_WORD, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, FRIEND_IN_NEED, PROJECTION_OF_ENERGY}),
            Map.entry(BARBARIAN, new SpellName[]{BALL_OF_ENERGY, BLADES_OF_JUDGEMENT, FIREBALL, GLACIAL_BLOW, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY}),
            Map.entry(WITCH_DOCTOR, new SpellName[]{BALL_OF_ENERGY, FIREBALL, GLACIAL_BLOW, INFERNO, LIGHTNING_STRIKE, SPLITTING_WIND, WRATH_OF_NATURE, ENERGY_SHIELD, PROTECTIVE_HAND_OF_NATURE, STRENGTHENING, ENERGY_BLOCKING, POWER_DRAIN, SHACKLE, WEAKENING, CURE, ENERGY_ABSORPTION, HEALING_WAVE, POWER_OF_NATURE, RECHARGING, REGENERATION, WOUND_HEALING, CALLING_ANIMALS, PROJECTION_OF_ENERGY, SUMMON_GUARDIAN})
    );
}