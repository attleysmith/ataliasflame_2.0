package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.HealingService;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_ABSORPTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class EnergyAbsorption extends SpellEffect {

    @Autowired
    private HealingService healingService;

    private static final int HEALTH_ABSORBING_EFFECT = 20;
    private static final int NEARBY_ABSORBING_CHANCE = 40;

    private final Spell spell = SPELLS.get(spellName);

    public EnergyAbsorption() {
        super(ENERGY_ABSORPTION);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        int absorbedHealth = targetMonster.getLocation().getMonsters().stream()
                .filter(Combatant::isDead)
                .filter(monster ->
                        monster.getReference().equals(targetMonster.getReference())
                                || successX(NEARBY_ABSORBING_CHANCE)
                )
                .map(monster -> percent(monster.getHealth().totalValue(), HEALTH_ABSORBING_EFFECT))
                .reduce(0, Integer::sum);
        healingService.replenishHealth(character, absorbedHealth);
    }
}
