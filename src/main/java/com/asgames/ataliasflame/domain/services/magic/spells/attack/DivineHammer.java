package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.DIVINE;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.DIVINE_HAMMER;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.CROSSFIRE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class DivineHammer extends AttackSpell {

    private static final int SPELL_COST = 10;

    // damage effect
    private static final int MIN_DAMAGE = 6;
    private static final int MAX_DAMAGE = 18;

    // area effect
    private static final int CROSSFIRE_EFFECT_CHANCE = 10;
    private static final int CROSSFIRE_EFFECT_RATIO = 50;

    public DivineHammer() {
        super(DIVINE_HAMMER, DIVINE);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        int directDamage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
        int crossfireDamage = percent(directDamage, CROSSFIRE_EFFECT_RATIO);
        targetMonster.getLocation().getMonsters().stream()
                .filter(Combatant::isAlive)
                .forEach(monster -> {
                    if (monster.getReference().equals(targetMonster.getReference())) {
                        monster.getHealth().damage(directDamage);
                        storyLineLogger.event(combatDamage(character, monster, directDamage, DIRECT));
                    } else if (successX(CROSSFIRE_EFFECT_CHANCE)) {
                        monster.getHealth().damage(crossfireDamage);
                        storyLineLogger.event(combatDamage(character, monster, crossfireDamage, CROSSFIRE));
                    }
                });
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
