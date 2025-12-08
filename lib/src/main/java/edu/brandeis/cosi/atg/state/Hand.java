package edu.brandeis.cosi.atg.state;

import javax.annotation.Nonnull;
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
    public Hand {
        java.util.Objects.requireNonNull(playedCards, "playedCards must not be null");
        java.util.Objects.requireNonNull(unplayedCards, "unplayedCards must not be null");
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
