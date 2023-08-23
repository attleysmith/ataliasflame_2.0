package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.mappers.DefensiveGodConversionCodeMapper;
import com.asgames.ataliasflame.application.model.DefensiveGodConversionCode;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import com.asgames.ataliasflame.domain.services.DefensiveGodConversionService;
import com.asgames.ataliasflame.domain.services.HealingService;
import com.asgames.ataliasflame.domain.services.MagicService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.DefensiveGodConversionLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CharacterAdventureService {

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
        healingService.sleep(character);
        magicService.sleep(character);
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
