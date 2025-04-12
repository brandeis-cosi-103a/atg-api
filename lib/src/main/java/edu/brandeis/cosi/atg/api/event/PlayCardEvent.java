package edu.brandeis.cosi.atg.api.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to play a card.
 */
public final class PlayCardEvent implements Event {
    private Card card;
    private String playerName;

    /**
     * Constructs a PlayCardEvent with the specified card.
     *
     * @param card       the card that was played
     * @param playerName the player who played the card
     */
    @JsonCreator
    public PlayCardEvent(@JsonProperty("card") Card card, @JsonProperty("playerName") String playerName) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        if (playerName == null) {
            throw new IllegalArgumentException("Player name cannot be null");
        }
        this.card = card;
        this.playerName = playerName;
    }

    /**
     * Gets the description of the play card event.
     *
     * @return the description of the play card event
     */
    @JsonIgnore
    public String getDescription() {
        return playerName + " played card: " + card;
    }

    /**
     * Gets the card that was played.
     *
     * @return the card that was played
     */
    public Card getCard() {
        return card;
    }

    /**
     * Gets the name of the player who played the card.
     *
     * @return the name of the player who played the card
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
        PlayCardEvent that = (PlayCardEvent) obj;
        return card.equals(that.card) && playerName.equals(that.playerName);
    }

    @Override
    public int hashCode() {
        int result = card.hashCode();
        result = 31 * result + playerName.hashCode();
        return result;
    }
}
