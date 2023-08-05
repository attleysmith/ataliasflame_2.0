package com.asgames.ataliasflame.domain.services;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

@Data
public class CombatContext {

    private final List<Round> rounds = new ArrayList<>();

    public void addRound(Round round) {
        rounds.add(round);
    }

    @Data
    public static class Round {
        private final List<AttackReport> attackReports;
    }

    public String report() {
        final StringBuilder sb = new StringBuilder("-- CombatContext --").append(lineSeparator());

        for (int i = 0; i < rounds.size(); i++) {
            sb.append(i + 1).append(". round:").append(lineSeparator());
            List<AttackReport> attacks = rounds.get(i).attackReports;
            for (int j = 0; j < attacks.size(); j++) {
                sb.append("  ").append(j + 1).append(". attack:").append(lineSeparator());
                sb.append("  ").append(attacks.get(j).getAttackerCode()).append(" deals ")
                        .append(attacks.get(j).getDealtDamage()).append(" damage to ")
                        .append(attacks.get(j).getDefenderCode()).append(lineSeparator());
                sb.append("  ").append("Remaining health: ").append(attacks.get(j).getRemainingHealth()).append(lineSeparator());
            }
        }

        return sb.toString();
    }
}
