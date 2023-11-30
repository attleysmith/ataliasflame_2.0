package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.BALL_OF_ENERGY;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static java.lang.Math.floor;

@Component
public class BallOfEnergy extends AttackSpell {

    private static final String ARG_KEY_ENERGY = "energy";

    private static final int BALL_COST = 3;

    // damage effect
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 5;

    public BallOfEnergy() {
        super(BALL_OF_ENERGY);
    }

    @Override
    public void enforce(Character character, Monster targetMonster, Map<String, String> args) {
        BallOfEnergyArgs ballOfEnergyArgs = new BallOfEnergyArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), ballOfEnergyArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        int numberOfBalls = (int) floor((float) investedEnergy / BALL_COST);
        while (numberOfBalls > 0 && targetMonster.isAlive()) {
            numberOfBalls--;
            doDamage(character, targetMonster);
        }

        List<Monster> monsters = targetMonster.getLocation().getMonsters();
        Optional<Monster> monster = searchTarget(monsters);
        while (numberOfBalls > 0 && monster.isPresent()) {
            numberOfBalls--;
            doDamage(character, monster.get());
            monster = searchTarget(monsters);
        }
    }

    private Optional<Monster> searchTarget(List<Monster> monsters) {
        return monsters.stream()
                .filter(Combatant::isAlive)
                .findAny();
    }

    private void doDamage(Character character, Monster monster) {
        int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
        damageService.doDamage(character, monster, damage, DIRECT);
    }

    @Override
    public int getCost() {
        return BALL_COST;
    }

    @Override
    public String getDetails() {
        return "Shooting a single ball of energy towards an enemy to cause damage (" + MIN_DAMAGE + "-" + MAX_DAMAGE + "). " +
                "Balls can be shot repeatedly depending on the invested 'energy' percentage. " +
                "After the target is killed then other targets can be found on the location while there are any remaining balls created. " +
                "Cost / ball: " + BALL_COST + " MP " +
                "Total cost: calculated from the invested 'energy' percentage";
    }

    @Override
    public int getMinDamage() {
        return MIN_DAMAGE;
    }

    @Override
    public int getMaxDamage() {
        return MAX_DAMAGE;
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        BallOfEnergyArgs.validateArgs(args);
    }

    private static class BallOfEnergyArgs {

        public final int energyPercentage;

        public BallOfEnergyArgs(Map<String, String> args) {
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
