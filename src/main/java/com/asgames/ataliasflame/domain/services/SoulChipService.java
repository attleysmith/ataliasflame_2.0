package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.SoulChipShape;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.SoulChipShape.valueByOrder;
import static com.asgames.ataliasflame.domain.services.storyline.events.SoulChipEvents.SoulChipUpgradeEvent.soulChipUpgrade;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Service
public class SoulChipService {

    private static final int SOUL_CHIP_ATTACK_BASE = 50;
    private static final int SOUL_CHIP_ATTACK_BONUS = 100;
    private static final int SOUL_CHIP_DEFENSE_BASE = 0;
    private static final int SOUL_CHIP_DEFENSE_BONUS = 50;
    private static final int SOUL_CHIP_MIN_DAMAGE_BASE = 1;
    private static final int SOUL_CHIP_MIN_DAMAGE_BONUS = 1;
    private static final int SOUL_CHIP_MAX_DAMAGE_BASE = 5;
    private static final int SOUL_CHIP_MAX_DAMAGE_BONUS = 10;
    private static final int SOUL_CHIP_INITIATIVE = -1;

    private static final int SLEEP_RECOVERY_EFFECT = 80;
    private static final int REST_RECOVERY_EFFECT = 5;

    @Autowired
    private StoryLineLogger storyLineLogger;

    public SoulChip getSoulChip(Character character, int percent) {
        SoulChipShape shape = valueByOrder(character.getSoulChips().size());
        int health = percent(character.getHealth().totalValue(), percent);
        return SoulChip.builder()
                .reference(UUID.randomUUID().toString())
                .name(shape.name)
                .shape(shape)
                .owner(character)
                .attack(SOUL_CHIP_ATTACK_BASE + percent(SOUL_CHIP_ATTACK_BONUS, percent))
                .defense(SOUL_CHIP_DEFENSE_BASE + percent(SOUL_CHIP_DEFENSE_BONUS, percent))
                .minDamage(SOUL_CHIP_MIN_DAMAGE_BASE + percent(SOUL_CHIP_MIN_DAMAGE_BONUS, percent))
                .maxDamage(SOUL_CHIP_MAX_DAMAGE_BASE + percent(SOUL_CHIP_MAX_DAMAGE_BONUS, percent))
                .health(Energy.withTotal(health))
                .initiative(SOUL_CHIP_INITIATIVE)
                .upgradedCaste(character.getCaste())
                .effectiveness(percent)
                .build();
    }

    public void upgradeSoulChips(Character character) {
        character.getSoulChips().forEach(soulChip -> {
            int oldHealth = soulChip.getHealth().totalValue();
            int newHealth = percent(character.getHealth().totalValue(), soulChip.getEffectiveness());
            soulChip.getHealth().set(newHealth);
            soulChip.getHealth().uplift(oldHealth);
            storyLineLogger.event(soulChipUpgrade(soulChip, oldHealth));
        });
    }

    public void sleep(Character character) {
        character.getSoulChips()
                .forEach(soulChip -> soulChip.getHealth().recover(SLEEP_RECOVERY_EFFECT));
    }

    public void rest(Character character) {
        character.getSoulChips()
                .forEach(soulChip -> soulChip.getHealth().recover(REST_RECOVERY_EFFECT));
    }
}
