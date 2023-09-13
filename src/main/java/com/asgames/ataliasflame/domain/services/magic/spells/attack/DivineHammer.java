package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.DIVINE_HAMMER;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.CROSSFIRE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class DivineHammer extends SpellEffect {

    private static final int CROSSFIRE_EFFECT_CHANCE = 10;
    private static final int CROSSFIRE_EFFECT_RATIO = 50;

    private final Spell spell = SPELLS.get(spellName);

    public DivineHammer() {
        super(DIVINE_HAMMER);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        int directDamage = pointOut(spell.getMinDamage(), spell.getMaxDamage());
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
}
