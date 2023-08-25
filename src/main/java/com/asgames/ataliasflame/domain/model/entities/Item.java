package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.ItemType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class Item {

    // JPA needs it
    public Item() {
    }

    @Id
    @Column(name = "reference")
    private String reference;
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    @Enumerated(STRING)
    private ItemType type;
    @Column(name = "healingEffect")
    private int healingEffect;
    @Column(name = "magicEffect")
    private int magicEffect;
}
