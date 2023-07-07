package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.HEALING_EFFECT_OF_SLEEP;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static java.lang.Math.max;

@Slf4j
@Service
public class HealingService {

    public Character sleep(Character character) {
        return heal(character, HEALING_EFFECT_OF_SLEEP);
    }

    private Character heal(Character character, int healingEffect) {
        if (character.getInjury() == 0) {
            log.info("Unnecessary healing!");
            return character;
        }

        int healingValue = percent(character.getTotalHealth(), healingEffect);
        int remainingInjury = max(0, character.getInjury() - healingValue);

        character.setInjury(remainingInjury);
        log.info("Healing to " + character.getActualHealth() + "!");

        return character;
    }
}
