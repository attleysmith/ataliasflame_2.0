package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

public abstract class AttackSpell extends Spell {

    public AttackSpell(SpellName name) {
        super(name);
    }

    public abstract int getMinDamage();

    public abstract int getMaxDamage();

    @Override
    public int averageDamage() {
        return (getMinDamage() + getMaxDamage()) / 2;
    }
}
