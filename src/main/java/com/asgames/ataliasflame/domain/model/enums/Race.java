package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

import java.util.Set;

import static com.asgames.ataliasflame.domain.model.enums.Booster.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;

@AllArgsConstructor
public enum Race {
    HUMAN(HUMAN_EFFECT, Set.of(), Set.of(), Set.of()),
    ELF(ELF_EFFECT, Set.of(), Set.of(), Set.of()),
    HALF_ELF(HALF_ELF_EFFECT, Set.of(), Set.of(), Set.of()),
    NIGHT_ELF(NIGHT_ELF_EFFECT, Set.of(), Set.of(), Set.of()),
    DWARF(DWARF_EFFECT, Set.of(), Set.of(HORA), Set.of(SORCERER)),
    ORC(ORC_EFFECT, Set.of(), Set.of(HORA, RUNID), Set.of(SORCERER)),
    MINOTAUR(MINOTAUR_EFFECT, Set.of(FEMALE), Set.of(HORA), Set.of(SORCERER, CLERIC)),
    ARIMASPI(ARIMASPI_EFFECT, Set.of(), Set.of(), Set.of(SORCERER, CLERIC)),
    NYMPH(NYMPH_EFFECT, Set.of(MALE), Set.of(GETON, ALATE), Set.of(WARRIOR, WANDERER)),
    HALFLING(HALFLING_EFFECT, Set.of(), Set.of(), Set.of());

    public final Booster booster;
    public final Set<Gender> prohibitedGenders;
    public final Set<God> prohibitedGods;
    public final Set<CasteGroup> prohibitedCasteGroups;
}
