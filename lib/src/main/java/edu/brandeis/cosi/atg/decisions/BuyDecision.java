package edu.brandeis.cosi.atg.decisions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to buy a card.
 */
public record BuyDecision(Card.Type cardType) implements Decision {
    public BuyDecision {
        java.util.Objects.requireNonNull(cardType, "cardType must not be null");
    }

    @Override
    public String getDescription() {
        return "Buy " + cardType.getDescription();
    }
}
