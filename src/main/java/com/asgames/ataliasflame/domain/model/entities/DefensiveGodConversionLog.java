package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.God;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class DefensiveGodConversionLog {

    // JPA needs it
    public DefensiveGodConversionLog() {
    }

    @Id
    @Column(name = "conversionCode")
    private String conversionCode;
    @ManyToOne(optional = false)
    @JoinColumn(name = "clericId", nullable = false, updatable = false)
    private Character cleric;
    @ManyToOne()
    @JoinColumn(name = "convertedCharacterId")
    private Character convertedCharacter;
    @Column(name = "god")
    @Enumerated(STRING)
    private God god;
}
