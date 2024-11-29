package edu.brandeis.cosi.atg.api.event;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents an event where a player gains a card. Note that this event is
 * generated when a player gains any card, including buying one from the supply.
 */
public final class CardEvent implements Event {
    private Card.Type cardType;
    private String playerName;
    private Type eventType;

    public enum Type {
        DISCARD, GAIN, PLAY, TRASH
    }

    /**
     * Constructs an GainCardEvent with the specified card type and player.
     *
     * @param cardType   the type of card gained by the player
     * @param eventType  the type of event
     * @param playerName the name of the player who gained the card
     */
    public CardEvent(Card.Type cardType, Type eventType, String playerName) {
        this.cardType = cardType;
        this.eventType = eventType;
        this.playerName = playerName;
    }

    /**
     * Gets the description of the card gain event.
     */
    public String getDescription() {
        return playerName + " " + eventType.name() + " card: " + cardType.getDescription();
    }

    /**
     * Gets the card type gained by the player.
     *
     * @return the card type gained by the player
     */
    public Card.Type getDecision() {
        return cardType;
    }

    /**
     * Gets the type of event.
     * 
     * @return the type of event
     */
    public Type getEventType() {
        return eventType;
    }

    /**
     * Gets the name of the player who gained the card.
     *
     * @return the name of the player who gained the card
     */
    public String getPlayerName() {
        return playerName;
    }
}
