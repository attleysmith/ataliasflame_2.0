package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import com.asgames.ataliasflame.domain.services.magic.spells.EnergySpell;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.ENERGY_PROJECTION;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.PROJECTION_OF_ENERGY;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class ProjectionOfEnergy extends SummonSpell implements EnergySpell {

    public ProjectionOfEnergy() {
        super(PROJECTION_OF_ENERGY);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        EnergyArgs energyArgs = new EnergyArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), energyArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        Companion companion = Companion.builder()
                .reference(UUID.randomUUID().toString())
                .name("Energy of " + character.getName())
                .type(ENERGY_PROJECTION)
                .owner(character)
                .attack(percent(character.getAttack(), energyArgs.energyPercentage))
                .defense(percent(character.getDefense(), energyArgs.energyPercentage))
                .minDamage(percent(character.getMinDamage(), energyArgs.energyPercentage))
                .maxDamage(percent(character.getMaxDamage(), energyArgs.energyPercentage))
                .health(Energy.withTotal(percent(character.getHealth().totalValue(), energyArgs.energyPercentage)))
                .initiative(character.getInitiative())
                .build();
        character.getCompanions().add(companion);
        storyLineLogger.event(summoning(companion));
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "Projecting the caster's energies into a humanoid body to help in combat. " +
                "The invested 'energy' percentage determines the inherited attributes (attack, defense, damage, health)" +
                "Cost: calculated from the invested 'energy' percentage";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        EnergyArgs.validateArgs(args);
    }
}
