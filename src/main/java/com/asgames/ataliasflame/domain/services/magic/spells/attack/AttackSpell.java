package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.ATTACK;

public abstract class AttackSpell extends Spell {

    public AttackSpell(SpellName name) {
        super(name);
        if (!name.type.equals(ATTACK)) {
            throw new IllegalArgumentException(name + " is not an ATTACK spell!");
        }
    }

    @Override
    public int getMinDamage() {
        throw new UnsupportedOperationException("Minimum damage of attack spell must be overridden!");
    }

    @Override
    public int getMaxDamage() {
        throw new UnsupportedOperationException("Maximum damage of attack spell must be overridden!");
    }
}
