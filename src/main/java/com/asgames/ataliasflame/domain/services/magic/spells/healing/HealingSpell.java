package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.HEALING;

public abstract class HealingSpell extends Spell {

    public HealingSpell(SpellName name) {
        super(name);
        if (!name.type.equals(HEALING)) {
            throw new IllegalArgumentException(name + " is not a HEALING spell!");
        }
    }
}
