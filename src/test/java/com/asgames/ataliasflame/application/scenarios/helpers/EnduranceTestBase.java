package com.asgames.ataliasflame.application.scenarios.helpers;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.application.scenarios.services.TestController;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.interfaces.model.*;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static com.asgames.ataliasflame.application.scenarios.helpers.Decisions.*;
import static com.asgames.ataliasflame.application.scenarios.helpers.HelperUtils.*;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.BODY_ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.HELMET;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.*;
import static com.asgames.ataliasflame.domain.services.magic.spells.EnergySpell.ARG_KEY_ENERGY;
import static com.asgames.ataliasflame.domain.services.magic.spells.SoulSpell.ARG_KEY_SOUL_CHIP;
import static java.lang.Math.min;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"booster.experience:true", "test.mode.controller:service"})
public abstract class EnduranceTestBase {

    protected final static int SINGLE_CASTE_MAX_LEVEL = 100;
    protected final static int DUAL_CASTE_MAX_LEVEL = 90;

    @Autowired
    private TestController controller;

    protected CharacterDto character;
    protected LocationDto location;
    protected Map<MagicType, List<SpellDto>> usableSpells;

    @AfterEach
    void tearDown() {
        controller.removeCharacter(character.getReference());
    }

    protected void initializeCharacter(CharacterInput characterInput) {
        character = controller.createCharacter(characterInput);
        location = controller.getLocation(character.getLocationReference());
        refreshUsableSpells();
    }

    protected void addAttributePoints(Attribute attribute, int points) {
        character = controller.addAttributePoints(character.getReference(), attribute, points);
    }

    protected void upgradeCaste(Caste newCaste) {
        while (injured(character)) {
            character = controller.sleep(character.getReference());
        }

        character = controller.upgradeCaste(character.getReference(), newCaste);
        if (isAlive(character)) {
            refreshUsableSpells();
            castHealingMagic();
        }
    }

    protected void doCombat() {
        summon();
        putOnBlessings();
        castAttackMagic();
        castCurseMagic();
        closeCombat();
        if (isAlive(character)) {
            lootLocation();
            organizeInventory();
            castHealingMagic();
            finishEncounter();
            sleep();
            enterNewLocation();
        }
    }

    protected void combatUntilNextLevel() {
        int actualLevel = character.getLevel();

        do {
            doCombat();
        } while (isAlive(character) && character.getLevel() == actualLevel);
    }

    private void summon() {
        if (noNeedToSummon(character)) {
            return;
        }
        summonOrder(usableSpells.get(SUMMON))
                .forEach(this::doSummon);
    }

    private void doSummon(SpellDto spell) {
        int previousNumberOfCompanions = -1;
        while (repeatSummon(character, previousNumberOfCompanions)) {
            previousNumberOfCompanions = character.getCompanions().size();

            if (hasMagicCost(character, spell)) {
                Map<String, String> args = new HashMap<>();
                if (spell.getName().equals(PROJECTION_OF_ENERGY)) {
                    int magic = actualMagicPercentage(character);
                    args.put(ARG_KEY_ENERGY, String.valueOf(magic));
                    character = controller.castSpell(character.getReference(), spell.getName(), args);
                } else if (isSoulMagic(spell)) {
                    chooseSoulChipToSummon(character, listReadySouls()).ifPresent(soulChip -> {
                        args.put(ARG_KEY_SOUL_CHIP, soulChip.getReference());
                        character = controller.castSpell(character.getReference(), spell.getName(), args);
                    });
                } else {
                    character = controller.castSpell(character.getReference(), spell.getName(), args);
                }
            }
        }
    }

    private void putOnBlessings() {
        blessingOrder(usableSpells.get(BLESSING))
                .forEach(this::doBlessing);
    }

    private void doBlessing(SpellDto spell) {
        if (spell.getGroup().equals(SOUL) && listReadySouls().isEmpty()) {
            return;
        }
        if (notEnoughBlessing(character, location) && hasMagicCost(character, spell)) {
            Map<String, String> args = new HashMap<>();
            if (spell.getName().equals(ENERGY_SHIELD)) {
                args.put(ARG_KEY_ENERGY, String.valueOf(ENERGY_INVESTMENT.get(ENERGY_SHIELD)));
            } else if (isSoulMagic(spell)) {
                chooseSoulChipToUse(character, listReadySouls()).ifPresent(soulChip -> {
                    args.put(ARG_KEY_SOUL_CHIP, soulChip.getReference());
                    character = controller.castSpell(character.getReference(), spell.getName(), args);
                });
            } else {
                character = controller.castSpell(character.getReference(), spell.getName(), args);
            }
        }
    }

    private void enterNewLocation() {
        LocationDto newLocation = controller.buildLocation(character.getLevel());

        LocationContextDto locationContext = controller.enterLocation(character.getReference(), newLocation.getReference());
        character = locationContext.getCharacter();
        location = locationContext.getLocation();
    }

