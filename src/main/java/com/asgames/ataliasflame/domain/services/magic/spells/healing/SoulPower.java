package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.services.HealingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.SOUL_POWER;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.FatigueEvent.fatigue;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;

@Component
public class SoulPower extends HealingSpell {

    @Autowired
    private HealingService healingService;

    private static final int SPELL_COST = 10;

    private static final int FATIGUE_EFFECT = 20;

    // healing effect
    private static final int HEALING_EFFECT = 40;

    public SoulPower() {
        super(SOUL_POWER, SOUL);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        List<SoulChip> readySouls = listReadySouls(character);
        if (readySouls.isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
        } else {
            character.getMagic().use(SPELL_COST);
            storyLineLogger.event(spellCasting(character, this));

            SoulChip soulChip = pointOut(readySouls);
            soulChip.getHealth().trauma(FATIGUE_EFFECT);
            storyLineLogger.event(fatigue(soulChip, FATIGUE_EFFECT));

            healingService.recoverHealth(character, HEALING_EFFECT);
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }
}
