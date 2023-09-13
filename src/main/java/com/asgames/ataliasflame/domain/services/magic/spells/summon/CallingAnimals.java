package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.AnimalTemplate;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.AnimalTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.CALLING_ANIMALS;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.NO_ANIMAL_APPEARED;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Component
public class CallingAnimals extends SpellEffect {

    private static final List<SelectionValue<Optional<AnimalTemplate>>> ANIMAL_SELECTOR = List.of(
            new SelectionValue<>(1, Optional.empty()),
            new SelectionValue<>(9, Optional.of(TAMED_FALCON)),
            new SelectionValue<>(50, Optional.of(TAMED_DOG)),
            new SelectionValue<>(35, Optional.of(TAMED_WOLF)),
            new SelectionValue<>(5, Optional.of(TAMED_BEAR))
    );

    private final Spell spell = SPELLS.get(spellName);

    public CallingAnimals() {
        super(CALLING_ANIMALS);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        choose(ANIMAL_SELECTOR)
                .map(animalSummoned -> animalSummoned.instance(character))
                .ifPresentOrElse(companion -> {
                            character.getCompanions().add(companion);
                            storyLineLogger.event(summoning(companion));
                        },
                        () -> storyLineLogger.event(debugReport(NO_ANIMAL_APPEARED)));
    }
}
