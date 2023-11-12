package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

import java.util.Set;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.*;

@AllArgsConstructor
public enum SpellName {
    FIREBALL(ELEMENTAL, ATTACK,
            Set.of(), Set.of(FIGHTER, TRACKER, HERMIT, MONK), Set.of()),
    LIGHTNING_STRIKE(ELEMENTAL, ATTACK,
            Set.of(), Set.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, HERMIT, DRUID, MONK, PRIEST), Set.of()),
    INFERNO(ELEMENTAL, ATTACK,
            Set.of(WARRIOR), Set.of(WIZARD, MAGE, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, ARCHDRUID, MONK, PRIEST, HIERARCH, BARBARIAN, APOSTLE, SHAMAN, NOMAD), Set.of()),
    BLADES_OF_JUDGEMENT(GENERAL, ATTACK,
            Set.of(NATURE_DWELLER), Set.of(WIZARD, MAGE, WITCHMASTER, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM, MONK, PRIEST, WITCH_DOCTOR, APOSTLE, CHARLATAN, SHAMAN, NOMAD), Set.of(HALFLING)),
    GLACIAL_BLOW(ELEMENTAL, ATTACK,
            Set.of(WARRIOR, WANDERER), Set.of(WIZARD, HERMIT, DRUID, MONK, PRIEST, HIERARCH, CRUSADER, APOSTLE, NOMAD), Set.of()),
    BALL_OF_ENERGY(ENERGY, ATTACK,
            Set.of(), Set.of(FIGHTER, HERMIT, MONK), Set.of()),
    SOUL_OUTBURST(SOUL, ATTACK,
            Set.of(), Set.of(), Set.of()),
    WRATH_OF_NATURE(NATURE, ATTACK,
            Set.of(WARRIOR), Set.of(WIZARD, MAGE, WITCHMASTER, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, MONK, PRIEST, HIERARCH, CRUSADER, CHAMPION), Set.of()),
    SPLITTING_WIND(ELEMENTAL, ATTACK,
            Set.of(), Set.of(FIGHTER, PALADIN, GRANDMASTER, TRACKER, RANGER, HERMIT, MONK, CHAMPION), Set.of()),
    DIVINE_HAMMER(DIVINE, ATTACK,
            Set.of(), Set.of(MONK), Set.of(HALFLING)),
    WOUND_HEALING(GENERAL, HEALING,
            Set.of(), Set.of(), Set.of()),
    HEALING_WORD(DIVINE, HEALING,
            Set.of(SORCERER), Set.of(), Set.of()),
    CURE(GENERAL, HEALING,
            Set.of(), Set.of(WIZARD, FIGHTER, TRACKER, RANGER, HERMIT, MONK), Set.of()),
    REGENERATION(GENERAL, HEALING,
            Set.of(), Set.of(FIGHTER, TRACKER, MONK), Set.of()),
    BREATH_OF_GOD(DIVINE, HEALING,
            Set.of(), Set.of(MONK), Set.of()),
    POWER_OF_NATURE(NATURE, HEALING,
            Set.of(WARRIOR), Set.of(WIZARD, MAGE, MONK), Set.of()),
    SOUL_POWER(SOUL, HEALING,
            Set.of(), Set.of(), Set.of()),
    RECHARGING(ENERGY, HEALING,
            Set.of(), Set.of(WIZARD, FIGHTER, PALADIN, HERMIT, MONK, PRIEST), Set.of()),
    HEALING_WAVE(GENERAL, HEALING,
            Set.of(), Set.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, HERMIT, DRUID), Set.of(ORC)),
    ENERGY_ABSORPTION(ENERGY, HEALING,
            Set.of(CLERIC), Set.of(WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, HERMIT, DRUID, CRUSADER, SAINT, NOMAD, CHAMPION), Set.of()),
    DIVINE_PROTECTION(DIVINE, BLESSING,
            Set.of(), Set.of(), Set.of()),
    STRENGTHENING(GENERAL, BLESSING,
            Set.of(), Set.of(WIZARD, TRACKER, RANGER, HERMIT, DRUID, ARCHDRUID, MONK), Set.of()),
    SOUL_CONNECTION(SOUL, BLESSING,
            Set.of(), Set.of(RANGER, PILGRIM, CHARLATAN), Set.of()),
    PROTECTIVE_HAND_OF_NATURE(NATURE, BLESSING,
            Set.of(SORCERER, WARRIOR), Set.of(TRACKER, MONK, PRIEST, CRUSADER), Set.of()),
    ENERGY_SHIELD(ENERGY, BLESSING,
            Set.of(), Set.of(HERMIT, MONK), Set.of()),
    SOUL_STRIKE(SOUL, CURSE,
            Set.of(), Set.of(RANGER), Set.of()),
    WEAKENING(GENERAL, CURSE,
            Set.of(WANDERER), Set.of(FIGHTER, PALADIN, HERMIT, MONK, PRIEST, CRUSADER, APOSTLE), Set.of()),
    SHACKLE(NATURE, CURSE,
            Set.of(), Set.of(WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, TRACKER, RANGER, HERMIT, MONK, CHAMPION), Set.of()),
    ENERGY_BLOCKING(ENERGY, CURSE,
            Set.of(), Set.of(WIZARD, FIGHTER, PALADIN, GRANDMASTER, HERMIT, DRUID, ARCHDRUID, MONK, PRIEST, HIERARCH, BARBARIAN, CRUSADER, APOSTLE, NOMAD, CHAMPION), Set.of()),
    POWER_DRAIN(GENERAL, CURSE,
            Set.of(WARRIOR, WANDERER, CLERIC), Set.of(WIZARD, MAGE, HERMIT, DRUID, BARBARIAN, SAINT, NOMAD), Set.of()),
    CALLING_THE_SOULS(SOUL, SUMMON,
            Set.of(), Set.of(), Set.of()),
    CALLING_ANIMALS(NATURE, SUMMON,
            Set.of(), Set.of(WIZARD, FIGHTER, PALADIN, MONK), Set.of(MINOTAUR)),
    SUMMON_GUARDIAN(GENERAL, SUMMON,
            Set.of(), Set.of(WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, MONK, PRIEST, BARBARIAN, CRUSADER, NOMAD, CHAMPION), Set.of(ORC)),
    PROJECTION_OF_ENERGY(ENERGY, SUMMON,
            Set.of(), Set.of(WIZARD, FIGHTER, PALADIN, HERMIT, DRUID, MONK, PRIEST, APOSTLE, NOMAD), Set.of()),
    FRIEND_IN_NEED(DIVINE, SUMMON,
            Set.of(SORCERER, NATURE_DWELLER), Set.of(SHAMAN), Set.of());

    public final SpellGroup group;
    public final MagicType type;
    public final Set<CasteGroup> prohibitedCasteGroups;
    public final Set<Caste> prohibitedCastes;
    public final Set<Race> prohibitedRaces;
}
