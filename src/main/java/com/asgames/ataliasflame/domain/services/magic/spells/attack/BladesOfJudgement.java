package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.GENERAL;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.BLADES_OF_JUDGEMENT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.IntimidationEvent.intimidation;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class BladesOfJudgement extends AttackSpell {

    private static final int SPELL_COST = 25;

    // damage effect
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 30;

    // debuff effect
    private static final int INTIMIDATION_EFFECT_CHANCE = 60;
    private static final int INTIMIDATION_ATTACK_MULTIPLIER = -30;
    private static final int INTIMIDATION_DEFENSE_MULTIPLIER = -20;

    public BladesOfJudgement() {
        super(BLADES_OF_JUDGEMENT, GENERAL);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        if (targetMonster.isAlive()) {
            int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
            damageService.doDamage(character, targetMonster, damage, DIRECT);
        }

        if (targetMonster.isDead()) {
            targetMonster.getLocation().getMonsters().stream()
                    .filter(Combatant::isAlive)
                    .forEach(monster -> {
                        if (successX(INTIMIDATION_EFFECT_CHANCE)) {
                            intimidate(monster);
                        }
                    });
        }
    }

    private void intimidate(Monster monster) {
        int oldAttack = monster.getAttack();
        int oldDefense = monster.getDefense();
        int oldMinDamage = monster.getMinDamage();
        int oldMaxDamage = monster.getMaxDamage();
        int oldHealth = monster.getHealth().totalValue();

        monster.setAttack(calculate(oldAttack, INTIMIDATION_ATTACK_MULTIPLIER));
        monster.setDefense(calculate(oldDefense, INTIMIDATION_DEFENSE_MULTIPLIER));

        storyLineLogger.event(intimidation(monster, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
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
