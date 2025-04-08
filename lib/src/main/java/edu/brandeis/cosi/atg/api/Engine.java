package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;

/**
 * An Engine executes a full game, given a list of available cards, and players
 * to participate.
 * <br/>
 * <br/>
 *
 * The Engine interacts with the {@link Player} interface by calling the
 * {@link Player#makeDecision(GameState, ImmutableList, Optional)
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
 * {@link edu.brandeis.cosi.atg.api.decisions.PlayCardDecision PlayCardDecision}
 * for each unplayed Action card in the player's hand, and a single
 * {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision
 * EndPhaseDecision}, which the player can use to indicate that they have
 * finished playing action cards for this turn. A player starts a turn with one
 * action, but can earn additional actions by playing certain action cards. See
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type Card Types} for details on
 * the semantics of each card type.
 *
 * <br/>
 * <br/>
 * 2. The {@link GameState.TurnPhase#MONEY MONEY} phase. During this phase, the
 * Engine should prompt the Player with one
 * {@link edu.brandeis.cosi.atg.api.decisions.PlayCardDecision
 * PlayCardDecisions} for each unplayed card in the player's hand, and a single
 * {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision
 * EndPhaseDecision}, which the player can use to indicate that they have
 * finished playing money for this turn.
 * <br/>
 * <br/>
 * 3. The {@link GameState.TurnPhase#BUY BUY} phase. During this phase, the
 * Engine should prompt the Player with one
 * {@link edu.brandeis.cosi.atg.api.decisions.BuyDecision BuyCardDecision}
 * for each card in the {@link GameDeck} that the player can afford to buy, and
 * a single {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision
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
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#HACK Hack} or
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#EVERGREEN_TEST Evergreen
 * Test}). At the time that the attack card is played, each other player
 * that holds a
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#MONITORING Monitoring card}
 * should be prompted with a
 * {@link edu.brandeis.cosi.atg.api.decisions.PlayCardDecision}
 * for the Monitoring card, and an
 * {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision}
 * with the phase set to {@link GameState.TurnPhase#REACTION REACTION}.
 * <br/>
 * <br/>
 * 2. The {@link GameState.TurnPhase#DISCARD DISCARD} phase. This phase occurs
 * anytime a player needs to discard cards from their hand.
 * The player should be prompted with a list of
 * {@link edu.brandeis.cosi.atg.api.decisions.DiscardCardDecision
 * DiscardCardDecisions}, one for each eligible (unplayed) card in their hand.
 * If the player is allowed to stop discarding, the Engine should also include
 * an {@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision
 * EndPhaseDecision} with the phase set to {@link GameState.TurnPhase#DISCARD
 * DISCARD}. In cases where the player is obligated to discard cards, the Engine
 * should not include an EndPhaseDecision in the list of possibilities (and
 * raise a {@link PlayerViolationException} if the player attempts to end the
 * phase or does not choose a valid discard decision).
 * <br/>
 * <br/>
 * 3. The {@link GameState.TurnPhase#GAIN GAIN} phase. This phase occurs anytime
 * a player needs to gain cards. The player should be prompted with a list of
 * {@link edu.brandeis.cosi.atg.api.decisions.GainCardDecision
 * GainCardDecisions}, one for each eligible card that the player can gain.
 * Gained cards go directly to a player's discard pile.
 *
 * <br/>
 * <br/>
 * <strong> Ending the game</strong>
 * <br/>
 * <br/>
 *
 * When all {@link edu.brandeis.cosi.atg.api.cards.Card.Type#FRAMEWORK
 * FRAMEWORK} cards have been purchased, the game ends, and the Engine returns a
 * list of {@link Player.ScorePair Player.ScorePairs} representing the scores of
 * each player.
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
 * See the {@link edu.brandeis.cosi.atg.api.event event} package
 * documentation for details on events.
 * <br/>
 * <br/>
 * <strong>Creating Engines:</strong>
 * <br/>
 * <br/>
 * Implementations of this class can have any constructor signature(s).
 * However, a package containing an Engine must provide exactly one method
 * annotated with the {@link EngineCreator} annotation. This allows an Engine to
 * be created generically for testing or other purposes. See the
 * {@link EngineCreator} documentation for more details.
 * <br/>
 * <br/>
 * <strong>Starting cards:</strong>
 * <br/>
 * <br/>
 * Engines should initialize a {@link GameDeck} with the following cards:
 * <ul>
 * <li>60x {@link edu.brandeis.cosi.atg.api.cards.Card.Type#BITCOIN Bitcoin}
 * cards</li>
 * <li>40x {@link edu.brandeis.cosi.atg.api.cards.Card.Type#ETHEREUM Ethereum}
 * cards</li>
 * <li>30x {@link edu.brandeis.cosi.atg.api.cards.Card.Type#DOGECOIN Dogecoin}
 * cards</li>
 * <li>14x {@link edu.brandeis.cosi.atg.api.cards.Card.Type#METHOD Method}
 * cards</li>
 * <li>8x {@link edu.brandeis.cosi.atg.api.cards.Card.Type#MODULE Module}
 * cards</li>
 * <li>8x {@link edu.brandeis.cosi.atg.api.cards.Card.Type#FRAMEWORK Framework}
 * cards</li>
 * <li>10x each of the 10 action cards:
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#BACKLOG Backlog}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#DAILY_SCRUM Daily Scrum}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#IPO IPO}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#HACK Hack}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#MONITORING Monitoring}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#TECH_DEBT Tech Debt}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#REFACTOR Refactor}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#PARALLELIZATION
 * Parallelization}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#CODE_REVIEW Code Review}
 * {@link edu.brandeis.cosi.atg.api.cards.Card.Type#EVERGREEN_TEST Evergreen
 * Test}
 * </li> *
 * </ul>
 *
 * Starting hands for players should be dealt from this GameDeck. Each player's
 * staring hand should include:
 * <ul>
 * <li>7x {@link edu.brandeis.cosi.atg.api.cards.Card.Type#BITCOIN Bitcoin}
 * cards</li>
 * <li>3x {@link edu.brandeis.cosi.atg.api.cards.Card.Type#METHOD Method}
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
    public ImmutableList<Player.ScorePair> play() throws PlayerViolationException;
}
