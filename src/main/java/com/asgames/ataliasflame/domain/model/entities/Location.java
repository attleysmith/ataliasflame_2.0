package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Builder
@Data
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class Location {

    @Id
    @Column(name = "reference")
    private String reference;

    @Column(name = "level")
    private int level;

    @Column(name = "seized")
    private boolean seized;

    @OneToMany(mappedBy = "location", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private List<Monster> monsters;

    @JoinTable(
            name = "LocationItem",
            joinColumns = @JoinColumn(name = "LocationId", referencedColumnName = "reference"),
            inverseJoinColumns = @JoinColumn(name = "ItemId", referencedColumnName = "reference")
    )
    @OneToMany(cascade = ALL, fetch = EAGER)
    private List<Item> items;

    public List<Item> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public static Location build(int level) {
        return Location.builder()
                .reference(UUID.randomUUID().toString())
                .level(level)
                .build();
    }
}
