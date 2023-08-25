package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.ItemTemplate;
import com.asgames.ataliasflame.domain.model.dtos.MonsterTemplate;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
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

    public void processMonsters(Location location) {
        location.getMonsters().stream()
                .filter(Combatant::isDead)
                .forEach(this::processMonster);
    }

    private void processMonster(Monster monster) {
        List<List<SelectionValue<Optional<ItemTemplate>>>> drops = MONSTER_DROPS.get(monster.getCode());
        if (drops == null) {
            return;
        }
        for (List<SelectionValue<Optional<ItemTemplate>>> drop : drops) {
            choose(drop).ifPresent(item -> {
                monster.getLocation().getItems().add(item.instance());
                storyLineLogger.event(DEBUG, monster.getCode() + " dropped " + item.getCode());
            });
        }
    }
}
