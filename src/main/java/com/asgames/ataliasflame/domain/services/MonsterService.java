package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Item;
import com.asgames.ataliasflame.domain.model.dtos.Monster;
import com.asgames.ataliasflame.domain.model.dtos.MonsterTemplate;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.MONSTER_DROPS;
import static com.asgames.ataliasflame.domain.MockConstants.MONSTER_SELECTOR;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Slf4j
@Service
public class MonsterService {

    @Autowired
    private InventoryService inventoryService;

    public List<Monster> populateMonsters() {
        List<Monster> monsters = new ArrayList<>();

        MonsterTemplate monsterAppeared = choose(MONSTER_SELECTOR);
        log.info("Enemy appeared: " + monsterAppeared.getCode());

        do {
            Monster monster = monsterAppeared.instance();
            monsters.add(monster);
        } while (successX(monsterAppeared.getMass()));

        log.info("Number of enemies: " + monsters.size());
        return monsters;
    }

    public void looting(Character character, List<Monster> monsters) {
        monsters.forEach(monster -> looting(character, monster));
    }

    public void looting(Character character, Monster monster) {
        List<SelectionValue<Optional<Item>>> drops = MONSTER_DROPS.get(monster.getCode());
        if (drops == null) {
            return;
        }
        choose(drops).ifPresent(item -> {
            log.info(item.getCode() + " gained!");
            inventoryService.use(character, item);
        });
    }
}
