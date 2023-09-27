package com.asgames.ataliasflame.interfaces.model;

import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SoulChipShape;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SoulChipDto {
    private String reference;
    private String name;
    private SoulChipShape shape;
    private int attack;
    private int defense;
    private int minDamage;
    private int maxDamage;
    private int initiative;
    private Caste upgradedCaste;
    private int effectiveness;
    private int totalHealth;
    private int fatigue;
}
