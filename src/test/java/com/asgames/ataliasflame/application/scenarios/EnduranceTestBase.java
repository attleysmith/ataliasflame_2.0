package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.interfaces.model.*;
import org.junit.jupiter.api.AfterEach;

import java.util.*;

import static com.asgames.ataliasflame.application.scenarios.HelperUtils.*;
import static com.asgames.ataliasflame.application.scenarios.Decisions.*;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.BODY_ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.HELMET;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_ABSORPTION;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public abstract class EnduranceTestBase extends WebTestBase {

    protected CharacterDto character;
    protected LocationDto location;
    protected Map<MagicType, List<SpellDto>> usableSpells;

    @AfterEach
    void tearDown() {
        removeCharacter(character.getReference());
    }

    protected void initializeCharacter(CharacterInput characterInput) {
        character = createCharacter(characterInput);
        location = getLocation(character.getLocationReference());
        refreshUsableSpells();
    }

    protected void addAttributePoints(Attribute attribute, int points) {
        character = addAttributePoints(character.getReference(), attribute, points);
    }

    protected void upgradeCaste(Caste newCaste) {
        while (injured(character)) {
            character = sleep(character.getReference());
        }

        character = upgradeCaste(character.getReference(), newCaste);
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
                character = castSpell(character.getReference(), spell.getName());
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
            character = castSpell(character.getReference(), spell.getName());
        }
    }

    private void enterNewLocation() {
        LocationDto newLocation = buildLocation(character.getLevel());

        LocationContextDto locationContext = enterLocation(character.getReference(), newLocation.getReference());
        character = locationContext.getCharacter();
        location = locationContext.getLocation();
    }

    private void castAttackMagic() {
        if (noNeedToCastAttackMagic(location)) {
            return;
        }
        targetMonsterOrder(location, ATTACK)
                .forEach(this::castAttackMagic);

        location = getLocation(location.getReference());
    }

    private void castAttackMagic(MonsterDto monster) {
        MonsterDto targetMonster = monster;

        boolean tryToAttack = true;
        while (tryToAttack && monsterIsAlive(targetMonster)) {
            boolean hasAvailableSoul = !listReadySouls().isEmpty();
            Optional<SpellDto> attackSpell = chooseAttackSpell(usableSpells.get(ATTACK), character, hasAvailableSoul);
            if (attackSpell.isPresent() && worthyTargetOfAttackSpell(targetMonster, attackSpell.get())) {
                TargetContextDto targetContext = castTargetingSpell(character.getReference(), attackSpell.get().getName(), targetMonster.getReference());

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

        location = getLocation(location.getReference());
    }

    private void castCurseMagic(MonsterDto monster) {
        boolean hasAvailableSoul = !listReadySouls().isEmpty();
        Optional<SpellDto> curseSpell = chooseCurseSpell(usableSpells.get(CURSE), character, hasAvailableSoul);
        if (curseSpell.isPresent() && worthyTargetOfCurseSpell(monster, character)) {
            TargetContextDto targetContext = castTargetingSpell(character.getReference(), curseSpell.get().getName(), monster.getReference());

            character = targetContext.getCharacter();
        }
    }

    private void closeCombat() {
        LocationContextDto locationContext = seizeLocation(character.getReference());
        character = locationContext.getCharacter();
        location = locationContext.getLocation();
    }

    private void lootLocation() {
        lootFood();
        lootWeapons();
        lootShields();
        lootArmors();
    }

    private void lootFood() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(FOOD))
                .forEach(item -> {
                    LocationContextDto locationContext = useItem(character.getReference(), item.getReference());
                    character = locationContext.getCharacter();
                    location = locationContext.getLocation();
                });
    }

    private void lootWeapons() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(WEAPON))
                .forEach(item -> {
                    WeaponDto newWeapon = getWeapon(location.getReference(), item.getReference());
                    if (needToChangeWeapon(character, newWeapon)) {
                        LocationContextDto locationContext = useItem(character.getReference(), newWeapon.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    } else if (needToStoreWeapon(character, newWeapon)) {
                        LocationContextDto locationContext = storeItem(character.getReference(), newWeapon.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    }
                });
    }

    private void lootShields() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(SHIELD))
                .forEach(item -> {
                    ShieldDto newShield = getShield(location.getReference(), item.getReference());
                    if (needToChangeShield(character, newShield)) {
                        LocationContextDto locationContext = useItem(character.getReference(), newShield.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    }
                });
    }

    private void lootArmors() {
        location.getItems().stream()
                .filter(item -> item.getType().equals(ARMOR))
                .forEach(item -> {
                    ArmorDto newArmor = getArmor(location.getReference(), item.getReference());
                    if ((newArmor.getArmorType().equals(HELMET) && needToChangeHelmet(character, newArmor))
                            || (newArmor.getArmorType().equals(BODY_ARMOR) && needToChangeBodyArmor(character, newArmor))) {
                        LocationContextDto locationContext = useItem(character.getReference(), newArmor.getReference());
                        character = locationContext.getCharacter();
                        location = locationContext.getLocation();
                    }
                });
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
                    TargetContextDto targetContext = castTargetingSpell(character.getReference(), spell.getName(), targetMonster.getReference());

                    character = targetContext.getCharacter();
                } else {
                    character = castSpell(character.getReference(), spell.getName());
                }

                readyToGo = noNeedToCastHealingMagic(character);
            } else {
                readyToGo = true;
            }
        }
    }

    private void finishEncounter() {
        character = timePassed(character.getReference());
    }

    private void sleep() {
        if (noNeedToPrepareToSummon(usableSpells.get(SUMMON), character)
                && noNeedToRecover(character)) {
            return;
        }
        character = sleep(character.getReference());
    }

    private void refreshUsableSpells() {
        usableSpells = listCharacterSpells(character.getReference())
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
