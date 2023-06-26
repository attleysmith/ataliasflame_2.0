package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.CombatContext.Round;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.asgames.ataliasflame.domain.utils.DiceUtils.*;
import static java.lang.Math.min;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class CombatService {

    @Autowired
    private CalculatorService calculatorService;

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
            List<Combatant> combatOrder = getCombatOrder(combatant1, combatant2);
            combatContext.addRound(new Round(
                    attack(combatOrder.get(0), combatOrder.get(1)),
                    attack(combatOrder.get(1), combatOrder.get(0))));
        }
        log.debug(combatContext.report());
    }

    private AttackReport attack(Combatant attacker, Combatant defender) {
        int damage = 0;
        if (attacker.getActualHealth() > 0) {
            int chance = attacker.getAttack() - defender.getDefense();
            if (successX(chance)) {
                damage = calculatorService.pointOut(attacker.getMinDamage(), attacker.getMaxDamage());
                defender.setInjury(min(defender.getInjury() + damage, defender.getTotalHealth()));
            }
        }
        return new AttackReport(attacker.getCode(), damage, defender.getActualHealth());
    }

    private List<Combatant> getCombatOrder(Combatant... combatants) {
        return stream(combatants)
                .collect(toMap(
                        combatant -> combatant,
                        combatant -> combatant.getInitiative() + roll10())
                )
                .entrySet().stream().sorted((initiative1, initiative2) -> {
                    int naturalOrder = initiative1.getValue().compareTo(initiative2.getValue());
                    if (naturalOrder == 0) {
                        return diceDuel();
                    } else {
                        return naturalOrder;
                    }
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
