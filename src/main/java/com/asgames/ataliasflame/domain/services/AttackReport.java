package com.asgames.ataliasflame.domain.services;

import lombok.Data;

@Data
public class AttackReport {

    private final String attackerCode;
    private final int dealtDamage;
    private final int remainingHealth;
}
