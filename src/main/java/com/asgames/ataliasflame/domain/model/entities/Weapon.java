package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor // Builder needs it
public class Weapon extends Item {

    // JPA needs it
    public Weapon() {
    }

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

    public void belongsTo(Character character) {
        character.setWeapon(this);
    }
}
