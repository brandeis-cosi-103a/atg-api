package edu.brandeis.cosi.atg.api;

import edu.brandeis.cosi.atg.api.event.Event;

public interface GameObserver {
    public void notifyEvent(GameState state, Event action);
}
