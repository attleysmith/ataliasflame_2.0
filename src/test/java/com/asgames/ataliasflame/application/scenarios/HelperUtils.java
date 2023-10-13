package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.interfaces.model.*;
import org.springframework.lang.Nullable;

import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

public final class HelperUtils {

    private HelperUtils() {
    }

    public static int actualHealthOf(CharacterDto character) {
        return character.getTotalHealth() - character.getInjury();
    }

    public static boolean injured(CharacterDto character) {
        return character.getInjury() != 0;
    }

    public static boolean isAlive(CharacterDto character) {
        return character.getTotalHealth() - character.getInjury() > 0;
    }

    public static boolean isDead(CharacterDto character) {
        return character.getTotalHealth() == character.getInjury();
    }

    public static boolean hasMagicCost(CharacterDto character, SpellDto spell) {
        return character.getTotalMagicPoint() - character.getUsedMagicPoint() >= spell.getCost();
    }

    public static boolean tolerateLoss(CharacterDto character, int toleratedLossPercent) {
        int toleratedLossValue = percent(character.getTotalHealth(), toleratedLossPercent);
        return character.getInjury() <= toleratedLossValue;
    }

    public static int averageDamageOf(SpellDto spell) {
        return (spell.getMinDamage() + spell.getMaxDamage()) / 2;
    }

    public static boolean isOneHanded(@Nullable WeaponDto weapon) {
        if (weapon == null) {
            return true;
        }
        return weapon.isOneHanded();
    }

    public static int averageDamageOf(WeaponDto weapon) {
        return (weapon.getMinDamage() + weapon.getMaxDamage()) / 2;
    }

    public static int actualDurability(ShieldDto shield) {
        return shield.getDurability() - shield.getWearAndTear();
    }

    public static boolean lastsLonger(ShieldDto shield, int otherDurability) {
        return actualDurability(shield) > otherDurability;
    }

    public static boolean sameDurable(ShieldDto shield, int otherDurability) {
        return actualDurability(shield) == otherDurability;
    }

    public static int actualDurability(ArmorDto armor) {
        return armor.getDurability() - armor.getWearAndTear();
    }

    public static boolean lastsLonger(ArmorDto armor, int otherDurability) {
        return actualDurability(armor) > otherDurability;
    }

    public static boolean sameDurable(ArmorDto armor, int otherDurability) {
        return actualDurability(armor) == otherDurability;
    }


    public static int actualHealthOf(CompanionDto companion) {
        return companion.getTotalHealth() - companion.getInjury();
    }

    public static boolean soulChipIsReady(SoulChipDto soulChip) {
        return soulChip.getTotalHealth() - soulChip.getFatigue() > 0;
    }

    public static boolean monsterIsAlive(MonsterDto monster) {
        return monster.getTotalHealth() - monster.getInjury() > 0;
    }

    public static boolean monsterIsDead(MonsterDto monster) {
        return monster.getTotalHealth() == monster.getInjury();
    }

    public static boolean hasHealth(MonsterDto monster, int expectedHealth) {
        return actualHealthOf(monster) >= expectedHealth;
    }

    public static int actualHealthOf(MonsterDto monster) {
        return monster.getTotalHealth() - monster.getInjury();
    }

    public static int actualVitalityOf(MonsterDto monster) {
        return monster.getTotalVitality() - monster.getLostVitality();
    }
}
