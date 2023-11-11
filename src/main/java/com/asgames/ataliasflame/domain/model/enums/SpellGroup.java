package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

import java.util.Set;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;

@AllArgsConstructor
public enum SpellGroup {
    GENERAL(Set.of(UNSPECIALIZED), Set.of(), Set.of()),
    ELEMENTAL(Set.of(UNSPECIALIZED), Set.of(), Set.of()),
    ENERGY(Set.of(UNSPECIALIZED, WANDERER), Set.of(), Set.of(DWARF, ORC, HALFLING)),
    DIVINE(Set.of(UNSPECIALIZED, WARRIOR, WANDERER), Set.of(WIZARD, MAGE, WITCHMASTER, HERMIT, DRUID, ARCHDRUID, WITCH_DOCTOR, BARBARIAN, CHARLATAN, NOMAD, SAGE), Set.of(ARIMASPI, ORC)),
    NATURE(Set.of(UNSPECIALIZED), Set.of(), Set.of()),
    SOUL(Set.of(UNSPECIALIZED, SORCERER, WARRIOR, NATURE_DWELLER, CLERIC), Set.of(TRACKER), Set.of());

    public final Set<CasteGroup> prohibitedCasteGroups;
    public final Set<Caste> prohibitedCastes;
    public final Set<Race> prohibitedRaces;
}
