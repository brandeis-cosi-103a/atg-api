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
}
