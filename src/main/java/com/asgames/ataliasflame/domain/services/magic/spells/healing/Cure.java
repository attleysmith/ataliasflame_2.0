package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.HealingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.CURE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class Cure extends HealingSpell {

    @Autowired
    private HealingService healingService;

    private static final int SPELL_COST = 5;

    // healing effect
    private static final int HEALING_EFFECT = 8;

    public Cure() {
        super(CURE);
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
        return "Curing wounds and illnesses by magical energies. " +
                "Healing effect: " + HEALING_EFFECT + "% " +
                "Cost: " + SPELL_COST + " MP";
    }
}
