package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.entities.SummonedSoulChip;
import com.asgames.ataliasflame.domain.services.magic.spells.SoulSpell;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.CALLING_THE_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.SoulChipExhaustedEvent.soulChipExhausted;

@Component
public class CallingTheSouls extends SummonSpell implements SoulSpell {

    private static final int SPELL_COST = 18;

    private static final int FATIGUE_EFFECT = 1;

    public CallingTheSouls() {
        super(CALLING_THE_SOULS);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        SoulArgs soulArgs = new SoulArgs(args);
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        SoulChip soulChip = getSoulChip(character, soulArgs.soulChipReference);
        soulChip.getHealth().trauma(FATIGUE_EFFECT);
        storyLineLogger.event(fatigue(soulChip, FATIGUE_EFFECT));

        if (soulChip.isExhausted()) {
            storyLineLogger.event(soulChipExhausted(soulChip));
        } else {
            SummonedSoulChip summonedSoulChip = soulChip.summon();
            character.getCompanions().add(summonedSoulChip);
            storyLineLogger.event(summoning(summonedSoulChip));
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Summoning of a soul chip to help in combat. " +
                "Fatigue effect of the soul magic is " + FATIGUE_EFFECT + "%. " +
                "Cost: " + SPELL_COST + " MP";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        SoulArgs.validateArgs(args);
    }
}
