package edu.brandeis.cosi.atg.api.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents an event where a player gains a card. Note that this event is
 * generated when a player gains any card, including buying one from the supply.
 */
public final class GainCardEvent implements Event {
    private Card.Type cardType;
    private String playerName;

    /**
     * Constructs an GainCardEvent with the specified card type and player.
     *
     * @param cardType   the type of card gained by the player
     * @param playerName the name of the player who gained the card
     */
    @JsonCreator
    public GainCardEvent(@JsonProperty("cardType") Card.Type cardType, @JsonProperty("playerName") String playerName) {
        this.cardType = cardType;
        this.playerName = playerName;
    }

    /**
     * Gets the description of the card gain event.
     */
    @JsonIgnore
    public String getDescription() {
        return playerName + " gained card: " + cardType.getDescription();
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
     * Gets the name of the player who gained the card.
     *
     * @return the name of the player who gained the card
     */
    public String getPlayerName() {
        return playerName;
    }
}
