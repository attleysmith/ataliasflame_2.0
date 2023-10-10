package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.ENERGY_PROJECTION;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.PROJECTION_OF_ENERGY;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CompanionEvents.CompanionSummoningEvent.summoning;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class ProjectionOfEnergy extends SummonSpell {

    private static final int SPELL_COST = 30;

    private static final int ENERGY_PROJECTION_PERCENT = 50;

    public ProjectionOfEnergy() {
        super(PROJECTION_OF_ENERGY);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        Companion companion = Companion.builder()
                .reference(UUID.randomUUID().toString())
                .name("Energy of " + character.getName())
                .type(ENERGY_PROJECTION)
                .owner(character)
                .attack(percent(character.getAttack(), ENERGY_PROJECTION_PERCENT))
                .defense(percent(character.getDefense(), ENERGY_PROJECTION_PERCENT))
                .minDamage(percent(character.getMinDamage(), ENERGY_PROJECTION_PERCENT))
                .maxDamage(percent(character.getMaxDamage(), ENERGY_PROJECTION_PERCENT))
                .health(Energy.withTotal(percent(character.getHealth().totalValue(), ENERGY_PROJECTION_PERCENT)))
                .initiative(character.getInitiative())
                .build();
        character.getCompanions().add(companion);
        storyLineLogger.event(summoning(companion));
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }
}
