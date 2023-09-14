package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.TeamMember;
import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.DamageAbsorptionEvent.damageAbsorption;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.MissedAttackEvent.missedAttack;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.*;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.NO_ENEMY;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.*;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.collections4.ListUtils.union;

@Service
public class CombatService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    public void combat(List<? extends Combatant> team1, List<? extends Combatant> team2) {
        if (team1.isEmpty() || team2.isEmpty()) {
            storyLineLogger.event(warningReport(NO_ENEMY));
            return;
        }
        storyLineLogger.event(debugReport(COMBAT_START));

        teamCombat(
                team1.stream()
                        .filter(Combatant::isAlive)
                        .map(combatant -> TeamMember.builder()
                                .team(1)
                                .combatant(combatant)
                                .build())
                        .toList(),
                team2.stream()
                        .filter(Combatant::isAlive)
                        .map(combatant -> TeamMember.builder()
                                .team(2)
                                .combatant(combatant)
                                .build())
                        .toList());
    }

    private void teamCombat(List<TeamMember> team1, List<TeamMember> team2) {
        List<TeamMember> remainingTeam1 = new ArrayList<>(team1);
        List<TeamMember> remainingTeam2 = new ArrayList<>(team2);
        while (!remainingTeam1.isEmpty() && !remainingTeam2.isEmpty()) {
            List<TeamMember> combatOrder = getCombatOrder(union(remainingTeam1, remainingTeam2), true);

            for (TeamMember attacker : combatOrder) {
                if (attacker.isDead()) {
                    storyLineLogger.event(debugReport(DEAD_ATTACKER));
                    continue;
                }
                List<TeamMember> defenders = attacker.getTeam() == 1 ? remainingTeam2 : remainingTeam1;
                if (defenders.isEmpty()) {
                    storyLineLogger.event(debugReport(ELIMINATED_TEAM));
                    break;
                }
                attackTeam(attacker, defenders);
            }
        }
    }

    private void attackTeam(TeamMember attacker, List<TeamMember> defenders) {
        if (attacker.isDead() || defenders.isEmpty()) {
            throw new IllegalArgumentException("Attacker is dead or combat should be ended with one of the teams eliminated!");
        }
        int defenderPointer = roll(defenders.size()) - 1;

        TeamMember defender = defenders.get(defenderPointer);
        attack(attacker.getCombatant(), defender.getCombatant());

        if (defender.isDead()) {
            defenders.remove(defenderPointer);
        }
    }

    private void attack(Combatant attacker, Combatant defender) {
        if (attacker.isAlive()) {
            int chance = attacker.getAttack() - defender.getDefense();
            if (successX(chance)) {
                int damage = pointOut(attacker.getMinDamage(), attacker.getMaxDamage());
                doDamage(attacker, defender, damage);
            } else {
                storyLineLogger.event(missedAttack(attacker, defender));
            }
        }
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
                .toList();
    }

    private void doDamage(Combatant attacker, Combatant defender, int damage) {
        AtomicInteger remainingDamage = new AtomicInteger(damage);
        defender.getShield()
                .filter(shield -> shield.getDurability().hasOne())
                .ifPresent(shield -> absorption(shield, remainingDamage));
        defender.getCover().getEnergyArmor()
                .filter(armor -> armor.getDurability().hasOne())
                .ifPresent(armor -> absorption(armor, remainingDamage));
        defender.getCover().getPhysicalArmor()
                .filter(armor -> armor.getDurability().hasOne())
                .ifPresent(armor -> absorption(armor, remainingDamage));
        defender.getHealth().damage(remainingDamage.get());
        storyLineLogger.event(combatDamage(attacker, defender, remainingDamage.get()));
    }

    private void absorption(AbsorptionDefense defense, AtomicInteger damage) {
        int originalDamage = damage.get();
        int absorbedDamage = percent(originalDamage, defense.getAbsorption());
        int penetration = defense.getDurability().penetrate(absorbedDamage);
        damage.set(originalDamage - absorbedDamage + penetration);
        storyLineLogger.event(damageAbsorption(defense, originalDamage, absorbedDamage, penetration));
    }
}
