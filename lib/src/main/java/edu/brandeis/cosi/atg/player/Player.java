package edu.brandeis.cosi.atg.player;

import java.util.Optional;
import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.decisions.Decision;

import edu.brandeis.cosi.atg.engine.Engine;
import edu.brandeis.cosi.atg.event.Event;
import edu.brandeis.cosi.atg.event.GameObserver;
import edu.brandeis.cosi.atg.state.GameState;

/**
 * The interface to a player in a game of <i>Automation: The Game</i>.
 * <br/>
 * <br/>
 * A Player participates in a game and is responsible for making decisions
 * throughout the game. The Player cannot directly manipulate game state—it is
 * prompted by the {@link Engine} to choose from a list of presented decisions.
 * The Engine is responsible for applying the chosen decision and updating the
 * game state.
 * <br/>
 * <br/>
 * <strong>Responsibilities</strong>
 * <ul>
 * <li>Implement the zero-argument constructor (required by the Engine).</li>
 * <li>Implement {@link #getName()} to return a unique player identifier.</li>
 * <li>Implement
 * {@link #makeDecision(GameState, ImmutableList, java.util.Optional)} to select
 * a
 * decision from the provided options.</li>
 * <li>Handle exceptions gracefully; unhandled exceptions will cause the player
 * to forfeit the game.</li>
 * </ul>
 * <br/>
 * <strong>Implementation Notes</strong>
 * <br/>
 * {@link #makeDecision(GameState, ImmutableList, java.util.Optional)} is the
 * only method the Engine
 * calls on the Player. The provided options are the only valid choices;
 * selecting
 * any other decision will result in a
 * {@link edu.brandeis.cosi.atg.engine.PlayerViolationException}.
 * <br/>
 * <br/>
 */
public interface Player {

    // Player implementations MUST have a zero-argument constructor for the
    // Engine to use.

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName();

    /**
     * Makes a choice during the game.
     *
     * This method should handle exceptions to the greatest extent possible -
     * allowing an exception to escape will cause the player to forfeit the game.
     *
     * @param state   the current game state
     * @param options the available decisions to choose from
     * @param reason  the reason the player is being prompted with this decision, if
     *                any. A reason is provided when the player is being prompted
     *                with a decision as a result of another event in the game, but
     *                not when the player is being prompted for a decision during
     *                the normal course of their turn.
     * @return the chosen decision
     */
    public Decision makeDecision(GameState state, ImmutableList<Decision> options, Optional<Event> reason);

    /**
     * Gets the observer for this player.
     *
     * The observer is used to notify the player of game events. If a Player
     * is participating in a game, the Engine will notify this observer of
     * all game events.
     *
     * If the returned observer is not present, no events will be sent to the
     * player.
     *
     * @return the observer for this player, or an empty Optional if the player
     *         does not require access to game events.
     */
    public Optional<GameObserver> getObserver();
}
