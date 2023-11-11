package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static java.util.Collections.emptySet;

@AllArgsConstructor
public enum Caste {
    WITCH_DOCTOR(DUAL, Set.of(NATURE_DWELLER, SORCERER), Map.of(
            STRENGTH, 25,
            DEXTERITY, 25,
            CONSTITUTION, 45,
            AGILITY, 25,
            INTELLIGENCE, 50,
            LORE, 50,
            MENTAL_POWER, 50,
            SPIRITUAL_POWER, 15
    ), emptySet()),
    BARBARIAN(DUAL, Set.of(WARRIOR, NATURE_DWELLER), Map.of(
            STRENGTH, 45,
            DEXTERITY, 50,
            CONSTITUTION, 50,
            AGILITY, 50,
            INTELLIGENCE, 40,
            LORE, 25,
            MENTAL_POWER, 25,
            SPIRITUAL_POWER, 10
    ), emptySet()),
    CRUSADER(DUAL, Set.of(CLERIC, WARRIOR), Map.of(
            STRENGTH, 50,
            DEXTERITY, 50,
            CONSTITUTION, 50,
            AGILITY, 50,
            INTELLIGENCE, 40,
            LORE, 25,
            MENTAL_POWER, 25,
            SPIRITUAL_POWER, 5
    ), emptySet()),
    APOSTLE(DUAL, Set.of(WANDERER, CLERIC), Map.of(
            STRENGTH, 35,
            DEXTERITY, 45,
            CONSTITUTION, 45,
            AGILITY, 45,
            INTELLIGENCE, 40,
            LORE, 25,
            MENTAL_POWER, 20,
            SPIRITUAL_POWER, 25
    ), emptySet()),
    CHARLATAN(DUAL, Set.of(SORCERER, WANDERER), Map.of(
            STRENGTH, 35,
            DEXTERITY, 45,
            CONSTITUTION, 45,
            AGILITY, 45,
            INTELLIGENCE, 40,
            LORE, 25,
            MENTAL_POWER, 20,
            SPIRITUAL_POWER, 25
    ), emptySet()),
    SAINT(DUAL, Set.of(CLERIC, SORCERER), Map.of(
            STRENGTH, 25,
            DEXTERITY, 30,
            CONSTITUTION, 30,
            AGILITY, 30,
            INTELLIGENCE, 50,
            LORE, 50,
            MENTAL_POWER, 50,
            SPIRITUAL_POWER, 15
    ), emptySet()),
    SHAMAN(DUAL, Set.of(NATURE_DWELLER, CLERIC), Map.of(
            STRENGTH, 25,
            DEXTERITY, 30,
            CONSTITUTION, 45,
            AGILITY, 30,
            INTELLIGENCE, 45,
            LORE, 50,
            MENTAL_POWER, 45,
            SPIRITUAL_POWER, 10
    ), emptySet()),
    NOMAD(DUAL, Set.of(WANDERER, NATURE_DWELLER), Map.of(
            STRENGTH, 35,
            DEXTERITY, 45,
            CONSTITUTION, 45,
            AGILITY, 45,
            INTELLIGENCE, 40,
            LORE, 25,
            MENTAL_POWER, 15,
            SPIRITUAL_POWER, 25
    ), emptySet()),
    CHAMPION(DUAL, Set.of(WARRIOR, WANDERER), Map.of(
            STRENGTH, 50,
            DEXTERITY, 50,
            CONSTITUTION, 50,
            AGILITY, 50,
            INTELLIGENCE, 20,
            LORE, 20,
            MENTAL_POWER, 10,
            SPIRITUAL_POWER, 15
    ), emptySet()),
    SAGE(DUAL, Set.of(SORCERER, WARRIOR), Map.of(
            STRENGTH, 40,
            DEXTERITY, 45,
            CONSTITUTION, 45,
            AGILITY, 45,
            INTELLIGENCE, 40,
            LORE, 25,
            MENTAL_POWER, 25,
            SPIRITUAL_POWER, 10
    ), emptySet()),
    ARCHANGEL(CLERIC, Set.of(CLERIC), Map.of(
            STRENGTH, 50,
            DEXTERITY, 55,
            CONSTITUTION, 60,
            AGILITY, 50,
            INTELLIGENCE, 70,
            LORE, 100,
            MENTAL_POWER, 90,
            SPIRITUAL_POWER, 25
    ), emptySet()),
    HIERARCH(CLERIC, Set.of(CLERIC), Map.of(
            STRENGTH, 25,
            DEXTERITY, 27,
            CONSTITUTION, 26,
            AGILITY, 27,
            INTELLIGENCE, 45,
            LORE, 50,
            MENTAL_POWER, 45,
            SPIRITUAL_POWER, 5
    ), Set.of(ARCHANGEL)),
    PRIEST(CLERIC, Set.of(CLERIC), Map.of(
            STRENGTH, 8,
            DEXTERITY, 12,
            CONSTITUTION, 12,
            AGILITY, 12,
            INTELLIGENCE, 16,
            LORE, 20,
            MENTAL_POWER, 18,
            SPIRITUAL_POWER, 2
    ), Set.of(HIERARCH, SHAMAN, SAINT, APOSTLE, CRUSADER)),
    MONK(CLERIC, Set.of(CLERIC), Map.of(
            STRENGTH, 3,
            DEXTERITY, 3,
            CONSTITUTION, 2,
            AGILITY, 3,
            INTELLIGENCE, 4,
            LORE, 5,
            MENTAL_POWER, 4,
            SPIRITUAL_POWER, 1
    ), Set.of(PRIEST)),
    ATALIAS_PRIEST(NATURE_DWELLER, Set.of(NATURE_DWELLER), Map.of(
            STRENGTH, 45,
            DEXTERITY, 50,
            CONSTITUTION, 70,
            AGILITY, 50,
            INTELLIGENCE, 80,
            LORE, 90,
            MENTAL_POWER, 90,
            SPIRITUAL_POWER, 25
    ), emptySet()),
    ARCHDRUID(NATURE_DWELLER, Set.of(NATURE_DWELLER), Map.of(
            STRENGTH, 25,
            DEXTERITY, 25,
            CONSTITUTION, 45,
            AGILITY, 25,
            INTELLIGENCE, 40,
            LORE, 40,
            MENTAL_POWER, 40,
            SPIRITUAL_POWER, 10
    ), Set.of(ATALIAS_PRIEST)),
    DRUID(NATURE_DWELLER, Set.of(NATURE_DWELLER), Map.of(
            STRENGTH, 8,
            DEXTERITY, 12,
            CONSTITUTION, 17,
            AGILITY, 12,
            INTELLIGENCE, 18,
            LORE, 18,
            MENTAL_POWER, 12,
            SPIRITUAL_POWER, 3
    ), Set.of(ARCHDRUID, NOMAD, SHAMAN, BARBARIAN, WITCH_DOCTOR)),
    HERMIT(NATURE_DWELLER, Set.of(NATURE_DWELLER), Map.of(
            STRENGTH, 3,
            DEXTERITY, 3,
            CONSTITUTION, 4,
            AGILITY, 3,
            INTELLIGENCE, 4,
            LORE, 4,
            MENTAL_POWER, 3,
            SPIRITUAL_POWER, 1
    ), Set.of(DRUID)),
    FREE_SOUL(WANDERER, Set.of(WANDERER), Map.of(
            STRENGTH, 75,
            DEXTERITY, 80,
            CONSTITUTION, 85,
            AGILITY, 85,
            INTELLIGENCE, 40,
            LORE, 25,
            MENTAL_POWER, 10,
            SPIRITUAL_POWER, 100
    ), emptySet()),
    PILGRIM(WANDERER, Set.of(WANDERER), Map.of(
            STRENGTH, 35,
            DEXTERITY, 42,
            CONSTITUTION, 45,
            AGILITY, 45,
            INTELLIGENCE, 20,
            LORE, 18,
            MENTAL_POWER, 5,
            SPIRITUAL_POWER, 40
    ), Set.of(FREE_SOUL)),
    RANGER(WANDERER, Set.of(WANDERER), Map.of(
            STRENGTH, 16,
            DEXTERITY, 18,
            CONSTITUTION, 20,
            AGILITY, 18,
            INTELLIGENCE, 7,
            LORE, 7,
            MENTAL_POWER, 2,
            SPIRITUAL_POWER, 12
    ), Set.of(PILGRIM, CHAMPION, NOMAD, CHARLATAN, APOSTLE)),
    TRACKER(WANDERER, Set.of(WANDERER), Map.of(
            STRENGTH, 4,
            DEXTERITY, 5,
            CONSTITUTION, 5,
            AGILITY, 5,
            INTELLIGENCE, 2,
            LORE, 1,
            MENTAL_POWER, 1,
            SPIRITUAL_POWER, 2
    ), Set.of(RANGER)),
    TITAN(WARRIOR, Set.of(WARRIOR), Map.of(
            STRENGTH, 100,
            DEXTERITY, 100,
            CONSTITUTION, 100,
            AGILITY, 100,
            INTELLIGENCE, 40,
            LORE, 25,
            MENTAL_POWER, 25,
            SPIRITUAL_POWER, 10
    ), emptySet()),
    GRANDMASTER(WARRIOR, Set.of(WARRIOR), Map.of(
            STRENGTH, 50,
            DEXTERITY, 50,
            CONSTITUTION, 50,
            AGILITY, 50,
            INTELLIGENCE, 20,
            LORE, 13,
            MENTAL_POWER, 12,
            SPIRITUAL_POWER, 5
    ), Set.of(TITAN)),
    PALADIN(WARRIOR, Set.of(WARRIOR), Map.of(
            STRENGTH, 20,
            DEXTERITY, 20,
            CONSTITUTION, 20,
            AGILITY, 20,
            INTELLIGENCE, 7,
            LORE, 6,
            MENTAL_POWER, 5,
            SPIRITUAL_POWER, 2
    ), Set.of(GRANDMASTER, SAGE, CHAMPION, CRUSADER, BARBARIAN)),
    FIGHTER(WARRIOR, Set.of(WARRIOR), Map.of(
            STRENGTH, 5,
            DEXTERITY, 5,
            CONSTITUTION, 5,
            AGILITY, 5,
            INTELLIGENCE, 2,
            LORE, 1,
            MENTAL_POWER, 1,
            SPIRITUAL_POWER, 1
    ), Set.of(PALADIN)),
    AVATAR(SORCERER, Set.of(SORCERER), Map.of(
            STRENGTH, 40,
            DEXTERITY, 45,
            CONSTITUTION, 45,
            AGILITY, 45,
            INTELLIGENCE, 100,
            LORE, 100,
            MENTAL_POWER, 100,
            SPIRITUAL_POWER, 25
    ), emptySet()),
    WITCHMASTER(SORCERER, Set.of(SORCERER), Map.of(
            STRENGTH, 18,
            DEXTERITY, 25,
            CONSTITUTION, 20,
            AGILITY, 22,
            INTELLIGENCE, 50,
            LORE, 50,
            MENTAL_POWER, 50,
            SPIRITUAL_POWER, 15
    ), Set.of(AVATAR)),
    MAGE(SORCERER, Set.of(SORCERER), Map.of(
            STRENGTH, 6,
            DEXTERITY, 12,
            CONSTITUTION, 7,
            AGILITY, 10,
            INTELLIGENCE, 20,
            LORE, 20,
            MENTAL_POWER, 20,
            SPIRITUAL_POWER, 5
    ), Set.of(WITCHMASTER, SAGE, SAINT, CHARLATAN, WITCH_DOCTOR)),
    WIZARD(SORCERER, Set.of(SORCERER), Map.of(
            STRENGTH, 2,
            DEXTERITY, 3,
            CONSTITUTION, 2,
            AGILITY, 3,
            INTELLIGENCE, 5,
            LORE, 5,
            MENTAL_POWER, 4,
            SPIRITUAL_POWER, 1
    ), Set.of(MAGE)),
    ROGUE(UNSPECIALIZED, Set.of(UNSPECIALIZED), Map.of(
            STRENGTH, 1,
            DEXTERITY, 1,
            CONSTITUTION, 1,
            AGILITY, 1,
            INTELLIGENCE, 1,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    ), Set.of(WIZARD, FIGHTER, TRACKER, HERMIT, MONK));

    public final CasteGroup group;
    public final Set<CasteGroup> groupTags;
    public final Map<Attribute, Integer> minimumAttributes;
    public final Set<Caste> nextCastes;
}
