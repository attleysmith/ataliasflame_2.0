package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class AbstractMagicService {

    protected List<SoulChip> listUnusedSouls(Character character) {
        List<SoulChip> unusedSouls = new ArrayList<>(character.getSoulChips());
        List<String> companionReferences = character.getCompanions().stream().map(Companion::getReference).collect(toList());
        for (SoulChip soulChip : character.getSoulChips()) {
            if (companionReferences.contains(soulChip.getReference())
                    || character.getBlessings().contains(soulChip.getShape().name())) {
                unusedSouls.remove(soulChip);
            }
        }
        return unusedSouls;
    }
}
