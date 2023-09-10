package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.dtos.Modifier;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.MockConstants.MODIFIERS;
import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_STRIKE;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.UNNECESSARY_CURSE_ATTACK;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;

@Component
public class SoulStrike extends SpellEffect {

    private final Spell spell = SPELLS.get(spellName);

    public SoulStrike() {
        super(SOUL_STRIKE);
    }

    @Override
    public void enforce(Character character, Monster monster) {
        if (listUnusedSouls(character).isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
        } else {
            character.getMagic().use(spell.getCost());
            storyLineLogger.event(spellCasted(character, spell));

            if (monster.isDead()) {
                storyLineLogger.event(warningReport(UNNECESSARY_CURSE_ATTACK));
                return;
            }

            int oldAttack = monster.getAttack();
            int oldDefense = monster.getDefense();
            int oldMinDamage = monster.getMinDamage();
            int oldMaxDamage = monster.getMaxDamage();
            int oldHealth = monster.getHealth().totalValue();

            String curse = spellName.name();
            Modifier modifier = MODIFIERS.get(curse);
            monster.setAttack(calculate(oldAttack, modifier.getAttackMultiplier()));
            monster.setDefense(calculate(oldDefense, modifier.getDefenseMultiplier()));
            monster.setMinDamage(calculate(oldMinDamage, modifier.getDamageMultiplier()));
            monster.setMaxDamage(calculate(oldMaxDamage, modifier.getDamageMultiplier()));
            monster.getHealth().set(calculate(oldHealth, modifier.getHealthMultiplier()));

            storyLineLogger.event(curseCasting(monster, curse, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
        }
    }
}
