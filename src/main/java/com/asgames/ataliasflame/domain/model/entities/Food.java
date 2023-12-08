package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class Food extends Item {

    @Column(name = "healingEffect")
    private int healingEffect;
    @Column(name = "magicEffect")
    private int magicEffect;
}
