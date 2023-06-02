package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.CombatContext.Round;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;
import static java.lang.Math.min;

@Slf4j
@Service
public class CombatService {

    public void combat(List<Combatant> team1, List<Combatant> team2) {
        if (team1.size() == 0 || team2.size() == 0) {
            log.warn("Combat without an enemy.");
            return;
        }
        if (team1.size() > 1 || team2.size() > 1) {
            throw new UnsupportedOperationException("Only 1 vs 1 combat is available!");
        }

        Combatant combatant1 = team1.get(0);
        Combatant combatant2 = team2.get(0);
        log.debug(combatant1.toString());
        log.debug(combatant2.toString());

        CombatContext combatContext = new CombatContext();
        while (combatant1.getActualHealth() > 0 && combatant2.getActualHealth() > 0) {
            combatContext.addRound(new Round(
                    attack(combatant1, combatant2),
                    attack(combatant2, combatant1)));
        }
        log.debug(combatContext.report());
    }

    private AttackReport attack(Combatant attacker, Combatant defender) {
        int damage = 0;
        if (attacker.getActualHealth() > 0) {
            int chance = attacker.getAttack() - defender.getDefense();
            if (successX(chance)) {
                damage = attacker.getDamage();
                defender.setInjury(min(defender.getInjury() + damage, defender.getTotalHealth()));
            }
        }
        return new AttackReport(attacker.getCode(), damage, defender.getActualHealth());
    }
}
