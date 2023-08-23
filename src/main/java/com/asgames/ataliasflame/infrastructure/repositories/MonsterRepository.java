package com.asgames.ataliasflame.infrastructure.repositories;

import com.asgames.ataliasflame.domain.model.entities.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, String> {

}
