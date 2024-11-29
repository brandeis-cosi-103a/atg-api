package edu.brandeis.cosi.atg.api.event;

/**
 * Represents a decision by a player to end their turn.
 */
public final class TurnEvent implements Event {
    private Type eventType;

    public enum Type {
        START, END
    }

    /**
     * Constructs a TurnEvent with the specified type.
     *
     * @param eventType the type of turn event
     */
    public TurnEvent(Type eventType) {
        this.eventType = eventType;
    }

    /**
     * Gets the type of turn event.
     * 
     * @return the type of turn event
     */
    public Type getEventType() {
        return eventType;
    }

    /**
     * Gets the description of the end turn decision.
     *
     * @return the description of the end turn event
     */
    public String getDescription() {
        return eventType.name() + " turn";
    }
}
