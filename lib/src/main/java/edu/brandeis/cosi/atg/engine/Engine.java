package edu.brandeis.cosi.atg.engine;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.state.CardStacks;
import edu.brandeis.cosi.atg.state.GameState;

/**
 * An Engine executes a full game, given a list of available cards, and players
 * to participate.
 * <br/>
 * <br/>
 *
 * The Engine interacts with the {@link edu.brandeis.cosi.atg.player.Player}
 * interface by calling the
 * {@link edu.brandeis.cosi.atg.player.Player#makeDecision(GameState, ImmutableList)
 * Player.makeDecision}
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
 * 1. The {@link GameState.TurnPhase#ACTION ACTION} phase. During this phase,
 * the Engine should prompt the player with one
 * {@link edu.brandeis.cosi.atg.decisions.PlayCardDecision PlayCardDecision}
 * for each unplayed Action card in the player's hand, and a single
 * {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision EndPhaseDecision},
 * which the player can use to indicate that they have finished playing action
 * cards for this turn. A player starts a turn with one action, but can earn
 * additional actions by playing certain action cards. See
 * {@link edu.brandeis.cosi.atg.cards.Card.Type Card Types} for details on
 * the semantics of each card type.
 * <br/>
 * <br/>
 * 2. The {@link GameState.TurnPhase#MONEY MONEY} phase. During this phase, the
 * Engine should prompt the Player with one
 * {@link edu.brandeis.cosi.atg.decisions.PlayCardDecision PlayCardDecision}
 * for each unplayed card in the player's hand, and a single
 * {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision EndPhaseDecision},
 * which the player can use to indicate that they have finished playing money
 * for this turn.
 * <br/>
 * <br/>
 * 3. The {@link GameState.TurnPhase#BUY BUY} phase. During this phase, the
 * Engine should prompt the Player with one
 * {@link edu.brandeis.cosi.atg.decisions.BuyDecision BuyCardDecision}
 * for each card in the {@link edu.brandeis.cosi.atg.state.CardStacks} that the
 * player can afford to buy, and
 * a single {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision
 * EndPhaseDecision}, which the player can use to indicate that they have
 * finished buying cards for this turn. A player starts a turn with a single
 * buy, but can earn additional buys by playing certain action cards.
 * <br/>
 * <br/>
 * 4. The {@link GameState.TurnPhase#CLEANUP CLEANUP} phase. There is no player
 * involvement in this stage. The Engine should discard the player's hand, and
 * deal a new hand of 5 cards from the player's deck (shuffling if needed).
 *
 * <br/>
 * <br/>
 * <strong>Other turn phases:</strong>
 * <br/>
 * <br/>
 *
 * 1. The {@link GameState.TurnPhase#GAIN GAIN} phase. This phase occurs anytime
 * a player needs to gain cards. The player should be prompted with a list of
 * {@link edu.brandeis.cosi.atg.decisions.GainCardDecision
 * GainCardDecisions}, one for each eligible card that the player can gain.
 * Gained cards go directly to a player's discard pile.
 *
 * <br/>
 * <br/>
 * <strong> Ending the game</strong>
 * <br/>
 * <br/>
 *
 * When all {@link edu.brandeis.cosi.atg.cards.Card.Type#FRAMEWORK
 * FRAMEWORK} cards have been purchased, the game ends, and the Engine returns a
 * {@link GameResult} containing player results (name, score, and ending deck)
 * for each player, sorted by decreasing score.
 * <br/>
 * <br/>
 * <strong>Game events:</strong>
 * <br/>
 * <br/>
 * <strong>Creating Engines:</strong>
 * <br/>
 * <br/>
 * Engine implementations <strong>must</strong> have a 1-argument constructor
 * which accepts a
 * {@link java.util.List} of {@link edu.brandeis.cosi.atg.player.Player}s.
 * The Engine should throw an {@link java.lang.IllegalArgumentException} if the
 * list of Players contains more than 4 players.
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
 * <li>10x <b>per player</b> {@link edu.brandeis.cosi.atg.cards.Card.Type#BUG
 * Bug} cards</li>
 * <li>10x each of the 3 action cards:
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#REFACTOR Refactor}
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#CODE_REVIEW Code Review}
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#EVERGREEN_TEST Evergreen
 * Test}
 * </li>
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
     * Executes the game and returns the results for each player.
     *
     * @return The game results containing player information (name, score, and
     *         ending deck) for each player, sorted from most points to least.
     * @throws PlayerViolationException if a player violates the rules of the game
     *                                  or throws an exception when making a
     *                                  decision
     */
    public GameResult play() throws PlayerViolationException;
}
