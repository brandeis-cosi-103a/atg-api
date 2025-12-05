package edu.brandeis.cosi.atg.engine;

import com.google.common.collect.ImmutableList;

/**
 * Represents the results of a completed game, including all player results
 * sorted in order of decreasing score.
 * <br/>
 * <br/>
 * The player results are ordered from highest score to lowest score, providing
 * the final rankings of all players in the game.
 */
public final class GameResult {
    private final ImmutableList<PlayerResult> playerResults;

    /**
     * Constructs a GameResult with the specified player results.
     * <br/>
     * <br/>
     * The player results should be provided in order of decreasing score (highest
     * score first).
     *
     * @param playerResults an ordered list of player results, sorted from highest
     *                      score to lowest
     */
    public GameResult(ImmutableList<PlayerResult> playerResults) {
        this.playerResults = playerResults;
    }

    /**
     * Gets the player results in order of decreasing score.
     *
     * @return an immutable list of player results, sorted from highest score to
     *         lowest
     */
    public ImmutableList<PlayerResult> getPlayerResults() {
        return playerResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GameResult that = (GameResult) o;

        return playerResults.equals(that.playerResults);
    }

    @Override
    public int hashCode() {
        return playerResults.hashCode();
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "playerCount=" + playerResults.size() +
                ", results=" + playerResults +
                '}';
    }
}
