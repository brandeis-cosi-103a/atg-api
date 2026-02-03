package edu.brandeis.cosi.atg.engine;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.event.GameObserver;
import edu.brandeis.cosi.atg.player.Player;
import edu.brandeis.cosi.atg.state.CardStacks;
import edu.brandeis.cosi.atg.state.GameResult;
import edu.brandeis.cosi.atg.state.GameState;

/**
 * Game execution engine that orchestrates play between players.
 * <br/>
 * <br/>
 * <strong>Responsibility:</strong>
 * <br/>
 * The Engine executes a complete game given a list of players and available
 * cards,
 * managing game state transitions and enforcing game rules. It communicates
 * with
 * players through the {@link edu.brandeis.cosi.atg.player.Player} interface,
 * providing valid decision options and validating chosen decisions.
 * <br/>
 * <br/>
 * <strong>Player Interaction:</strong>
 * <br/>
 * The Engine calls
 * {@link edu.brandeis.cosi.atg.player.Player#makeDecision(GameState, ImmutableList, java.util.Optional)
 * Player.makeDecision} to obtain player decisions. The Engine must:
 * <ul>
 * <li>Only provide legal decision options to players</li>
 * <li>Validate that chosen decisions are from the provided options</li>
 * <li>Throw {@link PlayerViolationException} for invalid or non-compliant
 * choices</li>
 * </ul>
 * <br/>
 * <strong>Game Flow - Turn Phases:</strong>
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
 * 1. The {@link GameState.TurnPhase#REACTION REACTION} phase. This phase occurs
 * when another player plays an attack card (
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#HACK Hack} or
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#EVERGREEN_TEST Evergreen
 * Test}). At the time that the attack card is played, each other player
 * that holds a
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#MONITORING Monitoring card}
 * should be prompted with a
 * {@link edu.brandeis.cosi.atg.decisions.PlayCardDecision}
 * for the Monitoring card, and an
 * {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision}
 * with the phase set to {@link GameState.TurnPhase#REACTION REACTION}.
 * <br/>
 * <br/>
 * 2. The {@link GameState.TurnPhase#DISCARD DISCARD} phase. This phase occurs
 * anytime a player needs to discard cards from their hand.
 * The player should be prompted with a list of
 * {@link edu.brandeis.cosi.atg.decisions.DiscardCardDecision
 * DiscardCardDecisions}, one for each eligible (unplayed) card in their hand.
 * If the player is allowed to stop discarding, the Engine should also include
 * an {@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision
 * EndPhaseDecision} with the phase set to {@link GameState.TurnPhase#DISCARD
 * DISCARD}. In cases where the player is obligated to discard cards, the Engine
 * should not include an EndPhaseDecision in the list of possibilities (and
 * raise a {@link PlayerViolationException} if the player attempts to end the
 * phase or does not choose a valid discard decision).
 * <br/>
 * <br/>
 * 3. The {@link GameState.TurnPhase#GAIN GAIN} phase. This phase occurs anytime
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
 * Engines are responsible for logging game events to {@link GameObserver}s.
 * <br/>
 * <br/>
 * Each {@link Player} has a {@link Player#getObserver()} method, which returns
 * an {@link GameObserver} for that player. The Engine should log all events to
 * the Player observers (if present).
 * <br/>
 * <br/>
 * Additionally, Engines can be configured with an additional
 * {@link GameObserver} to log which events should be logged. This can be used
 * to log events to the console, a file, or to facilitate testing.
 * <br/>
 * <br/>
 * See the {@link edu.brandeis.cosi.atg.event event} package
 * documentation for details on events.
 * <br/>
 * <br/>
 * <strong>Creating Engines:</strong>
 * <br/>
 * <br/>
 * Engine implementations <strong>must</strong> have a 2-argument constructor
 * which accepts:
 * <ul>
 * <li>A {@link java.util.List} of
 * {@link edu.brandeis.cosi.atg.player.Player}s (must not be null)</li>
 * <li>A {@link java.util.List} of {@link edu.brandeis.cosi.atg.cards.Card.Type}
 * representing the 10 action card types to use in the game (must not be null,
 * must contain exactly 10 distinct action card types)</li>
 * </ul>
 * The Engine should throw an {@link java.lang.IllegalArgumentException} if:
 * <ul>
 * <li>The list of Players contains more than 4 players</li>
 * <li>The list of action card types is null, does not contain exactly 10 types,
 * contains duplicate types, or contains non-action card types</li>
 * </ul>
 * <br/>
 * <strong>Harness Responsibility:</strong>
 * <br/>
 * The game harness (the code that creates and runs Engine instances) is
 * responsible for randomly selecting 10 action card types from the 15 available
 * action cards and passing them to the Engine constructor. This random
 * selection
 * should happen once per game, not within the Engine.
 * <br/>
 * <br/>
 * <strong>Card Stack Configuration:</strong>
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
 * Bug}
 * cards</li>
 * <li>10x each of the 10 action card types provided in the constructor
 * parameter.
 * The harness randomly selects 10 from the 15 available action cards:
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#BACKLOG Backlog},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#CODE_REVIEW Code Review},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#DAILY_SCRUM Daily Scrum},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#DEPLOYMENT_PIPELINE Deployment
 * Pipeline},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#EVERGREEN_TEST Evergreen Test},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#HACK Hack},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#IPO IPO},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#MERGE_CONFLICT Merge Conflict},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#MONITORING Monitoring},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#PARALLELIZATION
 * Parallelization},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#REFACTOR Refactor},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#SPRINT_PLANNING Sprint
 * Planning},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#TECH_DEBT Tech Debt},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#RANSOMWARE
 * Ransomware},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#UNIT_TEST Unit Test}
 * </li>
 * </ul>
 *
 * <strong>Player Starting Hands:</strong>
 * <br/>
 * Each player's starting hand should include:
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

    /**
     * Sets an additional observer to receive game events.
     * <br/>
     * This observer is notified alongside any player-specific observers.
     * It can be used for logging, testing, or external monitoring.
     *
     * @param observer the observer to notify of game events
     */
    default void setObserver(GameObserver observer) {
        // Default no-op; implementations should override to store and use the observer.
    }
}
