package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.spells.EnergySpell;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.RECHARGING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class Recharging extends HealingSpell implements EnergySpell {

    public Recharging() {
        super(RECHARGING);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        EnergyArgs energyArgs = new EnergyArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), energyArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        healingService.recoverHealth(character, energyArgs.energyPercentage);
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
        EnergyArgs.validateArgs(args);
    }
}
