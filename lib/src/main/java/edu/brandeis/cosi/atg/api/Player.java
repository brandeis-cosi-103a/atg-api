package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.api.actions.Action;

public interface Player {
    public String getName();

    public ImmutableList<Action> makeChoice(GameState state, ImmutableList<Action> options);

    public final class ScorePair {
        public Player player;
        public int score;

        public ScorePair(Player player, int score) {
            this.player = player;
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }
}
