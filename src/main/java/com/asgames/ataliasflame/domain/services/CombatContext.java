package com.asgames.ataliasflame.domain.services;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

@Data
public class CombatContext {

    private List<Round> rounds = new ArrayList<>();

    public void addRound(Round round) {
        rounds.add(round);
    }

    @Data
    public static class Round {
        private final AttackReport attackReport1;
        private final AttackReport attackReport2;
    }

    public String report() {
        final StringBuilder sb = new StringBuilder("-- CombatContext --").append(lineSeparator());

        for (int i = 0; i < rounds.size(); i++) {
            sb.append(i + 1).append(". round:").append(lineSeparator());
            Round round = rounds.get(i);
            sb.append("initial attack (").append(round.attackReport1.getAttackerCode()).append("):").append(lineSeparator());
            sb.append("- damage: ").append(round.attackReport1.getDealtDamage()).append(lineSeparator());
            sb.append("- enemy's health: ").append(round.attackReport1.getRemainingHealth()).append(lineSeparator());
            sb.append("reply attack (").append(round.attackReport2.getAttackerCode()).append("):").append(lineSeparator());
            sb.append("- damage: ").append(round.attackReport2.getDealtDamage()).append(lineSeparator());
            sb.append("- enemy's health: ").append(round.attackReport2.getRemainingHealth()).append(lineSeparator());
        }

        return sb.toString();
    }
}
