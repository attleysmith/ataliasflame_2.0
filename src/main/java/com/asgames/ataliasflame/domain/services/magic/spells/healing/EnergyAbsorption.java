package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.magic.spells.EnergySpell;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_ABSORPTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.VitalityAbsorptionEvent.vitalityAbsorption;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class EnergyAbsorption extends HealingSpell implements EnergySpell {

    public EnergyAbsorption() {
        super(ENERGY_ABSORPTION);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        EnergyArgs energyArgs = new EnergyArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), energyArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        character.getLocation().getMonsters().stream()
                .filter(Combatant::isDead)
                .filter(monster -> monster.getVitality().hasOne())
                .forEach(monster -> {
                    absorbHealth(character, monster, energyArgs.energyPercentage);
                    absorbMagic(character, monster);
                });
    }

    private void absorbHealth(Character character, Monster monster, int percentage) {
        int absorbedVitality = percent(monster.getVitality().totalValue(), percentage);
        int excess = monster.getVitality().penetrate(absorbedVitality);
        int effectiveAbsorption = absorbedVitality - excess;
        storyLineLogger.event(vitalityAbsorption(monster, effectiveAbsorption));

        healingService.replenishHealth(character, effectiveAbsorption);
    }

    private void absorbMagic(Character character, Monster monster) {
        int absorbedVitality = monster.getVitality().actualValue();
        int excess = monster.getVitality().penetrate(absorbedVitality);
        int effectiveAbsorption = absorbedVitality - excess;
        storyLineLogger.event(vitalityAbsorption(monster, effectiveAbsorption));

        magicService.replenishMagic(character, effectiveAbsorption);
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "Absorbing the energies of the dead enemies to recover the caster's body and health. " +
                "Healing absorption effect: equal to the invested 'energy' percentage " +
                "Magic absorption effect: all the remaining vitality after healing " +
                "Cost: calculated from the invested 'energy' percentage";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        EnergyArgs.validateArgs(args);
    }
}
