package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class MonsterTemplate {

    private final String code;
    private final int attack;
    private final int defense;
    private final int minDamage;
    private final int maxDamage;
    private final int health;
    private final int initiative;

    private final int level;
    private final int experience;
    private final int chance;
    private final int spawn;

    public Monster instance(Location location) {
        return Monster.builder()
                .reference(UUID.randomUUID().toString())
                .code(code)
                .location(location)
                .attack(attack)
                .defense(defense)
                .minDamage(minDamage)
                .maxDamage(maxDamage)
                .health(Energy.withTotal(health))
                .initiative(initiative)
                .experience(experience)
                .level(level)
                .build();
    }
}
