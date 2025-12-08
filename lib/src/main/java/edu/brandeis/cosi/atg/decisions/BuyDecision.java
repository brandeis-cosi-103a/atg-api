package edu.brandeis.cosi.atg.decisions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to buy a card.
 */
public record BuyDecision(Card.Type cardType) implements Decision {
    public BuyDecision {
        java.util.Objects.requireNonNull(cardType, "cardType must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return "Buy " + cardType.getDescription();
    }
}
