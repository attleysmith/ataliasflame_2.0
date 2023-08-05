package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.Builder;
import lombok.Data;

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

    private final int mass;
    private final int experience;

    public Monster instance(String codeSuffix) {
        return Monster.builder()
                .code(codeSuffix.length() == 0 ? code : code + "-" + codeSuffix)
                .attack(attack)
                .defense(defense)
                .minDamage(minDamage)
                .maxDamage(maxDamage)
                .health(Energy.withTotal(health))
                .initiative(initiative)
                .experience(experience)
                .build();
    }
}
