package com.asgames.ataliasflame.infrastructure.repositories;

import com.asgames.ataliasflame.domain.model.entities.Character;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository {

    void save(Character character);

    Character getCharacter();
}
