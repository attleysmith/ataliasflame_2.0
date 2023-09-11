package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.dtos.Modifier;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.MODIFIERS;
import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.WEAKENING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.UNNECESSARY_CURSE_ATTACK;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;

@Component
public class Weakening extends SpellEffect {

    private final Spell spell = SPELLS.get(spellName);

    public Weakening() {
        super(WEAKENING);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));

        if (targetMonster.isDead()) {
            storyLineLogger.event(warningReport(UNNECESSARY_CURSE_ATTACK));
            return;
        }

        int oldAttack = targetMonster.getAttack();
        int oldDefense = targetMonster.getDefense();
        int oldMinDamage = targetMonster.getMinDamage();
        int oldMaxDamage = targetMonster.getMaxDamage();
        int oldHealth = targetMonster.getHealth().totalValue();

        String curse = spellName.name();
        Modifier modifier = MODIFIERS.get(curse);
        targetMonster.setAttack(calculate(oldAttack, modifier.getAttackMultiplier()));
        targetMonster.setDefense(calculate(oldDefense, modifier.getDefenseMultiplier()));
        targetMonster.setMinDamage(calculate(oldMinDamage, modifier.getDamageMultiplier()));
        targetMonster.setMaxDamage(calculate(oldMaxDamage, modifier.getDamageMultiplier()));
        targetMonster.getHealth().set(calculate(oldHealth, modifier.getHealthMultiplier()));

        storyLineLogger.event(curseCasting(targetMonster, curse, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
    }
}
