package edu.brandeis.cosi.atg.api.event;

import edu.brandeis.cosi.atg.api.Player;
import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents an event where a player gains a card. Note that this event is generated when a
 * player gains any card, including buying one from the supply.
 *
 * When a player buys a card from a supply, a PlayerDecisionEvent is generated, followed by
 * a GainCardEvent.
 */
public final class GainCardEvent implements Event {
    private Card.Type cardType;
    private Player player;

    /**
     * Constructs an GainCardEvent with the specified card type and player.
     *
     * @param cardType the type of card gained by the player
     * @param player the player who gained the card
     */
    public GainCardEvent(Card.Type cardType, Player player) {
        this.cardType = cardType;
        this.player = player;
    }

    /**
     * Gets the description of the card gain event.
     */
    public String getDescription() {
        return player.getName() + " gained card: " + cardType.getDescription();
    }

    /**
     * Gets the card type gained by the player.
     * @return the card type gained by the player
     */
    public Card.Type getDecision() {
        return cardType;
    }

    /**
     * Gets the player who gained the card.
     * @return the player who gained the card
     */
    public Player getPlayer() {
        return player;
    }
}
