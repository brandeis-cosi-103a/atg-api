package edu.brandeis.cosi.atg.api.decisions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to gain a card.
 */
public final class GainCardDecision implements Decision {
    private Card.Type cardType;

    /**
     * Constructs a GainDecision with the specified card type and number of
     * available cards.
     *
     * @param cardType the type of card to gain
     */
    @JsonCreator
    public GainCardDecision(@JsonProperty("cardType") Card.Type cardType) {
        this.cardType = cardType;
    }

    /**
     * Gets the description of the gain decision.
     *
     * @return the description of the gain decision
     */
    @JsonIgnore
    public String getDescription() {
        return "Gain " + cardType.getDescription();
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
     * Returns a string representation of the gain decision.
     *
     * @return a string representation of the gain decision
     */
    @Override
    public String toString() {
        return "GainDecision{cardType=" + cardType + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GainCardDecision)) {
            return false;
        }
        GainCardDecision other = (GainCardDecision) o;
        return cardType.equals(other.cardType);
    }
}
