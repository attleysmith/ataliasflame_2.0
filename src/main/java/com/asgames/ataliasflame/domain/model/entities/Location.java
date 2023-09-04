package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class Location {

    // JPA needs it
    public Location() {
    }

    @Id
    @Column(name = "reference")
    private String reference;

    @Column(name = "level")
    private int level;

    @OneToMany(mappedBy = "location", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private List<Monster> monsters;

    @JoinTable(
            name = "LocationItem",
            joinColumns = @JoinColumn(name = "LocationId", referencedColumnName = "reference"),
            inverseJoinColumns = @JoinColumn(name = "ItemId", referencedColumnName = "reference")
    )
    @OneToMany(cascade = ALL, fetch = EAGER)
    private List<Item> items;

    public static Location build(int level) {
        return Location.builder()
                .reference(UUID.randomUUID().toString())
                .level(level)
                .build();
    }
}
