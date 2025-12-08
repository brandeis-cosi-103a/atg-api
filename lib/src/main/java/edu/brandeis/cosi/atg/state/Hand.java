package edu.brandeis.cosi.atg.state;

import javax.annotation.Nonnull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * A Hand represents the collection of cards a player has in their hand during a
 * single turn of the game.
 */
public record Hand(
        @Nonnull ImmutableCollection<Card> playedCards,
        @Nonnull ImmutableCollection<Card> unplayedCards) {

    @JsonCreator
    public Hand(
            @JsonProperty("playedCards") ImmutableCollection<Card> playedCards,
            @JsonProperty("unplayedCards") ImmutableCollection<Card> unplayedCards) {
        java.util.Objects.requireNonNull(playedCards, "playedCards must not be null");
        java.util.Objects.requireNonNull(unplayedCards, "unplayedCards must not be null");
        // Normalize to ImmutableSet for equality
        this.playedCards = playedCards instanceof ImmutableSet ? playedCards : ImmutableSet.copyOf(playedCards);
        this.unplayedCards = unplayedCards instanceof ImmutableSet ? unplayedCards : ImmutableSet.copyOf(unplayedCards);
    }

    /**
     * Gets all cards in the hand.
     *
     * @return an immutable list of all cards in the hand
     */
    public ImmutableCollection<Card> getAllCards() {
        return ImmutableSet.<Card>builder().addAll(playedCards).addAll(unplayedCards).build();
    }
}
