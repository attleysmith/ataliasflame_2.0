package com.asgames.ataliasflame.domain.model.enums;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.Gender.FEMALE;
import static com.asgames.ataliasflame.domain.model.enums.Gender.MALE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;

public enum Race {
    HUMAN(List.of(), List.of(), List.of()),
    ELF(List.of(), List.of(), List.of()),
    HALF_ELF(List.of(), List.of(), List.of()),
    NIGHT_ELF(List.of(), List.of(), List.of()),
    DWARF(List.of(), List.of(HORA), List.of(WIZARD, MAGE, WITCHMASTER, AVATAR)),
    ORC(List.of(), List.of(HORA, RUNID), List.of(WIZARD, MAGE, WITCHMASTER, AVATAR)),
    MINOTAUR(List.of(FEMALE), List.of(HORA), List.of(WIZARD, MAGE, WITCHMASTER, AVATAR, MONK, PRIEST, HIERARCH, ARCHANGEL)),
    ARIMASPI(List.of(), List.of(), List.of(WIZARD, MAGE, WITCHMASTER, AVATAR)),
    NYMPH(List.of(MALE), List.of(GETON, ALATE), List.of(FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL)),
    HALFLING(List.of(), List.of(), List.of());

    Race(List<Gender> prohibitedGenders, List<God> prohibitedGods, List<Caste> prohibitedCastes) {
        this.prohibitedGenders = prohibitedGenders;
        this.prohibitedGods = prohibitedGods;
        this.prohibitedCastes = prohibitedCastes;
    }

    public final List<Gender> prohibitedGenders;
    public final List<God> prohibitedGods;
    public final List<Caste> prohibitedCastes;
}
