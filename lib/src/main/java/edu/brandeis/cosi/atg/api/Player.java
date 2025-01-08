package edu.brandeis.cosi.atg.api;

import java.util.Optional;
import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.api.decisions.Decision;
import edu.brandeis.cosi.atg.api.event.Event;

/**
 * A Player participates in a game. There is a single entry point:
 * {@link Player#makeDecision makeDecision}, which is invoked by the Engine when
 * the Player must
 * make a decision during the game, such as whether to play a card, which card
 * to buy, or which
 * cards to discard.
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
     * This method should handle exceptions to the greatest extent possible -
     * allowing an exception to escape will cause the player to forfeit the game.
     *
     * @param state   the current game state
     * @param options the available decisions to choose from
     * @param reason  the reason the player is being prompted with this decision, if
     *                any. A reason is provided when the player is being prompted
     *                with a decision as a result of another event in the game, but
     *                not when the player is being prompted for a decision during
     *                the normal course of their turn.
     * @return the chosen decision
     */
    public Decision makeDecision(GameState state, ImmutableList<Decision> options, Optional<Event> reason);

    /**
     * A factory for creating players.
     *
     * Implementations of this class <b>must</b> be default-constructible.
     */
    public interface PlayerFactory {
        /**
         * Creates a new player.
         *
         * @return the new player
         */
        public Player makePlayer();

        /**
         * Returns a short description of the player.
         *
         * This description should be suitable for display in place of a player name,
         * and so should be relatively short.
         *
         * @return a description of the player
         */
        public String getPlayerDescription();
    }

    /**
     * A pair of a player and their score.
     */
    public final class ScorePair {
        /**
         * The player that scored the score.
         */
        public Player player;
        /**
         * The score of the player.
         */
        public int score;

        /**
         * Constructs a ScorePair with the specified player and score.
         *
         * @param player the player
         * @param score  the score of the player
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
