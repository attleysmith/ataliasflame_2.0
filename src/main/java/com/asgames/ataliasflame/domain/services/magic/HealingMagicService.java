package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.HEALING;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

@Slf4j
@Service
public class HealingMagicService extends AttackMagicService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    public void castHealingSpell(Character character, Spell spell) {
        if (!spell.getType().equals(HEALING)) {
            throw new IllegalArgumentException("Healing spell expected!");
        }
        if (spell.getGroup().equals(SOUL)
                && listUnusedSouls(character).isEmpty()) {
            storyLineLogger.event(DEBUG, "Soul chips are occupied!");
            return;
        }
        character.getMagic().use(spell.getCost());
        character.getHealth().recover(spell.getHealingEffect());
        storyLineLogger.event(INFO, "Healed by " + spell.getName());
        if (spell.isArea()) {
            character.getCompanions().stream()
                    .filter(Combatant::isAlive)
                    .forEach(companion -> {
                        companion.getHealth().recover(spell.getHealingEffect());
                        storyLineLogger.event(INFO, companion.getName() + " healed!");
                    });
        }
    }
}
