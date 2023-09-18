package com.asgames.ataliasflame.domain.model.enums;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Booster.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;

public enum God {
    HORA(HORA_EFFECT, List.of()),
    SIFER(SIFER_EFFECT, List.of()),
    GETON(GETON_EFFECT, List.of()),
    RUNID(RUNID_EFFECT, List.of()),
    ALATE(ALATE_EFFECT, List.of(WIZARD, MAGE, WITCHMASTER, AVATAR)),
    GINDON(GINDON_EFFECT, List.of()),
    ATALIA(ATALIA_EFFECT, List.of());

    God(Booster booster, List<Caste> prohibitedCastes) {
        this.booster = booster;
        this.prohibitedCastes = prohibitedCastes;
    }

    public final Booster booster;
    public final List<Caste> prohibitedCastes;
}
