package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.entities.ActiveBlessing;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.Booster;
import com.asgames.ataliasflame.domain.model.enums.SoulChipShape;
import com.asgames.ataliasflame.domain.services.magic.spells.SoulSpell;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.Booster.*;
import static com.asgames.ataliasflame.domain.model.enums.SoulChipShape.*;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_CONNECTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;

@Component
public class SoulConnection extends BlessingSpell implements SoulSpell {

    private static final int SPELL_COST = 5;

    private static final int FATIGUE_EFFECT = 5;

    // buff effect
    private static final Map<SoulChipShape, Booster> BOOSTER_EFFECT_MAP = Map.of(
            CANINE, CANINE_SOUL_CONNECTION,
            APE_LIKE, APE_LIKE_SOUL_CONNECTION,
            BIRD_OF_PREY, BIRD_OF_PREY_SOUL_CONNECTION
    );

    public SoulConnection() {
        super(SOUL_CONNECTION);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        SoulArgs soulArgs = new SoulArgs(args);
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        SoulChip soulChip = getSoulChip(character, soulArgs.soulChipReference);
        soulChip.getHealth().trauma(FATIGUE_EFFECT);
        storyLineLogger.event(fatigue(soulChip, FATIGUE_EFFECT));

        Booster booster = BOOSTER_EFFECT_MAP.get(soulChip.getShape());
        if (character.getBlessings().stream()
                .noneMatch(blessing -> blessing.getBooster().equals(booster))) {
            ActiveBlessing activeBlessing = ActiveBlessing.of(character, booster).withSource(soulChip);
            character.getBlessings().add(activeBlessing);

            int originalHealth = character.getHealth().totalValue();
            int originalMagic = character.getMagic().totalValue();
            characterCalculationService.recalculateProperties(character);
            character.getHealth().uplift(originalHealth);
            character.getMagic().uplift(originalMagic);

            storyLineLogger.event(blessing(character, activeBlessing));
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Improves the attributes of the caster by connecting the energies of a soul chips. " +
                "Canine connection effect: [" + effectDetailsOf(CANINE_SOUL_CONNECTION) + "] " +
                "Ape like connection effect: [" + effectDetailsOf(APE_LIKE_SOUL_CONNECTION) + "] " +
                "Bird of prey connection effect: [" + effectDetailsOf(BIRD_OF_PREY_SOUL_CONNECTION) + "] " +
                "Effect of the connection is increased by the effectiveness of the connected soul chip. " +
                "Fatigue effect of the soul magic is " + FATIGUE_EFFECT + "%. " +
                "Cost: " + SPELL_COST + " MP";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        SoulArgs.validateArgs(args);
    }
}
