package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.God;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@Data
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class DefensiveGodConversionLog {

    @Id
    @Column(name = "conversionCode")
    private String conversionCode;
    @ManyToOne(optional = false)
    @JoinColumn(name = "clericId", nullable = false, updatable = false)
    private Character cleric;
    @ManyToOne()
    @JoinColumn(name = "convertedCharacterId")
    private Character convertedCharacter;
    @Enumerated(STRING)
    @Column(name = "god")
    private God god;
}
