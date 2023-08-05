package com.asgames.ataliasflame.domain.model.interfaces;

import com.asgames.ataliasflame.domain.model.vos.Energy;

public interface Combatant {
    String getCode();

    int getAttack();

    int getDefense();

    int getMinDamage();

    int getMaxDamage();

    Energy getHealth();

    int getInitiative();

    default boolean isAlive() {
        return getHealth().hasOne();
    }

    default boolean isDead() {
        return getHealth().isEmpty();
    }
}
