package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.ItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Inheritance(strategy = SINGLE_TABLE)
@Entity
@SuperBuilder
@Data
@AllArgsConstructor // Builder needs it
public class Item {

    // JPA needs it
    public Item() {
    }

    @Id
    @Column(name = "reference")
    protected String reference;
    @Column(name = "code")
    protected String code;
    @Column(name = "type")
    @Enumerated(STRING)
    protected ItemType type;
}
