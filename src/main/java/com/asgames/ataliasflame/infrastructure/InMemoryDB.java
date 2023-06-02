package com.asgames.ataliasflame.infrastructure;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.infrastructure.repositories.MonsterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryDB implements CharacterRepository, MonsterRepository {

    private Character character;
    private Monster monster;

    @Override
    public void save(Character character) {
        this.character = character;
    }

    @Override
    public Character getCharacter() {
        return character;
    }

    @Override
    public void save(Monster monster) {
        this.monster = monster;
    }

    @Override
    public Monster getMonster() {
        return monster;
    }
}
