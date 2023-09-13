package com.asgames.ataliasflame.domain.model.interfaces;

import com.asgames.ataliasflame.domain.model.vos.Energy;

public interface AbsorptionDefense {

    String getCode();

    int getDefense();

    int getAbsorption();

    Energy getDurability();

    default boolean lastsLonger(AbsorptionDefense other) {
        return this.getDurability().actualValue() > other.getDurability().actualValue();
    }

    default boolean sameDurable(AbsorptionDefense other) {
        return this.getDurability().actualValue() == other.getDurability().actualValue();
    }
}
