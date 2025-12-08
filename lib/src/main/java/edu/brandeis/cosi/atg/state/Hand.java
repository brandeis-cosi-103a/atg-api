package edu.brandeis.cosi.atg.state;

import java.util.Objects;

import javax.annotation.Nonnull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * A Hand represents the collection of cards a player has in their hand during a
 * single turn of the game.
 *
 * @param playedCards   the cards that have already been played this turn
 * @param unplayedCards the cards that have not yet been played this turn
 */
public record Hand(
        @Nonnull ImmutableCollection<Card> playedCards,
        @Nonnull ImmutableCollection<Card> unplayedCards) {

    /**
     * Compact constructor that validates both card collections are not null.
     */
    @JsonCreator
    public Hand(
            @JsonProperty("playedCards") ImmutableCollection<Card> playedCards,
            @JsonProperty("unplayedCards") ImmutableCollection<Card> unplayedCards) {
        Objects.requireNonNull(playedCards, "playedCards must not be null");
        Objects.requireNonNull(unplayedCards, "unplayedCards must not be null");
        // Normalize to ImmutableSet for equality
        this.playedCards = Objects
                .requireNonNull(playedCards instanceof ImmutableSet ? playedCards : ImmutableSet.copyOf(playedCards));
        this.unplayedCards = Objects.requireNonNull(
                unplayedCards instanceof ImmutableSet ? unplayedCards : ImmutableSet.copyOf(unplayedCards));
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
