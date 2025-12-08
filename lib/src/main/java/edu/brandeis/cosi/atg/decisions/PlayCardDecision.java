package edu.brandeis.cosi.atg.decisions;

import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to play a card.
 *
 * @param card the card to play
 */
public record PlayCardDecision(Card card) implements Decision {
    /**
     * Compact constructor that validates the card is not null.
     */
    public PlayCardDecision {
        java.util.Objects.requireNonNull(card, "card must not be null");
    }

    @Override
    public String getDescription() {
        return "Play " + card;
    }
}
