package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

public class SoulChipFactory {

    private static String soulChipNamePrefix(Character character) {
        return character.getName() + "'s ";
    }

    public static String getGivenSoulChipName(Character character, String originalName) {
        return soulChipNamePrefix(character) + originalName;
    }

    public static String getOriginalSoulChipName(Character character, String givenName) {
        return givenName.substring(soulChipNamePrefix(character).length());
    }

    public static SoulChip getSoulChip(Character character, int percent) {
        return SoulChip.builder()
                .owner(character)
                .name(getGivenSoulChipName(character, SOUL_CHIP_NAMES.get(character.getSoulChips().size())))
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
}
