package com.asgames.ataliasflame.domain.model.interfaces;

import com.asgames.ataliasflame.domain.model.entities.Cover;
import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.vos.Energy;

import java.util.Optional;

public interface Combatant {

    String getReference();

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

    default Optional<Shield> getShield() {
        return Optional.empty();
    }

    default Cover getCover() {
        return new Cover();
    }

    default String shortRef() {
        return getReference().substring(0, 8);
    }
}
