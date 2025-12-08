package edu.brandeis.cosi.atg.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents an event where a player trashes a card.
 */
public record TrashCardEvent(Card.Type cardType, String playerName) implements Event {
    public TrashCardEvent {
        java.util.Objects.requireNonNull(cardType, "cardType must not be null");
        java.util.Objects.requireNonNull(playerName, "playerName must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return playerName + " trashed card: " + cardType.description();
    }
}
