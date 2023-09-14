package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.ItemType;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
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

    public static final Map<MagicType, Integer> MIN_HEALTH_TO_TARGET = Map.of(
            ATTACK, 20,
            CURSE, 30
    );

    public static final Map<SpellName, Integer> SUMMON_PREFERENCES = Map.of(
            PROJECTION_OF_ENERGY, 1,
            FRIEND_IN_NEED, 2,
            SUMMON_GUARDIAN, 3,
            CALLING_ANIMALS, 4,
            CALLING_THE_SOULS, 5);

    public static final Map<SpellName, Integer> BLESSING_PREFERENCES = Map.of(
            DIVINE_PROTECTION, 1,
            STRENGTHENING, 2,
            PROTECTIVE_HAND_OF_NATURE, 3,
            SOUL_CONNECTION, 4,
            ENERGY_SHIELD, 5);

    public static final Map<SpellName, Integer> CURSE_PREFERENCES = Map.of(
            ENERGY_BLOCKING, 1,
            SOUL_STRIKE, 2,
            POWER_DRAIN, 3,
            WEAKENING, 4,
            SHACKLE, 5
    );

    public static final Map<ItemType, Integer> LOOTING_PREFERENCES = Map.of(
            FOOD, 1,
            WEAPON, 2,
            SHIELD, 3,
            ARMOR, 4
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

        if (overpowering < -150) return 5;
        else if (overpowering < -50) return 4;
        else if (overpowering < 50) return 3;
        else if (overpowering < 100) return 2;
        else if (overpowering < 150) return 1;
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
        return newWeapon.averageDamage() > character.getWeapon().averageDamage();
    }

    public static boolean needToChangeShield(Character character, Shield newShield) {
        return character.getWeapon().isOneHanded() && character.getShield()
                .map(oldShield -> newShield.lastsLonger(oldShield)
                        || (newShield.sameDurable(oldShield) && newShield.getDefense() > oldShield.getDefense()))
                .orElse(true);
    }

    public static boolean needToChangeArmor(Character character, Armor newArmor) {
        return character.getArmors().stream()
                .filter(Armor::isPhysical)
                .findAny()
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

    public static boolean repeatBlessing(Character character, Location location, Spell spell, int previousNumberOfBlessings) {
        int actualNumberOfBlessings = character.getBlessings().size();
        return previousNumberOfBlessings < actualNumberOfBlessings
                && actualNumberOfBlessings < maxNumberOfBlessings(character, location.getMonsters())
                && !character.getBlessings().contains(spell.getName().name());
    }

    public static Stream<Spell> summonOrder(List<Spell> usableSpells) {
        return usableSpells.stream()
                .sorted(comparing(spell -> SUMMON_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Stream<Spell> blessingOrder(List<Spell> usableSpells) {
        return usableSpells.stream()
                .sorted(comparing(spell -> BLESSING_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Stream<Item> lootingOrder(Location location) {
        return location.getItems().stream()
                .sorted(comparing(item -> LOOTING_PREFERENCES.getOrDefault(item.getType(), 0)));
    }

    public static Optional<Spell> chooseAttackSpell(List<Spell> usableSpells, Character character, boolean hasAvailableSoul) {
        return usableSpells.stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .max(comparing(Spell::averageDamage));
    }

    public static Optional<Spell> chooseCurseSpell(List<Spell> usableSpells, Character character, boolean hasAvailableSoul) {
        return usableSpells.stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .min(comparing(spell -> CURSE_PREFERENCES.getOrDefault(spell.getName(), 0)));
    }

    public static Optional<Spell> chooseHealingSpell(List<Spell> usableSpells, Character character, boolean hasAvailableSoul) {
        return usableSpells.stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .max(comparing(Spell::getHealingEffect));
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

    public static boolean worthyTarget(Monster monster, Spell spell) {
        return monster.getHealth().actualValue() >= spell.averageDamage();
    }
}
