package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.entities.ActiveBlessing;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Booster;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.PROTECTIVE_HAND_OF_NATURE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class ProtectiveHandOfNature extends BlessingSpell {

    private static final int SPELL_COST = 3;

    // buff effect
    private static final Booster BOOSTER = Booster.PROTECTIVE_HAND_OF_NATURE;

    public ProtectiveHandOfNature() {
        super(PROTECTIVE_HAND_OF_NATURE);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        if (character.getBlessings().stream()
                .noneMatch(blessing -> blessing.getBooster().equals(BOOSTER))) {
            ActiveBlessing activeBlessing = ActiveBlessing.of(character, BOOSTER);
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
        return "Improves the attributes of the caster by absorbing the energies of the surrounding natural elements. " +
                "Effect: [" + effectDetailsOf(BOOSTER) + "] " +
                "Cost: " + SPELL_COST + " MP";
    }
}
