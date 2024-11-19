package edu.brandeis.cosi.atg.api;

import java.util.Optional;
import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.api.decisions.Decision;
import edu.brandeis.cosi.atg.api.event.Event;

/**
 * The interface to a player in a game of <i>Automation: The Game</i>.
 * <br/>
 * <br/>
 * A Player participates in a game. There is a single entry point:
 * {@link Player#makeDecision makeDecision}, which is invoked by the Engine when
 * the Player must make a decision during the game, such as whether to play a
 * card, which card to buy, or which cards to discard.
 * <br/>
 * <br/>
 *
 * <strong>Creating Players:</strong>
 * <br/>
 * <br/>
 * Implementations of this class can have any constructor signature(s) required.
 * However, a package containing a Player must provide at least one method
 * annotated with the {@link PlayerCreator} annotation. This allows a Player to
 * be created generically for testing or other purposes. See the
 * {@link PlayerCreator} documentation for more details.
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
     * @return the chosen decision
     */
    public Decision makeDecision(GameState state, ImmutableList<Decision> options, Optional<Event> reason);

    /**
     * Gets the observer for this player.
     *
     * The observer is used to notify the player of game events. If a Player
     * is participating in a game, the Engine will notify this observer of
     * all game events.
     *
     * If the returned observer is not present, no events will be sent to the
     * player.
     *
     * @return the observer for this player, or an empty Optional if the player
     *         does not require access to game events.
     */
    public Optional<GameObserver> getObserver();

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
