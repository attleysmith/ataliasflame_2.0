package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.enums.SpellGroup;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.BLESSING;

public abstract class BlessingSpell extends Spell {

    public BlessingSpell(SpellName name, SpellGroup group) {
        super(name, group, BLESSING);
    }
}
