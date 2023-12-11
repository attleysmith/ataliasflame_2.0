package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.services.magic.spells.SoulSpell;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_POWER;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class SoulPower extends HealingSpell implements SoulSpell {

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
        SoulArgs soulArgs = new SoulArgs(args);
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        SoulChip soulChip = getSoulChip(character, soulArgs.soulChipReference);
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
        SoulArgs.validateArgs(args);
    }
}
