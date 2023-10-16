package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class Monster implements Combatant {

    @Id
    @Column(name = "reference")
    private String reference;
    @Column(name = "code")
    private String code;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "locationId", nullable = false, updatable = false)
    private Location location;

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

    @Column(name = "experience")
    private int experience;
    @Column(name = "level")
    private int level;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "totalHealth")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "injury"))
    })
    private Energy health;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "totalVitality")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "lostVitality"))
    })
    private Energy vitality;

    public Energy getHealth() {
        if (health == null) {
            health = new Energy();
        }
        return health;
    }
}
