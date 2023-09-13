package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.mappers.DefensiveGodConversionCodeMapper;
import com.asgames.ataliasflame.application.model.DefensiveGodConversionCode;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import com.asgames.ataliasflame.domain.services.DefensiveGodConversionService;
import com.asgames.ataliasflame.domain.services.HealingService;
import com.asgames.ataliasflame.domain.services.MagicService;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.DefensiveGodConversionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.SLEEPING;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;

@Service
public class CharacterAdventureService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private DefensiveGodConversionCodeMapper defensiveGodConversionCodeMapper;

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private DefensiveGodConversionLogRepository defensiveGodConversionLogRepository;

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;

    @Autowired
    private MagicService magicService;
    @Autowired
    private HealingService healingService;
    @Autowired
    private DefensiveGodConversionService defensiveGodConversionService;

    @Transactional
    public Character sleep(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        storyLineLogger.event(debugReport(SLEEPING));
        healingService.sleep(character);
        magicService.sleep(character);
        character.getCompanions().forEach(companion ->
                healingService.companionSleep(companion));
        return characterRepository.save(character);
    }

    @Transactional
    public DefensiveGodConversionCode produceDefensiveGodConversionCode(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);

        DefensiveGodConversionLog conversionLog = defensiveGodConversionService.getConversionLog(character);

        conversionLog = defensiveGodConversionLogRepository.save(conversionLog);
        return defensiveGodConversionCodeMapper.toCode(conversionLog);
    }
}
