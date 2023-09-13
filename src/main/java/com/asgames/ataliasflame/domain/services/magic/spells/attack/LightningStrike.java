package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
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
public class LightningStrike extends SpellEffect {

    private static final int CHAINING_EFFECT_CHANCE = 25;
    private static final int CHAINING_EFFECT_RATIO = 49;

    private final Spell spell = SPELLS.get(spellName);

    public LightningStrike() {
        super(LIGHTNING_STRIKE);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        int directDamage = pointOut(spell.getMinDamage(), spell.getMaxDamage());
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
}
