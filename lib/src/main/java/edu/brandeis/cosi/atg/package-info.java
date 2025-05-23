/**
 * This library contains interfaces and type definitions for <i>Automation: The
 * Game</i> (ATG). ATG is derived from the popular card game Dominion, and is
 * used for a semester-long programming project in Cosi-103a at Brandeis
 * University.
 * <br/>
 * <br/>
 * <strong>Overview</strong>
 * This library provides 2 main interfaces:
 * {@link edu.brandeis.cosi.atg.engine.Engine} and
 * {@link edu.brandeis.cosi.atg.player.Player}.
 * The
 * {@link edu.brandeis.cosi.atg.engine.Engine} interface is used to execute a
 * game, and the {@link edu.brandeis.cosi.atg.player.Player}
 * interface is used to make decisions during the game.
 * <br/>
 * <br/>
 * This library also includes concrete utility classes for representing game
 * state: {@link edu.brandeis.cosi.atg.state.GameState},
 * {@link edu.brandeis.cosi.atg.cards.Card},
 * {@link edu.brandeis.cosi.atg.state.CardStacks}, and
 * {@link edu.brandeis.cosi.atg.state.Hand}.
 * The Engine uses these classes to communicate the state of the game to the
 * Player.
 * <br/>
 * <br/>
 * Finally, the {@link edu.brandeis.cosi.atg.decisions} package contains classes
 * for representing
 * decisions which a player can make during the game. The Engine sends a list of
 * possible decisions to the Player, and the Player chooses one of them.
 *
 * <strong>Game Design</strong>
 * This library is designed to provide a strong separation between the game
 * Engine and the Players. The goal of this design is to allow untrusted players
 * to participate in a game without fear of cheating. The Players are not
 * intended to be able to manipulate the game state directly - they simply
 * choose decisions, and the Engine executes them.
 * <br/>
 * <br/>
 * The {@link edu.brandeis.cosi.atg.engine.Engine} and
 * {@link edu.brandeis.cosi.atg.player.Player} communicate via a single method:
 * {@link Player#makeDecision}. During the course of the game, when the Engine
 * needs to prompt the Player for a decision, it should call the
 * {@link Player#makeDecision} method, passing in the current game state and a
 * list of possible decisions. The Player should then choose one of the
 * decisions, and return it to the Engine. The Engine is then responsible for
 * executing the decision and updating the game state.
 * <br/>
 * <br/>
 */
package edu.brandeis.cosi.atg;

import edu.brandeis.cosi.atg.player.Player;
