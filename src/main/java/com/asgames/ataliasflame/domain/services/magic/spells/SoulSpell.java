package com.asgames.ataliasflame.domain.services.magic.spells;

import com.asgames.ataliasflame.domain.model.entities.ActiveBlessing;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.entities.SummonedSoulChip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.SOUL_CHIP;
import static com.asgames.ataliasflame.domain.services.magic.spells.SpellArgsValidatorUtil.argExists;
import static com.asgames.ataliasflame.domain.services.magic.spells.SpellArgsValidatorUtil.numberOfArgs;

public interface SoulSpell {

    String ARG_KEY_SOUL_CHIP = "soulChip";

    class SoulArgs {

        public final String soulChipReference;

        public SoulArgs(Map<String, String> args) {
            validateArgs(args);
            soulChipReference = args.get(ARG_KEY_SOUL_CHIP);
        }

        public static void validateArgs(Map<String, String> args) {
            numberOfArgs(args, 1);
            argExists(args, ARG_KEY_SOUL_CHIP);
        }
    }

    default SoulChip getSoulChip(Character character, String soulChipReference) {
        List<SoulChip> unusedSouls = new ArrayList<>(character.getSoulChips());
        character.getCompanions().stream()
                .filter(companion -> companion.getType().equals(SOUL_CHIP))
                .map(companion -> ((SummonedSoulChip) companion).getSource())
                .forEach(unusedSouls::remove);
        character.getBlessings().stream()
                .map(ActiveBlessing::getSource)
                .filter(Objects::nonNull)
                .forEach(unusedSouls::remove);
        return unusedSouls.stream()
                .filter(SoulChip::isReady)
                .filter(soulChip -> soulChip.getReference().equals(soulChipReference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Referenced soul chip is not available!"));
    }

}
