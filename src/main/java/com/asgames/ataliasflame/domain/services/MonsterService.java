package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.MonsterTemplate;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.services.MonsterDrops.getDrops;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.DropEvent.drop;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@Service
public class MonsterService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private InventoryService inventoryService;

    public List<Monster> populateMonsters(Location location) {
        List<Monster> monsters = new ArrayList<>();
        List<SelectionValue<MonsterTemplate>> monsterSelector = Stream.of(MonsterTemplate.values())
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

    public void processMonsters(List<Monster> monsters) {
        monsters.stream()
                .filter(Combatant::isDead)
                .forEach(this::processMonster);
    }

    private void processMonster(Monster monster) {
        getDrops(monster.getCode()).forEach(dropGroup ->
                choose(dropGroup).ifPresent(drop -> {
                    Item item = inventoryService.produce(drop);
                    monster.getLocation().getItems().add(item);
                    storyLineLogger.event(drop(monster, item));
                }));
    }
}
