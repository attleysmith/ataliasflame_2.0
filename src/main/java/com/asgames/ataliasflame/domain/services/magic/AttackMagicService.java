package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.SpellService;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.ATTACK;
import static com.asgames.ataliasflame.domain.model.enums.SpellGroup.SOUL;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class AttackMagicService extends AbstractMagicService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private SpellService spellService;

    public void castAttackMagic(Character character, List<Monster> monsters) {
        monsters.forEach(monster -> castAttackMagic(character, monster));
    }

    public void castAttackMagic(Character character, Monster monster) {
        AtomicBoolean tryToAttack = new AtomicBoolean(true);
        while (tryToAttack.get() && monster.isAlive()) {
            selectBestAttackSpell(character).ifPresentOrElse(
                    spell -> castAttackSpell(character, monster, spell),
                    () -> tryToAttack.set(false)
            );
        }
    }

    private void castAttackSpell(Character character, Monster monster, Spell spell) {
        if (monster.isDead()) {
            storyLineLogger.event(DEBUG, "Unnecessary use of attack spell!");
            return;
        }
        character.getMagic().use(spell.getCost());
        int damage = pointOut(spell.getMinDamage(), spell.getMaxDamage());
        monster.getHealth().damage(damage);
        storyLineLogger.event(DEBUG, spell.getName() + " deals " + damage + " damage to " + monster.getReference());
    }

    private Optional<Spell> selectBestAttackSpell(Character character) {
        List<Spell> spells = usableAttackSpells(character);
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

    private List<Spell> usableAttackSpells(Character character) {
        return spellService.listSpellsByType(character, ATTACK).stream()
                .filter(spell -> character.getMagic().has(spell.getCost()))
                .filter(spell -> !(spell.getGroup().equals(SOUL) && listUnusedSouls(character).isEmpty()))
                .collect(toList());
    }
}
