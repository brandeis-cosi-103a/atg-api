package edu.brandeis.cosi.atg.api.event;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to discard a card.
 */
public final class DiscardCardEvent implements Event {
    private Card card;

    /**
     * Constructs a PlayCardDecision with the specified card.
     */
    public DiscardCardEvent() {
    }

    /**
     * Constructs a PlayCardDecision with the specified card.
     *
     * @param card the card that was discarded
     */
    public DiscardCardEvent(Card card) {
        this.card = card;
    }

    /**
     * Gets the description of the discard card decision.
     */
    public String getDescription() {
        if(card != null) {
            return "Discard " + card.getDescription();
        } else {
            return "Discard";
        }
    }

    /**
     * Gets the card that was discarded, if it is visible.
     *
     * @return the card that was discarded if it is visible, otherwise null
     */
    public Card getCard() {
        return card;
    }
}
