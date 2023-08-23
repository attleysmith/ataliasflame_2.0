package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Item;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.AttackMagicService;
import com.asgames.ataliasflame.domain.services.magic.BlessingMagicService;
import com.asgames.ataliasflame.domain.services.magic.HealingMagicService;
import com.asgames.ataliasflame.domain.services.magic.SummonMagicService;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.MAGIC_RECOVERY_EFFECT_OF_SLEEP;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

@Slf4j
@Service
public class MagicService {

    @Autowired
    private StoryLineLogger storyLineLogger;

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
            return;
        }

        character.getMagic().recover(recoveryEffect);
        storyLineLogger.event(INFO, "Recovering magic -> " + character.getMagic().actualValue());
    }

    public void castSpell(Character character, Spell spell) {
        if (!character.getMagic().has(spell.getCost())) {
            throw new IllegalArgumentException("Character does not have enough magic to cast spell!");
        }
        switch (spell.getType()) {
            case SUMMON:
                summonMagicService.castSummoningSpell(character, spell);
                break;
            case BLESSING:
                blessingMagicService.castBlessingSpell(character, spell);
                break;
            case HEALING:
                healingMagicService.castHealingSpell(character, spell);
            case ATTACK:
            default:
                throw new UnsupportedOperationException("Spell type is not supported: " + spell.getType());
        }
    }

    public void castAttackMagic(Character character, List<Monster> monsters) {
        attackMagicService.castAttackMagic(character, monsters);
    }

    public void removeBlessingMagic(Character character) {
        blessingMagicService.removeBlessingMagic(character);
    }
}
