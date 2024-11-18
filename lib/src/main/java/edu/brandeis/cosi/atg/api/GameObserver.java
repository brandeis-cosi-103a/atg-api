package edu.brandeis.cosi.atg.api;

import edu.brandeis.cosi.atg.api.event.Event;

/**
 * A GameObserver is notified of game events as they occur.
 */
public interface GameObserver {

    /**
     * Notifies the observer of an event that has occurred in the game.
     *
     * @param state the current state of the game
     * @param event the event that occurred
     */
    public void notifyEvent(GameState state, Event event);
}
