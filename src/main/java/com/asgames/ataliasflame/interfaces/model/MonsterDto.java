package com.asgames.ataliasflame.interfaces.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MonsterDto {
    private String reference;
    private String code;
    private String locationReference;
    private int attack;
    private int defense;
    private int minDamage;
    private int maxDamage;
    private int initiative;
    private int experience;
    private int level;
    private int totalHealth;
    private int injury;
    private int totalVitality;
    private int lostVitality;
}
