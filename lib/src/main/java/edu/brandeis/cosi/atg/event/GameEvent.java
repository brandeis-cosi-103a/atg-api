package edu.brandeis.cosi.atg.event;

/**
 * Represents a general event in the game.
 */
public record GameEvent(String description) implements Event {
    public GameEvent {
        java.util.Objects.requireNonNull(description, "description must not be null");
    }

    @Override
    public String getDescription() {
        return description;
    }
}
