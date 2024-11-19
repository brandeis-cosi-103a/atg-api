package edu.brandeis.cosi.atg.api.decisions;

/**
 * Represents a decision by a player to end their turn.
 */
public final class EndTurnDecision implements Decision {

    /**
     * Gets the description of the end turn decision.
     *
     * @return the description of the end turn decision
     */
    public String getDescription() {
        return "End turn";
    }

    public boolean equals(Object o) {
        return o instanceof EndTurnDecision;
    }
}
