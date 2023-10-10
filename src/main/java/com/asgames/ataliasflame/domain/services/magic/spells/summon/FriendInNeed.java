package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.DivineGuardianTemplate;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.DivineGuardianTemplate.COMMANDER;
import static com.asgames.ataliasflame.domain.model.enums.DivineGuardianTemplate.KNIGHT;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.FRIEND_IN_NEED;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.NO_DIVINE_GUARDIAN_APPEARED;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class FriendInNeed extends SummonSpell {

    private static final int SPELL_COST = 15;

    private static final List<SelectionValue<Optional<DivineGuardianTemplate>>> DIVINE_GUARDIAN_SELECTOR = List.of(
            new SelectionValue<>(1, Optional.empty()),
            new SelectionValue<>(96, Optional.of(KNIGHT)),
            new SelectionValue<>(3, Optional.of(COMMANDER))
    );
    private static final int CHANCE_OF_MORE = 1;

    public FriendInNeed() {
        super(FRIEND_IN_NEED);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        do {
            choose(DIVINE_GUARDIAN_SELECTOR)
                    .map(guardianSummoned -> guardianSummoned.instance(character))
                    .ifPresentOrElse(companion -> {
                                character.getCompanions().add(companion);
                                storyLineLogger.event(summoning(companion));
                            },
                            () -> storyLineLogger.event(debugReport(NO_DIVINE_GUARDIAN_APPEARED)));
        } while (successX(CHANCE_OF_MORE));
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }
}
