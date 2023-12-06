package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.RECHARGING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class Recharging extends HealingSpell {

    private static final String ARG_KEY_ENERGY = "energy";

    public Recharging() {
        super(RECHARGING);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        RechargingArgs rechargingArgs = new RechargingArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), rechargingArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        healingService.recoverHealth(character, rechargingArgs.energyPercentage);
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "Circulating the caster's inner energies to recover their body and health. " +
                "Healing effect: equal to the invested 'energy' percentage " +
                "Cost: calculated from the invested 'energy' percentage";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        RechargingArgs.validateArgs(args);
    }

    private static class RechargingArgs {

        public final int energyPercentage;

        public RechargingArgs(Map<String, String> args) {
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
