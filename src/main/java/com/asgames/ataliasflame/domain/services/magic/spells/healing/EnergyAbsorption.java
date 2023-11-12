package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_ABSORPTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.VitalityAbsorptionEvent.vitalityAbsorption;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class EnergyAbsorption extends HealingSpell {

    private static final int SPELL_COST = 5;

    // healing effect
    private static final int VITALITY_ABSORBING_EFFECT = 20;

    // area effect
    private static final int NEARBY_ABSORBING_CHANCE = 40;

    public EnergyAbsorption() {
        super(ENERGY_ABSORPTION);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        targetMonster.getLocation().getMonsters().stream()
                .filter(Combatant::isDead)
                .filter(monster -> monster.getVitality().hasOne())
                .filter(monster ->
                        monster.getReference().equals(targetMonster.getReference())
                                || successX(NEARBY_ABSORBING_CHANCE)
                )
                .forEach(monster -> {
                    int absorbedVitality = percent(monster.getVitality().totalValue(), VITALITY_ABSORBING_EFFECT);
                    int excess = monster.getVitality().penetrate(absorbedVitality);
                    int effectiveAbsorption = absorbedVitality - excess;
                    storyLineLogger.event(vitalityAbsorption(monster, effectiveAbsorption));

                    healingService.replenishHealth(character, effectiveAbsorption);
                });
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Absorbing the energies of a dead enemy to recover the caster's body and health. " +
                "Vitality absorbing effect: " + VITALITY_ABSORBING_EFFECT + "% " +
                "There is a " + NEARBY_ABSORBING_CHANCE + "% chance to absorb the energies of nearby enemy corpses as well. " +
                "Cost: " + SPELL_COST + " MP";
    }
}
