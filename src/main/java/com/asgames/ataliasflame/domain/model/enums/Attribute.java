package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Attribute {
    STRENGTH(0, 1, 2, 0, 0),
    DEXTERITY(2, 1, 1, 0, 0),
    CONSTITUTION(0, 0, 0, 10, 0),
    AGILITY(1, 1, 0, 0, 0),
    INTELLIGENCE(0, 0, 0, 0, 5),
    LORE(0, 0, 0, 0, 2),
    MENTAL_POWER(0, 0, 0, 0, 10),
    SPIRITUAL_POWER(0, 0, 0, 0, 1);

    public final int attackMultiplier;
    public final int defenseMultiplier;
    public final int damageMultiplier;
    public final int healthMultiplier;
    public final int magicPoint;
}
