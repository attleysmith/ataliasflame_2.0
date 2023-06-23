package com.asgames.ataliasflame.domain.model.interfaces;

public interface Combatant {
    String getCode();

    int getAttack();

    int getDefense();

    int getDamage();

    int getTotalHealth();

    int getInjury();

    void setInjury(int injury);

    int getInitiative();

    default int getActualHealth() {
        return getTotalHealth() - getInjury();
    }
}
