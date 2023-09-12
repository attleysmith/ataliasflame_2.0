package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import com.asgames.ataliasflame.domain.utils.SelectionValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.ArmorTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.FoodTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.MonsterTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.ShieldTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.WeaponTemplate.*;

public final class MonsterDrops {

    private MonsterDrops() {
    }

    private static final Map<String, List<List<SelectionValue<Optional<ItemTemplate>>>>> MONSTER_DROPS = Map.of(
            BOAR.name(), List.of(
                    List.of(
                            new SelectionValue<>(10, Optional.empty()),
                            new SelectionValue<>(90, Optional.of(MEAT))
                    )),
            BANDIT.name(), List.of(
                    List.of(
                            new SelectionValue<>(90, Optional.empty()),
                            new SelectionValue<>(10, Optional.of(WATER))),
                    List.of(
                            new SelectionValue<>(85, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(BREAD))),
                    List.of(
                            new SelectionValue<>(90, Optional.empty()),
                            new SelectionValue<>(10, Optional.of(FRUIT))),
                    List.of(
                            new SelectionValue<>(85, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(MEAT))),
                    List.of(
                            new SelectionValue<>(95, Optional.empty()),
                            new SelectionValue<>(5, Optional.of(HEALING_HERB))),
                    List.of(
                            new SelectionValue<>(5, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(STAFF)),
                            new SelectionValue<>(25, Optional.of(DAGGER)),
                            new SelectionValue<>(25, Optional.of(SPEAR)),
                            new SelectionValue<>(30, Optional.of(SWORD))),
                    List.of(
                            new SelectionValue<>(40, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(BUCKLER)),
                            new SelectionValue<>(25, Optional.of(ROUND_SHIELD)),
                            new SelectionValue<>(15, Optional.of(KITE_SHIELD)),
                            new SelectionValue<>(5, Optional.of(TOWER_SHIELD))),
                    List.of(
                            new SelectionValue<>(10, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(LINEN_ARMOR)),
                            new SelectionValue<>(20, Optional.of(LEATHER_ARMOR)),
                            new SelectionValue<>(25, Optional.of(STUDDED_LEATHER_ARMOR)),
                            new SelectionValue<>(15, Optional.of(CHAIN_MAIL)),
                            new SelectionValue<>(10, Optional.of(PLATE_MAIL)),
                            new SelectionValue<>(5, Optional.of(FULL_PLATE_MAIL))
                    )),
            WEREWOLF.name(), List.of(
                    List.of(
                            new SelectionValue<>(65, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(WATER)),
                            new SelectionValue<>(15, Optional.of(MEAT)),
                            new SelectionValue<>(5, Optional.of(HEALING_HERB))),
                    List.of(
                            new SelectionValue<>(85, Optional.empty()),
                            new SelectionValue<>(10, Optional.of(DAGGER)),
                            new SelectionValue<>(5, Optional.of(SWORD))
                    )),
            NAGA.name(), List.of(
                    List.of(
                            new SelectionValue<>(60, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(WATER)),
                            new SelectionValue<>(15, Optional.of(MEAT)),
                            new SelectionValue<>(10, Optional.of(HEALING_HERB))),
                    List.of(
                            new SelectionValue<>(50, Optional.empty()),
                            new SelectionValue<>(25, Optional.of(DAGGER)),
                            new SelectionValue<>(15, Optional.of(SPEAR)),
                            new SelectionValue<>(10, Optional.of(SWORD))),
                    List.of(
                            new SelectionValue<>(90, Optional.empty()),
                            new SelectionValue<>(10, Optional.of(LEATHER_ARMOR))
                    )),
            OGRE.name(), List.of(
                    List.of(
                            new SelectionValue<>(60, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(WATER)),
                            new SelectionValue<>(20, Optional.of(MEAT)),
                            new SelectionValue<>(5, Optional.of(HEALING_HERB))),
                    List.of(
                            new SelectionValue<>(65, Optional.empty()),
                            new SelectionValue<>(15, Optional.of(DAGGER)),
                            new SelectionValue<>(20, Optional.of(SPEAR))
                    )),
            GHOUL.name(), List.of(
                    List.of(
                            new SelectionValue<>(80, Optional.empty()),
                            new SelectionValue<>(20, Optional.of(DAGGER))
                    ))
    );

    public static List<List<SelectionValue<Optional<ItemTemplate>>>> getDrops(String monsterCode) {
        return MONSTER_DROPS.getOrDefault(monsterCode, List.of());
    }
}
