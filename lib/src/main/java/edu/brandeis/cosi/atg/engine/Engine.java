package edu.brandeis.cosi.atg.engine;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.player.Player;
import edu.brandeis.cosi.atg.state.CardStacks;
import edu.brandeis.cosi.atg.state.GameState;

/**
 * An Engine executes a full game, given a list of available cards, and players
 * to participate.
 * <br/>
 * <br/>
 *
 * The Engine interacts with the {@link Player} interface by calling the
 * {@link Player#makeDecision(GameState, ImmutableList) Player.makeDecision}
 * method. In general, the Engine should only prompt the
 * Player with legal options, and the Engine is also responsible for ensuring
 * that the Player only selects from the decisions that were provided to it. If
 * a Player does not behave as expected - either by not returning a Decision, or
 * returning an invalid or disallowed Decision, the Engine should throw a
 * {@link PlayerViolationException}.
 * <br/>
 * <br/>
 * <strong>Primary phases of a turn:</strong>
 * <br/>
 * <br/>
 * During the {@link GameState.TurnPhase#MONEY MONEY} phase, the Engine should
 * prompt the Player with one
 * {@link edu.brandeis.cosi.atg.decisions.PlayCardDecision
 * PlayCardDecisions} for each unplayed card in the player's hand, and a single
 * {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision
 * EndPhaseDecision}, which the player can use to indicate that they have
 * finished playing money for this turn.
 * <br/>
 * <br/>
 *
 * During the {@link GameState.TurnPhase#BUY BUY} phase, the Engine should
 * prompt the Player with one
 * {@link edu.brandeis.cosi.atg.decisions.BuyDecision BuyCardDecision}
 * for each card in the {@link CardStacks} that the player can afford to buy,
 * and
 * a single {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision
 * EndPhaseDecision}, which the player can use to indicate that they have
 * finished buying cards for this turn. A player starts a turn with a single
 * buy. In this version of the game, there is no way to acquire additional buys.
 * <br/>
 * <br/>
 *
 * During the {@link GameState.TurnPhase#CLEANUP CLEANUP} phase, the Engine
 * should discard the player's hand, and deal a new hand of 5 cards from the
 * player's deck (shuffling if needed).
 * <br/>
 * <br/>
 * <strong>Ending the game:</strong>
 * <br/>
 * <br/>
 * When all {@link edu.brandeis.cosi.atg.cards.Card.Type#FRAMEWORK
 * FRAMEWORK} cards have been purchased, the game ends, and the Engine returns a
 * list of {@link ScorePair Player.ScorePairs} representing the scores of
 * each player.
 * <br/>
 * <br/>
 * <strong>Creating Engines:</strong>
 * <br/>
 * <br/>
 * Engine implementations <strong>must</strong> have a zero-argument
 * constructor. They may optionally have additional constructors for testing or
 * other purposes.
 * <br/>
 * <br/>
 * <strong>Starting cards:</strong>
 * <br/>
 * <br/>
 * Engines should initialize a {@link CardStacks} with the following cards:
 * <ul>
 * <li>60x {@link edu.brandeis.cosi.atg.cards.Card.Type#BITCOIN Bitcoin}
 * cards</li>
 * <li>40x {@link edu.brandeis.cosi.atg.cards.Card.Type#ETHEREUM Ethereum}
 * cards</li>
 * <li>30x {@link edu.brandeis.cosi.atg.cards.Card.Type#DOGECOIN Dogecoin}
 * cards</li>
 * <li>14x {@link edu.brandeis.cosi.atg.cards.Card.Type#METHOD Method}
 * cards</li>
 * <li>8x {@link edu.brandeis.cosi.atg.cards.Card.Type#MODULE Module}
 * cards</li>
 * <li>8x {@link edu.brandeis.cosi.atg.cards.Card.Type#FRAMEWORK Framework}
 * cards</li>
 * </ul>
 *
 * Starting hands for players should be dealt from this GameDeck. Each player's
 * starting hand should include:
 * <ul>
 * <li>7x {@link edu.brandeis.cosi.atg.cards.Card.Type#BITCOIN Bitcoin}
 * cards</li>
 * <li>3x {@link edu.brandeis.cosi.atg.cards.Card.Type#METHOD Method}
 * cards</li>
 * </ul>
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
    public ImmutableList<ScorePair> play() throws PlayerViolationException;
}
