package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.HealingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.HEALING_WAVE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class HealingWave extends HealingSpell {

    @Autowired
    private HealingService healingService;

    private static final int SPELL_COST = 15;

    // healing effect
    private static final int HEALING_EFFECT = 20;

    public HealingWave() {
        super(HEALING_WAVE);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        healingService.recoverHealth(character, HEALING_EFFECT);
        character.getCompanions().stream()
                .filter(Combatant::isAlive)
                .forEach(companion ->
                        healingService.healCompanion(companion, HEALING_EFFECT));
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }
}
