package com.asgames.ataliasflame.domain.model.interfaces;

import com.asgames.ataliasflame.domain.model.vos.Energy;

public interface AbsorptionDefense {

    String getCode();

    int getDefense();

    int getAbsorption();

    Energy getDurability();
}
