package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.INFERNO;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.AREA;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class Inferno extends AttackSpell {

    private static final int SPELL_COST = 20;

    // damage effect
    private static final int MIN_DAMAGE = 12;
    private static final int MAX_DAMAGE = 28;

    // area effect
    private static final int AREA_EFFECT_CHANCE = 60;
    private static final int AREA_EFFECT_RATIO = 80;

    public Inferno() {
        super(INFERNO);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        int directDamage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
        int areaDamage = percent(directDamage, AREA_EFFECT_RATIO);
        targetMonster.getLocation().getMonsters().stream()
                .filter(Combatant::isAlive)
                .forEach(monster -> {
                    if (monster.getReference().equals(targetMonster.getReference())) {
                        damageService.doDamage(character, monster, directDamage, DIRECT);
                    } else if (successX(AREA_EFFECT_CHANCE)) {
                        damageService.doDamage(character, monster, areaDamage, AREA);
                    }
                });
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Summoning infernal fire field under a targeted enemy to cause damage (" + MIN_DAMAGE + "-" + MAX_DAMAGE + "). " +
                "There is a " + AREA_EFFECT_CHANCE + "% chance that other enemies near the target get damage because of an area effect. " +
                "Area damage is " + AREA_EFFECT_RATIO + "% of the original damage. " +
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
