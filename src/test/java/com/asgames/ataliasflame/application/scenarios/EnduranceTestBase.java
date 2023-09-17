package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.*;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.application.model.LocationContext;
import com.asgames.ataliasflame.application.model.TargetContext;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.magic.SpellRegistry;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.application.scenarios.Decisions.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_ABSORPTION;
import static java.util.stream.Collectors.groupingBy;

public abstract class EnduranceTestBase {

    @Autowired
    protected CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    protected CharacterAdventureService characterAdventureService;
    @Autowired
    protected CharacterMagicService characterMagicService;
    @Autowired
    protected CharacterLocationService characterLocationService;
    @Autowired
    protected LocationAdventureService locationAdventureService;
    @Autowired
    protected SpellRegistry spellRegistry;

    protected Character character;
    protected Location location;
    protected Map<MagicType, List<Spell>> usableSpells;

    @AfterEach
    void tearDown() {
        characterMaintenanceService.removeCharacter(character.getReference());
    }

    protected void createCharacter(CharacterInput characterInput) {
        character = characterMaintenanceService.createCharacter(characterInput);
        refreshUsableSpells();
    }

    protected void addAttributePoints(Attribute attribute, int points) {
        character = characterMaintenanceService.addAttributePoints(character.getReference(), attribute, points);
    }

    protected void upgradeCaste(Caste newCaste) {
        while (!character.getHealth().isFull()) {
            character = characterAdventureService.sleep(character.getReference());
        }

        character = characterMaintenanceService.upgradeCaste(character.getReference(), newCaste);
        refreshUsableSpells();
        castHealingMagic();
    }

    protected void doCombat() {
        summon();
        enterLocation();
        putOnBlessings();
        castAttackMagic();
        castCurseMagic();
        closeCombat();
        if (character.isAlive()) {
            lootLocation();
            castHealingMagic();
            finishEncounter();
            sleep();
        }
    }

    protected void combatUntilNextLevel() {
        int actualLevel = character.getLevel();

        do {
            doCombat();
        } while (character.isAlive() && character.getLevel() == actualLevel);
    }

    private void summon() {
        if (noNeedToSummon(character)) {
            return;
        }
        summonOrder(usableSpells.get(SUMMON))
                .forEach(this::doSummon);
    }

    private void doSummon(Spell spell) {
        int previousNumberOfCompanions = -1;
        while (repeatSummon(character, previousNumberOfCompanions)) {
            previousNumberOfCompanions = character.getCompanions().size();

            if (character.getMagic().has(spell.getCost())) {
                character = characterMagicService.castSpell(character.getReference(), spell.getName());
            }
        }
    }

    private void putOnBlessings() {
        blessingOrder(usableSpells.get(BLESSING))
                .forEach(this::doBlessing);
    }

    private void doBlessing(Spell spell) {
        int previousNumberOfBlessings = -1;
        while (repeatBlessing(character, location, spell, previousNumberOfBlessings)) {
            previousNumberOfBlessings = character.getBlessings().size();

            if (character.getMagic().has(spell.getCost())) {
                character = characterMagicService.castSpell(character.getReference(), spell.getName());
            }
        }
    }

    private void enterLocation() {
        location = locationAdventureService.buildLocation(character.getLevel());
    }

    private void castAttackMagic() {
        if (noNeedToCastAttackMagic(location)) {
            return;
        }
        targetMonsterOrder(location, ATTACK)
                .forEach(this::castAttackMagic);

        location = locationAdventureService.getLocation(location.getReference());
    }

