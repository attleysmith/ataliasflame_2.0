package com.asgames.ataliasflame.domain.model.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Modifier {

    private final int attackMultiplier;
    private final int defenseMultiplier;
    private final int damageMultiplier;
    private final int healthMultiplier;
    private final int magicPoint;

}