    private void castAttackMagic() {
        if (noNeedToCastAttackMagic(location)) {
            return;
        }

        List<MonsterDto> targetedMonsters = new ArrayList<>();
        Optional<MonsterDto> targetMonster = targetMonsterOrder(location, ATTACK).findFirst();
        while (targetMonster.isPresent()) {
            targetedMonsters.add(targetMonster.get());
            castAttackMagic(targetMonster.get());
            location = controller.getLocation(location.getReference());
            targetMonster = targetMonsterOrder(location, ATTACK)
                    .filter(monster -> !targetedMonsters.contains(monster))
                    .findFirst();
        }
    }

    private void castAttackMagic(MonsterDto monster) {
        MonsterDto targetMonster = monster;

        boolean tryToAttack = true;
        while (tryToAttack && HelperUtils.isAlive(targetMonster)) {
            boolean hasAvailableSoul = !listReadySouls().isEmpty();
            Optional<SpellDto> attackSpell = chooseAttackSpell(usableSpells.get(ATTACK), character, hasAvailableSoul);
            if (attackSpell.isPresent() && worthyTargetOfAttackSpell(targetMonster, attackSpell.get())) {
                SpellDto spell = attackSpell.get();
                Map<String, String> args = new HashMap<>();
                if (spell.getName().equals(BALL_OF_ENERGY)) {
                    int effort = energyBallEffort(spell, character, targetMonster, location);
                    int magic = actualMagicPercentage(character);
                    args.put(ARG_KEY_ENERGY, String.valueOf(min(effort, magic)));
                    TargetContextDto targetContext = controller.castTargetingSpell(character.getReference(), spell.getName(), targetMonster.getReference(), args);
                    character = targetContext.getCharacter();
                    targetMonster = targetContext.getMonster();
                } else if (isSoulMagic(spell)) {
                    Optional<SoulChipDto> soulChip = chooseSoulChipToUse(character, listReadySouls());
                    if (soulChip.isPresent()) {
                        args.put(ARG_KEY_SOUL_CHIP, soulChip.get().getReference());
                        TargetContextDto targetContext = controller.castTargetingSpell(character.getReference(), spell.getName(), targetMonster.getReference(), args);
                        character = targetContext.getCharacter();
                        targetMonster = targetContext.getMonster();
                    }
                } else {
                    TargetContextDto targetContext = controller.castTargetingSpell(character.getReference(), spell.getName(), targetMonster.getReference(), args);
                    character = targetContext.getCharacter();
                    targetMonster = targetContext.getMonster();
                }
            } else {
                tryToAttack = false;
            }
        }
    }

    private void castCurseMagic() {
        getEnergyBlocking(usableSpells.get(CURSE), character, location)
                .ifPresentOrElse(energyBlocking -> {
                            Map<String, String> args = new HashMap<>();
                            args.put(ARG_KEY_ENERGY, String.valueOf(ENERGY_INVESTMENT.get(ENERGY_BLOCKING)));
                            character = controller.castSpell(character.getReference(), energyBlocking.getName(), args);
                        },
                        () -> targetMonsterOrder(location, CURSE)
                                .forEach(this::castCurseMagic));

        location = controller.getLocation(location.getReference());
    }

    private void castCurseMagic(MonsterDto monster) {
        boolean hasAvailableSoul = !listReadySouls().isEmpty();
        Optional<SpellDto> curseSpell = chooseCurseSpell(usableSpells.get(CURSE), character, hasAvailableSoul);
        if (curseSpell.isPresent() && worthyTargetOfCurseSpell(monster, character)) {
            SpellDto spell = curseSpell.get();
            Map<String, String> args = new HashMap<>();
            if (isSoulMagic(spell)) {
                chooseSoulChipToUse(character, listReadySouls()).ifPresent(soulChip -> {
                    args.put(ARG_KEY_SOUL_CHIP, soulChip.getReference());
                    TargetContextDto targetContext = controller.castTargetingSpell(character.getReference(), spell.getName(), monster.getReference(), args);
                    character = targetContext.getCharacter();
                });
            } else {
                TargetContextDto targetContext = controller.castTargetingSpell(character.getReference(), spell.getName(), monster.getReference(), args);
                character = targetContext.getCharacter();
            }
        }
    }

    private void closeCombat() {
        LocationContextDto locationContext = controller.seizeLocation(character.getReference());
        character = locationContext.getCharacter();
        location = locationContext.getLocation();
    }

    private void lootLocation() {
        useFood();
        useWeapons();
        useShields();
        useArmors();
        storeWeapons();
        storeShields();
    }

