package edu.brandeis.cosi.atg.decisions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to discard a card.
 *
 * @param card the card to discard
 */
public record DiscardCardDecision(Card card) implements Decision {
    /**
     * Compact constructor that validates the card is not null.
     */
    public DiscardCardDecision {
        java.util.Objects.requireNonNull(card, "card must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return "Discard " + card.description();
    }
}
