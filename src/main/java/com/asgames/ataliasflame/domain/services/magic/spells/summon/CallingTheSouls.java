package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.entities.SummonedSoulChip;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.CALLING_THE_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.SoulChipExhaustedEvent.soulChipExhausted;

@Component
public class CallingTheSouls extends SummonSpell {

    private static final String ARG_KEY_SOUL_CHIP = "soulChip";

    private static final int SPELL_COST = 18;

    private static final int FATIGUE_EFFECT = 1;

    public CallingTheSouls() {
        super(CALLING_THE_SOULS);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        CallingTheSoulsArgs callingTheSoulsArgs = new CallingTheSoulsArgs(args);
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        SoulChip soulChip = getSoulChip(character, callingTheSoulsArgs.soulChipReference);
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
        CallingTheSoulsArgs.validateArgs(args);
    }

    private static class CallingTheSoulsArgs {

        public final String soulChipReference;

        public CallingTheSoulsArgs(Map<String, String> args) {
            validateArgs(args);
            soulChipReference = args.get(ARG_KEY_SOUL_CHIP);
        }

        public static void validateArgs(Map<String, String> args) {
            if (!args.containsKey(ARG_KEY_SOUL_CHIP)) {
                throw new IllegalArgumentException("Missing argument: " + ARG_KEY_SOUL_CHIP);
            }
            if (args.size() != 1) {
                throw new IllegalArgumentException("Incorrect number of arguments.");
            }
        }
    }
}
