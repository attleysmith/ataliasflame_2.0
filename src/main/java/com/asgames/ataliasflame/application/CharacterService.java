package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.mappers.CharacterMapper;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.services.*;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.MonsterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private MonsterRepository monsterRepository;
    @Autowired
    private CombatService combatService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private CasteService casteService;

    public Character createCharacter(CharacterInput characterInput) {
        Character character = characterMapper.toCharacter(characterInput);
        character = characterInitializer.initialize(character);

        return characterRepository.save(character);
    }

    public Character getCharacter() {
        return characterRepository.getCharacter();
    }

    public Character addAttributePoints(Attribute attribute, int points) {
        Character character = characterRepository.getCharacter();
        character = attributeService.addAttributePoints(character, attribute, points);
        return characterRepository.save(character);
    }

    public Character upgradeCaste(Caste newCaste) {
        Character character = characterRepository.getCharacter();
        character = casteService.upgradeCaste(character, newCaste);
        return characterRepository.save(character);
    }

    public void combat() {
        Character character = characterRepository.getCharacter();
        Monster monster = monsterRepository.getMonster().toBuilder().build(); // monster clone

        combatService.combat(List.of(character), List.of(monster));
        if (character.getActualHealth() > 0) {
            character = experienceService.gainExperience(character, monster.getExperience());
            log.info("You are the winner!");
            log.info("Remaining health: " + character.getActualHealth());
        } else {
            log.info("You are defeated!");
            log.info("Enemy's health: " + monster.getActualHealth());
        }

        characterRepository.save(character);
    }
}
