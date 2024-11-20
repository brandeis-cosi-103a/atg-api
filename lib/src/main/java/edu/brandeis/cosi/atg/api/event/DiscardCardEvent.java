package edu.brandeis.cosi.atg.api.event;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents an event where a player discards a card.
 */
public final class DiscardCardEvent implements Event {
    private Card.Type cardType;
    private String playerName;

    /**
     * Constructs an DiscardCardEvent with the specified card type and player.
     *
     * @param cardType   the type of card discarded by the player
     * @param playerName the name of the player who discarded the card
     */
    public DiscardCardEvent(Card.Type cardType, String playerName) {
        this.cardType = cardType;
        this.playerName = playerName;
    }

    /**
     * Gets the description of the card discard event.
     */
    public String getDescription() {
        return playerName + " discarded card: " + cardType.getDescription();
    }

    /**
     * Gets the card type discarded by the player.
     *
     * @return the card type discarded by the player
     */
    public Card.Type getDecision() {
        return cardType;
    }

    /**
     * Gets the name of the player who discarded the card.
     *
     * @return the name of the player who discarded the card
     */
    public String getPlayerName() {
        return playerName;
    }
}
