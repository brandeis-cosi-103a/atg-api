package edu.brandeis.cosi.atg.api.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents an event in the game.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
                @JsonSubTypes.Type(value = EndTurnEvent.class, name = "end_turn"),
                @JsonSubTypes.Type(value = GainCardEvent.class, name = "gain_card"),
                @JsonSubTypes.Type(value = GameEvent.class, name = "game"),
                @JsonSubTypes.Type(value = PlayCardEvent.class, name = "play_card")
})
public sealed interface Event permits EndTurnEvent, GainCardEvent, GameEvent, PlayCardEvent {

        /**
         * Gets the description of the event.
         *
         * @return the description of the event
         */
        public String getDescription();
}
