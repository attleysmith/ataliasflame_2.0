package com.asgames.ataliasflame.interfaces.model;

import com.asgames.ataliasflame.domain.model.enums.CompanionType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompanionDto {
    private String reference;
    private String name;
    private CompanionType type;
    private int attack;
    private int defense;
    private int minDamage;
    private int maxDamage;
    private int initiative;
    private int totalHealth;
    private int injury;
    private String sourceSoulChip;
}
