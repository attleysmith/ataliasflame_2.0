package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.DIVINE_PROTECTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.DUPLICATED_BLESSING;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;

@Component
public class DivineProtection extends SpellEffect {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    private final Spell spell = SPELLS.get(spellName);

    public DivineProtection() {
        super(DIVINE_PROTECTION);
    }

    @Override
    public void enforce(Character character, @Nullable Monster monster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));

        String blessing = spellName.name();
        if (character.getBlessings().contains(blessing)) {
            storyLineLogger.event(warningReport(DUPLICATED_BLESSING));
            return;
        }

        int originalHealth = character.getHealth().totalValue();
        int originalMagic = character.getMagic().totalValue();
        character.getBlessings().add(blessing);
        characterCalculationService.recalculateProperties(character);
        character.getHealth().uplift(originalHealth);
        character.getMagic().uplift(originalMagic);
        storyLineLogger.event(blessing(character, blessing));
    }
}
