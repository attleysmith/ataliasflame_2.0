package com.asgames.ataliasflame.domain.services.magic.spells.healing;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

public abstract class HealingSpell extends Spell {

    public HealingSpell(SpellName name) {
        super(name);
    }
}
