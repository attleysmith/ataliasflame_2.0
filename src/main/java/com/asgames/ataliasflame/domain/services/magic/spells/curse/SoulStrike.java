package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_STRIKE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class SoulStrike extends CurseSpell {

    private static final String ARG_KEY_SOUL_CHIP = "soulChip";

    private static final int SPELL_COST = 10;

    private static final int FATIGUE_EFFECT = 10;

    // debuff effect
    private static final int ATTACK_MULTIPLIER = -10;
    private static final int DEFENSE_MULTIPLIER = -10;
    private static final int DAMAGE_MULTIPLIER = -10;
    private static final int HEALTH_MULTIPLIER = -2;

    public SoulStrike() {
        super(SOUL_STRIKE);
    }

    @Override
    public void enforce(Character character, Monster targetMonster, Map<String, String> args) {
        SoulStrikeArgs soulStrikeArgs = new SoulStrikeArgs(args);
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        SoulChip soulChip = getSoulChip(character, soulStrikeArgs.soulChipReference);
        soulChip.getHealth().trauma(FATIGUE_EFFECT);
        storyLineLogger.event(fatigue(soulChip, FATIGUE_EFFECT));

        if (targetMonster.isAlive()) {
            int oldAttack = targetMonster.getAttack();
            int attackMultiplier = ATTACK_MULTIPLIER + percent(ATTACK_MULTIPLIER, soulChip.getEffectiveness());
            targetMonster.setAttack(calculate(oldAttack, attackMultiplier));

            int oldDefense = targetMonster.getDefense();
            int defenseMultiplier = DEFENSE_MULTIPLIER + percent(DEFENSE_MULTIPLIER, soulChip.getEffectiveness());
            targetMonster.setDefense(calculate(oldDefense, defenseMultiplier));

            int oldMinDamage = targetMonster.getMinDamage();
            int oldMaxDamage = targetMonster.getMaxDamage();
            int damageMultiplier = DAMAGE_MULTIPLIER + percent(DAMAGE_MULTIPLIER, soulChip.getEffectiveness());
            targetMonster.setMinDamage(calculate(oldMinDamage, damageMultiplier));
            targetMonster.setMaxDamage(calculate(oldMaxDamage, damageMultiplier));

            int oldHealth = targetMonster.getHealth().totalValue();
            int healthMultiplier = HEALTH_MULTIPLIER + percent(HEALTH_MULTIPLIER, soulChip.getEffectiveness());
            targetMonster.getHealth().set(calculate(oldHealth, healthMultiplier));

            storyLineLogger.event(curseCasting(targetMonster, this, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Projection of the powers of a soul chip towards an enemy to cause body shock and so lowering their attributes. " +
                "Effect: [" + effectDetailsOf(ATTACK_MULTIPLIER, DEFENSE_MULTIPLIER, DAMAGE_MULTIPLIER, HEALTH_MULTIPLIER) + "] " +
                "Effect of the strike is increased by the effectiveness of the projected soul chip. " +
                "Fatigue effect of the soul magic is " + FATIGUE_EFFECT + "%. " +
                "Cost: " + SPELL_COST + " MP";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        SoulStrikeArgs.validateArgs(args);
    }

    private static class SoulStrikeArgs {

        public final String soulChipReference;

        public SoulStrikeArgs(Map<String, String> args) {
            validateArgs(args);
            soulChipReference = args.get(ARG_KEY_SOUL_CHIP);
        }

        public static void validateArgs(Map<String, String> args) {
            if (!args.containsKey(ARG_KEY_SOUL_CHIP)) {
                throw new IllegalArgumentException("Missing argument: " + ARG_KEY_SOUL_CHIP);
            }
            if (args.size() != 1) {
                throw new IllegalArgumentException("Incorrect number of arguments.");
            }
        }
    }
}
