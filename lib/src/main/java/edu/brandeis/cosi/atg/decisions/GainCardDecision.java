package edu.brandeis.cosi.atg.decisions;

import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents a decision by a player to gain a card.
 *
 * @param cardType the type of card to gain
 */
public record GainCardDecision(Card.Type cardType) implements Decision {
    /**
     * Compact constructor that validates the card type is not null.
     */
    public GainCardDecision {
        java.util.Objects.requireNonNull(cardType, "cardType must not be null");
    }

    @Override
    public String getDescription() {
        return "Gain " + cardType.description();
    }
}
