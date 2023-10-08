package com.asgames.ataliasflame.domain.model.enums;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;

public enum SpellName {
    FIREBALL(List.of(), List.of(FIGHTER, TRACKER, HERMIT, MONK), List.of()),
    LIGHTNING_STRIKE(List.of(), List.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, HERMIT, DRUID, MONK, PRIEST), List.of()),
    INFERNO(List.of(WARRIOR), List.of(WIZARD, MAGE, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, ARCHDRUID, MONK, PRIEST, HIERARCH), List.of()),
    BLADES_OF_JUDGEMENT(List.of(NATURE_DWELLER), List.of(WIZARD, MAGE, WITCHMASTER, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM, MONK, PRIEST), List.of(HALFLING)),
    GLACIAL_BLOW(List.of(WARRIOR, WANDERER), List.of(WIZARD, HERMIT, DRUID, MONK, PRIEST, HIERARCH), List.of()),
    BALL_OF_ENERGY(List.of(), List.of(FIGHTER, HERMIT, MONK), List.of()),
    SOUL_OUTBURST(List.of(), List.of(), List.of()),
    WRATH_OF_NATURE(List.of(WARRIOR), List.of(WIZARD, MAGE, WITCHMASTER, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, MONK, PRIEST, HIERARCH), List.of()),
    SPLITTING_WIND(List.of(), List.of(FIGHTER, PALADIN, GRANDMASTER, TRACKER, RANGER, HERMIT, MONK), List.of()),
    DIVINE_HAMMER(List.of(), List.of(MONK), List.of(HALFLING)),
    WOUND_HEALING(List.of(), List.of(), List.of()),
    HEALING_WORD(List.of(SORCERER), List.of(), List.of()),
    CURE(List.of(), List.of(WIZARD, FIGHTER, TRACKER, RANGER, HERMIT, MONK), List.of()),
    REGENERATION(List.of(), List.of(FIGHTER, TRACKER, MONK), List.of()),
    BREATH_OF_GOD(List.of(), List.of(MONK), List.of()),
    POWER_OF_NATURE(List.of(WARRIOR), List.of(WIZARD, MAGE, MONK), List.of()),
    SOUL_POWER(List.of(), List.of(), List.of()),
    RECHARGING(List.of(), List.of(WIZARD, FIGHTER, PALADIN, HERMIT, MONK, PRIEST), List.of()),
    HEALING_WAVE(List.of(), List.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, HERMIT, DRUID), List.of(ORC)),
    ENERGY_ABSORPTION(List.of(CLERIC), List.of(WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, HERMIT, DRUID), List.of()),
    DIVINE_PROTECTION(List.of(), List.of(), List.of()),
    STRENGTHENING(List.of(), List.of(WIZARD, TRACKER, RANGER, HERMIT, DRUID, ARCHDRUID, MONK), List.of()),
    SOUL_CONNECTION(List.of(), List.of(RANGER, PILGRIM), List.of()),
    PROTECTIVE_HAND_OF_NATURE(List.of(SORCERER, WARRIOR), List.of(TRACKER, MONK, PRIEST), List.of()),
    ENERGY_SHIELD(List.of(), List.of(HERMIT, MONK), List.of()),
    SOUL_STRIKE(List.of(), List.of(RANGER), List.of()),
    WEAKENING(List.of(WANDERER), List.of(FIGHTER, PALADIN, HERMIT, MONK, PRIEST), List.of()),
    SHACKLE(List.of(), List.of(WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, TRACKER, RANGER, HERMIT, MONK), List.of()),
    ENERGY_BLOCKING(List.of(), List.of(WIZARD, FIGHTER, PALADIN, GRANDMASTER, HERMIT, DRUID, ARCHDRUID, MONK, PRIEST, HIERARCH), List.of()),
    POWER_DRAIN(List.of(WARRIOR, WANDERER, CLERIC), List.of(WIZARD, MAGE, HERMIT, DRUID), List.of()),
    CALLING_THE_SOULS(List.of(), List.of(), List.of()),
    CALLING_ANIMALS(List.of(), List.of(WIZARD, FIGHTER, PALADIN, MONK), List.of(MINOTAUR)),
    SUMMON_GUARDIAN(List.of(), List.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, MONK, PRIEST), List.of(ORC)),
    PROJECTION_OF_ENERGY(List.of(), List.of(WIZARD, FIGHTER, PALADIN, HERMIT, DRUID, MONK, PRIEST), List.of()),
    FRIEND_IN_NEED(List.of(SORCERER, NATURE_DWELLER), List.of(), List.of());

    SpellName(List<CasteGroup> prohibitedCasteGroups, List<Caste> prohibitedCastes, List<Race> prohibitedRaces) {
        this.prohibitedCasteGroups = prohibitedCasteGroups;
        this.prohibitedCastes = prohibitedCastes;
        this.prohibitedRaces = prohibitedRaces;
    }

    public final List<CasteGroup> prohibitedCasteGroups;
    public final List<Caste> prohibitedCastes;
    public final List<Race> prohibitedRaces;
}
