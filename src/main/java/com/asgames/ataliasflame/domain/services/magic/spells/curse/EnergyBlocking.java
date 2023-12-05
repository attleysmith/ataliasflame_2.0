package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_BLOCKING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class EnergyBlocking extends CurseSpell {

    private static final String ARG_KEY_ENERGY = "energy";

    public EnergyBlocking() {
        super(ENERGY_BLOCKING);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        EnergyBlockingArgs energyBlockingArgs = new EnergyBlockingArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), energyBlockingArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        character.getLocation().getMonsters().forEach(monster ->
                blockEnergy(monster, energyBlockingArgs.energyPercentage));
    }

    private void blockEnergy(Monster monster, int percentage) {
        int oldAttack = monster.getAttack();
        int oldDefense = monster.getDefense();
        int oldMinDamage = monster.getMinDamage();
        int oldMaxDamage = monster.getMaxDamage();
        int oldHealth = monster.getHealth().totalValue();

        monster.setAttack(calculate(oldAttack, -1 * percentage));
        monster.setDefense(calculate(oldDefense, -1 * percentage));
        monster.setMinDamage(calculate(oldMinDamage, -1 * percentage));
        monster.setMaxDamage(calculate(oldMaxDamage, -1 * percentage));

        storyLineLogger.event(curseCasting(monster, this, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "Lowering the attributes of the enemies on the location by blocking their energies. " +
                "Effect: lowers attack, defense and damage by the invested 'energy' percentage" +
                "Cost: calculated from the invested 'energy' percentage";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        EnergyBlockingArgs.validateArgs(args);
    }

    private static class EnergyBlockingArgs {

        public final int energyPercentage;

        public EnergyBlockingArgs(Map<String, String> args) {
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
