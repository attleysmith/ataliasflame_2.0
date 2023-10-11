package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.SUMMON;

public abstract class SummonSpell extends Spell {

    public SummonSpell(SpellName name) {
        super(name);
        if (!name.type.equals(SUMMON)) {
            throw new IllegalArgumentException(name + " is not a SUMMON spell!");
        }
    }
}
