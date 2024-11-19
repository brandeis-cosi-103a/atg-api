package edu.brandeis.cosi.atg.api.decisions;

/**
 * Represents a decision by a player to end their turn.
 */
public final class EndPhaseDecision implements Decision {
    /**
     * Represents the phase of a turn.
     */
    public enum Phase {
        /** The phase of the turn that involves playing action cards */
        ACTION,
        /** A phase of a turn that involves discarding zero or more cards */
        DISCARD;
    }

    private Phase phase;

    /**
     * Constructs a EndPhaseDecision with the specified phase.
     * @param phase the phase to end
     */
    public EndPhaseDecision(Phase phase) {
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
    public Phase getPhase() {
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
}
