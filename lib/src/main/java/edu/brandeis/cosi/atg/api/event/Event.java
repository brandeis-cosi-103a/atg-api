package edu.brandeis.cosi.atg.api.event;

public sealed interface Event permits ActionEvent, GameEvent {
    public String getDescription();
}
