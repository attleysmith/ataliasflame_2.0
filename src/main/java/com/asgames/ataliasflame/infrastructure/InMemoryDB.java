package com.asgames.ataliasflame.infrastructure;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryDB implements CharacterRepository {

    private Character character;

    @Override
    public Character save(Character character) {
        this.character = character;
        return this.character;
    }

    @Override
    public Character getCharacter() {
        return character;
    }
}
