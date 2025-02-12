package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.api.actions.Action;

/**
 * A Player participates in a game. There is a single entry point: {@link Player#makeChoice makeChoice},
 * which is invoked by the Engine when the Player must make a decision during the game, such as
 * whether to play a card, which card to buy, or which cards to discard.
 */
public interface Player {

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName();

    /**
     * Makes a choice during the game.
     *
     * @param state the current game state
     * @param options the available actions to choose from
     * @return the chosen actions
     */
    public ImmutableList<Action> makeChoice(GameState state, ImmutableList<Action> options);

    /**
     * A pair of a player and their score.
     */
    public final class ScorePair {
        public Player player;
        public int score;

        /**
         * Constructs a ScorePair with the specified player and score.
         *
         * @param player the player
         * @param score the score of the player
         */
        public ScorePair(Player player, int score) {
            this.player = player;
            this.score = score;
        }

        /**
         * Gets the score of the player.
         *
         * @return the score of the player
         */
        public int getScore() {
            return score;
        }
    }
}
