package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;

@AllArgsConstructor
public enum SpellGroup {
    GENERAL(List.of(UNSPECIALIZED), List.of(), List.of()),
    ELEMENTAL(List.of(UNSPECIALIZED), List.of(), List.of()),
    ENERGY(List.of(UNSPECIALIZED, WANDERER), List.of(), List.of(DWARF, ORC, HALFLING)),
    DIVINE(List.of(UNSPECIALIZED, WARRIOR, WANDERER), List.of(WIZARD, MAGE, WITCHMASTER, HERMIT, DRUID, ARCHDRUID), List.of(ARIMASPI, ORC)),
    NATURE(List.of(UNSPECIALIZED), List.of(), List.of()),
    SOUL(List.of(UNSPECIALIZED, SORCERER, WARRIOR, NATURE_DWELLER, CLERIC), List.of(TRACKER), List.of());

    public final List<CasteGroup> prohibitedCasteGroups;
    public final List<Caste> prohibitedCastes;
    public final List<Race> prohibitedRaces;
}
