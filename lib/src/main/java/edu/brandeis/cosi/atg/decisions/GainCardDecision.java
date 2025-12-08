package edu.brandeis.cosi.atg.decisions;

import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to gain a card.
 */
public record GainCardDecision(Card.Type cardType) implements Decision {
    public GainCardDecision {
        java.util.Objects.requireNonNull(cardType, "cardType must not be null");
    }

    @Override
    public String getDescription() {
        return "Gain " + cardType.description();
    }
}
