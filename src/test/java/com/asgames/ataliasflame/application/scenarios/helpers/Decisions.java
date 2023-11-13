package com.asgames.ataliasflame.application.scenarios.helpers;

import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.interfaces.model.*;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.asgames.ataliasflame.application.scenarios.helpers.HelperUtils.*;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.BODY_ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.HELMET;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.ATTACK;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.CURSE;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.*;
import static java.util.Comparator.comparing;

public final class Decisions {

    private Decisions() {
    }

    private static final int MAX_NUMBER_OF_COMPANIONS = 5;
    private static final int TOLERATED_INJURY_TO_HEAL = 80;
    private static final int TOLERATED_INJURY_TO_SLEEP = 40;

    private static final Map<MagicType, Integer> MIN_HEALTH_TO_TARGET = Map.of(
            ATTACK, 20,
            CURSE, 30
    );

    private static final Map<SpellName, Integer> SUMMON_PREFERENCES = Map.of(
            PROJECTION_OF_ENERGY, 1,
            FRIEND_IN_NEED, 2,
            SUMMON_GUARDIAN, 3,
            CALLING_ANIMALS, 4,
            CALLING_THE_SOULS, 5
    );

    private static final Map<SpellName, Integer> BLESSING_PREFERENCES = Map.of(
            DIVINE_PROTECTION, 1,
            PROTECTIVE_HAND_OF_NATURE, 2,
            STRENGTHENING, 3,
            SOUL_CONNECTION, 4,
            ENERGY_SHIELD, 5
    );

    private static final Map<SpellName, Integer> ATTACK_PREFERENCES = Map.of(
            SOUL_OUTBURST, 1,
            INFERNO, 2,
            BLADES_OF_JUDGEMENT, 3,
            GLACIAL_BLOW, 4,
            WRATH_OF_NATURE, 5,
            DIVINE_HAMMER, 6,
            LIGHTNING_STRIKE, 7,
            FIREBALL, 8,
            SPLITTING_WIND, 9,
            BALL_OF_ENERGY, 10
    );

    private static final Map<SpellName, Integer> CURSE_PREFERENCES = Map.of(
            ENERGY_BLOCKING, 1,
            SOUL_STRIKE, 2,
            POWER_DRAIN, 3,
            WEAKENING, 4,
            SHACKLE, 5
    );

    private static final Map<SpellName, Integer> HEALING_PREFERENCES = Map.of(
            ENERGY_ABSORPTION, 1,
            SOUL_POWER, 2,
            REGENERATION, 3,
            HEALING_WAVE, 4,
            RECHARGING, 5,
            BREATH_OF_GOD, 6,
            HEALING_WORD, 7,
            POWER_OF_NATURE, 8,
            CURE, 9,
            WOUND_HEALING, 10
    );

    private static int maxNumberOfBlessings(CharacterDto character, List<MonsterDto> monsters) {
        int overpowering = actualHealthOf(character)
                +
                character.getCompanions().stream()
                        .map(HelperUtils::actualHealthOf)
                        .reduce(0, Integer::sum)
                -
                monsters.stream()
                        .map(HelperUtils::actualHealthOf)
                        .reduce(0, Integer::sum);

        if (overpowering < 0) return 5;
        else if (overpowering < 50) return 4;
        else if (overpowering < 150) return 3;
        else if (overpowering < 300) return 2;
        else if (overpowering < 500) return 1;
        else return 0;
    }

    public static boolean noNeedToSummon(CharacterDto character) {
        return character.getCompanions().size() >= MAX_NUMBER_OF_COMPANIONS;
    }

    public static boolean noNeedToCastAttackMagic(LocationDto location) {
        return location.getMonsters().size() <= 1;
    }

    public static boolean noNeedToCastHealingMagic(CharacterDto character) {
        return tolerateLoss(character, TOLERATED_INJURY_TO_HEAL);
    }

    public static boolean noNeedToPrepareToSummon(List<SpellDto> usableSpells, CharacterDto character) {
        boolean hasCompanion = !character.getCompanions().isEmpty();
        boolean noSpell = usableSpells.isEmpty();
        boolean hasMagic = usableSpells.stream().anyMatch(spell -> hasMagicCost(character, spell));
        return hasCompanion || noSpell || hasMagic;
    }

    public static boolean needToChangeWeapon(CharacterDto character, WeaponDto newWeapon) {
        return averageDamageOf(newWeapon) > (character.getWeapon() == null ? 1 : averageDamageOf(character.getWeapon()));
    }

    public static boolean needToStoreWeapon(CharacterDto character, WeaponDto newWeapon) {
        return averageDamageOf(newWeapon) > (character.getSpareWeapon() == null ? 1 : averageDamageOf(character.getSpareWeapon()));
    }

    public static boolean needToSwitchWeapons(CharacterDto character) {
        if (character.getSpareWeapon() == null) {
            return false;
        }
        return needToChangeWeapon(character, character.getSpareWeapon());
    }

    public static boolean newShieldAllowed(CharacterDto character) {
        return character.getSpareShield() == null;
    }

