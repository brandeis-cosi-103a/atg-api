package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * An Engine executes a full game, given a list of available cards, and players
 * to participate.
 *
 * Game events are logged to the {@link GameObserver}, to
 * facilitate printing to a console (or any other external handling of game
 * events).
 *
 * The Engine interacts with the {@link Player} interface by calling the
 * {@link Player#makeDecision(GameState, ImmutableList) Player.makeDecision}
 * method. In general, the Engine should only prompt the
 * Player with legal options, and the Engine is also responsible for ensuring
 * that the Player only selects from the decisions that were provided to it. If
 * a Player does not behave as expected - either by not returning a Decision, or
 * returning an invalid or disallowed Decision, the Engine should throw a
 * {@link PlayerViolationException}.
 *
 * During the {@link GameState.TurnPhase#MONEY MONEY} phase, the Engine should
 * prompt the Player with one
 * {@link edu.brandeis.cosi.atg.api.decisions.PlayCardDecision
 * PlayCardDecisions} for each unplayed card in the player's hand, and a single
 * {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision
 * EndPhaseDecision}, which the player can use to indicate that they have
 * finished playing money for this turn.
 *
 * During the {@link GameState.TurnPhase#BUY BUY} phase, the Engine should
 * prompt the Player with one
 * {@link edu.brandeis.cosi.atg.api.decisions.BuyDecision BuyCardDecision}
 * for each card in the {@link GameDeck} that the player can afford to buy, and
 * a single {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision
 * EndPhaseDecision}, which the player can use to indicate that they have
 * finished buying cards for this turn. A player starts a turn with a single
 * buy. In this version of the game, there is no way to acquire additional buys.
 *
 * During the {@link GameState.TurnPhase#CLEANUP CLEANUP} phase, the Engine
 * should discard the player's hand, and deal a new hand of 5 cards from the
 * player's deck (shuffling if needed).
 *
 * When all {@link edu.brandeis.cosi.atg.api.cards.Card.Type#FRAMEWORK
 * FRAMEWORK} cards have been purchased, the game ends, and the Engine returns a
 * list of {@link Player.ScorePair Player.ScorePairs} representing the scores of
 * each player.
 */
public interface Engine {

    /**
     * Executes the game and returns the score for each player.
     *
     * @return The scores for each player, sorted from most points to least.
     * @throws PlayerViolationException if a player violates the rules of the game
     *                                  or throws an exception when making a
     *                                  decision
     */
    public ImmutableList<Player.ScorePair> play() throws PlayerViolationException;

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
