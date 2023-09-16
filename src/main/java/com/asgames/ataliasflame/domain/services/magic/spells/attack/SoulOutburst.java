package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_OUTBURST;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.NOVA;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class SoulOutburst extends AttackSpellEffect {

    // damage effect
    private static final int MIN_DAMAGE = 15;
    private static final int MAX_DAMAGE = 30;

    // area effect
    private static final int NOVA_EFFECT_CHANCE = 5;

    private final Spell spell = SPELLS.get(spellName);

    public SoulOutburst() {
        super(SOUL_OUTBURST);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        if (listUnusedSouls(character).isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
        } else {
            character.getMagic().use(spell.getCost());
            storyLineLogger.event(spellCasting(character, spell));

            if (successX(NOVA_EFFECT_CHANCE)) {
                targetMonster.getLocation().getMonsters().stream()
                        .filter(Combatant::isAlive)
                        .forEach(monster -> {
                            int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
                            monster.getHealth().damage(damage);
                            storyLineLogger.event(combatDamage(character, monster, damage, NOVA));
                        });
            } else if (targetMonster.isAlive()) {
                int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE);
                targetMonster.getHealth().damage(damage);
                storyLineLogger.event(combatDamage(character, targetMonster, damage, DIRECT));
            }
        }
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
