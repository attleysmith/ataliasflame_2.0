package com.asgames.ataliasflame.domain;

import com.asgames.ataliasflame.domain.model.entities.Booster;
import com.asgames.ataliasflame.domain.model.entities.Modifier;
import com.asgames.ataliasflame.domain.model.enums.Caste;

import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.Caste.ROGUE;
import static com.asgames.ataliasflame.domain.model.enums.God.*;
import static com.asgames.ataliasflame.domain.model.enums.Race.*;

public final class MockConstants {

    private MockConstants() {
        // utility class
    }

    // Single-handed damage
    public static final int CHARACTER_DAMAGE = 5;

    // Level-up bonus
    public static final int LEVEL_ATTRIBUTE_POINTS = 5;

    // Starting caste and attributes
    public static final Caste STARTING_CASTE = ROGUE;
    public static final int STARTING_STRENGTH = 1;
    public static final int STARTING_DEXTERITY = 1;
    public static final int STARTING_CONSTITUTION = 1;
    public static final int STARTING_AGILITY = 1;
    public static final int STARTING_INTELLIGENCE = 1;

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

    public static final Map<String, Booster> BOOSTERS = Map.of(
            SIFER_BOOSTER.getCode(), SIFER_BOOSTER,
            GETON_BOOSTER.getCode(), GETON_BOOSTER,
            RUNID_BOOSTER.getCode(), RUNID_BOOSTER,
            ALATE_BOOSTER.getCode(), ALATE_BOOSTER,
            HUMAN_BOOSTER.getCode(), HUMAN_BOOSTER,
            ELF_BOOSTER.getCode(), ELF_BOOSTER,
            HALF_ELF_BOOSTER.getCode(), HALF_ELF_BOOSTER,
            NIGHT_ELF_BOOSTER.getCode(), NIGHT_ELF_BOOSTER,
            HALFLING_BOOSTER.getCode(), HALFLING_BOOSTER
    );

    public static final Map<Integer, Optional<Integer>> LEVELS = Map.ofEntries(
            Map.entry(1, Optional.of(50)),
            Map.entry(2, Optional.of(100)),
            Map.entry(3, Optional.of(150)),
            Map.entry(4, Optional.of(225)),
            Map.entry(5, Optional.of(300)),
            Map.entry(6, Optional.of(400)),
            Map.entry(7, Optional.of(500)),
            Map.entry(8, Optional.of(600)),
            Map.entry(9, Optional.of(750)),
            Map.entry(10, Optional.of(900)),
            Map.entry(11, Optional.of(1100)),
            Map.entry(12, Optional.of(1300)),
            Map.entry(13, Optional.of(1550)),
            Map.entry(14, Optional.of(1800)),
            Map.entry(15, Optional.of(2150)),
            Map.entry(16, Optional.of(2500)),
            Map.entry(17, Optional.of(2950)),
            Map.entry(18, Optional.of(3450)),
            Map.entry(19, Optional.of(4000)),
            Map.entry(20, Optional.of(4650)),
            Map.entry(21, Optional.of(5400)),
            Map.entry(22, Optional.of(6250)),
            Map.entry(23, Optional.of(7250)),
            Map.entry(24, Optional.of(8400)),
            Map.entry(25, Optional.of(9700)),
            Map.entry(26, Optional.of(11200)),
            Map.entry(27, Optional.of(13000)),
            Map.entry(28, Optional.of(15000)),
            Map.entry(29, Optional.of(17250)),
            Map.entry(30, Optional.of(20000)),
            Map.entry(31, Optional.of(23000)),
            Map.entry(32, Optional.of(26500)),
            Map.entry(33, Optional.of(30500)),
            Map.entry(34, Optional.of(35000)),
            Map.entry(35, Optional.of(40500)),
            Map.entry(36, Optional.of(46500)),
            Map.entry(37, Optional.of(53500)),
            Map.entry(38, Optional.of(61500)),
            Map.entry(39, Optional.of(71000)),
            Map.entry(40, Optional.of(81500)),
            Map.entry(41, Optional.of(94000)),
            Map.entry(42, Optional.of(108000)),
            Map.entry(43, Optional.of(125000)),
            Map.entry(44, Optional.of(143000)),
            Map.entry(45, Optional.of(165000)),
            Map.entry(46, Optional.of(190000)),
            Map.entry(47, Optional.of(218000)),
            Map.entry(48, Optional.of(250000)),
            Map.entry(49, Optional.of(290000)),
            Map.entry(50, Optional.of(330000)),
            Map.entry(51, Optional.of(380000)),
            Map.entry(52, Optional.of(440000)),
            Map.entry(53, Optional.of(505000)),
            Map.entry(54, Optional.of(580000)),
            Map.entry(55, Optional.of(670000)),
            Map.entry(56, Optional.of(770000)),
            Map.entry(57, Optional.of(880000)),
            Map.entry(58, Optional.of(1015000)),
            Map.entry(59, Optional.of(1160000)),
            Map.entry(60, Optional.of(1340000)),
            Map.entry(61, Optional.of(1550000)),
            Map.entry(62, Optional.of(1770000)),
            Map.entry(63, Optional.of(2050000)),
            Map.entry(64, Optional.of(2350000)),
            Map.entry(65, Optional.of(2700000)),
            Map.entry(66, Optional.of(3100000)),
            Map.entry(67, Optional.of(3600000)),
            Map.entry(68, Optional.of(4100000)),
            Map.entry(69, Optional.of(4750000)),
            Map.entry(70, Optional.of(5500000)),
            Map.entry(71, Optional.of(6250000)),
            Map.entry(72, Optional.of(7200000)),
            Map.entry(73, Optional.of(8250000)),
            Map.entry(74, Optional.of(9500000)),
            Map.entry(75, Optional.of(11000000)),
            Map.entry(76, Optional.of(12500000)),
            Map.entry(77, Optional.of(14500000)),
            Map.entry(78, Optional.of(16500000)),
            Map.entry(79, Optional.of(19000000)),
            Map.entry(80, Optional.of(22000000)),
            Map.entry(81, Optional.of(25000000)),
            Map.entry(82, Optional.of(29000000)),
            Map.entry(83, Optional.of(33000000)),
            Map.entry(84, Optional.of(38000000)),
            Map.entry(85, Optional.of(44000000)),
            Map.entry(86, Optional.of(51000000)),
            Map.entry(87, Optional.of(58000000)),
            Map.entry(88, Optional.of(67000000)),
            Map.entry(89, Optional.of(77000000)),
            Map.entry(90, Optional.of(89000000)),
            Map.entry(91, Optional.of(102000000)),
            Map.entry(92, Optional.of(118000000)),
            Map.entry(93, Optional.of(135000000)),
            Map.entry(94, Optional.of(155000000)),
            Map.entry(95, Optional.of(178000000)),
            Map.entry(96, Optional.of(205000000)),
            Map.entry(97, Optional.of(235000000)),
            Map.entry(98, Optional.of(270000000)),
            Map.entry(99, Optional.of(310000000)),
            Map.entry(100, Optional.empty())
    );

}
