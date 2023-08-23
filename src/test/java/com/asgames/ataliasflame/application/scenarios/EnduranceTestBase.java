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
import java.util.concurrent.atomic.AtomicBoolean;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public abstract class EnduranceTestBase {

    private static final int TOLERATED_INJURY_TO_HEAL = 80;
    private static final int TOLERATED_INJURY_TO_SLEEP = 40;
    private static final int MAX_NUMBER_OF_COMPANIONS = 5;

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
            putOnBlessing();
            enterLocation();
            castAttackMagic();
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

    private void putOnBlessing() {
        boolean hasAvailableSoul = !listUnusedSouls().isEmpty();
        usableSpells.get(BLESSING).stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .max(comparing(Spell::getCost))
                .ifPresent(spell ->
                        character = characterMagicService.castSpell(character.getReference(), spell.getName()));
    }

    private void enterLocation() {
        location = locationAdventureService.buildLocation(character.getLevel());
    }

    private void castAttackMagic() {
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
        AtomicBoolean tryToAttack = new AtomicBoolean(true);
        while (tryToAttack.get() && targetMonster.isAlive()) {
            Optional<Spell> attackSpell = usableSpells.get(ATTACK).stream()
                    .filter(spell -> character.getMagic().has(spell.getCost()))
                    .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                    .max(comparing(Spell::getCost));
            if (attackSpell.isPresent()) {
                AttackContext attackContext = characterMagicService.castAttackSpell(character.getReference(), attackSpell.get().getName(), targetMonster.getReference());

                character = attackContext.getCharacter();
                targetMonster = attackContext.getMonster();
            } else {
                tryToAttack.set(false);
            }
        }
    }

    private void closeCombat() {
        LocationContext locationContext = characterLocationService.seizeLocation(character.getReference(), location.getReference());
        character = locationContext.getCharacter();
        location = locationContext.getLocation();
    }

    private void lootLocation() {
        LocationContext locationContext = characterLocationService.lootLocation(character.getReference(), location.getReference());
        character = locationContext.getCharacter();
        location = locationContext.getLocation();
    }

    private void heal() {
        if (character.getHealth().tolerateLoss(TOLERATED_INJURY_TO_HEAL)) {
            return;
        }
        boolean hasAvailableSoul = !listUnusedSouls().isEmpty();
        usableSpells.get(HEALING).stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> hasAvailableSoul || !spell.getGroup().equals(SOUL))
                .max(comparing(Spell::getHealingEffect))
                .ifPresent(spell ->
                        character = characterMagicService.castSpell(character.getReference(), spell.getName()));
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
            if (companionReferences.contains(soulChip.getReference())) {
                unusedSouls.remove(soulChip);
            }
        }
        return unusedSouls;
    }
}
