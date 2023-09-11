package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.HealingService;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.HEALING_WAVE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;

@Component
public class HealingWave extends SpellEffect {

    @Autowired
    private HealingService healingService;

    private final Spell spell = SPELLS.get(spellName);

    public HealingWave() {
        super(HEALING_WAVE);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));

        healingService.heal(character, spell.getHealingEffect());
        character.getCompanions().stream()
                .filter(Combatant::isAlive)
                .forEach(companion ->
                        healingService.healCompanion(companion, spell.getHealingEffect()));
    }
}
