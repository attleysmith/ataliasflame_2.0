package com.asgames.ataliasflame.domain.services.magic.spells;

import java.util.Map;

public final class SpellArgsValidatorUtil {

    private SpellArgsValidatorUtil() {
        // utility class
    }

    public static void numberOfArgs(Map<String, String> args, int size) {
        if (args.size() != size) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }
    }

    public static void argExists(Map<String, String> args, String argKey) {
        if (!args.containsKey(argKey)) {
            throw new IllegalArgumentException("Missing argument: " + argKey);
        }
    }

    public static void argValueInRange(Map<String, String> args, String argKey, int min, int max) {
        try {
            int value = Integer.parseInt(args.get(argKey));
            if (value < min || max < value) {
                throw new IllegalArgumentException("Argument [" + argKey + "] must be between " + min + " and " + max + ".");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Argument [" + argKey + "] must be a number!");
        }
    }
}