    private void castAttackMagic(Monster monster) {
        Monster targetMonster = monster;

        boolean hasAvailableSoul = !listUnusedSouls().isEmpty();
        boolean tryToAttack = true;
        while (tryToAttack && targetMonster.isAlive()) {
            Optional<Spell> attackSpell = chooseAttackSpell(usableSpells.get(ATTACK), character, hasAvailableSoul);
            if (attackSpell.isPresent() && worthyTargetOfAttackSpell(targetMonster, spellRegistry.get(attackSpell.get().getName()))) {
                TargetContext targetContext = characterMagicService.castTargetingSpell(character.getReference(), attackSpell.get().getName(), targetMonster.getReference());

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

        location = locationAdventureService.getLocation(location.getReference());
    }

    private void castCurseMagic(Monster monster) {
        boolean hasAvailableSoul = !listUnusedSouls().isEmpty();
        Optional<Spell> curseSpell = chooseCurseSpell(usableSpells.get(CURSE), character, hasAvailableSoul);
        if (curseSpell.isPresent() && worthyTargetOfCurseSpell(monster, character)) {
            TargetContext targetContext = characterMagicService.castTargetingSpell(character.getReference(), curseSpell.get().getName(), monster.getReference());

            character = targetContext.getCharacter();
        }
    }

    private void closeCombat() {
        LocationContext locationContext = characterLocationService.seizeLocation(character.getReference(), location.getReference());
        character = locationContext.getCharacter();
        location = locationContext.getLocation();
    }

    private void lootLocation() {
        lootingOrder(location)
                .forEach(item -> {
                    LocationContext neverMind = LocationContext.builder().character(character).location(location).build();
                    LocationContext locationContext = switch (item.getType()) {
                        case FOOD ->
                                characterLocationService.useItem(character.getReference(), location.getReference(), item.getReference());
                        case WEAPON -> {
                            Weapon newWeapon = characterLocationService.getWeapon(item.getReference());
                            if (needToChangeWeapon(character, newWeapon)) {
                                yield characterLocationService.useItem(character.getReference(), location.getReference(), newWeapon.getReference());
                            } else {
                                yield neverMind;
                            }
                        }
                        case SHIELD -> {
                            Shield newShield = characterLocationService.getShield(item.getReference());
                            if (needToChangeShield(character, newShield)) {
                                yield characterLocationService.useItem(character.getReference(), location.getReference(), newShield.getReference());
                            } else {
                                yield neverMind;
                            }
                        }
                        case ARMOR -> {
                            Armor newArmor = characterLocationService.getArmor(item.getReference());
                            if (needToChangeArmor(character, newArmor)) {
                                yield characterLocationService.useItem(character.getReference(), location.getReference(), newArmor.getReference());
                            } else {
                                yield neverMind;
                            }
                        }
                    };
                    character = locationContext.getCharacter();
                    location = locationContext.getLocation();
                });
    }

    private void castHealingMagic() {
        boolean hasAvailableSoul = !listUnusedSouls().isEmpty();
        boolean reachDeadMonster = location.getMonsters().stream().anyMatch(Combatant::isDead);
        boolean readyToGo = noNeedToCastHealingMagic(character);
        while (!readyToGo) {
            Optional<Spell> healingSpell = chooseHealingSpell(usableSpells.get(HEALING), character, hasAvailableSoul, reachDeadMonster);
            if (healingSpell.isPresent()) {
                Spell spell = healingSpell.get();
                if (spell.getName().equals(ENERGY_ABSORPTION)) {
                    Monster targetMonster = targetOfEnergyAbsorption(location)
                            .orElseThrow(() -> new IllegalStateException("Wrong healing spell chosen!"));
                    TargetContext targetContext = characterMagicService.castTargetingSpell(character.getReference(), spell.getName(), targetMonster.getReference());

                    character = targetContext.getCharacter();
                } else {
                    character = characterMagicService.castSpell(character.getReference(), spell.getName());
                }

                readyToGo = noNeedToCastHealingMagic(character);
            } else {
                readyToGo = true;
            }
        }
    }

    private void finishEncounter() {
        character = characterMagicService.removeBlessingMagic(character.getReference());
    }

    private void sleep() {
        if (noNeedToPrepareToSummon(usableSpells.get(SUMMON), character)
                && noNeedToRecover(character)) {
            return;
        }
        character = characterAdventureService.sleep(character.getReference());
    }

    private void refreshUsableSpells() {
        usableSpells = characterMagicService.listCharacterSpells(character.getReference())
                .stream()
                .collect(groupingBy(Spell::getType));
        for (MagicType magicType : MagicType.values()) {
            usableSpells.putIfAbsent(magicType, new ArrayList<>());
        }
    }

    private List<SoulChip> listUnusedSouls() {
        List<SoulChip> unusedSouls = new ArrayList<>(character.getSoulChips());
        List<String> companionReferences = character.getCompanions().stream().map(Companion::getReference).toList();
        for (SoulChip soulChip : character.getSoulChips()) {
            if (companionReferences.contains(soulChip.getReference())
                    || character.getBlessings().contains(soulChip.getShape().name())) {
                unusedSouls.remove(soulChip);
            }
        }
        return unusedSouls;
    }
}
