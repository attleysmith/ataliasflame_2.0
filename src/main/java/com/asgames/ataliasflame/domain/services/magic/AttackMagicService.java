package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.asgames.ataliasflame.domain.model.enums.MagicType.ATTACK;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;

@Slf4j
@Service
public class AttackMagicService extends AbstractMagicService {

    public void castAttackMagic(Character character, List<Monster> monsters) {
        monsters.forEach(monster -> castAttackMagic(character, monster));
    }

    public void castAttackMagic(Character character, Monster monster) {
        AtomicBoolean tryToAttack = new AtomicBoolean(true);
        while (tryToAttack.get() && monster.isAlive()) {
            selectBestSpell(character, ATTACK).ifPresentOrElse(
                    spell -> castAttackSpell(character, monster, spell),
                    () -> tryToAttack.set(false)
            );
        }
    }

    private void castAttackSpell(Character character, Monster monster, Spell spell) {
        if (monster.isDead()) {
            log.warn("Unnecessary use of attack spell!");
            return;
        }
        character.getMagic().use(spell.getCost());
        int damage = pointOut(spell.getMinDamage(), spell.getMaxDamage());
        monster.getHealth().damage(damage);
        log.info(spell.getName() + " deals " + damage + " damage to " + monster.getReference());
    }
}
