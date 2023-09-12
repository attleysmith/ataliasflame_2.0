package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.dtos.CompanionTemplate;
import com.asgames.ataliasflame.domain.model.enums.AnimalCode;
import com.asgames.ataliasflame.domain.model.enums.DivineGuardianCode;
import com.asgames.ataliasflame.domain.model.enums.GuardianWarriorCode;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.AnimalCode.*;
import static com.asgames.ataliasflame.domain.model.enums.CompanionType.*;
import static com.asgames.ataliasflame.domain.model.enums.DivineGuardianCode.COMMANDER;
import static com.asgames.ataliasflame.domain.model.enums.DivineGuardianCode.KNIGHT;
import static com.asgames.ataliasflame.domain.model.enums.GuardianWarriorCode.*;

public final class Summonings {

    private Summonings() {
    }

    public static final Map<AnimalCode, CompanionTemplate> ANIMALS = Map.of(
            TAMED_FALCON, CompanionTemplate.builder()
                    .code(TAMED_FALCON.name())
                    .type(ANIMAL)
                    .attack(75)
                    .defense(0)
                    .minDamage(1)
                    .maxDamage(2)
                    .health(10)
                    .initiative(-6)
                    .build(),
            TAMED_DOG, CompanionTemplate.builder()
                    .code(TAMED_DOG.name())
                    .type(ANIMAL)
                    .attack(70)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(3)
                    .health(20)
                    .initiative(-3)
                    .build(),
            TAMED_WOLF, CompanionTemplate.builder()
                    .code(TAMED_WOLF.name())
                    .type(ANIMAL)
                    .attack(70)
                    .defense(5)
                    .minDamage(1)
                    .maxDamage(3)
                    .health(25)
                    .initiative(-3)
                    .build(),
            TAMED_BEAR, CompanionTemplate.builder()
                    .code(TAMED_BEAR.name())
                    .type(ANIMAL)
                    .attack(80)
                    .defense(10)
                    .minDamage(1)
                    .maxDamage(5)
                    .health(40)
                    .initiative(-2)
                    .build()
    );

    public static final Map<GuardianWarriorCode, CompanionTemplate> GUARDIAN_WARRIORS = Map.of(
            HUNTER, CompanionTemplate.builder()
                    .code(HUNTER.name())
                    .type(GUARDIAN_WARRIOR)
                    .attack(85)
                    .defense(5)
                    .minDamage(2)
                    .maxDamage(6)
                    .health(75)
                    .initiative(-9)
                    .build(),
            MILITIA, CompanionTemplate.builder()
                    .code(MILITIA.name())
                    .type(GUARDIAN_WARRIOR)
                    .attack(85)
                    .defense(10)
                    .minDamage(3)
                    .maxDamage(15)
                    .health(80)
                    .initiative(-3)
                    .build(),
            SWORDSMAN, CompanionTemplate.builder()
                    .code(SWORDSMAN.name())
                    .type(GUARDIAN_WARRIOR)
                    .attack(90)
                    .defense(25)
                    .minDamage(5)
                    .maxDamage(15)
                    .health(90)
                    .initiative(-3)
                    .build()
    );

    public static final Map<DivineGuardianCode, CompanionTemplate> DIVINE_GUARDIANS = Map.of(
            KNIGHT, CompanionTemplate.builder()
                    .code(KNIGHT.name())
                    .type(DIVINE_GUARDIAN)
                    .attack(90)
                    .defense(25)
                    .minDamage(5)
                    .maxDamage(20)
                    .health(100)
                    .initiative(-4)
                    .build(),
            COMMANDER, CompanionTemplate.builder()
                    .code(COMMANDER.name())
                    .type(DIVINE_GUARDIAN)
                    .attack(100)
                    .defense(40)
                    .minDamage(6)
                    .maxDamage(24)
                    .health(120)
                    .initiative(-5)
                    .build()
    );
}
