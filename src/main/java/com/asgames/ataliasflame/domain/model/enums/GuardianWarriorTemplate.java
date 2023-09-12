package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.vos.Energy;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.GUARDIAN_WARRIOR;

public enum GuardianWarriorTemplate {
    HUNTER(85, 5, 2, 6, 75, -9),
    MILITIA(85, 10, 3, 15, 80, -3),
    SWORDSMAN(90, 25, 5, 15, 90, -3);

    GuardianWarriorTemplate(int attack, int defense, int minDamage, int maxDamage, int health, int initiative) {
        this.attack = attack;
        this.defense = defense;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.health = health;
        this.initiative = initiative;
    }

    private final int attack;
    private final int defense;
    private final int minDamage;
    private final int maxDamage;
    private final int health;
    private final int initiative;

    public Companion instance(Character owner) {
        return Companion.builder()
                .reference(UUID.randomUUID().toString())
                .name(name())
                .type(GUARDIAN_WARRIOR)
                .owner(owner)
                .attack(attack)
                .defense(defense)
                .minDamage(minDamage)
                .maxDamage(maxDamage)
                .health(Energy.withTotal(health))
                .initiative(initiative)
                .build();
    }
}
