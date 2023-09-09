package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.SoulChipShape;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.SoulChipShape.valueByOrder;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.SoulChipUpgradeEvent.soulChipUpgrade;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Service
public class SoulChipService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    public SoulChip getSoulChip(Character character, int percent) {
        SoulChipShape shape = valueByOrder(character.getSoulChips().size());
        return SoulChip.builder()
                .owner(character)
                .reference(UUID.randomUUID().toString())
                .name(shape.name)
                .shape(shape)
                .attack(SOUL_CHIP_ATTACK_BASE + percent(SOUL_CHIP_ATTACK_BONUS, percent))
                .defense(SOUL_CHIP_DEFENSE_BASE + percent(SOUL_CHIP_DEFENSE_BONUS, percent))
                .minDamage(SOUL_CHIP_MIN_DAMAGE_BASE + percent(SOUL_CHIP_MIN_DAMAGE_BONUS, percent))
                .maxDamage(SOUL_CHIP_MAX_DAMAGE_BASE + percent(SOUL_CHIP_MAX_DAMAGE_BONUS, percent))
                .health(percent(character.getHealth().totalValue(), percent))
                .initiative(SOUL_CHIP_INITIATIVE)
                .upgradedCaste(character.getCaste())
                .upgradePercent(percent)
                .build();
    }

    public void upgradeSoulChips(Character character) {
        character.getSoulChips().forEach(soulChip -> {
            int oldHealth = soulChip.getHealth();
            soulChip.setHealth(percent(character.getHealth().totalValue(), soulChip.getUpgradePercent()));
            storyLineLogger.event(soulChipUpgrade(soulChip, oldHealth));
        });
    }
}
