package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.*;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.valueobjects.Weapon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class Character implements Combatant {

    // JPA needs it
    public Character() {
    }

    @Id
    private String name;
    @Column
    @Enumerated(STRING)
    private Gender gender;
    @Column
    @Enumerated(STRING)
    private Race race;
    @Column
    @Enumerated(STRING)
    private Caste caste;
    @Column
    @Enumerated(STRING)
    private God defensiveGod;

    @Column
    private int attack;
    @Column
    private int defense;
    @Column
    private int minDamage;
    @Column
    private int maxDamage;
    @Column
    private int damageMultiplier;
    @Column
    private int totalHealth;
    @Column
    private int injury;
    @Column
    private int initiative;

    @Column
    private int level;
    @Column
    private int experience;
    @Column
    private int attributePoints;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "weaponCode")),
            @AttributeOverride(name = "minDamage", column = @Column(name = "weaponMinDamage")),
            @AttributeOverride(name = "maxDamage", column = @Column(name = "weaponMaxDamage")),
            @AttributeOverride(name = "defense", column = @Column(name = "weaponDefense")),
            @AttributeOverride(name = "initiative", column = @Column(name = "weaponInitiative")),
            @AttributeOverride(name = "popularity", column = @Column(name = "weaponPopularity"))
    })
    private Weapon weapon;

    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "CharacterAttributeMapping",
            joinColumns = {@JoinColumn(name = "characterName")})
    @MapKeyColumn(name = "attributeCode")
    @Column(name = "attributeValue")
    private Map<Attribute, Integer> attributes;

    @Override
    public String getCode() {
        return name;
    }

    public Map<Attribute, Integer> getAttributes() {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        return attributes;
    }
}
