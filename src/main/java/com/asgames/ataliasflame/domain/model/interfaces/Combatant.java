package com.asgames.ataliasflame.domain.model.interfaces;

public interface Combatant {
    String getCode();

    int getAttack();

    int getDefense();

    int getDamage();

    int getTotalHealth();

    void setTotalHealth(int totalHealth);

    int getInjury();

    void setInjury(int injury);

    default int getActualHealth() {
        return getTotalHealth() - getInjury();
    }
}
