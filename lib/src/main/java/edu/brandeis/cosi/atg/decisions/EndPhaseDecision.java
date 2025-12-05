package edu.brandeis.cosi.atg.decisions;

import edu.brandeis.cosi.atg.state.GameState;

/**
 * Represents a decision by a player to end a phase of their turn.
 */
public record EndPhaseDecision(GameState.TurnPhase phase) implements Decision {
    public EndPhaseDecision {
        java.util.Objects.requireNonNull(phase, "phase must not be null");
    }

    @Override
    public String getDescription() {
        return "End phase: " + phase.name().toLowerCase();
    }
}
