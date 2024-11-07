package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * An Engine executes a full game, given a list of available cards, and players to participate.
 *
 * Game events are logged to the {@link GameObserver GameObserver}, to facilitate printing to a
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
         * @param cards the list of available cards
         * @param playerOne the first player
         * @param playerTwo the second player
         * @param observer the game observer
         * @return an instance of {@link Engine}
         */
        public Engine makeEngine(ImmutableList<Card> cards, Player playerOne, Player playerTwo, GameObserver observer);
    }
}
