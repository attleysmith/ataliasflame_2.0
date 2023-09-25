package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.TeamMember;
import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.HitType.BODY_HIT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.HitType.HEAD_HIT;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.DamageAbsorptionEvent.damageAbsorption;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.MissedAttackEvent.missedAttack;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.DebugReportCause.*;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.DebugEvent.debugReport;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.WarningReportCause.NO_ENEMY;
import static com.asgames.ataliasflame.domain.services.storyline.events.SimpleEvents.WarningEvent.warningReport;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.pointOut;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll10;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.collections4.ListUtils.union;

@Service
public class CombatService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    private static final int FOCUS_ON_TARGET = 99;
    private static final int CRITICAL_HIT_CHANCE = 2;
    private static final int CRITICAL_HIT_DAMAGE_BONUS = 50;
    private static final int HEAD_HIT_CHANCE = 20;
    private static final int HEAD_HIT_DAMAGE_BONUS = 25;
    private static final int SHIELD_BLOCKING_PENETRATION = 55;

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
        TeamMember defender = Optional.ofNullable(attacker.getSwornEnemy())
                .filter(TeamMember::isAlive)
                .filter(swornEnemy -> successX(FOCUS_ON_TARGET))
                .orElseGet(() -> {
                    TeamMember swornEnemy = pointOut(defenders);
                    attacker.setSwornEnemy(swornEnemy);
                    return swornEnemy;
                });
        attack(attacker.getCombatant(), defender.getCombatant());

        if (defender.isDead()) {
            defenders.remove(defender);
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
        defender.getShield().filter(shield -> shield.getDurability().hasOne()).ifPresent(shield -> blocking(shield, remainingDamage));

        boolean headHit = headHitX(remainingDamage);
        boolean criticalHit = criticalHitX(remainingDamage);

        defender.getCover().getEnergyArmor().filter(armor -> armor.getDurability().hasOne()).ifPresent(armor -> absorption(armor, remainingDamage));
        if (!criticalHit) {
            if (headHit) {
                defender.getCover().getHelmet().filter(armor -> armor.getDurability().hasOne()).ifPresent(armor -> absorption(armor, remainingDamage));
            } else {
                defender.getCover().getBodyArmor().filter(armor -> armor.getDurability().hasOne()).ifPresent(armor -> absorption(armor, remainingDamage));
            }
        }
        defender.getCover().getDivineArmor().filter(armor -> armor.getDurability().hasOne()).ifPresent(armor -> absorption(armor, remainingDamage));

        defender.getHealth().damage(remainingDamage.get());
        storyLineLogger.event(combatDamage(attacker, defender, remainingDamage.get(), headHit ? HEAD_HIT : BODY_HIT));
    }

    private void blocking(Shield shield, AtomicInteger damage) {
        if (successX(shield.getBlocking())) {
            damage.updateAndGet(value -> percent(value, SHIELD_BLOCKING_PENETRATION));
            absorption(shield, damage);
        }
    }

    private void absorption(AbsorptionDefense defense, AtomicInteger damage) {
        int originalDamage = damage.get();
        int absorbedDamage = percent(originalDamage, defense.getAbsorption());
        int penetration = defense.getDurability().penetrate(absorbedDamage);
        damage.set(originalDamage - absorbedDamage + penetration);
        storyLineLogger.event(damageAbsorption(defense, originalDamage, absorbedDamage, penetration));
    }

    private boolean criticalHitX(AtomicInteger damage) {
        boolean criticalHit = successX(CRITICAL_HIT_CHANCE);
        int bonusDamage = criticalHit ? percent(damage.get(), CRITICAL_HIT_DAMAGE_BONUS) : 0;
        damage.addAndGet(bonusDamage);
        return criticalHit;
    }

    private boolean headHitX(AtomicInteger damage) {
        boolean headHit = successX(HEAD_HIT_CHANCE);
        int bonusDamage = headHit ? percent(damage.get(), HEAD_HIT_DAMAGE_BONUS) : 0;
        damage.addAndGet(bonusDamage);
        return headHit;
    }
}
