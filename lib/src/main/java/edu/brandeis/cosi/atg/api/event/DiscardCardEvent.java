package edu.brandeis.cosi.atg.api.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonCreator
    public DiscardCardEvent(@JsonProperty("cardType") Card.Type cardType,
            @JsonProperty("playerName") String playerName) {
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
     * Gets the description of the card discard event.
     */
    @JsonIgnore
    public String getDescription() {
        return playerName + " discarded card: " + cardType.getDescription();
    }

    /**
     * Gets the card type discarded by the player.
     *
     * @return the card type discarded by the player
     */
    public Card.Type getCardType() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DiscardCardEvent that = (DiscardCardEvent) obj;
        return cardType == that.cardType && playerName.equals(that.playerName);
    }

    @Override
    public int hashCode() {
        int result = cardType.hashCode();
        result = 31 * result + playerName.hashCode();
        return result;
    }
}
