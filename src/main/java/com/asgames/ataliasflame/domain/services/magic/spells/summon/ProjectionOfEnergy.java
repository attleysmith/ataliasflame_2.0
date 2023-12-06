package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.vos.Energy;
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
public class ProjectionOfEnergy extends SummonSpell {

    private static final String ARG_KEY_ENERGY = "energy";

    public ProjectionOfEnergy() {
        super(PROJECTION_OF_ENERGY);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        ProjectionOfEnergyArgs projectionOfEnergyArgs = new ProjectionOfEnergyArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), projectionOfEnergyArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        Companion companion = Companion.builder()
                .reference(UUID.randomUUID().toString())
                .name("Energy of " + character.getName())
                .type(ENERGY_PROJECTION)
                .owner(character)
                .attack(percent(character.getAttack(), projectionOfEnergyArgs.energyPercentage))
                .defense(percent(character.getDefense(), projectionOfEnergyArgs.energyPercentage))
                .minDamage(percent(character.getMinDamage(), projectionOfEnergyArgs.energyPercentage))
                .maxDamage(percent(character.getMaxDamage(), projectionOfEnergyArgs.energyPercentage))
                .health(Energy.withTotal(percent(character.getHealth().totalValue(), projectionOfEnergyArgs.energyPercentage)))
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
        ProjectionOfEnergyArgs.validateArgs(args);
    }

    private static class ProjectionOfEnergyArgs {

        public final int energyPercentage;

        public ProjectionOfEnergyArgs(Map<String, String> args) {
            validateArgs(args);
            energyPercentage = Integer.parseInt(args.get(ARG_KEY_ENERGY));
        }

        public static void validateArgs(Map<String, String> args) {
            if (!args.containsKey(ARG_KEY_ENERGY)) {
                throw new IllegalArgumentException("Missing argument: " + ARG_KEY_ENERGY);
            }
            if (args.size() != 1) {
                throw new IllegalArgumentException("Incorrect number of arguments.");
            }
            try {
                int percentage = Integer.parseInt(args.get(ARG_KEY_ENERGY));
                if (percentage < 1 || 100 < percentage) {
                    throw new IllegalArgumentException("Argument [" + ARG_KEY_ENERGY + "] must be between 1 and 100.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Argument [" + ARG_KEY_ENERGY + "] must be a number!");
            }
        }
    }
}
