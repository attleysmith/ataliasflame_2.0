package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.GENERAL;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.WEAKENING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;

@Component
public class Weakening extends CurseSpell {

    private static final int SPELL_COST = 5;

    // debuff effect
    private static final int ATTACK_MULTIPLIER = -5;
    private static final int DEFENSE_MULTIPLIER = -5;
    private static final int DAMAGE_MULTIPLIER = -5;

    public Weakening() {
        super(WEAKENING, GENERAL);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        if (targetMonster.isAlive()) {
            int oldAttack = targetMonster.getAttack();
            int oldDefense = targetMonster.getDefense();
            int oldMinDamage = targetMonster.getMinDamage();
            int oldMaxDamage = targetMonster.getMaxDamage();
            int oldHealth = targetMonster.getHealth().totalValue();

            targetMonster.setAttack(calculate(oldAttack, ATTACK_MULTIPLIER));
            targetMonster.setDefense(calculate(oldDefense, DEFENSE_MULTIPLIER));
            targetMonster.setMinDamage(calculate(oldMinDamage, DAMAGE_MULTIPLIER));
            targetMonster.setMaxDamage(calculate(oldMaxDamage, DAMAGE_MULTIPLIER));

            storyLineLogger.event(curseCasting(targetMonster, this, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }
}
