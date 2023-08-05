package com.asgames.ataliasflame.domain.model.vos;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class Armor {

    // JPA needs it
    public Armor() {
    }

    private String code;
    private int defense;
    private int popularity;
}
