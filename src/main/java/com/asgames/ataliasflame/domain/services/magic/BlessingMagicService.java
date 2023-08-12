package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.MockConstants.BOOSTERS;
import static com.asgames.ataliasflame.domain.model.enums.MagicType.BLESSING;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.services.SoulChipFactory.getOriginalSoulChipName;

@Slf4j
@Service
public class BlessingMagicService extends AttackMagicService {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public void castBlessingMagic(Character character) {
        selectBestSpell(character, BLESSING).ifPresent(
                spell -> castBlessingSpell(character, spell)
        );
    }

    private void castBlessingSpell(Character character, Spell spell) {
        Optional<String> boosterName = Optional.of(spell.getName().name());
        if (spell.getGroup().equals(SOUL)) {
            boosterName = getSoulBooster(character);
        }
        boosterName.ifPresent(booster -> enforceBoosterEffect(character, booster));
        character.getMagic().use(spell.getCost());
        log.info("Blessed by " + spell.getName());
    }

    private Optional<String> getSoulBooster(Character character) {
        List<SoulChip> unusedSouls = listUnusedSouls(character);
        if (unusedSouls.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(getOriginalSoulChipName(character, unusedSouls.get(0).getName()));
        }
    }

    private void enforceBoosterEffect(Character character, String booster) {
        int originalHealth = character.getHealth().totalValue();
        characterCalculationService.recalculateProperties(character, BOOSTERS.get(booster));
        int blessedHealth = character.getHealth().totalValue();
        if (blessedHealth > originalHealth) {
            character.getHealth().replenish(blessedHealth - originalHealth);
        }
    }

    public void removeBlessingMagic(Character character) {
        characterCalculationService.recalculateProperties(character);
        if (character.getHealth().isEmpty()) {
            throw new IllegalStateException("You died of an expired bless effect!");
        }
    }
}
