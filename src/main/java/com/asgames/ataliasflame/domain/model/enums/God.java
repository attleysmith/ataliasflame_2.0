package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

import java.util.Set;

import static com.asgames.ataliasflame.domain.model.enums.Booster.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.SORCERER;

@AllArgsConstructor
public enum God {
    HORA(HORA_EFFECT, Set.of()),
    SIFER(SIFER_EFFECT, Set.of()),
    GETON(GETON_EFFECT, Set.of()),
    RUNID(RUNID_EFFECT, Set.of()),
    ALATE(ALATE_EFFECT, Set.of(SORCERER)),
    GINDON(GINDON_EFFECT, Set.of()),
    ATALIA(ATALIA_EFFECT, Set.of());

    public final Booster booster;
    public final Set<CasteGroup> prohibitedCasteGroups;
}
