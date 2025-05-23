package edu.brandeis.cosi.atg.decisions;

import edu.brandeis.cosi.atg.state.GameState;

/**
 * Represents a decision by a player to end a phase of their turn.
 */
public final class EndPhaseDecision implements Decision {
    private GameState.TurnPhase phase;

    /**
     * Constructs a EndPhaseDecision with the specified phase.
     *
     * @param phase the phase to end
     */
    public EndPhaseDecision(GameState.TurnPhase phase) {
        this.phase = phase;
    }

    /**
     * Gets the description of the end turn decision.
     *
     * @return the description of the end turn decision
     */
    public String getDescription() {
        return "End phase: " + phase.name().toLowerCase();
    }

    /**
     * Gets the phase to end.
     *
     * @return the phase to end
     */
    public GameState.TurnPhase getPhase() {
        return phase;
    }

    @Override
    public String toString() {
        return "EndPhaseDecision{" + "phase=" + phase + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EndPhaseDecision)) {
            return false;
        }
        EndPhaseDecision other = (EndPhaseDecision) o;
        return phase.equals(other.phase);
    }

    @Override
    public int hashCode() {
        return phase.hashCode();
    }
}
