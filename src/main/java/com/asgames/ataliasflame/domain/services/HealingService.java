package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.HEALING_EFFECT_OF_SLEEP;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static java.lang.Math.max;

@Slf4j
@Service
public class HealingService {

    public void sleep(Character character) {
        heal(character, HEALING_EFFECT_OF_SLEEP);
    }

    public void eat(Character character, Item item) {
        if (!item.getType().equals(FOOD)) {
            throw new IllegalArgumentException("Only food can be eaten!");
        }
        heal(character, item.getHealingEffect());
    }

    private void heal(Character character, int healingEffect) {
        if (character.getInjury() == 0) {
            log.debug("Unnecessary healing!");
            return;
        }

        int healingValue = percent(character.getTotalHealth(), healingEffect);
        int remainingInjury = max(0, character.getInjury() - healingValue);

        character.setInjury(remainingInjury);
        log.info("Healing to " + character.getActualHealth() + "!");
    }
}
