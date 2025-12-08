package edu.brandeis.cosi.atg.decisions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to discard a card.
 */
public record DiscardCardDecision(Card card) implements Decision {
    public DiscardCardDecision {
        java.util.Objects.requireNonNull(card, "card must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return "Discard " + card.getDescription();
    }
}
