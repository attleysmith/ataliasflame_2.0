package com.asgames.ataliasflame.domain;

import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.valueobjects.Armor;
import com.asgames.ataliasflame.domain.model.valueobjects.Shield;
import com.asgames.ataliasflame.domain.model.valueobjects.Weapon;
import com.asgames.ataliasflame.domain.utils.SelectionValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;
import static java.util.Collections.emptyList;

public final class MockConstants {

    private MockConstants() {
        // utility class
    }

    // Character development rules
    public static final Caste STARTING_CASTE = ROGUE;
    public static final int LEVEL_ATTRIBUTE_POINTS = 5;
    public static final int MAX_ATTRIBUTE_POINTS = 100;

    // Base character values
    public static final int BASE_HEALTH = 100;
    public static final int BASE_ATTACK = 80;
    public static final int BASE_DEFENSE = 20;
    public static final int BASE_DAMAGE_MULTIPLIER = 0;

    // Action settings
    public static final int HEALING_EFFECT_OF_SLEEP = 20;

    // Attribute modifiers
    public static final Modifier STRENGTH_MODIFIER = Modifier.builder()
            .code(STRENGTH.name())
            .attackMultiplier(0)
            .defenseMultiplier(1)
            .damageMultiplier(2)
            .healthMultiplier(0)
            .build();

    public static final Modifier DEXTERITY_MODIFIER = Modifier.builder()
            .code(DEXTERITY.name())
            .attackMultiplier(2)
            .defenseMultiplier(1)
            .damageMultiplier(1)
            .healthMultiplier(0)
            .build();

    public static final Modifier CONSTITUTION_MODIFIER = Modifier.builder()
            .code(CONSTITUTION.name())
            .attackMultiplier(0)
            .defenseMultiplier(0)
            .damageMultiplier(0)
            .healthMultiplier(10)
            .build();

    public static final Modifier AGILITY_MODIFIER = Modifier.builder()
            .code(AGILITY.name())
            .attackMultiplier(1)
            .defenseMultiplier(1)
            .damageMultiplier(0)
            .healthMultiplier(0)
            .build();

    public static final Modifier INTELLIGENCE_MODIFIER = Modifier.builder()
            .code(INTELLIGENCE.name())
            .attackMultiplier(0)
            .defenseMultiplier(0)
            .damageMultiplier(0)
            .healthMultiplier(0)
            .build();

    public static final Map<String, Modifier> MODIFIERS = Map.of(
            STRENGTH_MODIFIER.getCode(), STRENGTH_MODIFIER,
            DEXTERITY_MODIFIER.getCode(), DEXTERITY_MODIFIER,
            CONSTITUTION_MODIFIER.getCode(), CONSTITUTION_MODIFIER,
            AGILITY_MODIFIER.getCode(), AGILITY_MODIFIER,
            INTELLIGENCE_MODIFIER.getCode(), INTELLIGENCE_MODIFIER
    );

