package edu.brandeis.cosi.atg.api.decisions;

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
    public PlayCardDecision(Card card) {
        this.card = card;
    }

    /**
     * Gets the description of the play card decision.
     *
     * @return the description of the play card decision
     */
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

    public boolean equals(Object o) {
        if (!(o instanceof PlayCardDecision)) {
            return false;
        }
        PlayCardDecision other = (PlayCardDecision) o;
        return card.equals(other.card);
    }
}
