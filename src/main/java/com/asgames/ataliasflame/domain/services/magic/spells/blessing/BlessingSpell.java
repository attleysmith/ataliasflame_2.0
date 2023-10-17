package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import java.util.Map;
import java.util.stream.Collectors;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.BLESSING;

public abstract class BlessingSpell extends Spell {

    public BlessingSpell(SpellName name) {
        super(name);
        if (!name.type.equals(BLESSING)) {
            throw new IllegalArgumentException(name + " is not a BLESSING spell!");
        }
    }

    protected static String effectDetailsOf(int defense, int absorption, int durability) {
        return Map.of("Defense", defense, "Absorption", absorption, "Durability", durability).entrySet().stream()
                .filter(effect -> effect.getValue() != 0)
                .map(effect -> effect.getKey() + ": " + effect.getValue())
                .collect(Collectors.joining("; "));
    }
}
