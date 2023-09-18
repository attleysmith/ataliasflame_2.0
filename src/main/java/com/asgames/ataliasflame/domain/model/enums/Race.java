package com.asgames.ataliasflame.domain.model.enums;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Booster.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.ALATE;
import static com.asgames.ataliasflame.domain.model.enums.God.GETON;
import static com.asgames.ataliasflame.domain.model.enums.God.HORA;
import static com.asgames.ataliasflame.domain.model.enums.God.RUNID;

public enum Race {
    HUMAN(HUMAN_EFFECT, List.of(), List.of(), List.of()),
    ELF(ELF_EFFECT, List.of(), List.of(), List.of()),
    HALF_ELF(HALF_ELF_EFFECT, List.of(), List.of(), List.of()),
    NIGHT_ELF(NIGHT_ELF_EFFECT, List.of(), List.of(), List.of()),
    DWARF(DWARF_EFFECT, List.of(), List.of(HORA), List.of(WIZARD, MAGE, WITCHMASTER, AVATAR)),
    ORC(ORC_EFFECT, List.of(), List.of(HORA, RUNID), List.of(WIZARD, MAGE, WITCHMASTER, AVATAR)),
    MINOTAUR(MINOTAUR_EFFECT, List.of(FEMALE), List.of(HORA), List.of(WIZARD, MAGE, WITCHMASTER, AVATAR, MONK, PRIEST, HIERARCH, ARCHANGEL)),
    ARIMASPI(ARIMASPI_EFFECT, List.of(), List.of(), List.of(WIZARD, MAGE, WITCHMASTER, AVATAR)),
    NYMPH(NYMPH_EFFECT, List.of(MALE), List.of(GETON, ALATE), List.of(FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL)),
    HALFLING(HALFLING_EFFECT, List.of(), List.of(), List.of());

    Race(Booster booster, List<Gender> prohibitedGenders, List<God> prohibitedGods, List<Caste> prohibitedCastes) {
        this.booster = booster;
        this.prohibitedGenders = prohibitedGenders;
        this.prohibitedGods = prohibitedGods;
        this.prohibitedCastes = prohibitedCastes;
    }

    public final Booster booster;
    public final List<Gender> prohibitedGenders;
    public final List<God> prohibitedGods;
    public final List<Caste> prohibitedCastes;
}
