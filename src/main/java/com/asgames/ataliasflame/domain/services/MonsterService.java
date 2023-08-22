package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Item;
import com.asgames.ataliasflame.domain.model.dtos.MonsterTemplate;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.asgames.ataliasflame.domain.MockConstants.MONSTERS;
import static com.asgames.ataliasflame.domain.MockConstants.MONSTER_DROPS;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Slf4j
@Service
public class MonsterService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private InventoryService inventoryService;

    public List<Monster> populateMonsters(Location location) {
        List<Monster> monsters = new ArrayList<>();
        List<SelectionValue<MonsterTemplate>> monsterSelector = MONSTERS.values().stream()
                .filter(monsterTemplate -> monsterTemplate.getLevel() <= location.getLevel())
                .map(monsterTemplate -> new SelectionValue<>(monsterTemplate.getChance(), monsterTemplate))
                .collect(Collectors.toList());

        MonsterTemplate monsterAppeared = choose(monsterSelector);
        do {
            Monster monster = monsterAppeared.instance(location);
            monsters.add(monster);
        } while (successX(monsterAppeared.getSpawn()));

        return monsters;
    }

    public void lootMonster(Character character, Monster monster) {
        List<List<SelectionValue<Optional<Item>>>> drops = MONSTER_DROPS.get(monster.getCode());
        if (drops == null) {
            return;
        }
        for (List<SelectionValue<Optional<Item>>> drop : drops) {
            choose(drop).ifPresent(item -> {
                storyLineLogger.event(DEBUG, item.getCode() + " gained!");
                inventoryService.use(character, item);
            });
        }
    }
}
