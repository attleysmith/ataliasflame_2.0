package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.*;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
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
    @Column(name = "reference")
    private String reference;
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
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "totalHealth")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "injury"))
    })
    private Energy health;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "totalMagicPoint")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "usedMagicPoint"))
    })
    private Energy magic;

    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "CharacterAttributeMapping",
            joinColumns = {@JoinColumn(name = "characterId")})
    @MapKeyColumn(name = "attributeCode")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "attributeValue")
    private Map<Attribute, Integer> attributes;

    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "CharacterBlessing",
            joinColumns = {@JoinColumn(name = "characterId")})
    @Column(name = "blessing")
    private Set<String> blessings;

    @OneToMany(mappedBy = "owner", cascade = ALL, fetch = EAGER)
    private Set<SoulChip> soulChips;

    @OneToMany(mappedBy = "owner", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<Companion> companions;

    @JoinColumn(name = "weaponId", nullable = false)
    @OneToOne(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Weapon weapon;

    @JoinColumn(name = "shieldId")
    @OneToOne(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Shield shield;

    @Embedded
    private Cover cover;

    @Override
    public Optional<Shield> getShield() {
        return Optional.ofNullable(shield);
    }

    @Override
    public Cover getCover() {
        if (cover == null) {
            cover = new Cover();
        }
        return cover;
    }

    @Override
    public String getCode() {
        return name;
    }

    public Energy getHealth() {
        if (health == null) {
            health = new Energy();
        }
        return health;
    }

    public Energy getMagic() {
        if (magic == null) {
            magic = new Energy();
        }
        return magic;
    }

    public Map<Attribute, Integer> getAttributes() {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        return attributes;
    }

    public Set<String> getBlessings() {
        if (blessings == null) {
            blessings = new HashSet<>();
        }
        return blessings;
    }

    public Set<SoulChip> getSoulChips() {
        if (soulChips == null) {
            soulChips = new HashSet<>();
        }
        return soulChips;
    }

    public Set<Companion> getCompanions() {
        if (companions == null) {
            companions = new HashSet<>();
        }
        return companions;
    }
}
