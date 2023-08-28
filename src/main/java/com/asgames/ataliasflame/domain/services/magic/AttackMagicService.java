package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.ATTACK;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.UNNECESSARY_SPELL_ATTACK;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;

@Slf4j
@Service
public class AttackMagicService extends AbstractMagicService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    public void castAttackSpell(Character character, Spell spell, Monster monster) {
        if (!spell.getType().equals(ATTACK)) {
            throw new IllegalArgumentException("Attack spell expected!");
        }
        if (spell.getGroup().equals(SOUL)
                && listUnusedSouls(character).isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
            return;
        }
        if (monster.isDead()) {
            storyLineLogger.event(warningReport(UNNECESSARY_SPELL_ATTACK));
            return;
        }
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));
        int damage = pointOut(spell.getMinDamage(), spell.getMaxDamage());
        monster.getHealth().damage(damage);
        storyLineLogger.event(combatDamage(character, monster, damage));
    }
}
