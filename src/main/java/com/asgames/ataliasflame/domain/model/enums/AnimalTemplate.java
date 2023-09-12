package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.vos.Energy;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.ANIMAL;

public enum AnimalTemplate {
    TAMED_FALCON(75, 0, 1, 2, 10, -6),
    TAMED_DOG(70, 5, 1, 3, 20, -3),
    TAMED_WOLF(70, 5, 1, 3, 25, -3),
    TAMED_BEAR(80, 10, 1, 5, 40, -2);

    AnimalTemplate(int attack, int defense, int minDamage, int maxDamage, int health, int initiative) {
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
                .type(ANIMAL)
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
