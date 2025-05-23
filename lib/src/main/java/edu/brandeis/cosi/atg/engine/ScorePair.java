package edu.brandeis.cosi.atg.engine;

import edu.brandeis.cosi.atg.player.Player;

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