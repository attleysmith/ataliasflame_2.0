package com.asgames.ataliasflame.domain.model.enums;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Booster.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;

public enum Race {
    HUMAN(HUMAN_EFFECT, List.of(), List.of(), List.of()),
    ELF(ELF_EFFECT, List.of(), List.of(), List.of()),
    HALF_ELF(HALF_ELF_EFFECT, List.of(), List.of(), List.of()),
    NIGHT_ELF(NIGHT_ELF_EFFECT, List.of(), List.of(), List.of()),
    DWARF(DWARF_EFFECT, List.of(), List.of(HORA), List.of(SORCERER)),
    ORC(ORC_EFFECT, List.of(), List.of(HORA, RUNID), List.of(SORCERER)),
    MINOTAUR(MINOTAUR_EFFECT, List.of(FEMALE), List.of(HORA), List.of(SORCERER, CLERIC)),
    ARIMASPI(ARIMASPI_EFFECT, List.of(), List.of(), List.of(SORCERER, CLERIC)),
    NYMPH(NYMPH_EFFECT, List.of(MALE), List.of(GETON, ALATE), List.of(WARRIOR, WANDERER)),
    HALFLING(HALFLING_EFFECT, List.of(), List.of(), List.of());

    Race(Booster booster, List<Gender> prohibitedGenders, List<God> prohibitedGods, List<CasteGroup> prohibitedCasteGroups) {
        this.booster = booster;
        this.prohibitedGenders = prohibitedGenders;
        this.prohibitedGods = prohibitedGods;
        this.prohibitedCasteGroups = prohibitedCasteGroups;
    }

    public final Booster booster;
    public final List<Gender> prohibitedGenders;
    public final List<God> prohibitedGods;
    public final List<CasteGroup> prohibitedCasteGroups;
}
