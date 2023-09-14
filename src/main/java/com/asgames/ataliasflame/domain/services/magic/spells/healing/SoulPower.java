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
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_POWER;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;

@Component
public class SoulPower extends SpellEffect {

    @Autowired
    private HealingService healingService;

    private final Spell spell = SPELLS.get(spellName);

    public SoulPower() {
        super(SOUL_POWER);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        if (listUnusedSouls(character).isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
        } else {
            character.getMagic().use(spell.getCost());
            storyLineLogger.event(spellCasting(character, spell));

            healingService.heal(character, spell.getHealingEffect());
        }
    }
}