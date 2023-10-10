package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

public abstract class CurseSpell extends Spell {

    public CurseSpell(SpellName name) {
        super(name);
    }
}
