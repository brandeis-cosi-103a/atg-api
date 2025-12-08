/**
 * This package contains classes related to the {@link Engine}. See the
 * {@link Engine} class for more details.
 * <br/>
 * <br/>
 * <strong>Version 2 Requirements:</strong>
 * <br/>
 * In version 2, Engine implementations must:
 * <ul>
 * <li>Support the {@link edu.brandeis.cosi.atg.event.GameObserver} interface
 * for logging game events to observers</li>
 * <li>Handle the new
 * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#REACTION REACTION}
 * phase for processing Attack and Reaction card interactions</li>
 * <li>Handle the new
 * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#DISCARD DISCARD}
 * phase for card discard mechanics</li>
 * <li>Support the new
 * {@link edu.brandeis.cosi.atg.decisions.DiscardCardDecision}
 * decision type</li>
 * <li>Implement mechanics for the 7 new action cards added in v2</li>
 * </ul>
 */
package edu.brandeis.cosi.atg.engine;