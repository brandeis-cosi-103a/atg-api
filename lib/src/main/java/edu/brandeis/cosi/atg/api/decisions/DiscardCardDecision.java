package edu.brandeis.cosi.atg.api.decisions;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to discard a card.
 *
 * When a player can decide to discard a variable number of cards, the player should indicate
 * they are finished discarding by also playing an EndPhaseDecision.
 */
public final class DiscardCardDecision implements Decision {
    private Card card;

    /**
     * Constructs a PlayCardDecision with the specified card.
     *
     * @param card the card to discard
     */
    public DiscardCardDecision(Card card) {
        this.card = card;
    }

    /**
     * Gets the description of the discard card decision.
     *
     * @return the description of the discard card decision
     */
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
}
