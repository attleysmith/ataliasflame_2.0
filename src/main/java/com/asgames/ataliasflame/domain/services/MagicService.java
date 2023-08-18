package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Item;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.services.magic.AttackMagicService;
import com.asgames.ataliasflame.domain.services.magic.BlessingMagicService;
import com.asgames.ataliasflame.domain.services.magic.HealingMagicService;
import com.asgames.ataliasflame.domain.services.magic.SummonMagicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.MAGIC_RECOVERY_EFFECT_OF_SLEEP;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;

@Slf4j
@Service
public class MagicService {

    @Autowired
    private SummonMagicService summonMagicService;
    @Autowired
    private AttackMagicService attackMagicService;
    @Autowired
    private BlessingMagicService blessingMagicService;
    @Autowired
    private HealingMagicService healingMagicService;

    public void sleep(Character character) {
        recover(character, MAGIC_RECOVERY_EFFECT_OF_SLEEP);
    }

    public void eat(Character character, Item item) {
        if (!item.getType().equals(FOOD)) {
            throw new IllegalArgumentException("Only food can be eaten!");
        }
        recover(character, item.getMagicEffect());
    }

    private void recover(Character character, int recoveryEffect) {
        if (character.getMagic().isFull()) {
            log.debug("Unnecessary magic recovery!");
            return;
        }

        character.getMagic().recover(recoveryEffect);
        log.info("Recovering to " + character.getMagic().actualValue() + " magic point!");
    }

    public void castSummoningMagic(Character character) {
        summonMagicService.castSummoningMagic(character);
    }

    public void castAttackMagic(Character character, List<Monster> monsters) {
        attackMagicService.castAttackMagic(character, monsters);
    }

    public void castBlessingMagic(Character character) {
        blessingMagicService.castBlessingMagic(character);
    }

    public void removeBlessingMagic(Character character) {
        blessingMagicService.removeBlessingMagic(character);
    }

    public void castHealingMagic(Character character) {
        healingMagicService.castHealingMagic(character);
    }
}
