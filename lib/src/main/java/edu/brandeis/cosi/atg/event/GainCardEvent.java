package edu.brandeis.cosi.atg.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents an event where a player gains a card. Note that this event is
 * generated when a player gains any card, including buying one from the supply.
 */
public record GainCardEvent(Card.Type cardType, String playerName) implements Event {
    public GainCardEvent {
        java.util.Objects.requireNonNull(cardType, "cardType must not be null");
        java.util.Objects.requireNonNull(playerName, "playerName must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return playerName + " gained card: " + cardType.getDescription();
    }
}
