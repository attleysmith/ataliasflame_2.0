package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.mappers.DefensiveGodConversionCodeMapper;
import com.asgames.ataliasflame.application.model.DefensiveGodConversionCode;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import com.asgames.ataliasflame.domain.services.*;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.DefensiveGodConversionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.SLEEPING;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;

@RequiredArgsConstructor
@Service
public class CharacterAdventureService {

    private final StoryLineLogger storyLineLogger;
    private final DefensiveGodConversionCodeMapper defensiveGodConversionCodeMapper;
    private final CharacterRepository characterRepository;
    private final DefensiveGodConversionLogRepository defensiveGodConversionLogRepository;
    private final CharacterMaintenanceService characterMaintenanceService;
    private final MagicService magicService;
    private final HealingService healingService;
    private final SoulChipService soulChipService;
    private final DefensiveGodConversionService defensiveGodConversionService;
    private final InventoryService inventoryService;

    @Transactional
    public Character sleep(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        storyLineLogger.event(debugReport(SLEEPING));
        healingService.sleep(character);
        magicService.sleep(character);
        soulChipService.sleep(character);
        character.getCompanions().forEach(healingService::companionSleep);
        return characterRepository.save(character);
    }

    @Transactional
    public DefensiveGodConversionCode produceDefensiveGodConversionCode(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);

        DefensiveGodConversionLog conversionLog = defensiveGodConversionService.getConversionLog(character);

        conversionLog = defensiveGodConversionLogRepository.save(conversionLog);
        return defensiveGodConversionCodeMapper.toCode(conversionLog);
    }

    @Transactional
    public Character timePassed(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        magicService.removeBlessingMagic(character);
        soulChipService.rest(character);
        return characterRepository.save(character);
    }

    public Character switchWeapons(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        inventoryService.switchWeapons(character);
        return characterRepository.save(character);
    }

    public Character switchShields(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        inventoryService.switchShields(character);
        return characterRepository.save(character);
    }
}
