package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.MONSTER_DROPS;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Slf4j
@Service
public class MonsterService {

    public Monster getRandomMonster() {
        Monster monster = choose(MONSTER_DROPS);
        log.info("Enemy appeared: " + monster.getCode());

        return monster.toBuilder().build(); // monster clone
    }
}
