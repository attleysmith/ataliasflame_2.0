package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.Getter;

import java.util.UUID;

public enum MonsterTemplate {
    RAT(40, 0, 1, 1, 10, 3, 1, 10, 20, 70),
    BOAR(60, 5, 1, 2, 25, 0, 11, 20, 15, 25),
    WOLF(70, 5, 1, 3, 25, -3, 21, 30, 25, 50),
    BANDIT(75, 5, 1, 4, 30, -1, 31, 40, 35, 40),
    WEREWOLF(75, 10, 1, 5, 40, -3, 41, 50, 5, 15),
    NAGA(80, 10, 2, 8, 50, -3, 51, 60, 5, 10),
    OGRE(85, 15, 2, 10, 65, 0, 61, 70, 5, 5),
    GHOUL(90, 10, 3, 12, 80, 0, 71, 80, 5, 5),
    GRIFFIN(100, 20, 5, 15, 100, -3, 81, 90, 5, 10),
    DRAGON(160, 50, 10, 30, 200, -3, 91, 100, 5, 5);

    MonsterTemplate(int attack, int defense, int minDamage, int maxDamage, int health, int initiative, int level, int experience, int chance, int spawn) {
        this.attack = attack;
        this.defense = defense;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.health = health;
        this.initiative = initiative;
        this.level = level;
        this.experience = experience;
        this.chance = chance;
        this.spawn = spawn;
    }

    private final int attack;
    private final int defense;
    private final int minDamage;
    private final int maxDamage;
    private final int health;
    private final int initiative;
    @Getter
    private final int level;
    private final int experience;
    @Getter
    private final int chance;
    @Getter
    private final int spawn;

    public Monster instance(Location location) {
        return Monster.builder()
                .reference(UUID.randomUUID().toString())
                .code(name())
                .location(location)
                .attack(attack)
                .defense(defense)
                .minDamage(minDamage)
                .maxDamage(maxDamage)
                .health(Energy.withTotal(health))
                .vitality(Energy.withTotal(health))
                .initiative(initiative)
                .experience(experience)
                .level(level)
                .build();
    }
}
