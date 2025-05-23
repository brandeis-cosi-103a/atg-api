/**
 * This package contains classes related to decisions made by players in the
 * game.
 * <br/>
 * <br/>
 * There are six types of decisions:
 * <ul>
 * <li>{@link edu.brandeis.cosi.atg.decisions.BuyDecision} - a decision to
 * buy a card</li>
 * <li>{@link edu.brandeis.cosi.atg.decisions.PlayCardDecision} - a decision
 * to play a card</li>
 * <li>{@link edu.brandeis.cosi.atg.decisions.GainCardDecision} - a decision
 * to gain a card</li>
 * <li>{@link edu.brandeis.cosi.atg.decisions.DiscardCardDecision} - a
 * decision to discard a card</li>
 * <li>{@link edu.brandeis.cosi.atg.decisions.TrashCardDecision} - a
 * decision to trash (permanently remove from the player's deck) a card</li>
 * <li>{@link edu.brandeis.cosi.atg.decisions.EndPhaseDecision} - a decision
 * to end a phase of play</li>
 * </ul>
 * <br/>
 * <br/>
 * The {@link edu.brandeis.cosi.atg.engine.Engine Engine} is responsible for
 * creating lists of possible valid decisions, and prompting the {@link
 * edu.brandeis.cosi.atg.player.Player Player} to choose one. The {@link
 * edu.brandeis.cosi.atg.player.Player Player} is responsible for choosing
 * a decision when prompted.
 */
package edu.brandeis.cosi.atg.decisions;