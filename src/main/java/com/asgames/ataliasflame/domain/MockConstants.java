package com.asgames.ataliasflame.domain;

import com.asgames.ataliasflame.domain.model.entities.Booster;
import com.asgames.ataliasflame.domain.model.entities.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Modifier;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.valueobjects.Weapon;
import com.asgames.ataliasflame.domain.services.SelectionValue;

import java.util.List;
import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
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

    // Weapons
    public static final Map<String, Weapon> WEAPONS = Map.of(
            "FIST", Weapon.builder()
                    .code("FIST")
                    .minDamage(1)
                    .maxDamage(2)
                    .defense(0)
                    .initiative(1)
                    .build(),
            "STAFF", Weapon.builder()
                    .code("STAFF")
                    .minDamage(1)
                    .maxDamage(5)
                    .defense(10)
                    .initiative(-5)
                    .build(),
            "DAGGER", Weapon.builder()
                    .code("DAGGER")
                    .minDamage(2)
                    .maxDamage(6)
                    .defense(1)
                    .initiative(0)
                    .build(),
            "SPEAR", Weapon.builder()
                    .code("SPEAR")
                    .minDamage(2)
                    .maxDamage(12)
                    .defense(10)
                    .initiative(-6)
                    .build(),
            "SWORD", Weapon.builder()
                    .code("SWORD")
                    .minDamage(2)
                    .maxDamage(18)
                    .defense(8)
                    .initiative(-3)
                    .build()
    );

    public static final List<SelectionValue<Weapon>> STARTING_WEAPON_DROPS = List.of(
            new SelectionValue<>(5, WEAPONS.get("FIST")),
            new SelectionValue<>(30, WEAPONS.get("STAFF")),
            new SelectionValue<>(30, WEAPONS.get("DAGGER")),
            new SelectionValue<>(20, WEAPONS.get("SPEAR")),
            new SelectionValue<>(15, WEAPONS.get("SWORD"))
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

    public static final List<SelectionValue<Monster>> MONSTER_DROPS = List.of(
            new SelectionValue<>(20, MONSTERS.get("RAT")),
            new SelectionValue<>(15, MONSTERS.get("BOAR")),
            new SelectionValue<>(25, MONSTERS.get("WOLF")),
            new SelectionValue<>(35, MONSTERS.get("BANDIT")),
            new SelectionValue<>(5, MONSTERS.get("WEREWOLF"))
    );

}
