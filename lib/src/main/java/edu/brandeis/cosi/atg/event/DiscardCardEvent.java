package edu.brandeis.cosi.atg.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents an event where a player discards a card.
 *
 * @param cardType   the type of card being discarded
 * @param playerName the name of the player discarding the card
 */
public record DiscardCardEvent(Card.Type cardType, String playerName) implements Event {
    /**
     * Compact constructor that validates required fields are not null.
     */
    public DiscardCardEvent {
        java.util.Objects.requireNonNull(cardType, "cardType must not be null");
        java.util.Objects.requireNonNull(playerName, "playerName must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return playerName + " discarded card: " + cardType.description();
    }
}
