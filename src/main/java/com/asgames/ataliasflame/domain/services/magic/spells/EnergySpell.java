package com.asgames.ataliasflame.domain.services.magic.spells;

import java.util.Map;

import static com.asgames.ataliasflame.domain.services.magic.spells.SpellArgsValidatorUtil.*;

public interface EnergySpell {

    String ARG_KEY_ENERGY = "energy";

    class EnergyArgs {

        public final int energyPercentage;

        public EnergyArgs(Map<String, String> args) {
            validateArgs(args);
            energyPercentage = Integer.parseInt(args.get(ARG_KEY_ENERGY));
        }

        public static void validateArgs(Map<String, String> args) {
            numberOfArgs(args, 1);
            argExists(args, ARG_KEY_ENERGY);
            argValueInRange(args, ARG_KEY_ENERGY, 1, 100);
        }
    }

}
