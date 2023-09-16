package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SPLITTING_WIND;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;

@Component
public class SplittingWind extends AttackSpellEffect {

    // damage effect
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 10;

    private final Spell spell = SPELLS.get(spellName);

    public SplittingWind() {
        super(SPLITTING_WIND);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        if (targetMonster.isAlive()) {
            int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
            targetMonster.getHealth().damage(damage);
            storyLineLogger.event(combatDamage(character, targetMonster, damage, DIRECT));
        }
    }

    @Override
    public int getMinDamage() {
        return MIN_DAMAGE;
    }

    @Override
    public int getMaxDamage() {
        return MAX_DAMAGE;
    }
}
