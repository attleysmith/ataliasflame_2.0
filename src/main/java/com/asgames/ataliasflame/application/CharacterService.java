package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.mappers.CharacterMapper;
import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.services.AttributeService;
import com.asgames.ataliasflame.domain.services.CombatService;
import com.asgames.ataliasflame.domain.services.ExperienceService;
import com.asgames.ataliasflame.domain.services.CharacterInitializer;
import com.asgames.ataliasflame.infrastructure.repositories.MonsterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
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

    public Character createCharacter(CharacterInput characterInput) {
        Character character = characterMapper.toCharacter(characterInput);
        character = characterInitializer.initialize(character);

        characterRepository.save(character);
        return character;
    }

    public Character getCharacter() {
        return characterRepository.getCharacter();
    }

    public Character addAttributePoints(Attribute attribute, int points) {
        Character character = characterRepository.getCharacter();
        return attributeService.addAttributePoints(character, attribute, points);
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
