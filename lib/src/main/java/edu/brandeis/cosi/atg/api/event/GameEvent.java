package edu.brandeis.cosi.atg.api.event;

public final class GameEvent implements Event {
    private String description;

    public GameEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
