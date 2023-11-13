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
import com.asgames.ataliasflame.infrastructure.repositories.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CharacterMaintenanceService {

    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;
    private final DefensiveGodConversionLogRepository defensiveGodConversionLogRepository;
    private final CharacterInitializer characterInitializer;
    private final AttributeService attributeService;
    private final CasteService casteService;
    private final DefensiveGodConversionService defensiveGodConversionService;

    @Transactional
    public Character createCharacter(CharacterInput characterInput) {
        Character character = characterMapper.toCharacter(characterInput);
        character = characterInitializer.initialize(character);

        locationRepository.save(character.getLocation());
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
