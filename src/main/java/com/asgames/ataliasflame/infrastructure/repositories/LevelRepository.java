package com.asgames.ataliasflame.infrastructure.repositories;

import com.asgames.ataliasflame.domain.model.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
}
