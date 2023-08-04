package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.interfaces.Companion;
import com.asgames.ataliasflame.domain.model.valueobjects.Energy;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class SoulChip implements Companion {

    // JPA needs it
    public SoulChip() {
    }

    @Id
    @Column(name = "name")
    private String name;

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

    @Column(name = "upgradedCaste")
    @Enumerated(STRING)
    private Caste upgradedCaste;
    @Column(name = "upgradePercent")
    private int upgradePercent;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "totalHealth")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "injury"))
    })
    private Energy health;

}
