package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class Weapon extends Item {

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
