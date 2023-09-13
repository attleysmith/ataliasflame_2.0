package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.GuardianWarriorTemplate;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.GuardianWarriorTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SUMMON_GUARDIAN;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.NO_GUARDIAN_WARRIOR_APPEARED;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static java.util.Optional.empty;

@Component
public class SummonGuardian extends SpellEffect {

    private static final List<SelectionValue<Optional<GuardianWarriorTemplate>>> GUARDIAN_WARRIOR_SELECTOR = List.of(
            new SelectionValue<>(1, empty()),
            new SelectionValue<>(49, Optional.of(HUNTER)),
            new SelectionValue<>(35, Optional.of(MILITIA)),
            new SelectionValue<>(15, Optional.of(SWORDSMAN))
    );

    private final Spell spell = SPELLS.get(spellName);

    public SummonGuardian() {
        super(SUMMON_GUARDIAN);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        choose(GUARDIAN_WARRIOR_SELECTOR)
                .map(guardianSummoned -> guardianSummoned.instance(character))
                .ifPresentOrElse(companion -> {
                            character.getCompanions().add(companion);
                            storyLineLogger.event(summoning(companion));
                        },
                        () -> storyLineLogger.event(debugReport(NO_GUARDIAN_WARRIOR_APPEARED)));
    }
}
