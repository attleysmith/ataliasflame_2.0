package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Modifier;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.MODIFIERS;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.CURSE;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.UNNECESSARY_CURSE_ATTACK;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;

@Service
public class CurseMagicService extends AbstractMagicService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    public void castCurseSpell(Character character, Spell spell, Monster monster) {
        if (!spell.getType().equals(CURSE)) {
            throw new IllegalArgumentException("Curse spell expected!");
        }
        if (spell.getGroup().equals(SOUL)
                && listUnusedSouls(character).isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
            return;
        }
        if (monster.isDead()) {
            storyLineLogger.event(warningReport(UNNECESSARY_CURSE_ATTACK));
            return;
        }
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));
        enforceCurseEffect(monster, spell.getName().name());
    }

    private void enforceCurseEffect(Monster monster, String curse) {
        int oldAttack = monster.getAttack();
        int oldDefense = monster.getDefense();
        int oldMinDamage = monster.getMinDamage();
        int oldMaxDamage = monster.getMaxDamage();
        int oldHealth = monster.getHealth().totalValue();

        Modifier modifier = MODIFIERS.get(curse);
        monster.setAttack(calculate(oldAttack, modifier.getAttackMultiplier()));
        monster.setDefense(calculate(oldDefense, modifier.getDefenseMultiplier()));
        monster.setMinDamage(calculate(oldMinDamage, modifier.getDamageMultiplier()));
        monster.setMaxDamage(calculate(oldMaxDamage, modifier.getDamageMultiplier()));
        monster.getHealth().set(calculate(oldHealth, modifier.getHealthMultiplier()));

        storyLineLogger.event(curseCasting(monster, curse, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
    }
}
