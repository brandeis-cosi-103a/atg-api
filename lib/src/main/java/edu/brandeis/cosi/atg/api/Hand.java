package edu.brandeis.cosi.atg.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * A Hand represents the collection of cards a player has in their hand during a
 * single turn of the game.
 */
public final class Hand {
    private final ImmutableSet<Card> playedCards;
    private final ImmutableSet<Card> unplayedCards;

    /**
     * Constructs a Hand with the specified cards
     *
     * @param playedCards   The cards that have already been played this turn.
     * @param unplayedCards The cards that have not yet been played this turn.
     */
    @JsonCreator
    public Hand(@JsonProperty("playedCards") ImmutableCollection<Card> playedCards,
            @JsonProperty("unplayedCards") ImmutableCollection<Card> unplayedCards) {
        this.playedCards = ImmutableSet.<Card>builder().addAll(playedCards).build();
        this.unplayedCards = ImmutableSet.<Card>builder().addAll(unplayedCards).build();
    }

    /**
     * Gets all cards in the hand.
     *
     * @return an immutable list of all cards in the hand
     */
    @JsonIgnore
    public ImmutableCollection<Card> getAllCards() {
        return ImmutableSet.<Card>builder().addAll(playedCards).addAll(unplayedCards).build();
    }

    /**
     * Gets the unplayed cards in the hand.
     *
     * @return an immutable collection of unplayed cards in the hand
     */
    public ImmutableCollection<Card> getUnplayedCards() {
        return unplayedCards;
    }

    /**
     * Gets the played cards in the hand.
     *
     * @return an immutable collection of played cards in the hand
     */
    public ImmutableCollection<Card> getPlayedCards() {
        return playedCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Hand hand = (Hand) o;

        return playedCards.equals(hand.playedCards) &&
                unplayedCards.equals(hand.unplayedCards);
    }

    @Override
    public int hashCode() {
        int result = playedCards.hashCode();
        result = 31 * result + unplayedCards.hashCode();
        return result;
    }
}
