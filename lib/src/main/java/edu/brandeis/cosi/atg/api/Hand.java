package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;

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
    public ImmutableList<Card> getAllCards();

    /**
     * Gets the unplayed cards in the hand.
     *
     * @return an immutable list of unplayed cards in the hand
     */
    public ImmutableList<Card> getUnplayedCards();

    /**
     * Gets the played cards in the hand.
     *
     * @return an immutable list of played cards in the hand
     */
    public ImmutableList<Card> getPlayedCards();
}
