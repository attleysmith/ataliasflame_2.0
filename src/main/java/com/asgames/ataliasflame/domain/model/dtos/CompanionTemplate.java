package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CompanionTemplate {

    private final String code;
    private final int attack;
    private final int defense;
    private final int minDamage;
    private final int maxDamage;
    private final int health;
    private final int initiative;

    public Companion instance(Character owner, String codeSuffix) {
        return Companion.builder()
                .reference(UUID.randomUUID().toString())
                .name(codeSuffix.length() == 0 ? code : code + "-" + codeSuffix)
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
