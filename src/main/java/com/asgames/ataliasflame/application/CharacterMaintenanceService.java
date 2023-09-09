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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterMaintenanceService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private DefensiveGodConversionLogRepository defensiveGodConversionLogRepository;

    @Autowired
    private CharacterInitializer characterInitializer;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private CasteService casteService;
    @Autowired
    private DefensiveGodConversionService defensiveGodConversionService;

    @Transactional
    public Character createCharacter(CharacterInput characterInput) {
        Character character = characterMapper.toCharacter(characterInput);
        character = characterInitializer.initialize(character);

        return characterRepository.save(character);
    }

    @Transactional(readOnly = true)
    public Character getCharacter(String characterReference) {
        return characterRepository.findById(characterReference)
                .orElseThrow(() -> new EntityNotFoundException("Character does not exist!"));
    }

    @Transactional
    public void removeCharacter(String characterReference) {
        characterRepository.deleteById(characterReference);
    }

    @Transactional
    public Character addAttributePoints(String characterReference, Attribute attribute, int points) {
        Character character = getCharacter(characterReference);
        attributeService.addAttributePoints(character, attribute, points);
        return characterRepository.save(character);
    }

    @Transactional
    public Character upgradeCaste(String characterReference, Caste newCaste) {
        Character character = getCharacter(characterReference);
        casteService.upgradeCaste(character, newCaste);
        return characterRepository.save(character);
    }

    @Transactional
    public Character convertDefensiveGod(String characterReference, String conversionCode) {
        Character character = getCharacter(characterReference);
        DefensiveGodConversionLog conversionLog = defensiveGodConversionLogRepository.findById(conversionCode)
                .orElseThrow(() -> new EntityNotFoundException("Missing conversion code!"));

        conversionLog = defensiveGodConversionService.convert(character, conversionLog);
        defensiveGodConversionLogRepository.save(conversionLog);

        return characterRepository.save(character);
    }
}
