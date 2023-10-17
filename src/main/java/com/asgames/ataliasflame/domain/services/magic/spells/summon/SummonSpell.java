package com.asgames.ataliasflame.domain.services.magic.spells.summon;

import com.asgames.ataliasflame.domain.model.enums.CompanionTemplate;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import com.asgames.ataliasflame.domain.utils.SelectionValue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.SUMMON;

public abstract class SummonSpell extends Spell {

    public SummonSpell(SpellName name) {
        super(name);
        if (!name.type.equals(SUMMON)) {
            throw new IllegalArgumentException(name + " is not a SUMMON spell!");
        }
    }

    protected static <T extends CompanionTemplate> String summonings(List<SelectionValue<Optional<T>>> selector) {
        return selector.stream()
                .map(selection -> selection.getValue()
                        .map(CompanionTemplate::getCode).orElse("Chance of failure")
                        + ": " + selection.getChance() + "%")
                .collect(Collectors.joining("; "));
    }
}
