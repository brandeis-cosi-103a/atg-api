package edu.brandeis.cosi.atg.api.actions;

import edu.brandeis.cosi.atg.api.cards.Card;

public final class BuyAction implements Action {
    private Card.Type cardType;
    private int numAvailable;

    public BuyAction(Card.Type cardType, int numAvailable) {
        this.cardType = cardType;
        this.numAvailable = numAvailable;
    }

    public String getDescription() {
        return "Buy " + cardType.getDescription() + " [" + numAvailable + " avail]";
    }

    public Card.Type getCardType() {
        return cardType;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public String toString() {
        return "BuyAction{" +
            "cardType=" + cardType +
            ", numAvailable=" + numAvailable +
            "}";
    }
}
