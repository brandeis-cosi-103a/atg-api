package edu.brandeis.cosi.atg.api.actions;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents an action where a player plays a card.
 */
public final class PlayCardAction implements Action {
    private Card card;

    /**
     * Constructs a PlayCardAction with the specified card.
     *
     * @param card the card to play
     */
    public PlayCardAction(Card card) {
        this.card = card;
    }

    /**
     * Gets the description of the play card action.
     *
     * @return the description of the play card action
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
}
