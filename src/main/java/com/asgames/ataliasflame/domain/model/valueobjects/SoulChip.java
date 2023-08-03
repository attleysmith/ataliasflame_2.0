package com.asgames.ataliasflame.domain.model.valueobjects;

import com.asgames.ataliasflame.domain.model.enums.Caste;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static jakarta.persistence.EnumType.STRING;

@Builder
@Data
@AllArgsConstructor // Builder needs it
public class SoulChip {

    // JPA needs it
    public SoulChip() {
    }

    @Enumerated(STRING)
    private Caste upgradedCaste;
    private int percent;
}
