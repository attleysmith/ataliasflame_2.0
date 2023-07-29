package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.Attribute;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class Booster {

    private Map<Attribute, Integer> effects;

}
