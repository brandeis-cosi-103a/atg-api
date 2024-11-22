package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * An Engine executes a full game, given a list of available cards, and players
 * to participate.
 *
 * Game events are logged to the {@link GameObserver GameObserver}, to
 * facilitate printing to a
 * console (or any other external handling of game events).
 */
public interface Engine {

    /**
     * Executes the game and returns the score for each player.
     *
     * @return The scores for each player, sorted from most points to least.
     */
    public ImmutableList<Player.ScorePair> play();

    /**
     * Factory interface for creating instances of {@link Engine}.
     */
    public interface EngineFactory {

        /**
         * Creates an instance of {@link Engine}.
         *
         * @param playerOne   the first player. Player one always goes first.
         * @param playerTwo   the second player
         * @param supplyPiles the types of cards available for purchase in this game
         * @param observer    the game observer
         * @return an instance of {@link Engine}
         * @throws IllegalArgumentException if the number of supply piles is not equal
         *                                  to 10
         */
        public Engine makeEngine(Player playerOne, Player playerTwo, ImmutableSet<Card.Type> supplyPiles,
                GameObserver observer);
    }
}
