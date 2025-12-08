package edu.brandeis.cosi.atg.decisions;

import edu.brandeis.cosi.atg.state.GameState;

/**
 * Represents a decision by a player to end a phase of their turn.
 *
 * @param phase the phase to end
 */
public record EndPhaseDecision(GameState.TurnPhase phase) implements Decision {
    /**
     * Compact constructor that validates the phase is not null.
     */
    public EndPhaseDecision {
        java.util.Objects.requireNonNull(phase, "phase must not be null");
    }

    @Override
    public String getDescription() {
        return "End phase: " + phase.name().toLowerCase();
    }
}
