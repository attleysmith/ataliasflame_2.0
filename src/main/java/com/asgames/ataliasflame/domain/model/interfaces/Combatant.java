package com.asgames.ataliasflame.domain.model.interfaces;

public interface Combatant {
    String getCode();

    int getAttack();

    int getDefense();

    int getMinDamage();

    int getMaxDamage();

    int getTotalHealth();

    int getInjury();

    void setInjury(int injury);

    int getInitiative();

    default int getActualHealth() {
        return getTotalHealth() - getInjury();
    }
}
