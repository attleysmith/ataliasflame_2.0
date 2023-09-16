package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.FIREBALL;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.*;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class Fireball extends AttackSpellEffect {

    // damage effect
    private static final int MIN_DAMAGE = 2;
    private static final int MAX_DAMAGE = 12;

    // area effect
    private static final int AREA_EFFECT_CHANCE = 30;
    private static final int AREA_EFFECT_RATIO = 60;
    private static final int BLAST_EFFECT_CHANCE = 60;
    private static final int BLAST_EFFECT_RATIO = 20;

    private final Spell spell = SPELLS.get(spellName);

    public Fireball() {
        super(FIREBALL);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        int directDamage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
        int areaDamage = percent(directDamage, AREA_EFFECT_RATIO);
        int blastDamage = percent(directDamage, BLAST_EFFECT_RATIO);
        targetMonster.getLocation().getMonsters().stream()
                .filter(Combatant::isAlive)
                .forEach(monster -> {
                    if (monster.getReference().equals(targetMonster.getReference())) {
                        monster.getHealth().damage(directDamage);
                        storyLineLogger.event(combatDamage(character, monster, directDamage, DIRECT));
                    } else if (successX(AREA_EFFECT_CHANCE)) {
                        monster.getHealth().damage(areaDamage);
                        storyLineLogger.event(combatDamage(character, monster, areaDamage, AREA));
                    } else if (successX(BLAST_EFFECT_CHANCE)) {
                        monster.getHealth().damage(blastDamage);
                        storyLineLogger.event(combatDamage(character, monster, blastDamage, BLAST));
                    }
                });
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
