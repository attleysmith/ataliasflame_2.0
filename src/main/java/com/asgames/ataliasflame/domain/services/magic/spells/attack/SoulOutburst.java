package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_OUTBURST;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.NOVA;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Component
public class SoulOutburst extends AttackSpell {

    private static final int SPELL_COST = 16;

    private static final int FATIGUE_EFFECT = 10;

    // damage effect
    private static final int MIN_DAMAGE = 15;
    private static final int MAX_DAMAGE = 30;
    private static final int BONUS_DAMAGE = 10;

    // area effect
    private static final int NOVA_EFFECT_CHANCE = 5;

    public SoulOutburst() {
        super(SOUL_OUTBURST);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        List<SoulChip> readySouls = listReadySouls(character);
        if (readySouls.isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
        } else {
            character.getMagic().use(SPELL_COST);
            storyLineLogger.event(spellCasting(character, this));

            SoulChip soulChip = pointOut(readySouls);
            soulChip.getHealth().trauma(FATIGUE_EFFECT);
            storyLineLogger.event(fatigue(soulChip, FATIGUE_EFFECT));

            int bonusDamage = percent(BONUS_DAMAGE, soulChip.getEffectiveness());
            if (successX(NOVA_EFFECT_CHANCE)) {
                targetMonster.getLocation().getMonsters().stream()
                        .filter(Combatant::isAlive)
                        .forEach(monster -> {
                            int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE) + bonusDamage;
                            damageService.doDamage(character, monster, damage, NOVA);
                        });
            } else if (targetMonster.isAlive()) {
                int damage = pointOut(MIN_DAMAGE, MAX_DAMAGE) + bonusDamage;
                damageService.doDamage(character, targetMonster, damage, DIRECT);
            }
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
