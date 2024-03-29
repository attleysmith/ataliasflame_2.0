package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SoulChipShape;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.SOUL_CHIP;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@Data
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class SoulChip {

    @Id
    @Column(name = "reference")
    private String reference;
    @Column(name = "name")
    private String name;
    @Enumerated(STRING)
    @Column(name = "shape")
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
    @Column(name = "initiative")
    private int initiative;

    @Enumerated(STRING)
    @Column(name = "upgradedCaste")
    private Caste upgradedCaste;
    @Column(name = "effectiveness")
    private int effectiveness;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "totalHealth")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "fatigue"))
    })
    private Energy health;

    public boolean isExhausted() {
        return health.isEmpty();
    }

    public boolean isReady() {
        return health.hasOne();
    }

    public SummonedSoulChip summon() {
        return SummonedSoulChip.builder()
                .reference(UUID.randomUUID().toString())
                .name(name)
                .type(SOUL_CHIP)
                .owner(owner)
                .attack(attack)
                .defense(defense)
                .minDamage(minDamage)
                .maxDamage(maxDamage)
                .health(Energy.fromSource(health))
                .initiative(initiative)
                .source(this)
                .build();
    }

}
