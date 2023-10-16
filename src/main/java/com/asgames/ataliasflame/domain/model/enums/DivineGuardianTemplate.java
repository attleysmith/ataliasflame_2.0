package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.DIVINE_GUARDIAN;

@AllArgsConstructor
public enum DivineGuardianTemplate {
    KNIGHT(90, 25, 5, 20, 100, -4),
    COMMANDER(100, 40, 6, 24, 120, -5);

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
                .type(DIVINE_GUARDIAN)
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
