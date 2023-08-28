package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class Weapon {

    // JPA needs it
    public Weapon() {
    }

    @Id
    @Column(name = "reference")
    private String reference;
    @Column(name = "code")
    private String code;

    @Column(name = "minDamage")
    private int minDamage;
    @Column(name = "maxDamage")
    private int maxDamage;
    @Column(name = "defense")
    private int defense;
    @Column(name = "initiative")
    private int initiative;
    @Column(name = "oneHanded")
    private boolean oneHanded;

    public boolean isBetterThan(Weapon otherWeapon) {
        return this.averageDamage() > otherWeapon.averageDamage();
    }

    public void belongsTo(Character character) {
        character.setWeapon(this);
    }

    private int averageDamage() {
        return (minDamage + maxDamage) / 2;
    }
}
