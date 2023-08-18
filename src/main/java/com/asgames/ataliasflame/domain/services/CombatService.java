package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.TeamMember;
import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.CombatContext.Round;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.ListUtils.union;

@Slf4j
@Service
public class CombatService {

    public void combat(List<? extends Combatant> team1, List<? extends Combatant> team2) {
        if (team1.isEmpty() || team2.isEmpty()) {
            log.warn("Combat without an enemy.");
            return;
        }

        teamCombat(
                team1.stream()
                        .filter(Combatant::isAlive)
                        .map(combatant -> TeamMember.builder()
                                .team(1)
                                .combatant(combatant)
                                .build()).collect(toList()),
                team2.stream()
                        .filter(Combatant::isAlive)
                        .map(combatant -> TeamMember.builder()
                                .team(2)
                                .combatant(combatant)
                                .build()).collect(toList()));
    }

    private void teamCombat(List<TeamMember> team1, List<TeamMember> team2) {
        List<TeamMember> remainingTeam1 = new ArrayList<>(team1);
        List<TeamMember> remainingTeam2 = new ArrayList<>(team2);
        CombatContext combatContext = new CombatContext();
        while (remainingTeam1.size() > 0 && remainingTeam2.size() > 0) {
            List<TeamMember> combatOrder = getCombatOrder(union(remainingTeam1, remainingTeam2), true);

            List<AttackReport> attackReports = new ArrayList<>();
            for (TeamMember attacker : combatOrder) {
                if (attacker.isDead()) {
                    log.debug("Skipping attack. " + attacker.getReference() + " is already dead.");
                    continue;
                }
                List<TeamMember> defenders = attacker.getTeam() == 1 ? remainingTeam2 : remainingTeam1;
                if (defenders.isEmpty()) {
                    log.debug("Stop fighting. One of the teams is eliminated.");
                    break;
                }
                attackReports.add(attackTeam(attacker, defenders));
            }
            combatContext.addRound(new Round(attackReports));
        }
        log.debug(combatContext.report());
    }

    private AttackReport attackTeam(TeamMember attacker, List<TeamMember> defenders) {
        if (attacker.isDead() || defenders.isEmpty()) {
            throw new IllegalArgumentException("Attacker is dead or combat should be ended with one of the teams eliminated!");
        }
        int defenderPointer = roll(defenders.size()) - 1;

        TeamMember defender = defenders.get(defenderPointer);
        AttackReport attackReport = attack(attacker.getCombatant(), defender.getCombatant());

        if (defender.isDead()) {
            defenders.remove(defenderPointer);
        }
        return attackReport;
    }

    private AttackReport attack(Combatant attacker, Combatant defender) {
        int damage = 0;
        if (attacker.isAlive()) {
            int chance = attacker.getAttack() - defender.getDefense();
            if (successX(chance)) {
                damage = pointOut(attacker.getMinDamage(), attacker.getMaxDamage());
                doDamage(defender, damage);
            }
        }
        return new AttackReport(attacker.getReference(), defender.getReference(), damage, defender.getHealth().actualValue());
    }

    private List<TeamMember> getCombatOrder(List<TeamMember> naturalOrder, boolean initiate) {
        if (naturalOrder.size() == 1) {
            return naturalOrder;
        }

        return naturalOrder.stream()
                .collect(groupingBy(teamMember -> (initiate ? teamMember.getInitiative() : 0) + roll10()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(ties -> getCombatOrder(ties.getValue(), false))
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private void doDamage(Combatant defender, int damage) {
        AtomicInteger remainingDamage = new AtomicInteger(damage);
        defender.getShield().ifPresent(
                shield -> absorption(shield, remainingDamage));
        defender.getArmor().ifPresent(
                armor -> absorption(armor, remainingDamage));
        defender.getHealth().damage(remainingDamage.get());
    }

    private void absorption(AbsorptionDefense defense, AtomicInteger damage) {
        int originalDamage = damage.get();
        int absorbedDamage = percent(originalDamage, defense.getAbsorption());
        int penetration = defense.getDurability().penetrate(absorbedDamage);
        damage.set(originalDamage - absorbedDamage + penetration);
    }
}
