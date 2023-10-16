package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.Booster;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@Data
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class ActiveBlessing {

    @Id
    @Column(name = "reference")
    private String reference;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false, updatable = false)
    private Character owner;

    @Column(name = "booster")
    @Enumerated(STRING)
    private Booster booster;

    @JoinColumn(name = "sourceId")
    @OneToOne(fetch = LAZY)
    private SoulChip source;

    public static ActiveBlessing of(Character owner, Booster booster) {
        return ActiveBlessing.builder()
                .reference(UUID.randomUUID().toString())
                .owner(owner)
                .booster(booster)
                .build();
    }

    public ActiveBlessing withSource(SoulChip soulChip) {
        this.source = soulChip;
        return this;
    }
}
