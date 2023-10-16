package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.Booster.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.SORCERER;

@AllArgsConstructor
public enum God {
    HORA(HORA_EFFECT, List.of()),
    SIFER(SIFER_EFFECT, List.of()),
    GETON(GETON_EFFECT, List.of()),
    RUNID(RUNID_EFFECT, List.of()),
    ALATE(ALATE_EFFECT, List.of(SORCERER)),
    GINDON(GINDON_EFFECT, List.of()),
    ATALIA(ATALIA_EFFECT, List.of());

    public final Booster booster;
    public final List<CasteGroup> prohibitedCasteGroups;
}
