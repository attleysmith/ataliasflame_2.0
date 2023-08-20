package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.services.SpellService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public abstract class AbstractMagicService {

    @Autowired
    private SpellService spellService;

    protected List<Spell> usableSpellsOfType(Character character, MagicType magicType) {
        return spellService.listSpellsByType(character, magicType).stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> !(spell.getGroup().equals(SOUL) && listUnusedSouls(character).isEmpty()))
                .collect(toList());
    }

    protected Optional<Spell> selectBestSpell(Character character, MagicType magicType) {
        List<Spell> spells = usableSpellsOfType(character, magicType);
        switch (spells.size()) {
            case 0:
                return Optional.empty();
            case 1:
                return Optional.of(spells.get(0));
            default:
                return spells.stream()
                        .max(comparing(Spell::getCost));
        }
    }

    protected List<SoulChip> listUnusedSouls(Character character) {
        List<SoulChip> unusedSouls = new ArrayList<>(character.getSoulChips());
        List<String> companionReferences = character.getCompanions().stream().map(Companion::getReference).collect(toList());
        for (SoulChip soulChip : character.getSoulChips()) {
            if (companionReferences.contains(soulChip.getReference())) {
                unusedSouls.remove(soulChip);
            }
        }
        return unusedSouls;
    }
}
