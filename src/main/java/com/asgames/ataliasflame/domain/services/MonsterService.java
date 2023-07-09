package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.MONSTER_DROPS;
import static com.asgames.ataliasflame.domain.MockConstants.MONSTER_SELECTOR;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Slf4j
@Service
public class MonsterService {

    @Autowired
    private InventoryService inventoryService;

    public Monster getRandomMonster() {
        Monster monster = choose(MONSTER_SELECTOR);
        log.info("Enemy appeared: " + monster.getCode());

        return monster.toBuilder().build(); // monster clone
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
