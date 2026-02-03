package edu.brandeis.cosi.atg.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import edu.brandeis.cosi.atg.state.CardStacks;

/**
 * Represents the start of a game.
 *
 * <p>Fired once at the beginning of {@link edu.brandeis.cosi.atg.engine.Engine#play()},
 * after players have been dealt their starting hands and the supply has been initialized.
 *
 * @param playerNames   the names of the players in turn order
 * @param initialSupply the initial card supply available for purchase
 */
public record GameStartEvent(ImmutableList<String> playerNames, CardStacks initialSupply) implements Event {

    /**
     * Compact constructor that validates required fields are not null.
     */
    public GameStartEvent {
        java.util.Objects.requireNonNull(playerNames, "playerNames must not be null");
        java.util.Objects.requireNonNull(initialSupply, "initialSupply must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return "Game started with players: " + String.join(", ", playerNames);
    }
}
