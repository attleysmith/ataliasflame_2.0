package com.asgames.ataliasflame.infrastructure.repositories;

import com.asgames.ataliasflame.domain.model.entities.Monster;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository {

    void save(Monster monster);

    Monster getMonster();
}
