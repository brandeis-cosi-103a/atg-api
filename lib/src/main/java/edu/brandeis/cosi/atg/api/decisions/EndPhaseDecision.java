package edu.brandeis.cosi.atg.api.decisions;

/**
 * Represents a decision by a player to end their turn.
 */
public final class EndPhaseDecision implements Decision {
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
}
