package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.RECHARGING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class Recharging extends HealingSpell {

    private static final int SPELL_COST = 8;

    // healing effect
    private static final int HEALING_EFFECT = 20;

    public Recharging() {
        super(RECHARGING);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        healingService.recoverHealth(character, HEALING_EFFECT);
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Circulating the caster's inner energies to recover their body and health. " +
                "Healing effect: " + HEALING_EFFECT + "% " +
                "Cost: " + SPELL_COST + " MP";
    }
}
