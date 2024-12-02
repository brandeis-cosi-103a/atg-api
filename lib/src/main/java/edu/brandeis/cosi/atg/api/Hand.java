package edu.brandeis.cosi.atg.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * A Hand represents the collection of cards a player has during the game.
 */
public final class Hand {
    private final ImmutableCollection<Card> playedCards;
    private final ImmutableCollection<Card> unplayedCards;

    /**
     * Constructs a Hand with the specified cards
     *
     * @param playedCards
     * @param unplayedCards
     */
    @JsonCreator
    public Hand(@JsonProperty("playedCards") ImmutableCollection<Card> playedCards,
            @JsonProperty("unplayedCards") ImmutableCollection<Card> unplayedCards) {
        this.playedCards = playedCards;
        this.unplayedCards = unplayedCards;
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
}
