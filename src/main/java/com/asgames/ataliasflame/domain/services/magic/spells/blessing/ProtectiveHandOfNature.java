package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.NATURE;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.PROTECTIVE_HAND_OF_NATURE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class ProtectiveHandOfNature extends BlessingSpell {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    private static final int SPELL_COST = 3;

    public ProtectiveHandOfNature() {
        super(PROTECTIVE_HAND_OF_NATURE, NATURE);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        String blessing = name.name();
        if (!character.getBlessings().contains(blessing)) {
            int originalHealth = character.getHealth().totalValue();
            int originalMagic = character.getMagic().totalValue();
            character.getBlessings().add(blessing);
            characterCalculationService.recalculateProperties(character);
            character.getHealth().uplift(originalHealth);
            character.getMagic().uplift(originalMagic);
            storyLineLogger.event(blessing(character, blessing));
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }
}
