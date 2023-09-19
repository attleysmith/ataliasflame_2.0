package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor // Builder needs it
public class SummonedSoulChip extends Companion {

    // JPA needs it
    public SummonedSoulChip() {
    }

    @JoinColumn(name = "sourceId")
    @OneToOne(fetch = LAZY)
    private SoulChip source;
}
