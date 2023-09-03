package com.asgames.ataliasflame.domain.model.enums;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;

public enum SpellGroup {
    GENERAL(List.of(ROGUE), List.of()),
    ELEMENTAL(List.of(ROGUE), List.of()),
    ENERGY(List.of(ROGUE, TRACKER, RANGER, PILGRIM, FREE_SOUL), List.of(DWARF, ORC, HALFLING)),
    DIVINE(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, HERMIT, DRUID, ARCHDRUID), List.of(ARIMASPI, ORC)),
    NATURE(List.of(ROGUE), List.of()),
    SOUL(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, AVATAR, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, HERMIT, DRUID, ARCHDRUID, ATALIAS_PRIEST, MONK, PRIEST, HIERARCH, ARCHANGEL), List.of());

    SpellGroup(List<Caste> prohibitedCastes, List<Race> prohibitedRaces) {
        this.prohibitedCastes = prohibitedCastes;
        this.prohibitedRaces = prohibitedRaces;
    }

    public final List<Caste> prohibitedCastes;
    public final List<Race> prohibitedRaces;
}
