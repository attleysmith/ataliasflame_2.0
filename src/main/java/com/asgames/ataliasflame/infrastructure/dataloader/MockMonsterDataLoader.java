package com.asgames.ataliasflame.infrastructure.dataloader;

import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.infrastructure.repositories.MonsterRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockMonsterDataLoader implements InitializingBean {
    public static final String MONSTER_CODE = "DUMMY_MONSTER";
    private static final int MONSTER_ATTACK = 75;
    private static final int MONSTER_DEFENSE = 0;
    private static final int MONSTER_DAMAGE = 2;
    private static final int MONSTER_HEALTH = 50;
    private static final int MONSTER_EXPERIENCE = 50;

    @Autowired
    private MonsterRepository monsterRepository;

    @Override
    public void afterPropertiesSet() {
        monsterRepository.save(Monster.builder()
                .code(MONSTER_CODE)
                .attack(MONSTER_ATTACK)
                .defense(MONSTER_DEFENSE)
                .damage(MONSTER_DAMAGE)
                .totalHealth(MONSTER_HEALTH)
                .experience(MONSTER_EXPERIENCE)
                .build());
    }
}
