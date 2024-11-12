package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableCollection;

import edu.brandeis.cosi.atg.api.cards.Card;

/**
 * Represents the piles of cards available for purchase during the game.
 *
 * This class does not represent a single player's deck of cards (which is a private implementation
 * detail of the game engine), but rather the piles of cards available for purchase during the game.
 *
 */
public interface GameDeck {

    /**
     * Gets the number of available cards of the specified type.
     *
     * @param cardType the type of card
     * @return the number of available cards of the specified type
     */
    int getNumAvailable(Card.Type cardType);

    /**
     * Gets all types of cards in the deck.
     *
     * @return an immutable collection of all card types in the deck
     */
    ImmutableCollection<Card.Type> getCardTypes();
}
