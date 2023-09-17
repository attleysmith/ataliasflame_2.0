package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.enums.SpellGroup;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.ATTACK;

public abstract class AttackSpell extends Spell {

    public AttackSpell(SpellName name, SpellGroup group) {
        super(name, group, ATTACK);
    }

    public abstract int getMinDamage();

    public abstract int getMaxDamage();

    @Override
    public int averageDamage() {
        return (getMinDamage() + getMaxDamage()) / 2;
    }
}
