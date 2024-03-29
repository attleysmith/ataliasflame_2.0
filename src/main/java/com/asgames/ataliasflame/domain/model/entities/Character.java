package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.*;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@Data
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class Character implements Combatant {

    @Id
    @Column(name = "reference")
    private String reference;
    @Column(name = "name")
    private String name;
    @Enumerated(STRING)
    @Column(name = "gender")
    private Gender gender;
    @Enumerated(STRING)
    @Column(name = "race")
    private Race race;
    @Enumerated(STRING)
    @Column(name = "caste")
    private Caste caste;
    @Enumerated(STRING)
    @Column(name = "defensiveGod")
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

    @OneToMany(mappedBy = "owner", cascade = ALL, fetch = EAGER)
    private Set<SoulChip> soulChips;

    @OneToMany(mappedBy = "owner", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<Companion> companions;

    @OneToMany(mappedBy = "owner", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<ActiveBlessing> blessings;

    @JoinColumn(name = "weaponId")
    @OneToOne(cascade = ALL, fetch = EAGER)
    private Weapon weapon;

    @JoinColumn(name = "shieldId")
    @OneToOne(cascade = ALL, fetch = EAGER)
    private Shield shield;

    @Embedded
    private Cover cover;

    @Embedded
    private Inventory inventory;

    @JoinColumn(name = "locationId")
    @OneToOne(fetch = LAZY)
    private Location location;

    public Optional<Weapon> getWeapon() {
        return Optional.ofNullable(weapon);
    }

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

    public Inventory getInventory() {
        if (inventory == null) {
            inventory = new Inventory();
        }
        return inventory;
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

    public Set<ActiveBlessing> getBlessings() {
        if (blessings == null) {
            blessings = new HashSet<>();
        }
        return blessings;
    }

    public boolean hasFreeHand() {
        return getWeapon().map(Weapon::isOneHanded).orElse(true);
    }
}
