package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.valueobjects.Energy;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

public class SoulChipFactory {

    public static SoulChip getSoulChip(Character character, int percent) {
        return SoulChip.builder()
                .owner(character)
                .name(character.getName() + "'s " + SOUL_CHIP_NAMES.get(character.getSoulChips().size()))
                .attack(SOUL_CHIP_ATTACK_BASE + percent(SOUL_CHIP_ATTACK_BONUS, percent))
                .defense(SOUL_CHIP_DEFENSE_BASE + percent(SOUL_CHIP_DEFENSE_BONUS, percent))
                .minDamage(SOUL_CHIP_MIN_DAMAGE_BASE + percent(SOUL_CHIP_MIN_DAMAGE_BONUS, percent))
                .maxDamage(SOUL_CHIP_MAX_DAMAGE_BASE + percent(SOUL_CHIP_MAX_DAMAGE_BONUS, percent))
                .health(Energy.withTotal(percent(character.getHealth().totalValue(), percent)))
                .initiative(SOUL_CHIP_INITIATIVE)
                .upgradedCaste(character.getCaste())
                .upgradePercent(percent)
                .build();
    }
}
