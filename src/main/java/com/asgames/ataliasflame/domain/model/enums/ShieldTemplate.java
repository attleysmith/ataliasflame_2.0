package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.SHIELD;

@AllArgsConstructor
public enum ShieldTemplate implements ItemTemplate {
    BUCKLER(30, 1, 60, 30),
    ROUND_SHIELD(50, 2, 70, 50),
    KITE_SHIELD(65, 3, 80, 70),
    TOWER_SHIELD(75, 5, 90, 100);

    private final int blocking;
    private final int defense;
    private final int absorption;
    private final int durability;

    @Override
    public ItemType getType() {
        return SHIELD;
    }

    public Shield instance() {
        return Shield.builder()
                .reference(UUID.randomUUID().toString())
                .code(name())
                .type(getType())
                .blocking(blocking)
                .defense(defense)
                .absorption(absorption)
                .durability(Energy.withTotal(durability))
                .build();
    }
}