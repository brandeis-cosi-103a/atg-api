package edu.brandeis.cosi.atg.api.decisions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a decision that a player can make during the game.
 *
 * Decisions are the primary interface between an {@link Engine} and a
 * {@link Player}. The Engine presents a Player with a list of possible
 * decisions, and the Player chooses one of them. The Engine then executes the
 * chosen decision.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BuyDecision.class, name = "buy"),
        @JsonSubTypes.Type(value = EndPhaseDecision.class, name = "end_phase"),
        @JsonSubTypes.Type(value = PlayCardDecision.class, name = "play_card")
})
public sealed interface Decision permits BuyDecision, EndPhaseDecision, PlayCardDecision {
    /**
     * Gets the description of the decision.
     *
     * @return the description of the decision
     */
    public String getDescription();
}
