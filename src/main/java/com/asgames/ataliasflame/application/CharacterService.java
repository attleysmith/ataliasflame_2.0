package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.mappers.CharacterMapper;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.services.*;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CharacterService {

    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterInitializer characterInitializer;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CombatService combatService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private CasteService casteService;
    @Autowired
    private HealingService healingService;
    @Autowired
    private MonsterService monsterService;

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
    public Character sleep(String characterName) {
        Character character = getCharacter(characterName);
        character = healingService.sleep(character);
        return characterRepository.save(character);
    }

    @Transactional
    public Character combat(String characterName) {
        Character character = getCharacter(characterName);
        Monster monster = monsterService.getRandomMonster();

        combatService.combat(List.of(character), List.of(monster));
        if (character.getActualHealth() > 0) {
            character = experienceService.gainExperience(character, monster.getExperience());
            log.info("You are the winner!");
            log.info("Remaining health: " + character.getActualHealth());
        } else {
            log.info("You are defeated!");
            log.info("Enemy's health: " + monster.getActualHealth());
        }

        return characterRepository.save(character);
    }
}
