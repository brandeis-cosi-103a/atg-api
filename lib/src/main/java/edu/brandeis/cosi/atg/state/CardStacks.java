package edu.brandeis.cosi.atg.state;

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
 */
public final class CardStacks {
    private final ImmutableMap<Card.Type, Integer> cardCounts;

    /**
     * Constructs a CardStacks with the specified cards types and counts.
     *
     * @param cardCounts a map of card types to the number of available cards of
     *                   that type
     */
    public CardStacks(ImmutableMap<Card.Type, Integer> cardCounts) {
        this.cardCounts = cardCounts;
    }

    /**
     * Gets the number of available cards of each type.
     *
     * @return a map of card types to the number of available cards of that type
     */
    public ImmutableMap<Card.Type, Integer> getCardCounts() {
        return cardCounts;
    }

    /**
     * Gets the number of available cards of the specified type.
     *
     * @param cardType the type of card
     * @return the number of available cards of the specified type
     */
    public int getNumAvailable(Card.Type cardType) {
        return cardCounts.get(cardType);
    }

    /**
     * Gets all types of cards in the deck.
     *
     * @return an immutable collection of all card types in the deck
     */
    public ImmutableSet<Card.Type> getCardTypes() {
        return cardCounts.keySet();
    }
}
