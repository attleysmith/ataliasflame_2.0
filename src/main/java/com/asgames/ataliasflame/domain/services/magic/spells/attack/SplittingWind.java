package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.SPLITTING_WIND;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;

@Component
public class SplittingWind extends AttackSpell {

    private static final int SPELL_COST = 8;

    // damage effect
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 10;

    public SplittingWind() {
        super(SPLITTING_WIND);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        if (targetMonster.isAlive()) {
            int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
            damageService.doDamage(character, targetMonster, damage, DIRECT);
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
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
