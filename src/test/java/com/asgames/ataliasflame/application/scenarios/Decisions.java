package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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

    private static int maxNumberOfBlessings(Character character, List<Monster> monsters) {
        int overpowering = character.getHealth().actualValue()
                +
                character.getCompanions().stream()
                        .map(companion -> companion.getHealth().actualValue())
                        .reduce(0, Integer::sum)
                -
                monsters.stream()
                        .map(monster -> monster.getHealth().actualValue())
                        .reduce(0, Integer::sum);

        if (overpowering < 0) return 5;
        else if (overpowering < 50) return 4;
        else if (overpowering < 150) return 3;
        else if (overpowering < 300) return 2;
        else if (overpowering < 500) return 1;
        else return 0;
    }

    public static boolean noNeedToSummon(Character character) {
        return character.getCompanions().size() >= MAX_NUMBER_OF_COMPANIONS;
    }

    public static boolean noNeedToCastAttackMagic(Location location) {
        return location.getMonsters().size() <= 1;
    }

    public static boolean noNeedToCastHealingMagic(Character character) {
        return character.getHealth().tolerateLoss(TOLERATED_INJURY_TO_HEAL);
    }

    public static boolean noNeedToPrepareToSummon(List<Spell> usableSpells, Character character) {
        boolean hasCompanion = !character.getCompanions().isEmpty();
        boolean noSpell = usableSpells.isEmpty();
        boolean hasMagic = usableSpells.stream().anyMatch(spell -> character.getMagic().has(spell.getCost()));
        return hasCompanion || noSpell || hasMagic;
    }

    public static boolean needToChangeWeapon(Character character, Weapon newWeapon) {
        return newWeapon.averageDamage() > character.getWeapon().map(Weapon::averageDamage).orElse(1);
    }

    public static boolean needToChangeShield(Character character, Shield newShield) {
        return character.hasFreeHand() && character.getShield()
                .map(oldShield -> newShield.lastsLonger(oldShield)
                        || (newShield.sameDurable(oldShield) && newShield.getBlocking() > oldShield.getBlocking()))
                .orElse(true);
    }

    public static boolean needToChangeHelmet(Character character, Armor newArmor) {
        return character.getCover().getHelmet()
                .map(oldArmor -> newArmor.lastsLonger(oldArmor)
                        || (newArmor.sameDurable(oldArmor) && newArmor.getDefense() > oldArmor.getDefense()))
                .orElse(true);
    }

    public static boolean needToChangeBodyArmor(Character character, Armor newArmor) {
        return character.getCover().getBodyArmor()
                .map(oldArmor -> newArmor.lastsLonger(oldArmor)
                        || (newArmor.sameDurable(oldArmor) && newArmor.getDefense() > oldArmor.getDefense()))
                .orElse(true);
    }

    public static boolean noNeedToRecover(Character character) {
        return character.getHealth().tolerateLoss(TOLERATED_INJURY_TO_SLEEP);
    }

    public static boolean repeatSummon(Character character, int previousNumberOfCompanions) {
        int actualNumberOfCompanions = character.getCompanions().size();
        return previousNumberOfCompanions < actualNumberOfCompanions
                && actualNumberOfCompanions < MAX_NUMBER_OF_COMPANIONS;
    }

    public static boolean notEnoughBlessing(Character character, Location location) {
        int actualNumberOfBlessings = character.getBlessings().size();
        return actualNumberOfBlessings < maxNumberOfBlessings(character, location.getMonsters());
    }

    public static Stream<Spell> summonOrder(List<Spell> usableSpells) {
        return usableSpells.stream()
                .sorted(comparing(spell -> SUMMON_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Stream<Spell> blessingOrder(List<Spell> usableSpells) {
        return usableSpells.stream()
                .sorted(comparing(spell -> BLESSING_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Optional<Spell> chooseAttackSpell(List<Spell> usableSpells, Character character, boolean hasAvailableSoul) {
        return usableSpells.stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .min(comparing(spell -> ATTACK_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Optional<Spell> chooseCurseSpell(List<Spell> usableSpells, Character character, boolean hasAvailableSoul) {
        return usableSpells.stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .min(comparing(spell -> CURSE_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Optional<Spell> chooseHealingSpell(List<Spell> usableSpells, Character character, boolean hasAvailableSoul, boolean reachDeadMonster) {
        return usableSpells.stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .filter(spell -> reachDeadMonster || !spell.getName().equals(ENERGY_ABSORPTION))
                .min(comparing(spell -> HEALING_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Stream<Monster> targetMonsterOrder(Location location, MagicType magicType) {
        return location.getMonsters().stream()
                .filter(Combatant::isAlive)
                .filter(monster -> monster.getHealth().has(MIN_HEALTH_TO_TARGET.get(magicType)))
                .sorted((monster1, monster2) -> {
                    Integer monster1Health = monster1.getHealth().actualValue();
                    Integer monster2Health = monster2.getHealth().actualValue();
                    return monster2Health.compareTo(monster1Health);
                });
    }

    public static boolean worthyTargetOfAttackSpell(Monster monster, Spell spell) {
        return monster.getHealth().actualValue() >= spell.averageDamage();
    }

    public static boolean worthyTargetOfCurseSpell(Monster monster, Character character) {
        return monster.getAttack() > character.getDefense();
    }

    public static Optional<Monster> targetOfEnergyAbsorption(Location location) {
        return location.getMonsters().stream()
                .filter(Combatant::isDead)
                .max(comparing(monster -> monster.getVitality().actualValue()));
    }
}
