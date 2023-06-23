package com.asgames.ataliasflame.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class Weapon {

    // JPA needs it
    public Weapon() {
    }

    private String code;
    private int damage;
    private int defense;
    private int initiative;
}
