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

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.SUMMON;
import static java.util.stream.Collectors.toList;

public abstract class EnduranceTestBase {

    private static final int TOLERATED_INJURY = 40;

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

        do {
            Location location = locationAdventureService.buildLocation();
            character = characterMagicService.castSummoningMagic(character.getReference());
            character = characterMagicService.castBlessingMagic(character.getReference());
            character = characterMagicService.castAttackMagic(character.getReference(), location.getReference());
            character = characterLocationService.seizeLocation(character.getReference(), location.getReference());
            if (character.isAlive()) {
                character = characterLocationService.lootLocation(character.getReference(), location.getReference());
                character = characterMagicService.castHealingMagic(character.getReference());
                character = characterMagicService.removeBlessingMagic(character.getReference());

                character = sleeping();
                character = prepareToSummon();
            }
        } while (character.isAlive() && character.getLevel() == actualLevel);

        return character;
    }

    private Character sleeping() {
        if (character.getHealth().tolerateLoss(TOLERATED_INJURY)) {
            return character;
        }
        return characterAdventureService.sleep(character.getReference());
    }

    private Character prepareToSummon() {
        if (character.getCompanions().isEmpty()
                && !summoningSpells().isEmpty()
                && usableSummoningSpells().isEmpty()) {
            return characterAdventureService.sleep(character.getReference());
        }
        return character;
    }

    private List<Spell> summoningSpells() {
        return SPELLS.values().stream()
                .filter(spell -> spell.getType().equals(SUMMON))
                .filter(spell -> !CASTE_SPELL_PROHIBITION.get(character.getCaste())
                        .contains(spell.getName()))
                .filter(spell -> !RACE_SPELL_PROHIBITION.get(character.getRace())
                        .contains(spell.getName()))
                .collect(toList());
    }

    private List<Spell> usableSummoningSpells() {
        return summoningSpells().stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .collect(toList());
    }
}
