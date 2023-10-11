package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.CURSE;

public abstract class CurseSpell extends Spell {

    public CurseSpell(SpellName name) {
        super(name);
        if (!name.type.equals(CURSE)) {
            throw new IllegalArgumentException(name + " is not a CURSE spell!");
        }
    }
}
