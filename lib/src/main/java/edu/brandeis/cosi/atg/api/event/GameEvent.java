package edu.brandeis.cosi.atg.api.event;

/**
 * Represents a general event in the game.
 */
public final class GameEvent implements Event {
    private String description;

    /**
     * Constructs a GameEvent with the specified description.
     *
     * @param description the description of the event
     */
    public GameEvent(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the game event.
     *
     * @return the description of the game event
     */
    public String getDescription() {
        return description;
    }
}
