package edu.brandeis.cosi.atg.api.decisions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to trash a card. Trashing a card
 * removes it from the player's deck entirely, and does not return it
 * to the supply pile.
 */
public final class TrashCardDecision implements Decision {
    private Card card;

    /**
     * Constructs a TrashCardDecision with the specified card.
     *
     * @param card the card to trash
     */
    @JsonCreator
    public TrashCardDecision(@JsonProperty("card") Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        this.card = card;
    }

    /**
     * Gets the description of the trash card decision.
     *
     * @return the description of the trash card decision
     */
    @JsonIgnore
    public String getDescription() {
        return "Trash " + card.getDescription();
    }

    /**
     * Gets the card to trash.
     *
     * @return the card to trash
     */
    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "TrashCardDecision{" + "card=" + card + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TrashCardDecision)) {
            return false;
        }
        TrashCardDecision other = (TrashCardDecision) o;
        return card.equals(other.card);
    }

    @Override
    public int hashCode() {
        return card.hashCode();
    }
}
