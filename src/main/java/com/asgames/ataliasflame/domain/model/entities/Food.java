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
public class Food extends Item {

    // JPA needs it
    public Food() {
    }

    @Column(name = "healingEffect")
    private int healingEffect;
    @Column(name = "magicEffect")
    private int magicEffect;
}
