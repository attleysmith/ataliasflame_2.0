package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.BLESSING;

public abstract class BlessingSpell extends Spell {

    public BlessingSpell(SpellName name) {
        super(name);
        if (!name.type.equals(BLESSING)) {
            throw new IllegalArgumentException(name + " is not a BLESSING spell!");
        }
    }
}
