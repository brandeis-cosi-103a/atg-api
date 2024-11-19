package edu.brandeis.cosi.atg.api.event;

import edu.brandeis.cosi.atg.api.Player;
import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents a decision by a player to play a card.
 */
public final class PlayCardEvent implements Event {
    private Card card;
    private Player player;

    /**
     * Constructs a PlayCardEvent with the specified card.
     *
     * @param card the card that was played
     */
    public PlayCardEvent(Card card, Player player) {
        this.card = card;
        this.player = player;
    }

    /**
     * Gets the description of the play card event.
     */
    public String getDescription() {
        return player.getName() + " played card: " + card;
    }

    /**
     * Gets the card that was played.
     */
    public Card getCard() {
        return card;
    }
}
