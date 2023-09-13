package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_BLOCKING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;

@Component
public class EnergyBlocking extends SpellEffect {

    private static final int ATTACK_MULTIPLIER = -25;
    private static final int DEFENSE_MULTIPLIER = -10;
    private static final int DAMAGE_MULTIPLIER = -10;

    private final Spell spell = SPELLS.get(spellName);

    public EnergyBlocking() {
        super(ENERGY_BLOCKING);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        if (targetMonster.isAlive()) {
            int oldAttack = targetMonster.getAttack();
            int oldDefense = targetMonster.getDefense();
            int oldMinDamage = targetMonster.getMinDamage();
            int oldMaxDamage = targetMonster.getMaxDamage();
            int oldHealth = targetMonster.getHealth().totalValue();

            targetMonster.setAttack(calculate(oldAttack, ATTACK_MULTIPLIER));
            targetMonster.setDefense(calculate(oldDefense, DEFENSE_MULTIPLIER));
            targetMonster.setMinDamage(calculate(oldMinDamage, DAMAGE_MULTIPLIER));
            targetMonster.setMaxDamage(calculate(oldMaxDamage, DAMAGE_MULTIPLIER));

            storyLineLogger.event(curseCasting(targetMonster, spellName.name(), oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
        }
    }
}
