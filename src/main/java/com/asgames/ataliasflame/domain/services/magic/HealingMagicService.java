package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.HEALING;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static java.util.Comparator.comparing;

@Slf4j
@Service
public class HealingMagicService extends AttackMagicService {

    private static final int TOLERATED_INJURY = 80;

    public void castHealingMagic(Character character) {
        if (character.getHealth().tolerateLoss(TOLERATED_INJURY)) {
            return;
        }
        selectBestHealingSpell(character).ifPresent(
                spell -> castHealingSpell(character, spell)
        );
    }

    private void castHealingSpell(Character character, Spell spell) {
        if (spell.getGroup().equals(SOUL)
                && listUnusedSouls(character).isEmpty()) {
            return;
        }
        character.getMagic().use(spell.getCost());
        character.getHealth().recover(spell.getHealingEffect());
        log.info("Healed by " + spell.getName());
        if (spell.isArea()) {
            character.getCompanions().stream()
                    .filter(Combatant::isAlive)
                    .forEach(companion -> {
                        companion.getHealth().recover(spell.getHealingEffect());
                        log.info(companion.getName() + " healed!");
                    });
        }
    }

    private Optional<Spell> selectBestHealingSpell(Character character) {
        List<Spell> spells = usableSpellsOfType(character, HEALING);
        switch (spells.size()) {
            case 0:
                return Optional.empty();
            case 1:
                return Optional.of(spells.get(0));
            default:
                return spells.stream()
                        .max(comparing(Spell::getHealingEffect));
        }
    }
}
