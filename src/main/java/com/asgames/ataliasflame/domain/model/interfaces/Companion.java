package com.asgames.ataliasflame.domain.model.interfaces;

public interface Companion extends Combatant {

    String getName();

    default String getCode() {
        return getName();
    }

}
