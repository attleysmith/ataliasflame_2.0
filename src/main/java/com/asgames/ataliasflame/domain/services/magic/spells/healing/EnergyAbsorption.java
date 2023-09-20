package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.HealingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.ENERGY;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_ABSORPTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.VitalityAbsorptionEvent.vitalityAbsorption;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class EnergyAbsorption extends HealingSpell {

    @Autowired
    private HealingService healingService;

    private static final int SPELL_COST = 5;

    // healing effect
    private static final int VITALITY_ABSORBING_EFFECT = 20;

    // area effect
    private static final int NEARBY_ABSORBING_CHANCE = 40;

    public EnergyAbsorption() {
        super(ENERGY_ABSORPTION, ENERGY);
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
}
