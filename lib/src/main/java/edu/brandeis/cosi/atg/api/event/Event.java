package edu.brandeis.cosi.atg.api.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents an event in the game.
 *
 * The {@link edu.brandeis.cosi.atg.api.Engine} will notify
 * {@link edu.brandeis.cosi.atg.api.GameObserver}s of events as they occur.
 * Events are intended to be informational - they can be printed to a console,
 * logged or saved, or used by {@link edu.brandeis.cosi.atg.api.Player Players}
 * to more finely track the progression of the game, if needed by their
 * strategy.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
                @JsonSubTypes.Type(value = DiscardCardEvent.class, name = "discard_card"),
                @JsonSubTypes.Type(value = EndTurnEvent.class, name = "end_turn"),
                @JsonSubTypes.Type(value = GainCardEvent.class, name = "gain_card"),
                @JsonSubTypes.Type(value = GameEvent.class, name = "game"),
                @JsonSubTypes.Type(value = PlayCardEvent.class, name = "play_card"),
                @JsonSubTypes.Type(value = TrashCardEvent.class, name = "trash_card"),
})
public sealed interface Event
                permits DiscardCardEvent, EndTurnEvent, GainCardEvent, GameEvent, PlayCardEvent, TrashCardEvent {

        /**
         * Gets the description of the event.
         *
         * @return the description of the event
         */
        public String getDescription();
}
