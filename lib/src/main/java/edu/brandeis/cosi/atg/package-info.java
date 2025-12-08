/**
 * This library contains interfaces and type definitions for <i>Automation: The
 * Game</i> (ATG). ATG is derived from the popular card game Dominion, and is
 * used for a semester-long programming project in Cosi-103a at Brandeis
 * University.
 * <br/>
 * <br/>
 * <strong>What's Changed Since v1</strong>
 * <br/>
 * Version 2 introduces significant gameplay enhancements and infrastructure
 * improvements:
 * <ul>
 * <li><strong>Event System:</strong> New {@link edu.brandeis.cosi.atg.event}
 * package
 * with {@link edu.brandeis.cosi.atg.event.GameObserver} interface for tracking
 * game
 * events (card plays, gains, discards, etc.). Engines must now log events to
 * observers.</li>
 * <li><strong>New Action Cards:</strong> Seven new action cards added with
 * diverse mechanics:
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#BACKLOG Backlog},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#DAILY_SCRUM Daily Scrum},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#IPO IPO},
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#HACK Hack} (Attack),
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#MONITORING Monitoring}
 * (Reaction),
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#TECH_DEBT Tech Debt}, and
 * {@link edu.brandeis.cosi.atg.cards.Card.Type#PARALLELIZATION
 * Parallelization}.</li>
 * <li><strong>Attack and Reaction Mechanics:</strong> New Attack card category
 * (Hack, {@link edu.brandeis.cosi.atg.cards.Card.Type#EVERGREEN_TEST Evergreen
 * Test})
 * and Reaction card (Monitoring) that can counter attacks.</li>
 * <li><strong>New Game Phases:</strong> Added
 * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#REACTION REACTION}
 * phase
 * for responding to attacks and
 * {@link edu.brandeis.cosi.atg.state.GameState.TurnPhase#DISCARD DISCARD} phase
 * for discarding cards.</li>
 * <li><strong>New Decision Type:</strong>
 * {@link edu.brandeis.cosi.atg.decisions.DiscardCardDecision} added for discard
 * mechanics.</li>
 * <li><strong>JSON Serialization:</strong> Full Jackson annotation support
 * added to all
 * game state, decision, and event types for JSON
 * serialization/deserialization.</li>
 * <li><strong>Card Implementation:</strong> Card class converted to a record
 * with
 * explicit JSON property annotations and improved documentation.</li>
 * </ul>
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
 * <li><strong>Events:</strong> Provides game event notifications via the
 * {@link edu.brandeis.cosi.atg.event} package and
 * {@link edu.brandeis.cosi.atg.event.GameObserver} interface.</li>
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
