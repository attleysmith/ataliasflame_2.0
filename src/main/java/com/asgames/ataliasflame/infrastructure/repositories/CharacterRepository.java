package com.asgames.ataliasflame.infrastructure.repositories;

import com.asgames.ataliasflame.domain.model.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, String> {

}