    public static boolean needToChangeShield(CharacterDto character, ShieldDto newShield) {
        return isOneHanded(character.getWeapon()) && isBetterShield(newShield, character.getShield());
    }

    public static boolean newSpareShieldAllowed(CharacterDto character) {
        return character.getShield() == null;
    }

    public static boolean needToStoreShield(CharacterDto character, ShieldDto newShield) {
        return isBetterShield(newShield, character.getSpareShield());
    }

    public static boolean needToSwitchShields(CharacterDto character) {
        if (character.getSpareShield() == null) {
            return false;
        }
        return needToChangeShield(character, character.getSpareShield());
    }

    private static boolean isBetterShield(ShieldDto newShield, @Nullable ShieldDto oldShield) {
        if (oldShield == null) {
            return true;
        }
        int oldDurability = actualDurability(oldShield);
        return lastsLonger(newShield, oldDurability)
                || (sameDurable(newShield, oldDurability) && newShield.getBlocking() > oldShield.getBlocking());
    }

    public static boolean needToChangeHelmet(CharacterDto character, ArmorDto newArmor) {
        if (!newArmor.getArmorType().equals(HELMET)) {
            throw new IllegalArgumentException("New armor must be a HELMET!");
        }
        return isBetterArmor(newArmor, character.getCover().getHelmet());
    }

    public static boolean needToChangeBodyArmor(CharacterDto character, ArmorDto newArmor) {
        if (!newArmor.getArmorType().equals(BODY_ARMOR)) {
            throw new IllegalArgumentException("New armor must be a BODY_ARMOR!");
        }
        return isBetterArmor(newArmor, character.getCover().getBodyArmor());
    }

    private static boolean isBetterArmor(ArmorDto newArmor, @Nullable ArmorDto oldArmor) {
        if (oldArmor == null) {
            return true;
        }
        int oldDurability = actualDurability(oldArmor);
        return lastsLonger(newArmor, oldDurability)
                || (sameDurable(newArmor, oldDurability) && newArmor.getDefense() > oldArmor.getDefense());
    }

    public static boolean noNeedToRecover(CharacterDto character) {
        return tolerateLoss(character, TOLERATED_INJURY_TO_SLEEP);
    }

    public static boolean repeatSummon(CharacterDto character, int previousNumberOfCompanions) {
        int actualNumberOfCompanions = character.getCompanions().size();
        return previousNumberOfCompanions < actualNumberOfCompanions
                && actualNumberOfCompanions < MAX_NUMBER_OF_COMPANIONS;
    }

    public static boolean notEnoughBlessing(CharacterDto character, LocationDto location) {
        int actualNumberOfBlessings = character.getBlessings().size();
        return actualNumberOfBlessings < maxNumberOfBlessings(character, location.getMonsters());
    }

    public static Stream<SpellDto> summonOrder(List<SpellDto> usableSpells) {
        return usableSpells.stream()
                .sorted(comparing(spell -> SUMMON_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Stream<SpellDto> blessingOrder(List<SpellDto> usableSpells) {
        return usableSpells.stream()
                .sorted(comparing(spell -> BLESSING_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Optional<SpellDto> chooseAttackSpell(List<SpellDto> usableSpells, CharacterDto character, boolean hasAvailableSoul) {
        return usableSpells.stream()
                .filter(spell -> hasMagicCost(character, spell))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .min(comparing(spell -> ATTACK_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Optional<SpellDto> chooseCurseSpell(List<SpellDto> usableSpells, CharacterDto character, boolean hasAvailableSoul) {
        return usableSpells.stream()
                .filter(spell -> hasMagicCost(character, spell))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .min(comparing(spell -> CURSE_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Optional<SpellDto> chooseHealingSpell(List<SpellDto> usableSpells, CharacterDto character, boolean hasAvailableSoul, boolean reachDeadMonster) {
        return usableSpells.stream()
                .filter(spell -> hasMagicCost(character, spell))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .filter(spell -> reachDeadMonster || !spell.getName().equals(ENERGY_ABSORPTION))
                .min(comparing(spell -> HEALING_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Stream<MonsterDto> targetMonsterOrder(LocationDto location, MagicType magicType) {
        return location.getMonsters().stream()
                .filter(HelperUtils::monsterIsAlive)
                .filter(monster -> hasHealth(monster, MIN_HEALTH_TO_TARGET.get(magicType)))
                .sorted((monster1, monster2) -> {
                    Integer monster1Health = actualHealthOf(monster1);
                    Integer monster2Health = actualHealthOf(monster2);
                    return monster2Health.compareTo(monster1Health);
                });
    }

    public static boolean worthyTargetOfAttackSpell(MonsterDto monster, SpellDto spell) {
        return hasHealth(monster, averageDamageOf(spell));
    }

    public static boolean worthyTargetOfCurseSpell(MonsterDto monster, CharacterDto character) {
        return monster.getAttack() > character.getDefense();
    }

    public static Optional<MonsterDto> targetOfEnergyAbsorption(LocationDto location) {
        return location.getMonsters().stream()
                .filter(HelperUtils::monsterIsDead)
                .max(comparing(HelperUtils::actualVitalityOf));
    }
}
