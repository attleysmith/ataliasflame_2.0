package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.dtos.Modifier;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.MODIFIERS;
import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.BLADES_OF_JUDGEMENT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.IntimidationEvent.intimidation;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class BladesOfJudgement extends SpellEffect {

    public static final int INTIMIDATION_EFFECT_CHANCE = 60;
    private final Spell spell = SPELLS.get(spellName);

    public BladesOfJudgement() {
        super(BLADES_OF_JUDGEMENT);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));

        if (targetMonster.isAlive()) {
            int damage = pointOut(spell.getMinDamage(), spell.getMaxDamage());
            targetMonster.getHealth().damage(damage);
            storyLineLogger.event(combatDamage(character, targetMonster, damage, DIRECT));
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

        Modifier modifier = MODIFIERS.get("INTIMIDATION");
        monster.setAttack(calculate(oldAttack, modifier.getAttackMultiplier()));
        monster.setDefense(calculate(oldDefense, modifier.getDefenseMultiplier()));
        monster.setMinDamage(calculate(oldMinDamage, modifier.getDamageMultiplier()));
        monster.setMaxDamage(calculate(oldMaxDamage, modifier.getDamageMultiplier()));
        monster.getHealth().set(calculate(oldHealth, modifier.getHealthMultiplier()));

        storyLineLogger.event(intimidation(monster, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
    }
}
