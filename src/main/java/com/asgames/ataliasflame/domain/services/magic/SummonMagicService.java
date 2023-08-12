package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.SUMMON;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static java.util.Comparator.comparing;

@Slf4j
@Service
public class SummonMagicService extends AbstractMagicService {

    public void castSummoningMagic(Character character) {
        int previousNumberOfCompanions = -1;
        int actualNumberOfCompanions = character.getCompanions().size();
        while (previousNumberOfCompanions < actualNumberOfCompanions) {
            previousNumberOfCompanions = actualNumberOfCompanions;

            usableSpellsOfType(character, SUMMON).stream()
                    .sorted(comparing(Spell::getCost).reversed())
                    .forEach(spell -> {
                        if (character.getMagic().has(spell.getCost())) {
                            castSummoningSpell(character, spell);
                        }
                    });

            actualNumberOfCompanions = character.getCompanions().size();
        }
    }

    private void castSummoningSpell(Character character, Spell spell) {
        Optional<Companion> summoning;
        switch (spell.getGroup()) {
            case SOUL:
                summoning = summonSoulChip(character);
                break;
            case NATURE:
                summoning = summonAnimal(character);
                break;
            case GENERAL:
                summoning = summonGuardianWarrior(character);
                break;
            case ENERGY:
                summoning = summonEnergy(character);
                break;
            case DIVINE:
                summoning = summonDivineGuardian(character);
                break;
            default:
                throw new UnsupportedOperationException(spell.getGroup() + " summoning is not supported!");
        }
        if (summoning.isEmpty()) {
            log.warn("Summoning was unsuccessful!");
            return;
        }
        character.getMagic().use(spell.getCost());
        character.getCompanions().add(summoning.get());
        log.info(summoning.get().getName() + " summoned as companion.");
    }

    private Optional<Companion> summonSoulChip(Character character) {
        List<SoulChip> unusedSouls = listUnusedSouls(character);
        if (unusedSouls.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(unusedSouls.get(0).summon());
        }
    }

    private Optional<Companion> summonAnimal(Character character) {
        int numberOfCompanions = character.getCompanions().size();
        if (numberOfCompanions < 5) {
            return choose(ANIMAL_SELECTOR).map(animalSummoned ->
                    animalSummoned.instance(character, UUID.randomUUID().toString()));
        }
        return Optional.empty();
    }

    private Optional<Companion> summonGuardianWarrior(Character character) {
        int numberOfCompanions = character.getCompanions().size();
        if (numberOfCompanions < 2) {
            return choose(GUARDIAN_WARRIOR_SELECTOR).map(guardianSummoned ->
                    guardianSummoned.instance(character, UUID.randomUUID().toString()));
        }
        return Optional.empty();
    }

    private Optional<Companion> summonEnergy(Character character) {
        int numberOfCompanions = character.getCompanions().size();
        if (numberOfCompanions < 1) {
            Companion projection = Companion.builder()
                    .name("Energy of " + character.getName() + "-" + UUID.randomUUID())
                    .owner(character)
                    .attack(percent(character.getAttack(), ENERGY_PROJECTION_PERCENT))
                    .defense(percent(character.getDefense(), ENERGY_PROJECTION_PERCENT))
                    .minDamage(percent(character.getMinDamage(), ENERGY_PROJECTION_PERCENT))
                    .maxDamage(percent(character.getMaxDamage(), ENERGY_PROJECTION_PERCENT))
                    .health(Energy.withTotal(percent(character.getHealth().totalValue(), ENERGY_PROJECTION_PERCENT)))
                    .initiative(character.getInitiative())
                    .build();
            return Optional.of(projection);
        }
        return Optional.empty();
    }

    private Optional<Companion> summonDivineGuardian(Character character) {
        int numberOfCompanions = character.getCompanions().size();
        if (numberOfCompanions < 1) {
            return choose(DIVINE_GUARDIAN_SELECTOR).map(guardianSummoned ->
                    guardianSummoned.instance(character, UUID.randomUUID().toString()));
        }
        return Optional.empty();
    }
}
