package edu.brandeis.cosi.atg.decisions;

import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to trash a card. Trashing a card
 * removes it from the player's deck entirely, and does not return it
 * to the supply pile.
 */
public record TrashCardDecision(Card card) implements Decision {
    public TrashCardDecision {
        java.util.Objects.requireNonNull(card, "card must not be null");
    }

    @Override
    public String getDescription() {
        return "Trash " + card;
    }
}