    // Divine boosters
    public static final Booster HORA_BOOSTER = Booster.builder()
            .code(HORA.name())
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0
            ))
            .build();

    public static final Booster SIFER_BOOSTER = Booster.builder()
            .code(SIFER.name())
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 1,
                    AGILITY, 0,
                    INTELLIGENCE, 0
            ))
            .build();

    public static final Booster GETON_BOOSTER = Booster.builder()
            .code(GETON.name())
            .effects(Map.of(
                    STRENGTH, 3,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0
            ))
            .build();

    public static final Booster RUNID_BOOSTER = Booster.builder()
            .code(RUNID.name())
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 2
            ))
            .build();

    public static final Booster ALATE_BOOSTER = Booster.builder()
            .code(ALATE.name())
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 3,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0
            ))
            .build();

    public static final Booster GINDON_BOOSTER = Booster.builder()
            .code(GINDON.name())
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0
            ))
            .build();

    public static final Booster HUMAN_BOOSTER = Booster.builder()
            .code(HUMAN.name())
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0
            ))
            .build();

    public static final Booster ELF_BOOSTER = Booster.builder()
            .code(ELF.name())
            .effects(Map.of(
                    STRENGTH, -30,
                    DEXTERITY, 20,
                    CONSTITUTION, -10,
                    AGILITY, 0,
                    INTELLIGENCE, 2
            ))
            .build();

    public static final Booster HALF_ELF_BOOSTER = Booster.builder()
            .code(HALF_ELF.name())
            .effects(Map.of(
                    STRENGTH, -15,
                    DEXTERITY, 10,
                    CONSTITUTION, -5,
                    AGILITY, 0,
                    INTELLIGENCE, 1
            ))
            .build();

    public static final Booster NIGHT_ELF_BOOSTER = Booster.builder()
            .code(NIGHT_ELF.name())
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 10,
                    CONSTITUTION, -1,
                    AGILITY, 0,
                    INTELLIGENCE, 0
            ))
            .build();

    public static final Booster ARIMASPI_BOOSTER = Booster.builder()
            .code(ARIMASPI.name())
            .effects(Map.of(
                    STRENGTH, 5,
                    DEXTERITY, 5,
                    CONSTITUTION, 5,
                    AGILITY, 5,
                    INTELLIGENCE, -1
            ))
            .build();

    public static final Booster HALFLING_BOOSTER = Booster.builder()
            .code(HALFLING.name())
            .effects(Map.of(
                    STRENGTH, -30,
                    DEXTERITY, 50,
                    CONSTITUTION, -10,
                    AGILITY, -5,
                    INTELLIGENCE, 0
            ))
            .build();

    public static final Map<String, Booster> BOOSTERS = Map.ofEntries(
            Map.entry(HORA_BOOSTER.getCode(), HORA_BOOSTER),
            Map.entry(SIFER_BOOSTER.getCode(), SIFER_BOOSTER),
            Map.entry(GETON_BOOSTER.getCode(), GETON_BOOSTER),
            Map.entry(RUNID_BOOSTER.getCode(), RUNID_BOOSTER),
            Map.entry(ALATE_BOOSTER.getCode(), ALATE_BOOSTER),
            Map.entry(GINDON_BOOSTER.getCode(), GINDON_BOOSTER),
            Map.entry(HUMAN_BOOSTER.getCode(), HUMAN_BOOSTER),
            Map.entry(ELF_BOOSTER.getCode(), ELF_BOOSTER),
            Map.entry(HALF_ELF_BOOSTER.getCode(), HALF_ELF_BOOSTER),
            Map.entry(NIGHT_ELF_BOOSTER.getCode(), NIGHT_ELF_BOOSTER),
            Map.entry(ARIMASPI_BOOSTER.getCode(), ARIMASPI_BOOSTER),
            Map.entry(HALFLING_BOOSTER.getCode(), HALFLING_BOOSTER)
    );

    public static final Map<Caste, CasteDetails> CASTE_DETAILS = Map.of(
            ROGUE, CasteDetails.builder()
                    .group(UNSPECIALIZED)
                    .caste(ROGUE)
                    .nextCastes(List.of(FIGHTER, TRACKER, HERMIT))
                    .minimumAttributes(Map.of(
                            STRENGTH, 1,
                            DEXTERITY, 1,
                            CONSTITUTION, 1,
                            AGILITY, 1,
                            INTELLIGENCE, 1
                    )).build(),
            FIGHTER, CasteDetails.builder()
                    .group(WARRIOR)
                    .caste(FIGHTER)
                    .nextCastes(List.of(PALADIN))
                    .minimumAttributes(Map.of(
                            STRENGTH, 5,
                            DEXTERITY, 5,
                            CONSTITUTION, 5,
                            AGILITY, 5,
                            INTELLIGENCE, 2
                    )).build(),
            PALADIN, CasteDetails.builder()
                    .group(WARRIOR)
                    .caste(PALADIN)
                    .nextCastes(List.of(GRANDMASTER))
                    .minimumAttributes(Map.of(
                            STRENGTH, 20,
                            DEXTERITY, 20,
                            CONSTITUTION, 20,
                            AGILITY, 20,
                            INTELLIGENCE, 7
                    )).build(),
            GRANDMASTER, CasteDetails.builder()
                    .group(WARRIOR)
                    .caste(GRANDMASTER)
                    .nextCastes(List.of(TITAN))
                    .minimumAttributes(Map.of(
                            STRENGTH, 50,
                            DEXTERITY, 50,
                            CONSTITUTION, 50,
                            AGILITY, 50,
                            INTELLIGENCE, 20
                    )).build(),
            TITAN, CasteDetails.builder()
                    .group(WARRIOR)
                    .caste(TITAN)
                    .nextCastes(emptyList())
                    .minimumAttributes(Map.of(
                            STRENGTH, 100,
                            DEXTERITY, 100,
                            CONSTITUTION, 100,
                            AGILITY, 100,
                            INTELLIGENCE, 40
                    )).build(),
            TRACKER, CasteDetails.builder()
                    .group(WANDERER)
                    .caste(TRACKER)
                    .nextCastes(emptyList())
                    .minimumAttributes(Map.of(
                            STRENGTH, 4,
                            DEXTERITY, 5,
                            CONSTITUTION, 5,
                            AGILITY, 5,
                            INTELLIGENCE, 2
                    )).build(),
            HERMIT, CasteDetails.builder()
                    .group(NATURE_DWELLER)
                    .caste(HERMIT)
                    .nextCastes(emptyList())
                    .minimumAttributes(Map.of(
                            STRENGTH, 3,
                            DEXTERITY, 3,
                            CONSTITUTION, 4,
                            AGILITY, 3,
                            INTELLIGENCE, 4
                    )).build()
    );

    // Armory
    public static final Map<String, Weapon> WEAPONS = Map.of(
            "FIST", Weapon.builder()
                    .code("FIST")
                    .minDamage(1)
                    .maxDamage(2)
                    .defense(0)
                    .initiative(1)
                    .popularity(0)
                    .oneHanded(true)
                    .build(),
            "STAFF", Weapon.builder()
                    .code("STAFF")
                    .minDamage(1)
                    .maxDamage(5)
                    .defense(5)
                    .initiative(-5)
                    .popularity(10)
                    .oneHanded(false)
                    .build(),
            "DAGGER", Weapon.builder()
                    .code("DAGGER")
                    .minDamage(2)
                    .maxDamage(6)
                    .defense(1)
                    .initiative(0)
                    .popularity(20)
                    .oneHanded(true)
                    .build(),
            "SPEAR", Weapon.builder()
                    .code("SPEAR")
                    .minDamage(2)
                    .maxDamage(12)
                    .defense(5)
                    .initiative(-6)
                    .popularity(30)
                    .oneHanded(false)
                    .build(),
            "SWORD", Weapon.builder()
                    .code("SWORD")
                    .minDamage(2)
                    .maxDamage(18)
                    .defense(3)
                    .initiative(-3)
                    .popularity(40)
                    .oneHanded(true)
                    .build()
    );

    public static final Map<String, Shield> SHIELDS = Map.of(
            "BUCKLER", Shield.builder()
                    .code("BUCKLER")
                    .defense(5)
                    .popularity(0)
                    .build(),
            "ROUND_SHIELD", Shield.builder()
                    .code("ROUND_SHIELD")
                    .defense(10)
                    .popularity(10)
                    .build(),
            "KITE_SHIELD", Shield.builder()
                    .code("KITE_SHIELD")
                    .defense(15)
                    .popularity(20)
                    .build(),
            "TOWER_SHIELD", Shield.builder()
                    .code("TOWER_SHIELD")
                    .defense(20)
                    .popularity(30)
                    .build()
    );

    public static final Map<String, Armor> ARMORS = Map.of(
            "LINEN_ARMOR", Armor.builder()
                    .code("LINEN_ARMOR")
                    .defense(10)
                    .popularity(10)
                    .build(),
            "LEATHER_ARMOR", Armor.builder()
                    .code("LEATHER_ARMOR")
                    .defense(15)
                    .popularity(20)
                    .build(),
            "STUDDED_LEATHER_ARMOR", Armor.builder()
                    .code("STUDDED_LEATHER_ARMOR")
                    .defense(20)
                    .popularity(30)
                    .build(),
            "CHAIN_MAIL", Armor.builder()
                    .code("CHAIN_MAIL")
                    .defense(25)
                    .popularity(40)
                    .build(),
            "PLATE_MAIL", Armor.builder()
                    .code("PLATE_MAIL")
                    .defense(30)
                    .popularity(50)
                    .build(),
            "FULL_PLATE_MAIL", Armor.builder()
                    .code("FULL_PLATE_MAIL")
                    .defense(40)
                    .popularity(60)
                    .build()
    );

    public static final List<SelectionValue<Weapon>> STARTING_WEAPON_SELECTOR = List.of(
            new SelectionValue<>(5, WEAPONS.get("FIST")),
            new SelectionValue<>(30, WEAPONS.get("STAFF")),
            new SelectionValue<>(30, WEAPONS.get("DAGGER")),
            new SelectionValue<>(20, WEAPONS.get("SPEAR")),
            new SelectionValue<>(15, WEAPONS.get("SWORD"))
    );

    public static final List<SelectionValue<Optional<Shield>>> STARTING_SHIELD_SELECTOR = List.of(
            new SelectionValue<>(60, Optional.empty()),
            new SelectionValue<>(10, Optional.of(SHIELDS.get("BUCKLER"))),
            new SelectionValue<>(15, Optional.of(SHIELDS.get("ROUND_SHIELD"))),
            new SelectionValue<>(10, Optional.of(SHIELDS.get("KITE_SHIELD"))),
            new SelectionValue<>(5, Optional.of(SHIELDS.get("TOWER_SHIELD")))
    );

    public static final List<SelectionValue<Optional<Armor>>> STARTING_ARMOR_SELECTOR = List.of(
            new SelectionValue<>(50, Optional.empty()),
            new SelectionValue<>(15, Optional.of(ARMORS.get("LINEN_ARMOR"))),
            new SelectionValue<>(10, Optional.of(ARMORS.get("LEATHER_ARMOR"))),
            new SelectionValue<>(10, Optional.of(ARMORS.get("STUDDED_LEATHER_ARMOR"))),
            new SelectionValue<>(5, Optional.of(ARMORS.get("CHAIN_MAIL"))),
            new SelectionValue<>(5, Optional.of(ARMORS.get("PLATE_MAIL"))),
            new SelectionValue<>(5, Optional.of(ARMORS.get("FULL_PLATE_MAIL")))
    );

    // Monsters
    public static final Map<String, Monster> MONSTERS = Map.of(
            "RAT", Monster.builder()
                    .code("RAT")
                    .attack(40)
                    .defense(0)
                    .minDamage(1)
                    .maxDamage(1)
                    .totalHealth(10)
                    .experience(10)
                    .initiative(3)
                    .build(),
            "BOAR", Monster.builder()
                    .code("BOAR")
                    .attack(60)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(2)
                    .totalHealth(25)
                    .experience(20)
                    .initiative(0)
                    .build(),
            "WOLF", Monster.builder()
                    .code("WOLF")
                    .attack(70)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(3)
                    .totalHealth(25)
                    .experience(30)
                    .initiative(-3)
                    .build(),
            "BANDIT", Monster.builder()
                    .code("BANDIT")
                    .attack(75)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(4)
                    .totalHealth(30)
                    .experience(40)
                    .initiative(-1)
                    .build(),
            "WEREWOLF", Monster.builder()
                    .code("WEREWOLF")
                    .attack(75)
                    .defense(10)
                    .minDamage(1)
                    .maxDamage(5)
                    .totalHealth(40)
                    .experience(50)
                    .initiative(-3)
                    .build()
    );

    public static final List<SelectionValue<Monster>> MONSTER_SELECTOR = List.of(
            new SelectionValue<>(20, MONSTERS.get("RAT")),
            new SelectionValue<>(15, MONSTERS.get("BOAR")),
            new SelectionValue<>(25, MONSTERS.get("WOLF")),
            new SelectionValue<>(35, MONSTERS.get("BANDIT")),
            new SelectionValue<>(5, MONSTERS.get("WEREWOLF"))
    );

    public static final Map<String, List<SelectionValue<Optional<Item>>>> MONSTER_DROPS = Map.of(
            "BOAR", List.of(
                    new SelectionValue<>(10, Optional.empty()),
                    new SelectionValue<>(90, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("MEAT")
                            .healingEffect(10)
                            .build()))),
            "BANDIT", List.of(
                    new SelectionValue<>(5, Optional.empty()),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("WATER")
                            .healingEffect(3)
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("BREAD")
                            .healingEffect(5)
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("FRUIT")
                            .healingEffect(8)
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("MEAT")
                            .healingEffect(10)
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("HEALING_HERB")
                            .healingEffect(20)
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(WEAPON)
                            .code("STAFF")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(WEAPON)
                            .code("DAGGER")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(WEAPON)
                            .code("SPEAR")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(WEAPON)
                            .code("SWORD")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(SHIELD)
                            .code("BUCKLER")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(SHIELD)
                            .code("ROUND_SHIELD")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(SHIELD)
                            .code("KITE_SHIELD")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(SHIELD)
                            .code("TOWER_SHIELD")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(ARMOR)
                            .code("LINEN_ARMOR")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(ARMOR)
                            .code("LEATHER_ARMOR")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(ARMOR)
                            .code("STUDDED_LEATHER_ARMOR")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(ARMOR)
                            .code("CHAIN_MAIL")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(ARMOR)
                            .code("PLATE_MAIL")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(ARMOR)
                            .code("FULL_PLATE_MAIL")
                            .build()))),
            "WEREWOLF", List.of(
                    new SelectionValue<>(50, Optional.empty()),
                    new SelectionValue<>(15, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("WATER")
                            .healingEffect(3)
                            .build())),
                    new SelectionValue<>(15, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("MEAT")
                            .healingEffect(10)
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(FOOD)
                            .code("HEALING_HERB")
                            .healingEffect(20)
                            .build())),
                    new SelectionValue<>(10, Optional.of(Item.builder()
                            .type(WEAPON)
                            .code("DAGGER")
                            .build())),
                    new SelectionValue<>(5, Optional.of(Item.builder()
                            .type(WEAPON)
                            .code("SWORD")
                            .build())))
    );

}
