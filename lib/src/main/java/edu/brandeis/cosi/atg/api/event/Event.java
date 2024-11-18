package edu.brandeis.cosi.atg.api.event;

/**
 * Represents an event in the game.
 */
public sealed interface Event permits EndTurnEvent, GameEvent, GainCardEvent, PlayCardEvent, DiscardCardEvent {

    /**
     * Gets the description of the event.
     *
     * @return the description of the event
     */
    public String getDescription();
}
