package com.asgames.ataliasflame.domain.model.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Modifier {

    private int attackMultiplier;
    private int defenseMultiplier;
    private int damageMultiplier;
    private int healthMultiplier;
    private int magicPoint;

}
