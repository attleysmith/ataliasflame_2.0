package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.SHACKLE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.STRESS;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class Shackle extends CurseSpell {

    private static final int SPELL_COST = 8;

    // damage effect
    private static final int DAMAGE_CHANCE = 40;
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 5;

    // area effect
    private static final int NEARBY_CATCHING_CHANCE = 25;

    // debuff effect
    private static final int ATTACK_MULTIPLIER = -10;
    private static final int DEFENSE_MULTIPLIER = -5;
    private static final int DAMAGE_MULTIPLIER = -5;

    public Shackle() {
        super(SHACKLE);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        targetMonster.getLocation().getMonsters().stream()
                .filter(Combatant::isAlive)
                .forEach(monster -> {
                    if (monster.getReference().equals(targetMonster.getReference())
                            || successX(NEARBY_CATCHING_CHANCE)) {
                        shackle(character, monster);
                    }
                });
    }

    private void shackle(Character character, Monster targetMonster) {
        if (targetMonster.isAlive() && successX(DAMAGE_CHANCE)) {
            int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
            damageService.doDamage(character, targetMonster, damage, STRESS);
        }

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

    @Override
    public String getDetails() {
        return "Summoning tendrils from the earth to shackle the targeted enemy. " +
                "Shackle lowers the attributes of the captured enemy. " +
                "Effect: [" + effectDetailsOf(ATTACK_MULTIPLIER, DEFENSE_MULTIPLIER, DAMAGE_MULTIPLIER, 0) + "] " +
                "There is a " + NEARBY_CATCHING_CHANCE + "% chance to catch nearby enemies as well. " +
                "There is a " + DAMAGE_CHANCE + "% chance to cause stress damage (" + MIN_DAMAGE + "-" + MAX_DAMAGE + ") to captured enemies. " +
                "Cost: " + SPELL_COST + " MP";
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
