package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableCollection;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * A Hand represents the collection of cards a player has during the game.
 */
public interface Hand {

    /**
     * Gets all cards in the hand.
     *
     * @return an immutable list of all cards in the hand
     */
    public ImmutableCollection<Card> getAllCards();

    /**
     * Gets the unplayed cards in the hand.
     *
     * @return an immutable collection of unplayed cards in the hand
     */
    public ImmutableCollection<Card> getUnplayedCards();

    /**
     * Gets the played cards in the hand.
     *
     * @return an immutable collection of played cards in the hand
     */
    public ImmutableCollection<Card> getPlayedCards();
}
