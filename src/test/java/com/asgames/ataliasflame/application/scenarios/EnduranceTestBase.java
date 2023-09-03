package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.*;
import com.asgames.ataliasflame.application.model.AttackContext;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.application.model.LocationContext;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public abstract class EnduranceTestBase {

    private static final int TOLERATED_INJURY_TO_HEAL = 80;
    private static final int TOLERATED_INJURY_TO_SLEEP = 40;
    private static final int MAX_NUMBER_OF_COMPANIONS = 5;
    private static final int MIN_HEALTH_TO_CURSE = 20;

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
    }


    protected void combatUntilNextLevel() {
        int actualLevel = character.getLevel();

        do {
            summon();
            enterLocation();
            putOnBlessings();
            castAttackMagic();
            castCurseMagic();
            closeCombat();
            if (character.isAlive()) {
                lootLocation();
                heal();
                finishEncounter();
                prepareToSummon();
                sleep();
            }
        } while (character.isAlive() && character.getLevel() == actualLevel);
    }

    private void summon() {
        if (character.getCompanions().size() >= MAX_NUMBER_OF_COMPANIONS) {
            return;
        }

        int previousNumberOfCompanions = -1;
        int actualNumberOfCompanions = character.getCompanions().size();
        while (previousNumberOfCompanions < actualNumberOfCompanions
                && actualNumberOfCompanions < MAX_NUMBER_OF_COMPANIONS) {
            previousNumberOfCompanions = actualNumberOfCompanions;

            usableSpells.get(SUMMON).stream()
                    .sorted(comparing(Spell::getCost).reversed())
                    .forEach(spell -> {
                        if (character.getMagic().has(spell.getCost())) {
                            character = characterMagicService.castSpell(character.getReference(), spell.getName());
                        }
                    });

            actualNumberOfCompanions = character.getCompanions().size();
        }
    }

    private void putOnBlessings() {
        int maxNumberOfBlessings =
                switch (location.getMonsters().size()) {
                    case 1, 2 -> 1;
                    case 3, 4 -> 2;
                    case 5, 6 -> 3;
                    case 7, 8 -> 4;
                    default -> 5;
                };

        int previousNumberOfBlessings = -1;
        int actualNumberOfBlessings = character.getBlessings().size();
        while (previousNumberOfBlessings < actualNumberOfBlessings
                && actualNumberOfBlessings < maxNumberOfBlessings) {
            previousNumberOfBlessings = actualNumberOfBlessings;

            usableSpells.get(BLESSING).stream()
                    .filter(spell -> character.getMagic().has(spell.getCost()))
                    .sorted(comparing(Spell::getCost).reversed())
                    .forEach(spell -> {
                        if (character.getMagic().has(spell.getCost())
                                && character.getBlessings().size() < maxNumberOfBlessings) {
                            character = characterMagicService.castSpell(character.getReference(), spell.getName());
                        }
                    });

            actualNumberOfBlessings = character.getBlessings().size();
        }
    }

    private void enterLocation() {
        location = locationAdventureService.buildLocation(character.getLevel());
    }

    private void castAttackMagic() {
        if (location.getMonsters().size() <= 1) {
            return;
        }
        location.getMonsters().stream()
                .filter(Combatant::isAlive)
                .sorted((monster1, monster2) -> {
                    Integer monster1Health = monster1.getHealth().actualValue();
                    Integer monster2Health = monster2.getHealth().actualValue();
                    return monster2Health.compareTo(monster1Health);
                })
                .forEach(this::castAttackMagic);
        location = locationAdventureService.getLocation(location.getReference());
    }

    private void castAttackMagic(Monster monster) {
        Monster targetMonster = monster;

        boolean hasAvailableSoul = !listUnusedSouls().isEmpty();
        boolean tryToAttack = true;
        while (tryToAttack && targetMonster.isAlive()) {
            Optional<Spell> attackSpell = usableSpells.get(ATTACK).stream()
                    .filter(spell -> character.getMagic().has(spell.getCost()))
                    .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                    .max(comparing(Spell::averageDamage));
            if (attackSpell.isPresent() && targetMonster.getHealth().actualValue() >= attackSpell.get().averageDamage()) {
                AttackContext attackContext = characterMagicService.castAttackSpell(character.getReference(), attackSpell.get().getName(), targetMonster.getReference());

                character = attackContext.getCharacter();
                targetMonster = attackContext.getMonster();
            } else {
                tryToAttack = false;
            }
        }
    }

    private void castCurseMagic() {
        location.getMonsters().stream()
                .filter(monster -> monster.getHealth().has(MIN_HEALTH_TO_CURSE))
                .sorted((monster1, monster2) -> {
                    Integer monster1Health = monster1.getHealth().actualValue();
                    Integer monster2Health = monster2.getHealth().actualValue();
                    return monster2Health.compareTo(monster1Health);
                })
                .forEach(this::castCurseMagic);
        location = locationAdventureService.getLocation(location.getReference());
    }

    private void castCurseMagic(Monster monster) {
        boolean hasAvailableSoul = !listUnusedSouls().isEmpty();
        usableSpells.get(CURSE).stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .max(comparing(Spell::getCost))
                .ifPresent(spell -> {
                    AttackContext attackContext = characterMagicService.castAttackSpell(character.getReference(), spell.getName(), monster.getReference());
                    character = attackContext.getCharacter();
                });
    }

    private void closeCombat() {
        LocationContext locationContext = characterLocationService.seizeLocation(character.getReference(), location.getReference());
        character = locationContext.getCharacter();
        location = locationContext.getLocation();
    }

    private void lootLocation() {
        location.getItems().forEach(item -> {
            LocationContext locationContext = characterLocationService.useItem(character.getReference(), location.getReference(), item.getReference());
            character = locationContext.getCharacter();
            location = locationContext.getLocation();
        });
    }

    private void heal() {
        boolean hasAvailableSoul = !listUnusedSouls().isEmpty();
        boolean readyToGo = character.getHealth().tolerateLoss(TOLERATED_INJURY_TO_HEAL);
        while (!readyToGo) {
            Optional<Spell> healingSpell = usableSpells.get(HEALING).stream()
                    .filter(spell -> character.getMagic().has(spell.getCost()))
                    .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                    .max(comparing(Spell::getHealingEffect));
            if (healingSpell.isPresent()) {
                character = characterMagicService.castSpell(character.getReference(), healingSpell.get().getName());
                readyToGo = character.getHealth().tolerateLoss(TOLERATED_INJURY_TO_HEAL);
            } else {
                readyToGo = true;
            }
        }
    }

    private void finishEncounter() {
        character = characterMagicService.removeBlessingMagic(character.getReference());
    }

    private void prepareToSummon() {
        if (!character.getCompanions().isEmpty()) {
            return;
        }
        List<Spell> summoningSpells = usableSpells.get(SUMMON);
        boolean hasSpell = !summoningSpells.isEmpty();
        boolean lowMagic = summoningSpells.stream().noneMatch(spell -> character.getMagic().has(spell.getCost()));
        if (hasSpell && lowMagic) {
            character = characterAdventureService.sleep(character.getReference());
        }
    }

    private void sleep() {
        if (character.getHealth().tolerateLoss(TOLERATED_INJURY_TO_SLEEP)) {
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
        List<String> companionReferences = character.getCompanions().stream().map(Companion::getReference).collect(toList());
        for (SoulChip soulChip : character.getSoulChips()) {
            if (companionReferences.contains(soulChip.getReference())
                    || character.getBlessings().contains(soulChip.getShape().name())) {
                unusedSouls.remove(soulChip);
            }
        }
        return unusedSouls;
    }
}
