package com.asgames.ataliasflame.domain.model.interfaces;

import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.vos.Energy;

import java.util.Optional;

public interface Combatant {

    String getReference();

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

    default Optional<Armor> getArmor() {
        return Optional.empty();
    }
}
