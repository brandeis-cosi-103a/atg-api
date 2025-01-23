package edu.brandeis.cosi.atg.api.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a decision by a player to end their turn.
 */
public final class EndTurnEvent implements Event {

    /**
     * Constructs an EndTurnEvent.
     */
    public EndTurnEvent() {
    }

    /**
     * Gets the description of the end turn decision.
     *
     * @return the description of the end turn decision
     */
    @JsonIgnore
    public String getDescription() {
        return "End turn";
    }
}
