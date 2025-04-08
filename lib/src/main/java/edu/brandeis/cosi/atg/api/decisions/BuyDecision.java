package edu.brandeis.cosi.atg.api.decisions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to buy a card.
 */
public final class BuyDecision implements Decision {
    private Card.Type cardType;

    /**
     * Constructs a BuyDecision with the specified card type.
     *
     * @param cardType the type of card to buy
     */
    @JsonCreator
    public BuyDecision(@JsonProperty("cardType") Card.Type cardType) {
        this.cardType = cardType;
    }

    /**
     * Gets the description of the buy decision.
     *
     * @return the description of the buy decision
     */
    @JsonIgnore
    public String getDescription() {
        return "Buy " + cardType.getDescription();
    }

    /**
     * Gets the type of card to buy.
     *
     * @return the type of card to buy
     */
    public Card.Type getCardType() {
        return cardType;
    }

    /**
     * Returns a string representation of the buy decision.
     *
     * @return a string representation of the buy decision
     */
    public String toString() {
        return "BuyDecision{cardType=" + cardType + "}";
    }

    public boolean equals(Object o) {
        if (!(o instanceof BuyDecision)) {
            return false;
        }
        BuyDecision other = (BuyDecision) o;
        return cardType.equals(other.cardType);
    }

    @Override
    public int hashCode() {
        return cardType.hashCode();
    }
}
