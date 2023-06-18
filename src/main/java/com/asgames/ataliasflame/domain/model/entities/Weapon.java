package com.asgames.ataliasflame.domain.model.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Weapon {

    private String code;
    private int damage;
    private int defense;
}
