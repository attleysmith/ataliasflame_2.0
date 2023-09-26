package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.ELEMENTAL;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.GLACIAL_BLOW;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.CROSSFIRE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class GlacialBlow extends AttackSpell {

    private static final int SPELL_COST = 18;

    // damage effect
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 25;

    // area effect
    private static final int CROSSFIRE_EFFECT_CHANCE = 15;
    private static final int CROSSFIRE_EFFECT_RATIO = 80;

    public GlacialBlow() {
        super(GLACIAL_BLOW, ELEMENTAL);
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
                        damageService.doDamage(character, monster, directDamage, DIRECT);
                    } else if (successX(CROSSFIRE_EFFECT_CHANCE)) {
                        damageService.doDamage(character, monster, crossfireDamage, CROSSFIRE);
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
