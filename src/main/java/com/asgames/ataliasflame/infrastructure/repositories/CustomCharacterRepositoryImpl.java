package com.asgames.ataliasflame.infrastructure.repositories;

import com.asgames.ataliasflame.domain.model.entities.Character;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class CustomCharacterRepositoryImpl implements CustomCharacterRepository {

    @Autowired
    @Lazy
    private CharacterRepository characterRepository;

    @Override
    public Character getCharacter() {
        List<Character> characters = characterRepository.findAll();
        if (characters.size() > 1) {
            throw new IllegalStateException("Only one character should be existent!");
        }
        if (characters.size() == 0) {
            throw new EntityNotFoundException("Character not found!");
        }
        return characters.get(0);
    }
}
