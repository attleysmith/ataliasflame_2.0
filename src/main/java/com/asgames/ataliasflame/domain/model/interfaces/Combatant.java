package com.asgames.ataliasflame.domain.model.interfaces;

import com.asgames.ataliasflame.domain.model.valueobjects.Energy;

public interface Combatant {
    String getCode();

    int getAttack();

    int getDefense();

    int getMinDamage();

    int getMaxDamage();

    Energy getHealth();

    int getInitiative();
}
