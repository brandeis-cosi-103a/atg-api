package edu.brandeis.cosi.atg.api.decisions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to discard a card.
 */
public final class DiscardCardDecision implements Decision {
    private Card card;

    /**
     * Constructs a TrashCardDecision with the specified card.
     *
     * @param card the card to discard
     */
    @JsonCreator
    public DiscardCardDecision(@JsonProperty("card") Card card) {
        this.card = card;
    }

    /**
     * Gets the description of the discard card decision.
     *
     * @return the description of the discard card decision
     */
    @JsonIgnore
    public String getDescription() {
        return "Discard " + card.getDescription();
    }

    /**
     * Gets the card to discard.
     *
     * @return the card to discard
     */
    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "DiscardCardDecision{" + "card=" + card + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DiscardCardDecision)) {
            return false;
        }
        DiscardCardDecision other = (DiscardCardDecision) o;
        return card.equals(other.card);
    }

    @Override
    public int hashCode() {
        return card.hashCode();
    }
}
