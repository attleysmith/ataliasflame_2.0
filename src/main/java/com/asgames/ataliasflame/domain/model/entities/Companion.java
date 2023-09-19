package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.CompanionType;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Inheritance(strategy = SINGLE_TABLE)
@Entity
@SuperBuilder
@Data
@AllArgsConstructor // Builder needs it
public class Companion implements Combatant {

    // JPA needs it
    public Companion() {
    }

    @Id
    @Column(name = "reference")
    private String reference;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    @Enumerated(STRING)
    private CompanionType type;

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "totalHealth")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "injury"))
    })
    private Energy health;

    @Override
    public String getCode() {
        return name;
    }
}
