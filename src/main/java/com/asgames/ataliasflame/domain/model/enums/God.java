package com.asgames.ataliasflame.domain.model.enums;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Caste.*;

public enum God {
    HORA(List.of()),
    SIFER(List.of()),
    GETON(List.of()),
    RUNID(List.of()),
    ALATE(List.of(WIZARD, MAGE, WITCHMASTER, AVATAR)),
    GINDON(List.of()),
    ATALIA(List.of());

    God(List<Caste> prohibitedCastes) {
        this.prohibitedCastes = prohibitedCastes;
    }

    public final List<Caste> prohibitedCastes;
}
