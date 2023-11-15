package com.asgames.ataliasflame.application.scenarios.helpers;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.application.scenarios.services.ControllerTest;
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
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_ABSORPTION;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"booster.experience:true", "test.mode.controller:service"})
public abstract class EnduranceTestBase {

    protected final static int SINGLE_CASTE_MAX_LEVEL = 100;
    protected final static int DUAL_CASTE_MAX_LEVEL = 90;

    @Autowired
    private ControllerTest controller;

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
        refreshUsableSpells();
        castHealingMagic();
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
                character = controller.castSpell(character.getReference(), spell.getName());
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
            character = controller.castSpell(character.getReference(), spell.getName());
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
        targetMonsterOrder(location, ATTACK)
                .forEach(this::castAttackMagic);

        location = controller.getLocation(location.getReference());
    }

    private void castAttackMagic(MonsterDto monster) {
        MonsterDto targetMonster = monster;

        boolean tryToAttack = true;
        while (tryToAttack && monsterIsAlive(targetMonster)) {
            boolean hasAvailableSoul = !listReadySouls().isEmpty();
            Optional<SpellDto> attackSpell = chooseAttackSpell(usableSpells.get(ATTACK), character, hasAvailableSoul);
            if (attackSpell.isPresent() && worthyTargetOfAttackSpell(targetMonster, attackSpell.get())) {
                TargetContextDto targetContext = controller.castTargetingSpell(character.getReference(), attackSpell.get().getName(), targetMonster.getReference());

                character = targetContext.getCharacter();
                targetMonster = targetContext.getMonster();
            } else {
                tryToAttack = false;
            }
        }
    }

    private void castCurseMagic() {
        targetMonsterOrder(location, CURSE)
                .forEach(this::castCurseMagic);

        location = controller.getLocation(location.getReference());
    }

    private void castCurseMagic(MonsterDto monster) {
        boolean hasAvailableSoul = !listReadySouls().isEmpty();
        Optional<SpellDto> curseSpell = chooseCurseSpell(usableSpells.get(CURSE), character, hasAvailableSoul);
        if (curseSpell.isPresent() && worthyTargetOfCurseSpell(monster, character)) {
            TargetContextDto targetContext = controller.castTargetingSpell(character.getReference(), curseSpell.get().getName(), monster.getReference());

            character = targetContext.getCharacter();
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
        boolean reachDeadMonster = location.getMonsters().stream().anyMatch(HelperUtils::monsterIsDead);
        boolean readyToGo = noNeedToCastHealingMagic(character);
        while (!readyToGo) {
            boolean hasAvailableSoul = !listReadySouls().isEmpty();
            Optional<SpellDto> healingSpell = chooseHealingSpell(usableSpells.get(HEALING), character, hasAvailableSoul, reachDeadMonster);
            if (healingSpell.isPresent()) {
                SpellDto spell = healingSpell.get();
                if (spell.getName().equals(ENERGY_ABSORPTION)) {
                    MonsterDto targetMonster = targetOfEnergyAbsorption(location)
                            .orElseThrow(() -> new IllegalStateException("Wrong healing spell chosen!"));
                    TargetContextDto targetContext = controller.castTargetingSpell(character.getReference(), spell.getName(), targetMonster.getReference());

                    character = targetContext.getCharacter();
                } else {
                    character = controller.castSpell(character.getReference(), spell.getName());
                }

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
