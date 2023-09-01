package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.CompanionType.ENERGY_PROJECTION;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.SUMMON;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.UNSUCCESSFUL_SUMMON;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Slf4j
@Service
public class SummonMagicService extends AbstractMagicService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    public void castSummoningSpell(Character character, Spell spell) {
        if (!spell.getType().equals(SUMMON)) {
            throw new IllegalArgumentException("Summoning spell expected!");
        }
        Optional<Companion> summoning = switch (spell.getGroup()) {
            case SOUL -> summonSoulChip(character);
            case NATURE -> summonAnimal(character);
            case GENERAL -> summonGuardianWarrior(character);
            case ENERGY -> summonEnergy(character);
            case DIVINE -> summonDivineGuardian(character);
            default -> throw new UnsupportedOperationException(spell.getGroup() + " summoning is not supported!");
        };
        if (summoning.isEmpty()) {
            storyLineLogger.event(warningReport(UNSUCCESSFUL_SUMMON));
            return;
        }
        Companion companion = summoning.get();
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));
        character.getCompanions().add(companion);
        storyLineLogger.event(summoning(companion));
    }

    private Optional<Companion> summonSoulChip(Character character) {
        List<SoulChip> unusedSouls = listUnusedSouls(character);
        if (unusedSouls.isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
            return Optional.empty();
        } else {
            return Optional.of(unusedSouls.get(0).summon());
        }
    }

    private Optional<Companion> summonAnimal(Character character) {
        return choose(ANIMAL_SELECTOR).map(animalSummoned ->
                animalSummoned.instance(character));
    }

    private Optional<Companion> summonGuardianWarrior(Character character) {
        return choose(GUARDIAN_WARRIOR_SELECTOR).map(guardianSummoned ->
                guardianSummoned.instance(character));
    }

    private Optional<Companion> summonDivineGuardian(Character character) {
        return choose(DIVINE_GUARDIAN_SELECTOR).map(guardianSummoned ->
                guardianSummoned.instance(character));
    }

    private Optional<Companion> summonEnergy(Character character) {
        Companion projection = Companion.builder()
                .reference(UUID.randomUUID().toString())
                .name("Energy of " + character.getName())
                .type(ENERGY_PROJECTION)
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
}
