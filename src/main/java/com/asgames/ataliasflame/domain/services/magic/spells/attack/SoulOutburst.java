package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_OUTBURST;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.NOVA;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class SoulOutburst extends AttackSpell {

    private static final String ARG_KEY_SOUL_CHIP = "soulChip";

    private static final int SPELL_COST = 16;

    private static final int FATIGUE_EFFECT = 10;

    // damage effect
    private static final int MIN_DAMAGE = 15;
    private static final int MAX_DAMAGE = 30;
    private static final int BONUS_DAMAGE = 10;

    // area effect
    private static final int NOVA_EFFECT_CHANCE = 5;

    public SoulOutburst() {
        super(SOUL_OUTBURST);
    }

    @Override
    public void enforce(Character character, Monster targetMonster, Map<String, String> args) {
        SoulOutburstArgs soulOutburstArgs = new SoulOutburstArgs(args);
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        SoulChip soulChip = getSoulChip(character, soulOutburstArgs.soulChipReference);
        soulChip.getHealth().trauma(FATIGUE_EFFECT);
        storyLineLogger.event(fatigue(soulChip, FATIGUE_EFFECT));

        int bonusDamage = percent(BONUS_DAMAGE, soulChip.getEffectiveness());
        if (successX(NOVA_EFFECT_CHANCE)) {
            targetMonster.getLocation().getMonsters().stream()
                    .filter(Combatant::isAlive)
                    .forEach(monster -> {
                        int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE) + bonusDamage;
                        damageService.doDamage(character, monster, damage, NOVA);
                    });
        } else if (targetMonster.isAlive()) {
            int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE) + bonusDamage;
            damageService.doDamage(character, targetMonster, damage, DIRECT);
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Summoning a wave of a soul chip targeting a single enemy to cause damage (" + MIN_DAMAGE + "-" + MAX_DAMAGE + "). " +
                "There is a " + NOVA_EFFECT_CHANCE + "% chance that the summoned wave explodes like a nova and every single enemies get damage not only the targeted one. " +
                "There can be an utmost " + BONUS_DAMAGE + " bonus damage depending on the soul chip's effectiveness. " +
                "Fatigue effect of the soul magic is " + FATIGUE_EFFECT + "%. " +
                "Cost: " + SPELL_COST + " MP";
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
        SoulOutburstArgs.validateArgs(args);
    }

    private static class SoulOutburstArgs {

        public final String soulChipReference;

        public SoulOutburstArgs(Map<String, String> args) {
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
