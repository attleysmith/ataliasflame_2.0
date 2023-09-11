package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.HealingService;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.HEALING_WORD;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;

@Component
public class HealingWord extends SpellEffect {

    @Autowired
    private HealingService healingService;

    private final Spell spell = SPELLS.get(spellName);

    public HealingWord() {
        super(HEALING_WORD);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));

        healingService.heal(character, spell.getHealingEffect());
    }
}
