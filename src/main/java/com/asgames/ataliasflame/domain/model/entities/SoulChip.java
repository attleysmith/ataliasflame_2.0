package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SoulChipShape;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class SoulChip {

    // JPA needs it
    public SoulChip() {
    }

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "shape")
    @Enumerated(STRING)
    private SoulChipShape shape;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false, updatable = false)
    private Character owner;

    @Column(name = "attack")
    private int attack;
    @Column(name = "defense")
    private int defense;
    @Column(name = "minDamage")
    private int minDamage;
    @Column(name = "maxDamage")
    private int maxDamage;
    @Column(name = "health")
    private int health;
    @Column(name = "initiative")
    private int initiative;

    @Column(name = "upgradedCaste")
    @Enumerated(STRING)
    private Caste upgradedCaste;
    @Column(name = "upgradePercent")
    private int upgradePercent;

    public Companion summon() {
        return Companion.builder()
                .name(name)
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
