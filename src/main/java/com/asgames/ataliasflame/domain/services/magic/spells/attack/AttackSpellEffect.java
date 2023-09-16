package com.asgames.ataliasflame.domain.services.magic.spells.attack;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;

public abstract class AttackSpellEffect extends SpellEffect {

    public AttackSpellEffect(SpellName spellName) {
        super(spellName);
    }

    public abstract int getMinDamage();

    public abstract int getMaxDamage();

    @Override
    public int averageDamage() {
        return (getMinDamage() + getMaxDamage()) / 2;
    }
}
