package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.mappers.CharacterMapper;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.services.AttributeService;
import com.asgames.ataliasflame.domain.services.CasteService;
import com.asgames.ataliasflame.domain.services.CharacterInitializer;
import com.asgames.ataliasflame.domain.services.DefensiveGodConversionService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.DefensiveGodConversionLogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CharacterMaintenanceService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterInitializer characterInitializer;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private CasteService casteService;
    @Autowired
    private DefensiveGodConversionService defensiveGodConversionService;

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private DefensiveGodConversionLogRepository defensiveGodConversionLogRepository;

    @Transactional
    public Character createCharacter(CharacterInput characterInput) {
        Character character = characterMapper.toCharacter(characterInput);
        character = characterInitializer.initialize(character);

        return characterRepository.save(character);
    }

    @Transactional(readOnly = true)
    public Character getCharacter(String characterName) {
        return characterRepository.findById(characterName)
                .orElseThrow(() -> new EntityNotFoundException("Character does not exist!"));
    }

    @Transactional
    public void removeCharacter(String characterName) {
        characterRepository.deleteById(characterName);
    }

    @Transactional
    public Character addAttributePoints(String characterName, Attribute attribute, int points) {
        Character character = getCharacter(characterName);
        character = attributeService.addAttributePoints(character, attribute, points);
        return characterRepository.save(character);
    }

    @Transactional
    public Character upgradeCaste(String characterName, Caste newCaste) {
        Character character = getCharacter(characterName);
        character = casteService.upgradeCaste(character, newCaste);
        return characterRepository.save(character);
    }

    @Transactional
    public Character convertDefensiveGod(String characterName, String conversionCode) {
        Character character = getCharacter(characterName);
        DefensiveGodConversionLog conversionLog = defensiveGodConversionLogRepository.findById(conversionCode)
                .orElseThrow(() -> new EntityNotFoundException("Missing conversion code!"));

        conversionLog = defensiveGodConversionService.convert(character, conversionLog);
        defensiveGodConversionLogRepository.save(conversionLog);

        return characterRepository.save(character);
    }
}
