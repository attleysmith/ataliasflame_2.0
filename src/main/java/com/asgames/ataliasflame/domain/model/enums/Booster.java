package com.asgames.ataliasflame.domain.model.enums;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.Attribute.*;
import static com.asgames.ataliasflame.domain.model.enums.BoosterType.*;

public enum Booster {

    HORA_EFFECT(GOD_EFFECT, Map.of(
            STRENGTH, 0,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 0,
            INTELLIGENCE, 0,
            LORE, 5,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    SIFER_EFFECT(GOD_EFFECT, Map.of(
            STRENGTH, 0,
            DEXTERITY, 0,
            CONSTITUTION, 1,
            AGILITY, 0,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    GETON_EFFECT(GOD_EFFECT, Map.of(
            STRENGTH, 3,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 0,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    RUNID_EFFECT(GOD_EFFECT, Map.of(
            STRENGTH, 0,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 0,
            INTELLIGENCE, 2,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    ALATE_EFFECT(GOD_EFFECT, Map.of(
            STRENGTH, 0,
            DEXTERITY, 3,
            CONSTITUTION, 0,
            AGILITY, 0,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    GINDON_EFFECT(GOD_EFFECT, Map.of(
            STRENGTH, 0,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 0,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 1,
            SPIRITUAL_POWER, 0
    )),
    ATALIA_EFFECT(GOD_EFFECT, Map.of(
            STRENGTH, 0,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 5,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 10
    )),
    HUMAN_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, 0,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 0,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    ELF_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, -30,
            DEXTERITY, 20,
            CONSTITUTION, -10,
            AGILITY, 0,
            INTELLIGENCE, 2,
            LORE, 0,
            MENTAL_POWER, 10,
            SPIRITUAL_POWER, 0
    )),
    HALF_ELF_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, -15,
            DEXTERITY, 10,
            CONSTITUTION, -5,
            AGILITY, 0,
            INTELLIGENCE, 1,
            LORE, 0,
            MENTAL_POWER, 5,
            SPIRITUAL_POWER, 0
    )),
    NIGHT_ELF_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, 0,
            DEXTERITY, 10,
            CONSTITUTION, -1,
            AGILITY, 0,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, -3,
            SPIRITUAL_POWER, 0
    )),
    DWARF_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, 10,
            DEXTERITY, -10,
            CONSTITUTION, 20,
            AGILITY, -20,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, -15,
            SPIRITUAL_POWER, 0
    )),
    ORC_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, 10,
            DEXTERITY, -5,
            CONSTITUTION, 20,
            AGILITY, 0,
            INTELLIGENCE, -2,
            LORE, 0,
            MENTAL_POWER, -20,
            SPIRITUAL_POWER, 0
    )),
    MINOTAUR_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, 20,
            DEXTERITY, -10,
            CONSTITUTION, 20,
            AGILITY, -10,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, -20,
            SPIRITUAL_POWER, 0
    )),
    ARIMASPI_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, 5,
            DEXTERITY, 5,
            CONSTITUTION, 5,
            AGILITY, 5,
            INTELLIGENCE, -1,
            LORE, 0,
            MENTAL_POWER, -9,
            SPIRITUAL_POWER, 0
    )),
    NYMPH_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, -10,
            DEXTERITY, 0,
            CONSTITUTION, -20,
            AGILITY, 0,
            INTELLIGENCE, 10,
            LORE, 5,
            MENTAL_POWER, 20,
            SPIRITUAL_POWER, -30
    )),
    HALFLING_EFFECT(RACE_EFFECT, Map.of(
            STRENGTH, -30,
            DEXTERITY, 50,
            CONSTITUTION, -10,
            AGILITY, -5,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    DIVINE_PROTECTION(SPELL_EFFECT, Map.of(
            STRENGTH, 6,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 6,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    STRENGTHENING(SPELL_EFFECT, Map.of(
            STRENGTH, 5,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 4,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    PROTECTIVE_HAND_OF_NATURE(SPELL_EFFECT, Map.of(
            STRENGTH, 5,
            DEXTERITY, 0,
            CONSTITUTION, 0,
            AGILITY, 4,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    CANINE_SOUL_CONNECTION(SPELL_EFFECT, Map.of(
            STRENGTH, 5,
            DEXTERITY, 5,
            CONSTITUTION, 2,
            AGILITY, 2,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    APE_LIKE_SOUL_CONNECTION(SPELL_EFFECT, Map.of(
            STRENGTH, 5,
            DEXTERITY, 2,
            CONSTITUTION, 5,
            AGILITY, 2,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    )),
    BIRD_OF_PREY_SOUL_CONNECTION(SPELL_EFFECT, Map.of(
            STRENGTH, 2,
            DEXTERITY, 5,
            CONSTITUTION, 2,
            AGILITY, 5,
            INTELLIGENCE, 0,
            LORE, 0,
            MENTAL_POWER, 0,
            SPIRITUAL_POWER, 0
    ));

    Booster(BoosterType type, Map<Attribute, Integer> effects) {
        this.type = type;
        this.effects = effects;
    }

    public final BoosterType type;
    public final Map<Attribute, Integer> effects;
}
