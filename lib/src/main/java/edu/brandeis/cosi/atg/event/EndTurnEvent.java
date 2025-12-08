package edu.brandeis.cosi.atg.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a decision by a player to end their turn.
 */
public record EndTurnEvent() implements Event {

    @Override
    @JsonIgnore
    public String getDescription() {
        return "End turn";
    }
}
