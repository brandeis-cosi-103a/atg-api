package edu.brandeis.cosi.atg.api.decisions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to play a card.
 */
public final class PlayCardDecision implements Decision {
    private Card card;

    /**
     * Constructs a PlayCardDecision with the specified card.
     *
     * @param card the card to play
     */
    @JsonCreator
    public PlayCardDecision(@JsonProperty("card") Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        this.card = card;
    }

    /**
     * Gets the description of the play card decision.
     *
     * @return the description of the play card decision
     */
    @JsonIgnore
    public String getDescription() {
        return "Play " + card.getDescription();
    }

    /**
     * Gets the card to play.
     *
     * @return the card to play
     */
    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "PlayCardDecision{" + "card=" + card + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlayCardDecision)) {
            return false;
        }
        PlayCardDecision other = (PlayCardDecision) o;
        return card.equals(other.card);
    }

    @Override
    public int hashCode() {
        return card.hashCode();
    }
}
