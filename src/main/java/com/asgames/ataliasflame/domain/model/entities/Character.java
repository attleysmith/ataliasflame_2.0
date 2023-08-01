package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.*;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.valueobjects.Armor;
import com.asgames.ataliasflame.domain.model.valueobjects.Shield;
import com.asgames.ataliasflame.domain.model.valueobjects.Weapon;
import jakarta.annotation.Nullable;
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
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    @Enumerated(STRING)
    private Gender gender;
    @Column(name = "race")
    @Enumerated(STRING)
    private Race race;
    @Column(name = "caste")
    @Enumerated(STRING)
    private Caste caste;
    @Column(name = "defensiveGod")
    @Enumerated(STRING)
    private God defensiveGod;

    @Column(name = "attack")
    private int attack;
    @Column(name = "defense")
    private int defense;
    @Column(name = "minDamage")
    private int minDamage;
    @Column(name = "maxDamage")
    private int maxDamage;
    @Column(name = "damageMultiplier")
    private int damageMultiplier;
    @Column(name = "totalHealth")
    private int totalHealth;
    @Column(name = "injury")
    private int injury;
    @Column(name = "totalMagicPoint")
    private int totalMagicPoint;
    @Column(name = "usedMagicPoint")
    private int usedMagicPoint;
    @Column(name = "initiative")
    private int initiative;

    @Column(name = "level")
    private int level;
    @Column(name = "experience")
    private int experience;
    @Column(name = "attributePoints")
    private int attributePoints;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "weaponCode")),
            @AttributeOverride(name = "minDamage", column = @Column(name = "weaponMinDamage")),
            @AttributeOverride(name = "maxDamage", column = @Column(name = "weaponMaxDamage")),
            @AttributeOverride(name = "defense", column = @Column(name = "weaponDefense")),
            @AttributeOverride(name = "initiative", column = @Column(name = "weaponInitiative")),
            @AttributeOverride(name = "popularity", column = @Column(name = "weaponPopularity")),
            @AttributeOverride(name = "oneHanded", column = @Column(name = "weaponOneHanded"))
    })
    private Weapon weapon;

    @Nullable
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "shieldCode")),
            @AttributeOverride(name = "defense", column = @Column(name = "shieldDefense")),
            @AttributeOverride(name = "popularity", column = @Column(name = "shieldPopularity"))
    })
    private Shield shield;

    @Nullable
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "armorCode")),
            @AttributeOverride(name = "defense", column = @Column(name = "armorDefense")),
            @AttributeOverride(name = "popularity", column = @Column(name = "armorPopularity"))
    })
    private Armor armor;

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

    public int getActualMagicPoint() {
        return getTotalMagicPoint() - getUsedMagicPoint();
    }
}
