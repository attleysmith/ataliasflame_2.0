package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.HealingService;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.HEALING;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;

@Slf4j
@Service
public class HealingMagicService extends AbstractMagicService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private HealingService healingService;

    public void castHealingSpell(Character character, Spell spell) {
        if (!spell.getType().equals(HEALING)) {
            throw new IllegalArgumentException("Healing spell expected!");
        }
        if (spell.getGroup().equals(SOUL)
                && listUnusedSouls(character).isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
            return;
        }
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));
        healingService.heal(character, spell.getHealingEffect());
        if (spell.isArea()) {
            character.getCompanions().stream()
                    .filter(Combatant::isAlive)
                    .forEach(companion ->
                            healingService.healCompanion(companion, spell.getHealingEffect()));
        }
    }
}
