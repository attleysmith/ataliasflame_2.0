package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.entities.ActiveBlessing;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Booster;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.STRENGTHENING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class Strengthening extends BlessingSpell {

    private static final int SPELL_COST = 5;

    // buff effect
    private static final Booster BOOSTER = Booster.STRENGTHENING;

    public Strengthening() {
        super(STRENGTHENING);
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
        return "Improves the attributes of the caster by strengthening their body. " +
                "Effect: [" + effectDetailsOf(BOOSTER) + "] " +
                "Cost: " + SPELL_COST + " MP";
    }
}
