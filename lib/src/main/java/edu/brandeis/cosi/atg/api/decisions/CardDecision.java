package edu.brandeis.cosi.atg.api.decisions;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to gain a card.
 */
public final class CardDecision implements Decision {
    private Card.Type cardType;
    private Type decisionType;

    public enum Type {
        BUY, DISCARD, GAIN, PLAY, TRASH
    }

    /**
     * Constructs a GainDecision with the specified card type and number of
     * available cards.
     *
     * @param cardType     the type of card to gain
     * @param decisionType the type of decision
     */
    public CardDecision(Card.Type cardType, Type decisionType) {
        this.cardType = cardType;
        this.decisionType = decisionType;
    }

    /**
     * Gets the description of the gain decision.
     *
     * @return the description of the gain decision
     */
    public String getDescription() {
        return decisionType.name() + " " + cardType.getDescription();
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
     * Gets the type of decision.
     *
     * @return the type of decision
     */
    public Type getDecisionType() {
        return decisionType;
    }

    /**
     * Returns a string representation of the gain decision.
     *
     * @return a string representation of the gain decision
     */
    @Override
    public String toString() {
        return decisionType.name() + "Decision{cardType=" + cardType + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CardDecision)) {
            return false;
        }
        CardDecision other = (CardDecision) o;
        return cardType.equals(other.cardType) && decisionType.equals(other.decisionType);
    }
}
