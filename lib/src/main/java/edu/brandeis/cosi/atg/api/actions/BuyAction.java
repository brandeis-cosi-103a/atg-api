package edu.brandeis.cosi.atg.api.actions;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents an action where a player buys a card.
 */
public final class BuyAction implements Action {
    private Card.Type cardType;
    private int numAvailable;

    /**
     * Constructs a BuyAction with the specified card type and number of available cards.
     *
     * @param cardType the type of card to buy
     * @param numAvailable the number of available cards of the specified type
     */
    public BuyAction(Card.Type cardType, int numAvailable) {
        this.cardType = cardType;
        this.numAvailable = numAvailable;
    }

    /**
     * Gets the description of the buy action.
     *
     * @return the description of the buy action
     */
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
     * Returns a string representation of the buy action.
     *
     * @return a string representation of the buy action
     */
    public String toString() {
        return "BuyAction{" +
            "cardType=" + cardType +
            ", numAvailable=" + numAvailable +
            "}";
    }
}
