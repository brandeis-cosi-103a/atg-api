package edu.brandeis.cosi.atg.event;

/**
 * Represents a general event in the game.
 *
 * @param description the description of the event
 */
public record GameEvent(String description) implements Event {
    /**
     * Compact constructor that validates the description is not null.
     */
    public GameEvent {
        java.util.Objects.requireNonNull(description, "description must not be null");
    }

    @Override
    public String getDescription() {
        return description;
    }
}
