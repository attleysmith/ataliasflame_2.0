package com.asgames.ataliasflame.application.scenarios;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.SpellName;

import java.util.List;
import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public final class Decisions {

    private Decisions() {
    }

    public static final int MAX_NUMBER_OF_COMPANIONS = 5;

    public static final Map<SpellName, Integer> SUMMON_PREFERENCES = Map.of(
            PROJECTION_OF_ENERGY, 1,
            FRIEND_IN_NEED, 2,
            SUMMON_GUARDIAN, 3,
            CALLING_ANIMALS, 4,
            CALLING_THE_SOULS, 5);

    public static final Map<SpellName, Integer> BLESSING_PREFERENCES = Map.of(
            DIVINE_PROTECTION, 1,
            STRENGTHENING, 2,
            PROTECTIVE_HAND_OF_NATURE, 3,
            SOUL_CONNECTION, 4,
            ENERGY_SHIELD, 5);

    public static boolean noNeedToSummon(Character character) {
        return character.getCompanions().size() >= MAX_NUMBER_OF_COMPANIONS;
    }

    public static boolean repeatSummon(Character character, int previousNumberOfCompanions) {
        int actualNumberOfCompanions = character.getCompanions().size();
        return previousNumberOfCompanions < actualNumberOfCompanions
                && actualNumberOfCompanions < MAX_NUMBER_OF_COMPANIONS;
    }

    public static List<Spell> summonOrder(List<Spell> usableSpells) {
        return usableSpells.stream()
                .sorted(comparing(spell -> SUMMON_PREFERENCES.getOrDefault(spell.getName(), 0)))
                .collect(toList());
    }

    private static int maxNumberOfBlessings(Character character, List<Monster> monsters) {
        int overpowering = character.getHealth().actualValue()
                +
                character.getCompanions().stream()
                        .map(companion -> companion.getHealth().actualValue())
                        .reduce(0, Integer::sum)
                -
                monsters.stream()
                        .map(monster -> monster.getHealth().actualValue())
                        .reduce(0, Integer::sum);

        if (overpowering < -150) return 5;
        else if (overpowering < -50) return 4;
        else if (overpowering < 50) return 3;
        else if (overpowering < 100) return 2;
        else if (overpowering < 150) return 1;
        else return 0;
    }

    public static boolean repeatBlessing(Character character, List<Monster> monsters, Spell spell, int previousNumberOfBlessings) {
        int actualNumberOfBlessings = character.getBlessings().size();
        return previousNumberOfBlessings < actualNumberOfBlessings
                && actualNumberOfBlessings < maxNumberOfBlessings(character, monsters)
                && !character.getBlessings().contains(spell.getName().name());
    }

    public static List<Spell> blessingOrder(List<Spell> usableSpells) {
        return usableSpells.stream()
                .sorted(comparing(spell -> BLESSING_PREFERENCES.getOrDefault(spell.getName(), 0)))
                .collect(toList());
    }
}
