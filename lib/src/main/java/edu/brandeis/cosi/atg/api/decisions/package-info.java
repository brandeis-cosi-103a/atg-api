/**
 * This package contains classes related to decisions made by players in the
 * game.
 * <br/>
 * <br/>
 * There are three types of decisions:
 * <ul>
 * <li>{@link edu.brandeis.cosi.atg.api.decisions.BuyDecision} - a decision to
 * buy a card</li>
 * <li>{@link edu.brandeis.cosi.atg.api.decisions.EndPhaseDecision} - a decision
 * to end a phase of play</li>
 * <li>{@link edu.brandeis.cosi.atg.api.decisions.PlayCardDecision} - a decision
 * to play a card</li>
 * </ul>
 * <br/>
 * <br/>
 * The {@link edu.brandeis.cosi.atg.api.Engine Engine} is responsible for
 * creating lists of possible valid decisions, and prompting the {@link
 * edu.brandeis.cosi.atg.api.Player Player} to choose one. The {@link
 * edu.brandeis.cosi.atg.api.Player Player} is responsible for choosing
 * a decision when prompted.
 */
package edu.brandeis.cosi.atg.api.decisions;