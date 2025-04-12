package edu.brandeis.cosi.atg.api.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonCreator
    public GameEvent(@JsonProperty("description") String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameEvent other = (GameEvent) obj;
        return description.equals(other.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
