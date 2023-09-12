package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.dtos.CompanionTemplate;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.DivineGuardianCode.COMMANDER;
import static com.asgames.ataliasflame.domain.model.enums.DivineGuardianCode.KNIGHT;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.FRIEND_IN_NEED;
import static com.asgames.ataliasflame.domain.services.magic.spells.summon.Summonings.DIVINE_GUARDIANS;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.NO_DIVINE_GUARDIAN_APPEARED;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Component
public class FriendInNeed extends SpellEffect {

    public static final List<SelectionValue<Optional<CompanionTemplate>>> DIVINE_GUARDIAN_SELECTOR = List.of(
            new SelectionValue<>(1, Optional.empty()),
            new SelectionValue<>(96, Optional.of(DIVINE_GUARDIANS.get(KNIGHT))),
            new SelectionValue<>(3, Optional.of(DIVINE_GUARDIANS.get(COMMANDER)))
    );

    private final Spell spell = SPELLS.get(spellName);

    public FriendInNeed() {
        super(FRIEND_IN_NEED);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));

        choose(DIVINE_GUARDIAN_SELECTOR)
                .map(guardianSummoned -> guardianSummoned.instance(character))
                .ifPresentOrElse(companion -> {
                            character.getCompanions().add(companion);
                            storyLineLogger.event(summoning(companion));
                        },
                        () -> storyLineLogger.event(debugReport(NO_DIVINE_GUARDIAN_APPEARED)));
    }
}
