/**
 * This package contains immutable data structures representing game state.
 * The Engine uses these classes to communicate the state of the game to Players
 * when prompting for decisions.
 * <br/>
 * <br/>
 * <strong>Core State Classes</strong>
 * <ul>
 * <li><strong>{@link edu.brandeis.cosi.atg.state.GameState}:</strong>
 * Represents
 * the current state of the game, including the current player, turn phase,
 * available actions/buys/money, the current player's hand, and available cards
 * for purchase. In v2, the
 * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase TurnPhase}
 * enum includes two new phases:
 * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#REACTION REACTION}
 * and {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#DISCARD
 * DISCARD}.</li>
 * <li><strong>{@link edu.brandeis.cosi.atg.state.Hand}:</strong> Represents a
 * player's current hand of cards, with separate collections for played and
 * unplayed cards.</li>
 * <li><strong>{@link edu.brandeis.cosi.atg.state.CardStacks}:</strong>
 * Represents
 * the supply of purchasable cards during the game.</li>
 * </ul>
 * <br/>
 * <strong>Result Classes</strong>
 * <ul>
 * <li><strong>{@link edu.brandeis.cosi.atg.state.GameResult}:</strong> Contains
 * final game results for all players, sorted by score (descending).</li>
 * <li><strong>{@link edu.brandeis.cosi.atg.state.PlayerResult}:</strong>
 * Contains
 * a single player's final results: name, score, and ending deck.</li>
 * </ul>
 * <br/>
 * <strong>Design Notes</strong>
 * <br/>
 * All classes in this package are immutable records. This ensures thread safety
 * and prevents accidental state corruption. The Engine is responsible for
 * creating new state objects as the game progresses.
 */
package edu.brandeis.cosi.atg.state;