    private void useFood() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(FOOD))
                .forEach(item -> {
                    LocationContextDto locationContext = controller.useItem(character.getReference(), item.getReference());
                    character = locationContext.getCharacter();
                    location = locationContext.getLocation();
                });
    }

    private void useWeapons() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(WEAPON))
                .forEach(item -> {
                    WeaponDto weaponToUse = controller.getWeapon(location.getReference(), item.getReference());
                    if (needToChangeWeapon(character, weaponToUse)) {
                        LocationContextDto locationContext = controller.useItem(character.getReference(), weaponToUse.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    }
                });
    }

    private void storeWeapons() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(WEAPON))
                .forEach(item -> {
                    WeaponDto weaponToStore = controller.getWeapon(location.getReference(), item.getReference());
                    if (needToStoreWeapon(character, weaponToStore)) {
                        LocationContextDto locationContext = controller.storeItem(character.getReference(), weaponToStore.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    }
                });
    }

    private void useShields() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(SHIELD))
                .forEach(item -> {
                    ShieldDto shieldToUse = controller.getShield(location.getReference(), item.getReference());
                    if (newShieldAllowed(character) && needToChangeShield(character, shieldToUse)) {
                        LocationContextDto locationContext = controller.useItem(character.getReference(), shieldToUse.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    }
                });
    }

    private void storeShields() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(SHIELD))
                .forEach(item -> {
                    ShieldDto shieldToStore = controller.getShield(location.getReference(), item.getReference());
                    if (newSpareShieldAllowed(character) && needToStoreShield(character, shieldToStore)) {
                        LocationContextDto locationContext = controller.storeItem(character.getReference(), shieldToStore.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    }
                });
    }

    private void useArmors() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(ARMOR))
                .forEach(item -> {
                    ArmorDto armorToUse = controller.getArmor(location.getReference(), item.getReference());
                    if ((armorToUse.getArmorType().equals(HELMET) && needToChangeHelmet(character, armorToUse))
                            || (armorToUse.getArmorType().equals(BODY_ARMOR) && needToChangeBodyArmor(character, armorToUse))) {
                        LocationContextDto locationContext = controller.useItem(character.getReference(), armorToUse.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    }
                });
    }

    private void organizeInventory() {
        if (needToSwitchWeapons(character)) {
            character = controller.switchWeapons(character.getReference());
        }
        if (needToSwitchShields(character)) {
            character = controller.switchShields(character.getReference());
        }
    }

    private void castHealingMagic() {
        boolean readyToGo = noNeedToCastHealingMagic(character);
        while (!readyToGo) {
            boolean hasAvailableSoul = !listReadySouls().isEmpty();
            Optional<SpellDto> healingSpell = chooseHealingSpell(usableSpells.get(HEALING), character, location, hasAvailableSoul);
            if (healingSpell.isPresent()) {
                SpellDto spell = healingSpell.get();
                Map<String, String> args = new HashMap<>();
                if (spell.getName().equals(ENERGY_ABSORPTION)) {
                    args.put(ARG_KEY_ENERGY, String.valueOf(ENERGY_INVESTMENT.get(ENERGY_ABSORPTION)));
                } else if (spell.getName().equals(RECHARGING)) {
                    int injury = actualInjuryPercentage(character);
                    int magic = actualMagicPercentage(character);
                    args.put(ARG_KEY_ENERGY, String.valueOf(min(injury, magic)));
                } else if (isSoulMagic(spell)) {
                    SoulChipDto soulChip = chooseSoulChipToUse(character, listReadySouls())
                            .orElseThrow(() -> new IllegalStateException("No soul chip to use!"));
                    args.put(ARG_KEY_SOUL_CHIP, soulChip.getReference());
                }
                character = controller.castSpell(character.getReference(), spell.getName(), args);
                location = controller.getLocation(location.getReference());

                readyToGo = noNeedToCastHealingMagic(character);
            } else {
                readyToGo = true;
            }
        }
    }

    private void finishEncounter() {
        character = controller.timePassed(character.getReference());
    }

    private void sleep() {
        if (noNeedToPrepareToSummon(usableSpells.get(SUMMON), character)
                && noNeedToRecover(character)) {
            return;
        }
        character = controller.sleep(character.getReference());
    }

    private void refreshUsableSpells() {
        usableSpells = controller.listCharacterSpells(character.getReference())
                .stream()
                .collect(groupingBy(SpellDto::getType));
        for (MagicType magicType : MagicType.values()) {
            usableSpells.putIfAbsent(magicType, new ArrayList<>());
        }
    }

    private List<String> listReadySouls() {
        List<String> unusedSouls = character.getSoulChips().stream()
                .filter(HelperUtils::soulChipIsReady)
                .map(SoulChipDto::getReference)
                .collect(toList());
        character.getCompanions().stream()
                .map(CompanionDto::getSourceSoulChip)
                .filter(Objects::nonNull)
                .forEach(unusedSouls::remove);
        character.getBlessings().stream()
                .map(ActiveBlessingDto::getSourceSoulChip)
                .filter(Objects::nonNull)
                .forEach(unusedSouls::remove);
        return unusedSouls;
    }
}
