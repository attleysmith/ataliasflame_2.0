package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.ELEMENTAL;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.LIGHTNING_STRIKE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.CHAINING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

@Component
public class LightningStrike extends AttackSpell {

    private static final int SPELL_COST = 10;

    // damage effect
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 15;

    // area effect
    private static final int CHAINING_EFFECT_CHANCE = 25;
    private static final int CHAINING_EFFECT_RATIO = 49;

    public LightningStrike() {
        super(LIGHTNING_STRIKE, ELEMENTAL);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        int directDamage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
        if (targetMonster.isAlive()) {
            targetMonster.getHealth().damage(directDamage);
            storyLineLogger.event(combatDamage(character, targetMonster, directDamage, DIRECT));
        }

        List<Monster> monsterChain = targetMonster.getLocation().getMonsters().stream()
                .filter(Combatant::isAlive)
                .filter(monster -> !monster.getReference().equals(targetMonster.getReference()))
                .collect(toList());
        shuffle(monsterChain);

        int chainingDamage = directDamage;
        for (Monster monster : monsterChain) {
            chainingDamage = percent(chainingDamage, CHAINING_EFFECT_RATIO);

            if (chainingDamage == 0 || !successX(CHAINING_EFFECT_CHANCE)) {
                break;
            }

            monster.getHealth().damage(chainingDamage);
            storyLineLogger.event(combatDamage(character, monster, chainingDamage, CHAINING));
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
