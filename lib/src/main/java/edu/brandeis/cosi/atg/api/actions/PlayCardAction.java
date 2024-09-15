package edu.brandeis.cosi.atg.api.actions;

import edu.brandeis.cosi.atg.api.cards.Card;

public final class PlayCardAction implements Action {
    private Card card;

    public PlayCardAction(Card card) {
        this.card = card;
    }
    
    public String getDescription() {
        return "Play " + card.getDescription();
    }

    public Card getCard() {
        return card;
    }
}
