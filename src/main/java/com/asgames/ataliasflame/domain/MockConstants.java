package com.asgames.ataliasflame.domain;

import com.asgames.ataliasflame.domain.model.dtos.*;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.utils.SelectionValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.*;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.CompanionType.*;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;
import static com.asgames.ataliasflame.domain.model.enums.SoulChipShape.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.*;
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
    public static final int BASE_MAGIC_POINT = 0;

    // Action settings
    public static final int HEALING_EFFECT_OF_SLEEP = 20;
    public static final int MAGIC_RECOVERY_EFFECT_OF_SLEEP = 50;

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

    public static final Map<String, Modifier> MODIFIERS = Map.of(
            STRENGTH.name(), STRENGTH_MODIFIER,
            DEXTERITY.name(), DEXTERITY_MODIFIER,
            CONSTITUTION.name(), CONSTITUTION_MODIFIER,
            AGILITY.name(), AGILITY_MODIFIER,
            INTELLIGENCE.name(), INTELLIGENCE_MODIFIER,
            LORE.name(), LORE_MODIFIER,
            MENTAL_POWER.name(), MENTAL_POWER_MODIFIER,
            SPIRITUAL_POWER.name(), SPIRITUAL_POWER_MODIFIER
    );

    // Attribute boosters
    public static final Booster HORA_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0,
                    LORE, 5,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster SIFER_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 1,
                    AGILITY, 0,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster GETON_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 3,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster RUNID_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 2,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster ALATE_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 3,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster GINDON_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 1,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster ATALIA_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 5,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 10
            ))
            .build();

    public static final Booster HUMAN_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 0,
                    AGILITY, 0,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster ELF_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, -30,
                    DEXTERITY, 20,
                    CONSTITUTION, -10,
                    AGILITY, 0,
                    INTELLIGENCE, 2,
                    LORE, 0,
                    MENTAL_POWER, 10,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster HALF_ELF_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, -15,
                    DEXTERITY, 10,
                    CONSTITUTION, -5,
                    AGILITY, 0,
                    INTELLIGENCE, 1,
                    LORE, 0,
                    MENTAL_POWER, 5,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster NIGHT_ELF_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 10,
                    CONSTITUTION, -1,
                    AGILITY, 0,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, -3,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster DWARF_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 10,
                    DEXTERITY, -10,
                    CONSTITUTION, 20,
                    AGILITY, -20,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, -15,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster ORC_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 10,
                    DEXTERITY, -5,
                    CONSTITUTION, 20,
                    AGILITY, 0,
                    INTELLIGENCE, -2,
                    LORE, 0,
                    MENTAL_POWER, -20,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster MINOTAUR_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 20,
                    DEXTERITY, -10,
                    CONSTITUTION, 20,
                    AGILITY, -10,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, -20,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster ARIMASPI_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 5,
                    DEXTERITY, 5,
                    CONSTITUTION, 5,
                    AGILITY, 5,
                    INTELLIGENCE, -1,
                    LORE, 0,
                    MENTAL_POWER, -9,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster NYMPH_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, -10,
                    DEXTERITY, 0,
                    CONSTITUTION, -20,
                    AGILITY, 0,
                    INTELLIGENCE, 10,
                    LORE, 5,
                    MENTAL_POWER, 20,
                    SPIRITUAL_POWER, -30
            ))
            .build();

    public static final Booster HALFLING_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, -30,
                    DEXTERITY, 50,
                    CONSTITUTION, -10,
                    AGILITY, -5,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster DIVINE_PROTECTION_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 10,
                    DEXTERITY, 0,
                    CONSTITUTION, 5,
                    AGILITY, 10,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster STRENGTHENING_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 5,
                    DEXTERITY, 2,
                    CONSTITUTION, 1,
                    AGILITY, 2,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster CANINE_SOUL_CHIP_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 5,
                    DEXTERITY, 5,
                    CONSTITUTION, 2,
                    AGILITY, 2,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster APE_LIKE_SOUL_CHIP_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 5,
                    DEXTERITY, 2,
                    CONSTITUTION, 5,
                    AGILITY, 2,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster BIRD_OF_PREY_SOUL_CHIP_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 2,
                    DEXTERITY, 5,
                    CONSTITUTION, 2,
                    AGILITY, 5,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster PROTECTIVE_HAND_OF_NATURE_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 5,
                    DEXTERITY, 2,
                    CONSTITUTION, 1,
                    AGILITY, 2,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Booster ENERGY_SHIELD_BOOSTER = Booster.builder()
            .effects(Map.of(
                    STRENGTH, 0,
                    DEXTERITY, 0,
                    CONSTITUTION, 15,
                    AGILITY, 0,
                    INTELLIGENCE, 0,
                    LORE, 0,
                    MENTAL_POWER, 0,
                    SPIRITUAL_POWER, 0
            ))
            .build();

    public static final Map<String, Booster> BOOSTERS = Map.ofEntries(
            Map.entry(HORA.name(), HORA_BOOSTER),
            Map.entry(SIFER.name(), SIFER_BOOSTER),
            Map.entry(GETON.name(), GETON_BOOSTER),
            Map.entry(RUNID.name(), RUNID_BOOSTER),
            Map.entry(ALATE.name(), ALATE_BOOSTER),
            Map.entry(GINDON.name(), GINDON_BOOSTER),
            Map.entry(ATALIA.name(), ATALIA_BOOSTER),
            Map.entry(HUMAN.name(), HUMAN_BOOSTER),
            Map.entry(ELF.name(), ELF_BOOSTER),
            Map.entry(HALF_ELF.name(), HALF_ELF_BOOSTER),
            Map.entry(NIGHT_ELF.name(), NIGHT_ELF_BOOSTER),
            Map.entry(DWARF.name(), DWARF_BOOSTER),
            Map.entry(ORC.name(), ORC_BOOSTER),
            Map.entry(MINOTAUR.name(), MINOTAUR_BOOSTER),
            Map.entry(ARIMASPI.name(), ARIMASPI_BOOSTER),
            Map.entry(NYMPH.name(), NYMPH_BOOSTER),
            Map.entry(HALFLING.name(), HALFLING_BOOSTER),
            Map.entry(DIVINE_PROTECTION.name(), DIVINE_PROTECTION_BOOSTER),
            Map.entry(STRENGTHENING.name(), STRENGTHENING_BOOSTER),
            Map.entry(CANINE.name(), CANINE_SOUL_CHIP_BOOSTER),
            Map.entry(APE_LIKE.name(), APE_LIKE_SOUL_CHIP_BOOSTER),
            Map.entry(BIRD_OF_PREY.name(), BIRD_OF_PREY_SOUL_CHIP_BOOSTER),
            Map.entry(PROTECTIVE_HAND_OF_NATURE.name(), PROTECTIVE_HAND_OF_NATURE_BOOSTER),
            Map.entry(ENERGY_SHIELD.name(), ENERGY_SHIELD_BOOSTER)
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

    // Armory
    public static final Map<String, WeaponTemplate> WEAPONS = Map.of(
            "FIST", WeaponTemplate.builder()
                    .code("FIST")
                    .minDamage(1)
                    .maxDamage(2)
                    .defense(0)
                    .initiative(1)
                    .oneHanded(true)
                    .build(),
            "STAFF", WeaponTemplate.builder()
                    .code("STAFF")
                    .minDamage(1)
                    .maxDamage(5)
                    .defense(5)
                    .initiative(-5)
                    .oneHanded(false)
                    .build(),
            "DAGGER", WeaponTemplate.builder()
                    .code("DAGGER")
                    .minDamage(2)
                    .maxDamage(6)
                    .defense(1)
                    .initiative(0)
                    .oneHanded(true)
                    .build(),
            "SPEAR", WeaponTemplate.builder()
                    .code("SPEAR")
                    .minDamage(2)
                    .maxDamage(12)
                    .defense(5)
                    .initiative(-6)
                    .oneHanded(false)
                    .build(),
            "SWORD", WeaponTemplate.builder()
                    .code("SWORD")
                    .minDamage(2)
                    .maxDamage(18)
                    .defense(3)
                    .initiative(-3)
                    .oneHanded(true)
                    .build()
    );

    public static final Map<String, ShieldTemplate> SHIELDS = Map.of(
            "BUCKLER", ShieldTemplate.builder()
                    .code("BUCKLER")
                    .defense(3)
                    .absorption(25)
                    .durability(30)
                    .build(),
            "ROUND_SHIELD", ShieldTemplate.builder()
                    .code("ROUND_SHIELD")
                    .defense(5)
                    .absorption(30)
                    .durability(50)
                    .build(),
            "KITE_SHIELD", ShieldTemplate.builder()
                    .code("KITE_SHIELD")
                    .defense(8)
                    .absorption(35)
                    .durability(70)
                    .build(),
            "TOWER_SHIELD", ShieldTemplate.builder()
                    .code("TOWER_SHIELD")
                    .defense(10)
                    .absorption(50)
                    .durability(100)
                    .build()
    );

    public static final Map<String, ArmorTemplate> ARMORS = Map.of(
            "LINEN_ARMOR", ArmorTemplate.builder()
                    .code("LINEN_ARMOR")
                    .defense(0)
                    .absorption(10)
                    .durability(50)
                    .build(),
            "LEATHER_ARMOR", ArmorTemplate.builder()
                    .code("LEATHER_ARMOR")
                    .defense(3)
                    .absorption(25)
                    .durability(60)
                    .build(),
            "STUDDED_LEATHER_ARMOR", ArmorTemplate.builder()
                    .code("STUDDED_LEATHER_ARMOR")
                    .defense(5)
                    .absorption(30)
                    .durability(80)
                    .build(),
            "CHAIN_MAIL", ArmorTemplate.builder()
                    .code("CHAIN_MAIL")
                    .defense(6)
                    .absorption(40)
                    .durability(100)
                    .build(),
            "PLATE_MAIL", ArmorTemplate.builder()
                    .code("PLATE_MAIL")
                    .defense(8)
                    .absorption(50)
                    .durability(100)
                    .build(),
            "FULL_PLATE_MAIL", ArmorTemplate.builder()
                    .code("FULL_PLATE_MAIL")
                    .defense(10)
                    .absorption(60)
                    .durability(120)
                    .build()
    );

    public static final List<SelectionValue<WeaponTemplate>> STARTING_WEAPON_SELECTOR = List.of(
            new SelectionValue<>(5, WEAPONS.get("FIST")),
            new SelectionValue<>(30, WEAPONS.get("STAFF")),
            new SelectionValue<>(30, WEAPONS.get("DAGGER")),
            new SelectionValue<>(20, WEAPONS.get("SPEAR")),
            new SelectionValue<>(15, WEAPONS.get("SWORD"))
    );

    public static final List<SelectionValue<Optional<ShieldTemplate>>> STARTING_SHIELD_SELECTOR = List.of(
            new SelectionValue<>(60, Optional.empty()),
            new SelectionValue<>(10, Optional.of(SHIELDS.get("BUCKLER"))),
            new SelectionValue<>(15, Optional.of(SHIELDS.get("ROUND_SHIELD"))),
            new SelectionValue<>(10, Optional.of(SHIELDS.get("KITE_SHIELD"))),
            new SelectionValue<>(5, Optional.of(SHIELDS.get("TOWER_SHIELD")))
    );

    public static final List<SelectionValue<Optional<ArmorTemplate>>> STARTING_ARMOR_SELECTOR = List.of(
            new SelectionValue<>(50, Optional.empty()),
            new SelectionValue<>(15, Optional.of(ARMORS.get("LINEN_ARMOR"))),
            new SelectionValue<>(10, Optional.of(ARMORS.get("LEATHER_ARMOR"))),
            new SelectionValue<>(10, Optional.of(ARMORS.get("STUDDED_LEATHER_ARMOR"))),
            new SelectionValue<>(5, Optional.of(ARMORS.get("CHAIN_MAIL"))),
            new SelectionValue<>(5, Optional.of(ARMORS.get("PLATE_MAIL"))),
            new SelectionValue<>(5, Optional.of(ARMORS.get("FULL_PLATE_MAIL")))
    );

    // Items
    public static final Map<String, ItemTemplate> ITEMS = Map.ofEntries(
            Map.entry("WATER", ItemTemplate.builder()
                    .type(FOOD)
                    .code("WATER")
                    .healingEffect(3)
                    .magicEffect(0)
                    .build()),
            Map.entry("BREAD", ItemTemplate.builder()
                    .type(FOOD)
                    .code("BREAD")
                    .healingEffect(5)
                    .magicEffect(0)
                    .build()),
            Map.entry("FRUIT", ItemTemplate.builder()
                    .type(FOOD)
                    .code("FRUIT")
                    .healingEffect(8)
                    .magicEffect(2)
                    .build()),
            Map.entry("MEAT", ItemTemplate.builder()
                    .type(FOOD)
                    .code("MEAT")
                    .healingEffect(10)
                    .magicEffect(1)
                    .build()),
            Map.entry("HEALING_HERB", ItemTemplate.builder()
                    .type(FOOD)
                    .code("HEALING_HERB")
                    .healingEffect(20)
                    .magicEffect(10)
                    .build()),
            Map.entry("STAFF", ItemTemplate.builder()
                    .type(WEAPON)
                    .code("STAFF")
                    .build()),
            Map.entry("DAGGER", ItemTemplate.builder()
                    .type(WEAPON)
                    .code("DAGGER")
                    .build()),
            Map.entry("SPEAR", ItemTemplate.builder()
                    .type(WEAPON)
                    .code("SPEAR")
                    .build()),
            Map.entry("SWORD", ItemTemplate.builder()
                    .type(WEAPON)
                    .code("SWORD")
                    .build()),
            Map.entry("BUCKLER", ItemTemplate.builder()
                    .type(SHIELD)
                    .code("BUCKLER")
                    .build()),
            Map.entry("ROUND_SHIELD", ItemTemplate.builder()
                    .type(SHIELD)
                    .code("ROUND_SHIELD")
                    .build()),
            Map.entry("KITE_SHIELD", ItemTemplate.builder()
                    .type(SHIELD)
                    .code("KITE_SHIELD")
                    .build()),
            Map.entry("TOWER_SHIELD", ItemTemplate.builder()
                    .type(SHIELD)
                    .code("TOWER_SHIELD")
                    .build()),
            Map.entry("LINEN_ARMOR", ItemTemplate.builder()
                    .type(ARMOR)
                    .code("LINEN_ARMOR")
                    .build()),
            Map.entry("LEATHER_ARMOR", ItemTemplate.builder()
                    .type(ARMOR)
                    .code("LEATHER_ARMOR")
                    .build()),
            Map.entry("STUDDED_LEATHER_ARMOR", ItemTemplate.builder()
                    .type(ARMOR)
                    .code("STUDDED_LEATHER_ARMOR")
                    .build()),
            Map.entry("CHAIN_MAIL", ItemTemplate.builder()
                    .type(ARMOR)
                    .code("CHAIN_MAIL")
                    .build()),
            Map.entry("PLATE_MAIL", ItemTemplate.builder()
                    .type(ARMOR)
                    .code("PLATE_MAIL")
                    .build()),
            Map.entry("FULL_PLATE_MAIL", ItemTemplate.builder()
                    .type(ARMOR)
                    .code("FULL_PLATE_MAIL")
                    .build())
    );

    // Magic
    public static final Map<SpellName, Spell> SPELLS = Map.ofEntries(
            Map.entry(FIREBALL, Spell.builder()
                    .name(FIREBALL)
                    .type(ATTACK)
                    .group(ELEMENTAL)
                    .cost(10)
                    .minDamage(2)
                    .maxDamage(12)
                    .prohibitedCastes(List.of(ROGUE, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(LIGHTNING_STRIKE, Spell.builder()
                    .name(LIGHTNING_STRIKE)
                    .type(ATTACK)
                    .group(ELEMENTAL)
                    .cost(10)
                    .minDamage(1)
                    .maxDamage(15)
                    .prohibitedCastes(List.of(ROGUE, FIGHTER, PALADIN, GRANDMASTER, TRACKER, RANGER, PILGRIM, FREE_SOUL))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(INFERNO, Spell.builder()
                    .name(INFERNO)
                    .type(ATTACK)
                    .group(ELEMENTAL)
                    .cost(20)
                    .minDamage(12)
                    .maxDamage(28)
                    .prohibitedCastes(List.of(ROGUE, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, HERMIT, MONK))
                    .prohibitedRaces(List.of(DWARF, ORC))
                    .build()),
            Map.entry(BLADES_OF_JUDGEMENT, Spell.builder()
                    .name(BLADES_OF_JUDGEMENT)
                    .type(ATTACK)
                    .group(GENERAL)
                    .cost(25)
                    .minDamage(10)
                    .maxDamage(30)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM, HERMIT, DRUID, ARCHDRUID, MONK, PRIEST))
                    .prohibitedRaces(List.of(HALFLING))
                    .build()),
            Map.entry(GLACIAL_BLOW, Spell.builder()
                    .name(GLACIAL_BLOW)
                    .type(ATTACK)
                    .group(ELEMENTAL)
                    .cost(18)
                    .minDamage(10)
                    .maxDamage(25)
                    .prohibitedCastes(List.of(ROGUE, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(BALL_OF_ENERGY, Spell.builder()
                    .name(BALL_OF_ENERGY)
                    .type(ATTACK)
                    .group(ENERGY)
                    .cost(3)
                    .minDamage(1)
                    .maxDamage(5)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, FIGHTER, TRACKER, RANGER, HERMIT, MONK, PRIEST))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(SOUL_OUTBURST, Spell.builder()
                    .name(SOUL_OUTBURST)
                    .type(ATTACK)
                    .group(SOUL)
                    .cost(25)
                    .minDamage(15)
                    .maxDamage(30)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, AVATAR, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, HERMIT, DRUID, ARCHDRUID, ATALIAS_PRIEST, MONK, PRIEST, HIERARCH, ARCHANGEL))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(WRATH_OF_NATURE, Spell.builder()
                    .name(WRATH_OF_NATURE)
                    .type(ATTACK)
                    .group(NATURE)
                    .cost(12)
                    .minDamage(5)
                    .maxDamage(20)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, MONK, PRIEST, HIERARCH, ARCHANGEL))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(SPLITTING_WIND, Spell.builder()
                    .name(SPLITTING_WIND)
                    .type(ATTACK)
                    .group(ELEMENTAL)
                    .cost(8)
                    .minDamage(1)
                    .maxDamage(10)
                    .prohibitedCastes(List.of(ROGUE, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(DIVINE_HAMMER, Spell.builder()
                    .name(DIVINE_HAMMER)
                    .type(ATTACK)
                    .group(DIVINE)
                    .cost(10)
                    .minDamage(6)
                    .maxDamage(18)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, AVATAR, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, HERMIT, DRUID, ARCHDRUID))
                    .prohibitedRaces(List.of(ORC, MINOTAUR, HALFLING))
                    .build()),
            Map.entry(WOUND_HEALING, Spell.builder()
                    .name(WOUND_HEALING)
                    .type(HEALING)
                    .group(GENERAL)
                    .cost(1)
                    .healingEffect(1)
                    .prohibitedCastes(List.of(ROGUE))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(HEALING_WORD, Spell.builder()
                    .name(HEALING_WORD)
                    .type(HEALING)
                    .group(DIVINE)
                    .cost(5)
                    .healingEffect(12)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, HERMIT, DRUID))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(CURE, Spell.builder()
                    .name(CURE)
                    .type(HEALING)
                    .group(GENERAL)
                    .cost(5)
                    .healingEffect(8)
                    .prohibitedCastes(List.of(ROGUE, TRACKER, RANGER))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(REGENERATION, Spell.builder()
                    .name(REGENERATION)
                    .type(HEALING)
                    .group(GENERAL)
                    .cost(10)
                    .healingEffect(30)
                    .prohibitedCastes(List.of(ROGUE, TRACKER))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(BREATH_OF_GOD, Spell.builder()
                    .name(BREATH_OF_GOD)
                    .type(HEALING)
                    .group(DIVINE)
                    .cost(7)
                    .healingEffect(15)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, AVATAR, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, HERMIT, DRUID, ARCHDRUID))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(POWER_OF_NATURE, Spell.builder()
                    .name(POWER_OF_NATURE)
                    .type(HEALING)
                    .group(NATURE)
                    .cost(5)
                    .healingEffect(10)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, MONK, PRIEST, HIERARCH, ARCHANGEL))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(SOUL_POWER, Spell.builder()
                    .name(SOUL_POWER)
                    .type(HEALING)
                    .group(SOUL)
                    .cost(10)
                    .healingEffect(40)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, AVATAR, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, HERMIT, DRUID, ARCHDRUID, ATALIAS_PRIEST, MONK, PRIEST, HIERARCH, ARCHANGEL))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(RECHARGING, Spell.builder()
                    .name(RECHARGING)
                    .type(HEALING)
                    .group(ENERGY)
                    .cost(8)
                    .healingEffect(20)
                    .prohibitedCastes(List.of(ROGUE, FIGHTER, TRACKER, RANGER, HERMIT, MONK))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(HEALING_WAVE, Spell.builder()
                    .name(HEALING_WAVE)
                    .type(HEALING)
                    .group(GENERAL)
                    .cost(15)
                    .healingEffect(20)
                    .area(true)
                    .prohibitedCastes(List.of(ROGUE, TRACKER, RANGER, PILGRIM))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(ENERGY_ABSORPTION, Spell.builder()
                    .name(ENERGY_ABSORPTION)
                    .type(HEALING)
                    .group(ENERGY)
                    .cost(10)
                    .healingEffect(28)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, FIGHTER, TRACKER, RANGER, HERMIT, MONK, PRIEST))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(DIVINE_PROTECTION, Spell.builder()
                    .name(DIVINE_PROTECTION)
                    .type(BLESSING)
                    .group(DIVINE)
                    .cost(10)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, AVATAR, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, HERMIT, DRUID, ARCHDRUID))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(STRENGTHENING, Spell.builder()
                    .name(STRENGTHENING)
                    .type(BLESSING)
                    .group(GENERAL)
                    .cost(5)
                    .prohibitedCastes(List.of(ROGUE, TRACKER, RANGER, PILGRIM, HERMIT))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(SOUL_CONNECTION, Spell.builder()
                    .name(SOUL_CONNECTION)
                    .type(BLESSING)
                    .group(SOUL)
                    .cost(10)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, AVATAR, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, HERMIT, DRUID, ARCHDRUID, ATALIAS_PRIEST, MONK, PRIEST, HIERARCH, ARCHANGEL))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(PROTECTIVE_HAND_OF_NATURE, Spell.builder()
                    .name(PROTECTIVE_HAND_OF_NATURE)
                    .type(BLESSING)
                    .group(NATURE)
                    .cost(5)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, MONK, PRIEST, HIERARCH, ARCHANGEL))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(ENERGY_SHIELD, Spell.builder()
                    .name(ENERGY_SHIELD)
                    .type(BLESSING)
                    .group(ENERGY)
                    .cost(8)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, FIGHTER, TRACKER, RANGER, HERMIT, DRUID, MONK, PRIEST))
                    .prohibitedRaces(List.of(ORC))
                    .build()),
            Map.entry(CALLING_THE_SOULS, Spell.builder()
                    .name(CALLING_THE_SOULS)
                    .type(SUMMON)
                    .group(SOUL)
                    .cost(25)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, WITCHMASTER, AVATAR, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, HERMIT, DRUID, ARCHDRUID, ATALIAS_PRIEST, MONK, PRIEST, HIERARCH, ARCHANGEL))
                    .prohibitedRaces(List.of())
                    .build()),
            Map.entry(CALLING_ANIMALS, Spell.builder()
                    .name(CALLING_ANIMALS)
                    .type(SUMMON)
                    .group(NATURE)
                    .cost(10)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, TITAN, MONK, PRIEST, HIERARCH, ARCHANGEL))
                    .prohibitedRaces(List.of(MINOTAUR))
                    .build()),
            Map.entry(SUMMON_GUARDIAN, Spell.builder()
                    .name(SUMMON_GUARDIAN)
                    .type(SUMMON)
                    .group(GENERAL)
                    .cost(20)
                    .prohibitedCastes(List.of(ROGUE, FIGHTER, PALADIN, TRACKER, RANGER, PILGRIM, HERMIT, DRUID))
                    .prohibitedRaces(List.of(ORC))
                    .build()),
            Map.entry(PROJECTION_OF_ENERGY, Spell.builder()
                    .name(PROJECTION_OF_ENERGY)
                    .type(SUMMON)
                    .group(ENERGY)
                    .cost(30)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, FIGHTER, PALADIN, TRACKER, RANGER, HERMIT, DRUID, ARCHDRUID, MONK, PRIEST))
                    .prohibitedRaces(List.of(DWARF, ORC, MINOTAUR))
                    .build()),
            Map.entry(FRIEND_IN_NEED, Spell.builder()
                    .name(FRIEND_IN_NEED)
                    .type(SUMMON)
                    .group(DIVINE)
                    .cost(15)
                    .prohibitedCastes(List.of(ROGUE, WIZARD, MAGE, FIGHTER, PALADIN, GRANDMASTER, TITAN, TRACKER, RANGER, PILGRIM, FREE_SOUL, HERMIT))
                    .prohibitedRaces(List.of(ORC))
                    .build())
    );

    // Monsters
    public static final Map<String, MonsterTemplate> MONSTERS = Map.of(
            "RAT", MonsterTemplate.builder()
                    .code("RAT")
                    .attack(40)
                    .defense(0)
                    .minDamage(1)
                    .maxDamage(1)
                    .health(10)
                    .initiative(3)
                    .level(1)
                    .experience(10)
                    .chance(20)
                    .spawn(70)
                    .build(),
            "BOAR", MonsterTemplate.builder()
                    .code("BOAR")
                    .attack(60)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(2)
                    .health(25)
                    .initiative(0)
                    .level(11)
                    .experience(20)
                    .chance(15)
                    .spawn(25)
                    .build(),
            "WOLF", MonsterTemplate.builder()
                    .code("WOLF")
                    .attack(70)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(3)
                    .health(25)
                    .initiative(-3)
                    .level(21)
                    .experience(30)
                    .chance(25)
                    .spawn(50)
                    .build(),
            "BANDIT", MonsterTemplate.builder()
                    .code("BANDIT")
                    .attack(75)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(4)
                    .health(30)
                    .initiative(-1)
                    .level(31)
                    .experience(40)
                    .chance(35)
                    .spawn(40)
                    .build(),
            "WEREWOLF", MonsterTemplate.builder()
                    .code("WEREWOLF")
                    .attack(75)
                    .defense(10)
                    .minDamage(1)
                    .maxDamage(5)
                    .health(40)
                    .initiative(-3)
                    .level(41)
                    .experience(50)
                    .chance(5)
                    .spawn(15)
                    .build(),
            "NAGA", MonsterTemplate.builder()
                    .code("NAGA")
                    .attack(80)
                    .defense(10)
                    .minDamage(2)
                    .maxDamage(8)
                    .health(50)
                    .initiative(-3)
                    .level(51)
                    .experience(60)
                    .chance(5)
                    .spawn(10)
                    .build(),
            "OGRE", MonsterTemplate.builder()
                    .code("OGRE")
                    .attack(85)
                    .defense(15)
                    .minDamage(2)
                    .maxDamage(10)
                    .health(65)
                    .initiative(0)
                    .level(61)
                    .experience(70)
                    .chance(5)
                    .spawn(5)
                    .build(),
            "GHOUL", MonsterTemplate.builder()
                    .code("GHOUL")
                    .attack(90)
                    .defense(10)
                    .minDamage(3)
                    .maxDamage(12)
                    .health(80)
                    .initiative(0)
                    .level(71)
                    .experience(80)
                    .chance(5)
                    .spawn(5)
                    .build(),
            "GRIFFIN", MonsterTemplate.builder()
                    .code("GRIFFIN")
                    .attack(100)
                    .defense(20)
                    .minDamage(5)
                    .maxDamage(15)
                    .health(100)
                    .initiative(-3)
                    .level(81)
                    .experience(90)
                    .chance(5)
                    .spawn(10)
                    .build(),
            "DRAGON", MonsterTemplate.builder()
                    .code("DRAGON")
                    .attack(160)
                    .defense(50)
                    .minDamage(10)
                    .maxDamage(30)
                    .health(200)
                    .initiative(-3)
                    .level(91)
                    .experience(100)
                    .chance(5)
                    .spawn(5)
                    .build()
    );

    public static final Map<String, List<List<SelectionValue<Optional<ItemTemplate>>>>> MONSTER_DROPS = Map.of(
            "BOAR", List.of(
                    List.of(
                            new SelectionValue<>(10, Optional.empty()),
                            new SelectionValue<>(90, Optional.of(ITEMS.get("MEAT")))
                    )),
            "BANDIT", List.of(
                    List.of(
                            new SelectionValue<>(90, Optional.empty()),
                            new SelectionValue<>(10, Optional.of(ITEMS.get("WATER")))),
                    List.of(
                            new SelectionValue<>(85, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("BREAD")))),
                    List.of(
                            new SelectionValue<>(90, Optional.empty()),
                            new SelectionValue<>(10, Optional.of(ITEMS.get("FRUIT")))),
                    List.of(
                            new SelectionValue<>(85, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("MEAT")))),
                    List.of(
                            new SelectionValue<>(95, Optional.empty()),
                            new SelectionValue<>(5, Optional.of(ITEMS.get("HEALING_HERB")))),
                    List.of(
                            new SelectionValue<>(5, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("STAFF"))),
                            new SelectionValue<>(25, Optional.of(ITEMS.get("DAGGER"))),
                            new SelectionValue<>(25, Optional.of(ITEMS.get("SPEAR"))),
                            new SelectionValue<>(30, Optional.of(ITEMS.get("SWORD")))),
                    List.of(
                            new SelectionValue<>(40, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("BUCKLER"))),
                            new SelectionValue<>(25, Optional.of(ITEMS.get("ROUND_SHIELD"))),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("KITE_SHIELD"))),
                            new SelectionValue<>(5, Optional.of(ITEMS.get("TOWER_SHIELD")))),
                    List.of(
                            new SelectionValue<>(10, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("LINEN_ARMOR"))),
                            new SelectionValue<>(20, Optional.of(ITEMS.get("LEATHER_ARMOR"))),
                            new SelectionValue<>(25, Optional.of(ITEMS.get("STUDDED_LEATHER_ARMOR"))),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("CHAIN_MAIL"))),
                            new SelectionValue<>(10, Optional.of(ITEMS.get("PLATE_MAIL"))),
                            new SelectionValue<>(5, Optional.of(ITEMS.get("FULL_PLATE_MAIL")))
                    )),
            "WEREWOLF", List.of(
                    List.of(
                            new SelectionValue<>(65, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("WATER"))),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("MEAT"))),
                            new SelectionValue<>(5, Optional.of(ITEMS.get("HEALING_HERB")))),
                    List.of(
                            new SelectionValue<>(85, Optional.empty()),
                            new SelectionValue<>(10, Optional.of(ITEMS.get("DAGGER"))),
                            new SelectionValue<>(5, Optional.of(ITEMS.get("SWORD")))
                    )),
            "NAGA", List.of(
                    List.of(
                            new SelectionValue<>(60, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("WATER"))),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("MEAT"))),
                            new SelectionValue<>(10, Optional.of(ITEMS.get("HEALING_HERB")))),
                    List.of(
                            new SelectionValue<>(50, Optional.empty()),
                            new SelectionValue<>(25, Optional.of(ITEMS.get("DAGGER"))),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("SPEAR"))),
                            new SelectionValue<>(10, Optional.of(ITEMS.get("SWORD")))),
                    List.of(
                            new SelectionValue<>(90, Optional.empty()),
                            new SelectionValue<>(10, Optional.of(ITEMS.get("LEATHER_ARMOR")))
                    )),
            "OGRE", List.of(
                    List.of(
                            new SelectionValue<>(60, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("WATER"))),
                            new SelectionValue<>(20, Optional.of(ITEMS.get("MEAT"))),
                            new SelectionValue<>(5, Optional.of(ITEMS.get("HEALING_HERB")))),
                    List.of(
                            new SelectionValue<>(65, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(ITEMS.get("DAGGER"))),
                            new SelectionValue<>(20, Optional.of(ITEMS.get("SPEAR")))
                    )),
            "GHOUL", List.of(
                    List.of(
                            new SelectionValue<>(80, Optional.empty()),
                            new SelectionValue<>(20, Optional.of(ITEMS.get("DAGGER")))
                    ))
    );

    // Soul chips
    public static final int SOUL_CHIP_ATTACK_BASE = 50;
    public static final int SOUL_CHIP_ATTACK_BONUS = 100;
    public static final int SOUL_CHIP_DEFENSE_BASE = 0;
    public static final int SOUL_CHIP_DEFENSE_BONUS = 50;
    public static final int SOUL_CHIP_MIN_DAMAGE_BASE = 1;
    public static final int SOUL_CHIP_MIN_DAMAGE_BONUS = 1;
    public static final int SOUL_CHIP_MAX_DAMAGE_BASE = 5;
    public static final int SOUL_CHIP_MAX_DAMAGE_BONUS = 10;
    public static final int SOUL_CHIP_INITIATIVE = -1;

    // Summonings
    //// Animals
    public static final Map<String, CompanionTemplate> ANIMALS = Map.of(
            "TAMED_FALCON", CompanionTemplate.builder()
                    .code("TAMED_FALCON")
                    .type(ANIMAL)
                    .attack(75)
                    .defense(0)
                    .minDamage(1)
                    .maxDamage(2)
                    .health(10)
                    .initiative(-6)
                    .build(),
            "TAMED_DOG", CompanionTemplate.builder()
                    .code("TAMED_DOG")
                    .type(ANIMAL)
                    .attack(70)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(3)
                    .health(20)
                    .initiative(-3)
                    .build(),
            "TAMED_WOLF", CompanionTemplate.builder()
                    .code("TAMED_WOLF")
                    .type(ANIMAL)
                    .attack(70)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(3)
                    .health(25)
                    .initiative(-3)
                    .build(),
            "TAMED_BEAR", CompanionTemplate.builder()
                    .code("TAMED_BEAR")
                    .type(ANIMAL)
                    .attack(80)
                    .defense(10)
                    .minDamage(1)
                    .maxDamage(5)
                    .health(40)
                    .initiative(-2)
                    .build()
    );

    public static final List<SelectionValue<Optional<CompanionTemplate>>> ANIMAL_SELECTOR = List.of(
            new SelectionValue<>(10, Optional.of(ANIMALS.get("TAMED_FALCON"))),
            new SelectionValue<>(50, Optional.of(ANIMALS.get("TAMED_DOG"))),
            new SelectionValue<>(35, Optional.of(ANIMALS.get("TAMED_WOLF"))),
            new SelectionValue<>(5, Optional.of(ANIMALS.get("TAMED_BEAR")))
    );

    //// Guardian warriors
    public static final Map<String, CompanionTemplate> GUARDIAN_WARRIORS = Map.of(
            "HUNTER", CompanionTemplate.builder()
                    .code("HUNTER")
                    .type(GUARDIAN_WARRIOR)
                    .attack(85)
                    .defense(5)
                    .minDamage(2)
                    .maxDamage(6)
                    .health(75)
                    .initiative(-9)
                    .build(),
            "MILITIA", CompanionTemplate.builder()
                    .code("MILITIA")
                    .type(GUARDIAN_WARRIOR)
                    .attack(85)
                    .defense(10)
                    .minDamage(3)
                    .maxDamage(15)
                    .health(80)
                    .initiative(-3)
                    .build(),
            "SWORDSMAN", CompanionTemplate.builder()
                    .code("SWORDSMAN")
                    .type(GUARDIAN_WARRIOR)
                    .attack(90)
                    .defense(25)
                    .minDamage(5)
                    .maxDamage(15)
                    .health(90)
                    .initiative(-3)
                    .build()
    );

    public static final List<SelectionValue<Optional<CompanionTemplate>>> GUARDIAN_WARRIOR_SELECTOR = List.of(
            new SelectionValue<>(50, Optional.of(GUARDIAN_WARRIORS.get("HUNTER"))),
            new SelectionValue<>(35, Optional.of(GUARDIAN_WARRIORS.get("MILITIA"))),
            new SelectionValue<>(15, Optional.of(GUARDIAN_WARRIORS.get("SWORDSMAN")))
    );

    //// Energy
    public static final int ENERGY_PROJECTION_PERCENT = 50;

    //// Divine guardians
    public static final Map<String, CompanionTemplate> DIVINE_GUARDIANS = Map.of(
            "KNIGHT", CompanionTemplate.builder()
                    .code("KNIGHT")
                    .type(DIVINE_GUARDIAN)
                    .attack(90)
                    .defense(25)
                    .minDamage(5)
                    .maxDamage(20)
                    .health(100)
                    .initiative(-4)
                    .build(),
            "COMMANDER", CompanionTemplate.builder()
                    .code("COMMANDER")
                    .type(DIVINE_GUARDIAN)
                    .attack(100)
                    .defense(40)
                    .minDamage(6)
                    .maxDamage(24)
                    .health(120)
                    .initiative(-5)
                    .build()
    );

    public static final List<SelectionValue<Optional<CompanionTemplate>>> DIVINE_GUARDIAN_SELECTOR = List.of(
            new SelectionValue<>(1, Optional.empty()),
            new SelectionValue<>(96, Optional.of(DIVINE_GUARDIANS.get("KNIGHT"))),
            new SelectionValue<>(3, Optional.of(DIVINE_GUARDIANS.get("COMMANDER")))
    );

}
