package com.asgames.ataliasflame.domain;

import com.asgames.ataliasflame.domain.model.dtos.CasteDetails;
import com.asgames.ataliasflame.domain.model.dtos.Modifier;
import com.asgames.ataliasflame.domain.model.enums.Caste;

import java.util.List;
import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static java.util.Collections.emptyList;

public final class MockConstants {

    private MockConstants() {
        // utility class
    }

    // Character development rules
    public static final int LEVEL_ATTRIBUTE_POINTS = 5;
    public static final int MAX_ATTRIBUTE_POINTS = 100;

    // Attribute modifiers
    public static final Modifier STRENGTH_MODIFIER = Modifier.builder()
            .attackMultiplier(0)
            .defenseMultiplier(1)
            .damageMultiplier(2)
            .healthMultiplier(0)
            .magicPoint(0)
            .build();

    public static final Modifier DEXTERITY_MODIFIER = Modifier.builder()
            .attackMultiplier(2)
            .defenseMultiplier(1)
            .damageMultiplier(1)
            .healthMultiplier(0)
            .magicPoint(0)
            .build();

    public static final Modifier CONSTITUTION_MODIFIER = Modifier.builder()
            .attackMultiplier(0)
            .defenseMultiplier(0)
            .damageMultiplier(0)
            .healthMultiplier(10)
            .magicPoint(0)
            .build();

    public static final Modifier AGILITY_MODIFIER = Modifier.builder()
            .attackMultiplier(1)
            .defenseMultiplier(1)
            .damageMultiplier(0)
            .healthMultiplier(0)
            .magicPoint(0)
            .build();

    public static final Modifier INTELLIGENCE_MODIFIER = Modifier.builder()
            .attackMultiplier(0)
            .defenseMultiplier(0)
            .damageMultiplier(0)
            .healthMultiplier(0)
            .magicPoint(5)
            .build();

    public static final Modifier LORE_MODIFIER = Modifier.builder()
            .attackMultiplier(0)
            .defenseMultiplier(0)
            .damageMultiplier(0)
            .healthMultiplier(0)
            .magicPoint(2)
            .build();

    public static final Modifier MENTAL_POWER_MODIFIER = Modifier.builder()
            .attackMultiplier(0)
            .defenseMultiplier(0)
            .damageMultiplier(0)
            .healthMultiplier(0)
            .magicPoint(10)
            .build();

    public static final Modifier SPIRITUAL_POWER_MODIFIER = Modifier.builder()
            .attackMultiplier(0)
            .defenseMultiplier(0)
            .damageMultiplier(0)
            .healthMultiplier(0)
            .magicPoint(1)
            .build();

    public static final Map<String, Modifier> MODIFIERS = Map.ofEntries(
            Map.entry(STRENGTH.name(), STRENGTH_MODIFIER),
            Map.entry(DEXTERITY.name(), DEXTERITY_MODIFIER),
            Map.entry(CONSTITUTION.name(), CONSTITUTION_MODIFIER),
            Map.entry(AGILITY.name(), AGILITY_MODIFIER),
            Map.entry(INTELLIGENCE.name(), INTELLIGENCE_MODIFIER),
            Map.entry(LORE.name(), LORE_MODIFIER),
            Map.entry(MENTAL_POWER.name(), MENTAL_POWER_MODIFIER),
            Map.entry(SPIRITUAL_POWER.name(), SPIRITUAL_POWER_MODIFIER)
    );

