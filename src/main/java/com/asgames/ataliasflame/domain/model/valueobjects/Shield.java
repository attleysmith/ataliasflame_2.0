package com.asgames.ataliasflame.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class Shield {

    // JPA needs it
    public Shield() {
    }

    private String code;
    private int defense;
    private int popularity;
}
