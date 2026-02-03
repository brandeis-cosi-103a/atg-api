package edu.brandeis.cosi.atg.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.state.CardStacks;
import edu.brandeis.cosi.atg.state.GameResult;

/**
 * Represents the end of a game.
 *
 * <p>Fired once at the end of {@link edu.brandeis.cosi.atg.engine.Engine#play()},
 * after the final turn has completed and before the result is returned.
 *
 * @param finalSupply the card supply remaining at the end of the game
 * @param result      the game result containing player scores and ending decks
 */
public record GameEndEvent(CardStacks finalSupply, GameResult result) implements Event {

    /**
     * Compact constructor that validates required fields are not null.
     */
    public GameEndEvent {
        java.util.Objects.requireNonNull(finalSupply, "finalSupply must not be null");
        java.util.Objects.requireNonNull(result, "result must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return "Game ended";
    }
}
