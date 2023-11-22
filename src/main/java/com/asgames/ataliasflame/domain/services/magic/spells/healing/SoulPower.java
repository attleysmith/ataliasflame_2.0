package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_POWER;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class SoulPower extends HealingSpell {

    private static final String ARG_KEY_SOUL_CHIP = "soulChip";

    private static final int SPELL_COST = 10;

    private static final int FATIGUE_EFFECT = 20;

    // healing effect
    private static final int HEALING_EFFECT = 40;
    private static final int BONUS_EFFECT = 20;

    public SoulPower() {
        super(SOUL_POWER);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        SoulPowerArgs soulPowerArgs = new SoulPowerArgs(args);
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        SoulChip soulChip = getSoulChip(character, soulPowerArgs.soulChipReference);
        soulChip.getHealth().trauma(FATIGUE_EFFECT);
        storyLineLogger.event(fatigue(soulChip, FATIGUE_EFFECT));

        int bonusEffect = percent(BONUS_EFFECT, soulChip.getEffectiveness());
        healingService.recoverHealth(character, HEALING_EFFECT + bonusEffect);
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Absorbing the energies of a soul chip to recover the caster's body and health. " +
                "Healing effect: " + HEALING_EFFECT + "% " +
                "There can be an utmost " + BONUS_EFFECT + "% bonus effect of healing depending on the soul chip's effectiveness. " +
                "Fatigue effect of the soul magic is " + FATIGUE_EFFECT + "%. " +
                "Cost: " + SPELL_COST + " MP";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        SoulPowerArgs.validateArgs(args);
    }

    private static class SoulPowerArgs {

        public final String soulChipReference;

        public SoulPowerArgs(Map<String, String> args) {
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
