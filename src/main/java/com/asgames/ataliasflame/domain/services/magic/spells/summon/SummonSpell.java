package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

public abstract class SummonSpell extends Spell {

    public SummonSpell(SpellName name) {
        super(name);
    }
}
