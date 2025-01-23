/**
 * This package contains classes related to game events.
 * <br/>
 * <br/>
 * There are four types of events:
 * <ul>
 * <li>{@link PlayCardEvent} - an event that occurs when a card is played</li>
 * <li>{@link GainCardEvent} - an event that occurs when a card is gained</li>
 * <li>{@link EndTurnEvent} - an event that occurs when a turn ends</li>
 * <li>{@link GameEvent} - a generic event which doesn't fit any of the other
 * categories</li>
 * </ul>
 * <br/>
 * <br/>
 * These events are used to notify
 * {@link edu.brandeis.cosi.atg.api.GameObserver}s of
 * game events. {link edu.brandeis.cosi.atg.api.Engine Engines} are responsible
 * for creating and dispatching these events.
 */
package edu.brandeis.cosi.atg.api.event;