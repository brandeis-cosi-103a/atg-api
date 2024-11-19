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
        /** The phase of a turn that involves playing money */
        MONEY,
        /**
         * The phase of a turn that involves buying cards.
         *
         * The BUY phase is the last phase of a turn, so ending the BUY phase is equivalent to
         * ending the turn.
         */
        BUY,
        /**
         * A phase of a turn that involves discarding zero or more cards.
         *
         * This phase only occurs when a player takes an action that requires cards to
         * be discarded.
         */
        DISCARD,
        /**
         * The phase of a turn where the player discards their hand and draws a new hand.
         */
        CLEANUP;
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
