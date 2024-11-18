package edu.brandeis.cosi.atg.api.decisions;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to gain a card.
 */
public final class GainDecision implements Decision {
    private Card.Type cardType;
    private int numAvailable;

    /**
     * Constructs a GainDecision with the specified card type and number of available cards.
     *
     * @param cardType the type of card to gain
     * @param numAvailable the number of available cards of the specified type
     */
    public GainDecision(Card.Type cardType, int numAvailable) {
        this.cardType = cardType;
        this.numAvailable = numAvailable;
    }

    /**
     * Gets the description of the gain decision.
     *
     * @return the description of the gain decision
     */
    public String getDescription() {
        return "Gain " + cardType.getDescription() + " [" + numAvailable + " avail]";
    }

    /**
     * Gets the type of card to gain.
     *
     * @return the type of card to gain
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
     * Returns a string representation of the gain decision.
     *
     * @return a string representation of the gain decision
     */
    public String toString() {
        return "GainDecision{" +
            "cardType=" + cardType +
            ", numAvailable=" + numAvailable +
            "}";
    }
}
