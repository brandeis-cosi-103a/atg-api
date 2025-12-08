package edu.brandeis.cosi.atg.decisions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to buy a card.
 *
 * @param cardType the type of card to buy
 */
public record BuyDecision(Card.Type cardType) implements Decision {
    /**
     * Compact constructor that validates the card type is not null.
     */
    public BuyDecision {
        java.util.Objects.requireNonNull(cardType, "cardType must not be null");
    }

    @Override
    @JsonIgnore
    public String getDescription() {
        return "Buy " + cardType.description();
    }
}
