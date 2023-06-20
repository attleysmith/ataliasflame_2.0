package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.MONSTER_DROPS;

@Slf4j
@Service
public class MonsterService {

    @Autowired
    private CalculatorService calculatorService;

    public Monster getRandomMonster() {
        Monster monster = calculatorService.choose(MONSTER_DROPS);
        log.info("Enemy appeared: " + monster.getCode());

        return monster.toBuilder().build(); // monster clone
    }
}