    // Caste details
    public static final Map<Caste, CasteDetails> CASTE_DETAILS = Map.ofEntries(
            Map.entry(ROGUE, CasteDetails.builder()
                    .group(UNSPECIALIZED)
                    .caste(ROGUE)
                    .nextCastes(List.of(WIZARD, FIGHTER, TRACKER, HERMIT, MONK))
                    .minimumAttributes(Map.of(
                            STRENGTH, 1,
                            DEXTERITY, 1,
                            CONSTITUTION, 1,
                            AGILITY, 1,
                            INTELLIGENCE, 1,
                            LORE, 0,
                            MENTAL_POWER, 0,
                            SPIRITUAL_POWER, 0
                    )).build()),
            Map.entry(WIZARD, CasteDetails.builder()
                    .group(SORCERER)
                    .caste(WIZARD)
                    .nextCastes(List.of(MAGE))
                    .minimumAttributes(Map.of(
                            STRENGTH, 2,
                            DEXTERITY, 3,
                            CONSTITUTION, 2,
                            AGILITY, 3,
                            INTELLIGENCE, 5,
                            LORE, 5,
                            MENTAL_POWER, 4,
                            SPIRITUAL_POWER, 1
                    )).build()),
            Map.entry(MAGE, CasteDetails.builder()
                    .group(SORCERER)
                    .caste(MAGE)
                    .nextCastes(List.of(WITCHMASTER))
                    .minimumAttributes(Map.of(
                            STRENGTH, 6,
                            DEXTERITY, 12,
                            CONSTITUTION, 7,
                            AGILITY, 10,
                            INTELLIGENCE, 20,
                            LORE, 20,
                            MENTAL_POWER, 20,
                            SPIRITUAL_POWER, 5
                    )).build()),
            Map.entry(WITCHMASTER, CasteDetails.builder()
                    .group(SORCERER)
                    .caste(WITCHMASTER)
                    .nextCastes(List.of(AVATAR))
                    .minimumAttributes(Map.of(
                            STRENGTH, 18,
                            DEXTERITY, 25,
                            CONSTITUTION, 20,
                            AGILITY, 22,
                            INTELLIGENCE, 50,
                            LORE, 50,
                            MENTAL_POWER, 50,
                            SPIRITUAL_POWER, 15
                    )).build()),
            Map.entry(AVATAR, CasteDetails.builder()
                    .group(SORCERER)
                    .caste(AVATAR)
                    .nextCastes(emptyList())
                    .minimumAttributes(Map.of(
                            STRENGTH, 40,
                            DEXTERITY, 45,
                            CONSTITUTION, 45,
                            AGILITY, 45,
                            INTELLIGENCE, 100,
                            LORE, 100,
                            MENTAL_POWER, 100,
                            SPIRITUAL_POWER, 25
                    )).build()),
            Map.entry(FIGHTER, CasteDetails.builder()
                    .group(WARRIOR)
                    .caste(FIGHTER)
                    .nextCastes(List.of(PALADIN))
                    .minimumAttributes(Map.of(
                            STRENGTH, 5,
                            DEXTERITY, 5,
                            CONSTITUTION, 5,
                            AGILITY, 5,
                            INTELLIGENCE, 2,
                            LORE, 1,
                            MENTAL_POWER, 1,
                            SPIRITUAL_POWER, 1
                    )).build()),
            Map.entry(PALADIN, CasteDetails.builder()
                    .group(WARRIOR)
                    .caste(PALADIN)
                    .nextCastes(List.of(GRANDMASTER))
                    .minimumAttributes(Map.of(
                            STRENGTH, 20,
                            DEXTERITY, 20,
                            CONSTITUTION, 20,
                            AGILITY, 20,
                            INTELLIGENCE, 7,
                            LORE, 6,
                            MENTAL_POWER, 5,
                            SPIRITUAL_POWER, 2
                    )).build()),
            Map.entry(GRANDMASTER, CasteDetails.builder()
                    .group(WARRIOR)
                    .caste(GRANDMASTER)
                    .nextCastes(List.of(TITAN))
                    .minimumAttributes(Map.of(
                            STRENGTH, 50,
                            DEXTERITY, 50,
                            CONSTITUTION, 50,
                            AGILITY, 50,
                            INTELLIGENCE, 20,
                            LORE, 13,
                            MENTAL_POWER, 12,
                            SPIRITUAL_POWER, 5
                    )).build()),
            Map.entry(TITAN, CasteDetails.builder()
                    .group(WARRIOR)
                    .caste(TITAN)
                    .nextCastes(emptyList())
                    .minimumAttributes(Map.of(
                            STRENGTH, 100,
                            DEXTERITY, 100,
                            CONSTITUTION, 100,
                            AGILITY, 100,
                            INTELLIGENCE, 40,
                            LORE, 25,
                            MENTAL_POWER, 25,
                            SPIRITUAL_POWER, 10
                    )).build()),
            Map.entry(TRACKER, CasteDetails.builder()
                    .group(WANDERER)
                    .caste(TRACKER)
                    .nextCastes(List.of(RANGER))
                    .minimumAttributes(Map.of(
                            STRENGTH, 4,
                            DEXTERITY, 5,
                            CONSTITUTION, 5,
                            AGILITY, 5,
                            INTELLIGENCE, 2,
                            LORE, 1,
                            MENTAL_POWER, 1,
                            SPIRITUAL_POWER, 2
                    )).build()),
            Map.entry(RANGER, CasteDetails.builder()
                    .group(WANDERER)
                    .caste(RANGER)
                    .nextCastes(List.of(PILGRIM))
                    .minimumAttributes(Map.of(
                            STRENGTH, 16,
                            DEXTERITY, 18,
                            CONSTITUTION, 20,
                            AGILITY, 18,
                            INTELLIGENCE, 7,
                            LORE, 7,
                            MENTAL_POWER, 2,
                            SPIRITUAL_POWER, 12
                    )).build()),
            Map.entry(PILGRIM, CasteDetails.builder()
                    .group(WANDERER)
                    .caste(PILGRIM)
                    .nextCastes(List.of(FREE_SOUL))
                    .minimumAttributes(Map.of(
                            STRENGTH, 35,
                            DEXTERITY, 42,
                            CONSTITUTION, 45,
                            AGILITY, 45,
                            INTELLIGENCE, 20,
                            LORE, 18,
                            MENTAL_POWER, 5,
                            SPIRITUAL_POWER, 40
                    )).build()),
            Map.entry(FREE_SOUL, CasteDetails.builder()
                    .group(WANDERER)
                    .caste(FREE_SOUL)
                    .nextCastes(emptyList())
                    .minimumAttributes(Map.of(
                            STRENGTH, 75,
                            DEXTERITY, 80,
                            CONSTITUTION, 85,
                            AGILITY, 85,
                            INTELLIGENCE, 40,
                            LORE, 25,
                            MENTAL_POWER, 10,
                            SPIRITUAL_POWER, 100
                    )).build()),
            Map.entry(HERMIT, CasteDetails.builder()
                    .group(NATURE_DWELLER)
                    .caste(HERMIT)
                    .nextCastes(List.of(DRUID))
                    .minimumAttributes(Map.of(
                            STRENGTH, 3,
                            DEXTERITY, 3,
                            CONSTITUTION, 4,
                            AGILITY, 3,
                            INTELLIGENCE, 4,
                            LORE, 4,
                            MENTAL_POWER, 3,
                            SPIRITUAL_POWER, 1
                    )).build()),
            Map.entry(DRUID, CasteDetails.builder()
                    .group(NATURE_DWELLER)
                    .caste(DRUID)
                    .nextCastes(List.of(ARCHDRUID))
                    .minimumAttributes(Map.of(
                            STRENGTH, 8,
                            DEXTERITY, 12,
                            CONSTITUTION, 17,
                            AGILITY, 12,
                            INTELLIGENCE, 18,
                            LORE, 18,
                            MENTAL_POWER, 12,
                            SPIRITUAL_POWER, 3
                    )).build()),
            Map.entry(ARCHDRUID, CasteDetails.builder()
                    .group(NATURE_DWELLER)
                    .caste(ARCHDRUID)
                    .nextCastes(List.of(ATALIAS_PRIEST))
                    .minimumAttributes(Map.of(
                            STRENGTH, 25,
                            DEXTERITY, 25,
                            CONSTITUTION, 45,
                            AGILITY, 25,
                            INTELLIGENCE, 40,
                            LORE, 40,
                            MENTAL_POWER, 40,
                            SPIRITUAL_POWER, 10
                    )).build()),
            Map.entry(ATALIAS_PRIEST, CasteDetails.builder()
                    .group(NATURE_DWELLER)
                    .caste(ATALIAS_PRIEST)
                    .nextCastes(emptyList())
                    .minimumAttributes(Map.of(
                            STRENGTH, 45,
                            DEXTERITY, 50,
                            CONSTITUTION, 70,
                            AGILITY, 50,
                            INTELLIGENCE, 80,
                            LORE, 90,
                            MENTAL_POWER, 90,
                            SPIRITUAL_POWER, 25
                    )).build()),
            Map.entry(MONK, CasteDetails.builder()
                    .group(CLERIC)
                    .caste(MONK)
                    .nextCastes(List.of(PRIEST))
                    .minimumAttributes(Map.of(
                            STRENGTH, 3,
                            DEXTERITY, 3,
                            CONSTITUTION, 2,
                            AGILITY, 3,
                            INTELLIGENCE, 4,
                            LORE, 5,
                            MENTAL_POWER, 4,
                            SPIRITUAL_POWER, 1
                    )).build()),
            Map.entry(PRIEST, CasteDetails.builder()
                    .group(CLERIC)
                    .caste(PRIEST)
                    .nextCastes(List.of(HIERARCH))
                    .minimumAttributes(Map.of(
                            STRENGTH, 8,
                            DEXTERITY, 12,
                            CONSTITUTION, 12,
                            AGILITY, 12,
                            INTELLIGENCE, 16,
                            LORE, 20,
                            MENTAL_POWER, 18,
                            SPIRITUAL_POWER, 2
                    )).build()),
            Map.entry(HIERARCH, CasteDetails.builder()
                    .group(CLERIC)
                    .caste(HIERARCH)
                    .nextCastes(List.of(ARCHANGEL))
                    .minimumAttributes(Map.of(
                            STRENGTH, 25,
                            DEXTERITY, 27,
                            CONSTITUTION, 26,
                            AGILITY, 27,
                            INTELLIGENCE, 45,
                            LORE, 50,
                            MENTAL_POWER, 45,
                            SPIRITUAL_POWER, 5
                    )).build()),
            Map.entry(ARCHANGEL, CasteDetails.builder()
                    .group(CLERIC)
                    .caste(ARCHANGEL)
                    .nextCastes(emptyList())
                    .minimumAttributes(Map.of(
                            STRENGTH, 50,
                            DEXTERITY, 55,
                            CONSTITUTION, 60,
                            AGILITY, 50,
                            INTELLIGENCE, 70,
                            LORE, 100,
                            MENTAL_POWER, 90,
                            SPIRITUAL_POWER, 25
                    )).build())
    );

}
