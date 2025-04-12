package edu.brandeis.cosi.atg.api.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents an event where a player trashes a card.
 */
public final class TrashCardEvent implements Event {
    private Card.Type cardType;
    private String playerName;

    /**
     * Constructs an TrashCardEvent with the specified card type and player.
     *
     * @param cardType   the type of card trashed by the player
     * @param playerName the name of the player who trashed the card
     */
    @JsonCreator
    public TrashCardEvent(@JsonProperty("cardType") Card.Type cardType, @JsonProperty("playerName") String playerName) {
        if (cardType == null) {
            throw new IllegalArgumentException("Card type cannot be null");
        }
        if (playerName == null) {
            throw new IllegalArgumentException("Player name cannot be null");
        }
        this.cardType = cardType;
        this.playerName = playerName;
    }

    /**
     * Gets the description of the card trash event.
     */
    @JsonIgnore
    public String getDescription() {
        return playerName + " trashed card: " + cardType.getDescription();
    }

    /**
     * Gets the card type trashed by the player.
     *
     * @return the card type trashed by the player
     */
    public Card.Type getCardType() {
        return cardType;
    }

    /**
     * Gets the name of the player who trashed the card.
     *
     * @return the name of the player who trashed the card
     */
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrashCardEvent that = (TrashCardEvent) obj;
        return cardType.equals(that.cardType) && playerName.equals(that.playerName);
    }

    @Override
    public int hashCode() {
        int result = cardType.hashCode();
        result = 31 * result + playerName.hashCode();
        return result;
    }
}
