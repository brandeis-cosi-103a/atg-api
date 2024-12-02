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
    private int numAvailable;

    /**
     * Constructs a BuyDecision with the specified card type and number of available
     * cards.
     *
     * @param cardType     the type of card to buy
     * @param numAvailable the number of available cards of the specified type
     */
    @JsonCreator
    public BuyDecision(@JsonProperty("cardType") Card.Type cardType, @JsonProperty("numAvailable") int numAvailable) {
        this.cardType = cardType;
        this.numAvailable = numAvailable;
    }

    /**
     * Gets the description of the buy decision.
     *
     * @return the description of the buy decision
     */
    @JsonIgnore
    public String getDescription() {
        return "Buy " + cardType.getDescription() + " [" + numAvailable + " avail]";
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
     * Gets the number of available cards of the specified type.
     *
     * @return the number of available cards of the specified type
     */
    public int getNumAvailable() {
        return numAvailable;
    }

    /**
     * Returns a string representation of the buy decision.
     *
     * @return a string representation of the buy decision
     */
    public String toString() {
        return "BuyDecision{cardType=" + cardType + ", numAvailable=" + numAvailable + "}";
    }

    public boolean equals(Object o) {
        if (!(o instanceof BuyDecision)) {
            return false;
        }
        BuyDecision other = (BuyDecision) o;
        return cardType.equals(other.cardType) && numAvailable == other.numAvailable;
    }

    @Override
    public int hashCode() {
        return cardType.hashCode() + numAvailable;
    }
}
