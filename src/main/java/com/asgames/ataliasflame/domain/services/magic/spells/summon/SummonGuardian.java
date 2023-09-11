package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.GUARDIAN_WARRIOR_SELECTOR;
import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SUMMON_GUARDIAN;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.NO_GUARDIAN_WARRIOR_APPEARED;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Component
public class SummonGuardian extends SpellEffect {

    private final Spell spell = SPELLS.get(spellName);

    public SummonGuardian() {
        super(SUMMON_GUARDIAN);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));

        choose(GUARDIAN_WARRIOR_SELECTOR)
                .map(guardianSummoned -> guardianSummoned.instance(character))
                .ifPresentOrElse(companion -> {
                            character.getCompanions().add(companion);
                            storyLineLogger.event(summoning(companion));
                        },
                        () -> storyLineLogger.event(debugReport(NO_GUARDIAN_WARRIOR_APPEARED)));
    }
}
