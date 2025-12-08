package edu.brandeis.cosi.atg.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to play a card.
 */
public record PlayCardEvent(Card card, String playerName) implements Event {
    public PlayCardEvent {
        java.util.Objects.requireNonNull(card, "card must not be null");
        java.util.Objects.requireNonNull(playerName, "playerName must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return playerName + " played card: " + card;
    }
}
