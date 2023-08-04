package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.structures.TeamMember;
import com.asgames.ataliasflame.domain.services.CombatContext.Round;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class CombatService {

    public void combat(List<Combatant> team1, List<Combatant> team2) {
        if (team1.size() == 0 || team2.size() == 0) {
            log.warn("Combat without an enemy.");
            return;
        }

        teamCombat(
                team1.stream().map(combatant -> TeamMember.builder()
                        .team(1)
                        .combatant(combatant)
                        .build()).collect(toList()),
                team2.stream().map(combatant -> TeamMember.builder()
                        .team(2)
                        .combatant(combatant)
                        .build()).collect(toList()));
    }

    private void teamCombat(List<TeamMember> team1, List<TeamMember> team2) {
        List<TeamMember> remainingTeam1 = new ArrayList<>(team1);
        List<TeamMember> remainingTeam2 = new ArrayList<>(team2);
        CombatContext combatContext = new CombatContext();
        while (remainingTeam1.size() > 0 && remainingTeam2.size() > 0) {
            List<TeamMember> combatOrder = getCombatOrder(remainingTeam1, remainingTeam2);

            List<AttackReport> attackReports = new ArrayList<>();
            for (TeamMember attacker : combatOrder) {
                List<TeamMember> defenders = attacker.getTeam() == 1 ? remainingTeam2 : remainingTeam1;
                attackReports.add(attackTeam(attacker, defenders));
            }
            combatContext.addRound(new Round(attackReports));
        }
        log.debug(combatContext.report());
    }

    private AttackReport attackTeam(TeamMember attacker, List<TeamMember> defenders) {
        if (attacker.getCombatant().getHealth().isEmpty() || defenders.size() == 0) {
            return new AttackReport(attacker.getCombatant().getCode(), "N/A", 0, 0);
        }
        int defenderPointer = roll(defenders.size()) - 1;

        TeamMember defender = defenders.get(defenderPointer);
        AttackReport attackReport = attack(attacker.getCombatant(), defender.getCombatant());

        if (defender.getCombatant().getHealth().isEmpty()) {
            defenders.remove(defenderPointer);
        }
        return attackReport;
    }

    private AttackReport attack(Combatant attacker, Combatant defender) {
        int damage = 0;
        if (attacker.getHealth().hasOne()) {
            int chance = attacker.getAttack() - defender.getDefense();
            if (successX(chance)) {
                damage = pointOut(attacker.getMinDamage(), attacker.getMaxDamage());
                defender.getHealth().damage(damage);
            }
        }
        return new AttackReport(attacker.getCode(), defender.getCode(), damage, defender.getHealth().actualValue());
    }

    private List<TeamMember> getCombatOrder(List<TeamMember> team1, List<TeamMember> team2) {
        return Stream
                .concat(team1.stream(), team2.stream())
                .collect(toMap(
                        teamMember -> teamMember,
                        teamMember -> teamMember.getCombatant().getInitiative() + roll10()
                ))
                .entrySet().stream().sorted((initiative1, initiative2) -> {
                    int naturalOrder = initiative1.getValue().compareTo(initiative2.getValue());
                    if (naturalOrder == 0) {
                        return diceDuel();
                    } else {
                        return naturalOrder;
                    }
                })
                .map(Map.Entry::getKey)
                .collect(toList());
    }
}
