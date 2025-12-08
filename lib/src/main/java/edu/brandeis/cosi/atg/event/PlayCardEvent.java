package edu.brandeis.cosi.atg.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to play a card.
 *
 * @param card       the card being played
 * @param playerName the name of the player playing the card
 */
public record PlayCardEvent(Card card, String playerName) implements Event {
    /**
     * Compact constructor that validates required fields are not null.
     */
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
