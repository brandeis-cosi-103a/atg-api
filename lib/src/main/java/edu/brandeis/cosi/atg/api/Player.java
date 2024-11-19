package edu.brandeis.cosi.atg.api;

import java.util.Optional;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.api.decisions.Decision;
import edu.brandeis.cosi.atg.api.event.Event;

/**
 * A Player participates in a game. There is a single entry point:
 * {@link Player#makeDecision makeDecision}, which is invoked by the Engine when the Player must
 * make a decision during the game, such as whether to play a card, which card to buy, or which
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
     * @param state the current game state
     * @param options the available decisions to choose from
     * @param reason the previous Decision that led to this decision point, if any
     * @return the chosen decisions
     */
    public ImmutableList<Decision> makeDecision(GameState state, ImmutableList<Decision> options, Optional<Event> reason);

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
