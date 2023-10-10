package com.asgames.ataliasflame.domain.model.enums;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.*;

public enum SpellName {
    FIREBALL(ELEMENTAL, ATTACK,
            List.of(), List.of(FIGHTER, TRACKER, HERMIT, MONK), List.of()),
    LIGHTNING_STRIKE(ELEMENTAL, ATTACK,
            List.of(), List.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, HERMIT, DRUID, MONK, PRIEST), List.of()),
    INFERNO(ELEMENTAL, ATTACK,
            List.of(WARRIOR), List.of(WIZARD, MAGE, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, ARCHDRUID, MONK, PRIEST, HIERARCH), List.of()),
    BLADES_OF_JUDGEMENT(GENERAL, ATTACK,
            List.of(NATURE_DWELLER), List.of(WIZARD, MAGE, WITCHMASTER, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM, MONK, PRIEST), List.of(HALFLING)),
    GLACIAL_BLOW(ELEMENTAL, ATTACK,
            List.of(WARRIOR, WANDERER), List.of(WIZARD, HERMIT, DRUID, MONK, PRIEST, HIERARCH), List.of()),
    BALL_OF_ENERGY(ENERGY, ATTACK,
            List.of(), List.of(FIGHTER, HERMIT, MONK), List.of()),
    SOUL_OUTBURST(SOUL, ATTACK,
            List.of(), List.of(), List.of()),
    WRATH_OF_NATURE(NATURE, ATTACK,
            List.of(WARRIOR), List.of(WIZARD, MAGE, WITCHMASTER, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, MONK, PRIEST, HIERARCH), List.of()),
    SPLITTING_WIND(ELEMENTAL, ATTACK,
            List.of(), List.of(FIGHTER, PALADIN, GRANDMASTER, TRACKER, RANGER, HERMIT, MONK), List.of()),
    DIVINE_HAMMER(DIVINE, ATTACK,
            List.of(), List.of(MONK), List.of(HALFLING)),
    WOUND_HEALING(GENERAL, HEALING,
            List.of(), List.of(), List.of()),
    HEALING_WORD(DIVINE, HEALING,
            List.of(SORCERER), List.of(), List.of()),
    CURE(GENERAL, HEALING,
            List.of(), List.of(WIZARD, FIGHTER, TRACKER, RANGER, HERMIT, MONK), List.of()),
    REGENERATION(GENERAL, HEALING,
            List.of(), List.of(FIGHTER, TRACKER, MONK), List.of()),
    BREATH_OF_GOD(DIVINE, HEALING,
            List.of(), List.of(MONK), List.of()),
    POWER_OF_NATURE(NATURE, HEALING,
            List.of(WARRIOR), List.of(WIZARD, MAGE, MONK), List.of()),
    SOUL_POWER(SOUL, HEALING,
            List.of(), List.of(), List.of()),
    RECHARGING(ENERGY, HEALING,
            List.of(), List.of(WIZARD, FIGHTER, PALADIN, HERMIT, MONK, PRIEST), List.of()),
    HEALING_WAVE(GENERAL, HEALING,
            List.of(), List.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, HERMIT, DRUID), List.of(ORC)),
    ENERGY_ABSORPTION(ENERGY, HEALING,
            List.of(CLERIC), List.of(WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, HERMIT, DRUID), List.of()),
    DIVINE_PROTECTION(DIVINE, BLESSING,
            List.of(), List.of(), List.of()),
    STRENGTHENING(GENERAL, BLESSING,
            List.of(), List.of(WIZARD, TRACKER, RANGER, HERMIT, DRUID, ARCHDRUID, MONK), List.of()),
    SOUL_CONNECTION(SOUL, BLESSING,
            List.of(), List.of(RANGER, PILGRIM), List.of()),
    PROTECTIVE_HAND_OF_NATURE(NATURE, BLESSING,
            List.of(SORCERER, WARRIOR), List.of(TRACKER, MONK, PRIEST), List.of()),
    ENERGY_SHIELD(ENERGY, BLESSING,
            List.of(), List.of(HERMIT, MONK), List.of()),
    SOUL_STRIKE(SOUL, CURSE,
            List.of(), List.of(RANGER), List.of()),
    WEAKENING(GENERAL, CURSE,
            List.of(WANDERER), List.of(FIGHTER, PALADIN, HERMIT, MONK, PRIEST), List.of()),
    SHACKLE(NATURE, CURSE,
            List.of(), List.of(WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, TRACKER, RANGER, HERMIT, MONK), List.of()),
    ENERGY_BLOCKING(ENERGY, CURSE,
            List.of(), List.of(WIZARD, FIGHTER, PALADIN, GRANDMASTER, HERMIT, DRUID, ARCHDRUID, MONK, PRIEST, HIERARCH), List.of()),
    POWER_DRAIN(GENERAL, CURSE,
            List.of(WARRIOR, WANDERER, CLERIC), List.of(WIZARD, MAGE, HERMIT, DRUID), List.of()),
    CALLING_THE_SOULS(SOUL, SUMMON,
            List.of(), List.of(), List.of()),
    CALLING_ANIMALS(NATURE, SUMMON,
            List.of(), List.of(WIZARD, FIGHTER, PALADIN, MONK), List.of(MINOTAUR)),
    SUMMON_GUARDIAN(GENERAL, SUMMON,
            List.of(), List.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, MONK, PRIEST), List.of(ORC)),
    PROJECTION_OF_ENERGY(ENERGY, SUMMON,
            List.of(), List.of(WIZARD, FIGHTER, PALADIN, HERMIT, DRUID, MONK, PRIEST), List.of()),
    FRIEND_IN_NEED(DIVINE, SUMMON,
            List.of(SORCERER, NATURE_DWELLER), List.of(), List.of());

    SpellName(SpellGroup group, MagicType type, List<CasteGroup> prohibitedCasteGroups, List<Caste> prohibitedCastes, List<Race> prohibitedRaces) {
        this.group = group;
        this.type = type;
        this.prohibitedCasteGroups = prohibitedCasteGroups;
        this.prohibitedCastes = prohibitedCastes;
        this.prohibitedRaces = prohibitedRaces;
    }

    public final SpellGroup group;
    public final MagicType type;
    public final List<CasteGroup> prohibitedCasteGroups;
    public final List<Caste> prohibitedCastes;
    public final List<Race> prohibitedRaces;
}
