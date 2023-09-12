package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_CONNECTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;

@Component
public class SoulConnection extends SpellEffect {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    private final Spell spell = SPELLS.get(spellName);

    public SoulConnection() {
        super(SOUL_CONNECTION);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        List<SoulChip> unusedSouls = listUnusedSouls(character);
        if (unusedSouls.isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
        } else {
            character.getMagic().use(spell.getCost());
            storyLineLogger.event(spellCasted(character, spell));

            String blessing = unusedSouls.get(0).getShape().name();
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
    }
}
