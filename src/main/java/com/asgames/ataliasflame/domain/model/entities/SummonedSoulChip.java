package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class SummonedSoulChip extends Companion {

    @JoinColumn(name = "sourceId")
    @OneToOne(fetch = LAZY)
    private SoulChip source;
}
