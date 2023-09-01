package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.BLESSING;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.CharacterReportCause.BLESSING_EXPIRY;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.CharacterReportEvent.characterReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasted;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.DUPLICATED_BLESSING;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.OCCUPIED_SOULS;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;

@Slf4j
@Service
public class BlessingMagicService extends AbstractMagicService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public void castBlessingSpell(Character character, Spell spell) {
        if (!spell.getType().equals(BLESSING)) {
            throw new IllegalArgumentException("Blessing spell expected!");
        }
        Optional<String> boosterName = getBooster(character, spell);
        if (boosterName.isEmpty()) {
            return;
        }
        String booster = boosterName.get();
        if (character.getBlessings().contains(booster)) {
            storyLineLogger.event(warningReport(DUPLICATED_BLESSING));
            return;
        }

        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasted(character, spell));
        enforceBoosterEffect(character, booster);
        storyLineLogger.event(blessing(character, booster));
    }

    private Optional<String> getBooster(Character character, Spell spell) {
        Optional<String> boosterName = Optional.of(spell.getName().name());
        if (spell.getGroup().equals(SOUL)) {
            boosterName = getSoulBooster(character);
        }
        return boosterName;
    }

    private Optional<String> getSoulBooster(Character character) {
        List<SoulChip> unusedSouls = listUnusedSouls(character);
        if (unusedSouls.isEmpty()) {
            storyLineLogger.event(warningReport(OCCUPIED_SOULS));
            return Optional.empty();
        } else {
            return Optional.of(unusedSouls.get(0).getShape().name());
        }
    }

    private void enforceBoosterEffect(Character character, String booster) {
        int originalHealth = character.getHealth().totalValue();
        character.getBlessings().add(booster);
        characterCalculationService.recalculateProperties(character);
        int blessedHealth = character.getHealth().totalValue();
        if (blessedHealth > originalHealth) {
            character.getHealth().replenish(blessedHealth - originalHealth);
        }
    }

    public void removeBlessingMagic(Character character) {
        character.setBlessings(null);
        characterCalculationService.recalculateProperties(character);
        if (character.getHealth().isEmpty()) {
            storyLineLogger.event(characterReport(character, BLESSING_EXPIRY));
        }
    }
}
