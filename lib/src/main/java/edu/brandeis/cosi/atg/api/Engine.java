package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.api.cards.Card;

public interface Engine {
    public Player.ScorePair play();

    public interface EngineFactory {
        public Engine makeEngine(ImmutableList<Card> cards, Player playerOne, Player playerTwo, GameObserver observer);
    }
}
