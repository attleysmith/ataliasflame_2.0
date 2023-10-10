package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

public abstract class BlessingSpell extends Spell {

    public BlessingSpell(SpellName name) {
        super(name);
    }
}
