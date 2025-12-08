package edu.brandeis.cosi.atg.state;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import edu.brandeis.cosi.atg.cards.Card;

/**
 * Represents the piles of cards available for purchase during the game.
 *
 * This class does not represent a single player's deck of cards (which is a
 * private implementation detail of the game engine), but rather the piles of
 * cards available for purchase during the game.
 *
 * @param cardCounts a map of card types to the number of cards available of
 *                   that type
 */
public record CardStacks(
        ImmutableMap<Card.Type, Integer> cardCounts) {
    /**
     * Compact constructor that validates the cardCounts map is not null.
     */
    public CardStacks {
        java.util.Objects.requireNonNull(cardCounts, "cardCounts must not be null");
    }

    /**
     * Gets the number of available cards of the specified type.
     *
     * @param cardType the type of card
     * @return the number of available cards of the specified type. 0 if none are
     *         available, or if the card type does not exist in the stacks.
     */
    @JsonIgnore
    public int getNumAvailable(Card.Type cardType) {
        return cardCounts.getOrDefault(cardType, 0);
    }

    /**
     * Gets all types of cards in the deck.
     *
     * @return an immutable collection of all card types in the deck
     */
    @JsonIgnore
    public ImmutableSet<Card.Type> getCardTypes() {
        return cardCounts.keySet();
    }
}
