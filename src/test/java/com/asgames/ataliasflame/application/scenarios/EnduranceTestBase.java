package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.application.*;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.*;
import static java.util.Comparator.comparing;

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

    @AfterEach
    void tearDown() {
        characterMaintenanceService.removeCharacter(character.getReference());
    }

    protected Character addAttributePoints(Attribute attribute, int points) {
        return characterMaintenanceService.addAttributePoints(character.getReference(), attribute, points);
    }

    protected Character upgradeCaste(Caste newCaste) {
        while (!character.getHealth().isFull()) {
            character = characterAdventureService.sleep(character.getReference());
        }

        return characterMaintenanceService.upgradeCaste(character.getReference(), newCaste);
    }

    protected Character combatUntilNextLevel() {
        int actualLevel = character.getLevel();
        List<Spell> summoningSpells = characterMagicService.listCharacterSpells(character.getReference(), SUMMON);
        List<Spell> blessingSpells = characterMagicService.listCharacterSpells(character.getReference(), BLESSING);
        List<Spell> healingSpells = characterMagicService.listCharacterSpells(character.getReference(), HEALING);

        do {
            summon(summoningSpells);
            putOnBlessing(blessingSpells);
            enterLocation();
            castAttackMagic();
            closeCombat();
            if (character.isAlive()) {
                lootLocation();
                heal(healingSpells);
                finishEncounter();
                prepareToSummon(summoningSpells);
                sleep();
            }
        } while (character.isAlive() && character.getLevel() == actualLevel);

        return character;
    }

    private void summon(List<Spell> summoningSpells) {
        if (character.getCompanions().size() >= MAX_NUMBER_OF_COMPANIONS) {
            return;
        }

        int previousNumberOfCompanions = -1;
        int actualNumberOfCompanions = character.getCompanions().size();
        while (previousNumberOfCompanions < actualNumberOfCompanions
                && actualNumberOfCompanions < MAX_NUMBER_OF_COMPANIONS) {
            previousNumberOfCompanions = actualNumberOfCompanions;

            summoningSpells.stream()
                    .sorted(comparing(Spell::getCost).reversed())
                    .forEach(spell -> {
                        if (character.getMagic().has(spell.getCost())) {
                            character = characterMagicService.castSpell(character.getReference(), spell.getName());
                        }
                    });

            actualNumberOfCompanions = character.getCompanions().size();
        }
    }

    private void putOnBlessing(List<Spell> blessingSpells) {
        blessingSpells.stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .max(comparing(Spell::getCost))
                .ifPresent(spell ->
                        character = characterMagicService.castSpell(character.getReference(), spell.getName()));
    }

    private void enterLocation() {
        location = locationAdventureService.buildLocation(character.getLevel());
    }

    private void castAttackMagic() {
        character = characterMagicService.castAttackMagic(character.getReference(), location.getReference());
    }

    private void closeCombat() {
        character = characterLocationService.seizeLocation(character.getReference(), location.getReference());
    }

    private void lootLocation() {
        character = characterLocationService.lootLocation(character.getReference(), location.getReference());
    }

    private void heal(List<Spell> healingSpells) {
        if (character.getHealth().tolerateLoss(TOLERATED_INJURY_TO_HEAL)) {
            return;
        }
        healingSpells.stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .max(comparing(Spell::getHealingEffect))
                .ifPresent(spell ->
                        character = characterMagicService.castSpell(character.getReference(), spell.getName()));
    }

    private void finishEncounter() {
        character = characterMagicService.removeBlessingMagic(character.getReference());
    }

    private void prepareToSummon(List<Spell> summoningSpells) {
        if (!character.getCompanions().isEmpty()) {
            return;
        }

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
}
