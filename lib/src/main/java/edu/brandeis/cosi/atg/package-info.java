/**
 * This library contains interfaces and type definitions for <i>Automation: The
 * Game</i> (ATG). ATG is derived from the popular card game Dominion, and is
 * used for a semester-long programming project in Cosi-103a at Brandeis
 * University.
 * <br/>
 * <br/>
 * <strong>Architecture Overview</strong>
 * <br/>
 * This library provides a framework for implementing and running games of ATG.
 * The primary components are:
 * <ul>
 * <li><strong>Engine:</strong> Implements game logic and turn management via
 * the
 * {@link edu.brandeis.cosi.atg.engine.Engine} interface.</li>
 * <li><strong>Player:</strong> Implements player decision-making via the
 * {@link edu.brandeis.cosi.atg.player.Player} interface.</li>
 * <li><strong>State:</strong> Represents game state via immutable records in
 * the
 * {@link edu.brandeis.cosi.atg.state} package.</li>
 * <li><strong>Decisions:</strong> Encapsulates player choices via sealed
 * interface types in the {@link edu.brandeis.cosi.atg.decisions} package.</li>
 * <li><strong>Cards:</strong> Defines card types and attributes via
 * {@link edu.brandeis.cosi.atg.cards.Card}.</li>
 * </ul>
 * <br/>
 * <strong>Design Principles</strong>
 * <br/>
 * This library enforces a strict separation between the Engine and Players:
 * <ul>
 * <li>Players cannot directly manipulate game state—they only choose from
 * presented decisions.</li>
 * <li>The Engine is responsible for validating decisions and updating
 * state.</li>
 * <li>All game state is immutable, ensuring consistency and safety.</li>
 * <li>All required parameters are validated with
 * {@link java.util.Objects#requireNonNull},
 * providing clear error messages.</li>
 * </ul>
 * <br/>
 * <strong>Communication Pattern</strong>
 * <br/>
 * The Engine and Player interact through a single method:
 * {@link edu.brandeis.cosi.atg.player.Player#makeDecision}. The Engine
 * provides the current game state and a list of valid decisions, and the Player
 * returns their chosen decision. The Engine then executes this decision and
 * updates the game state accordingly.
 * <br/>
 * <br/>
 */
package edu.brandeis.cosi.atg;

import edu.brandeis.cosi.atg.player.Player;
