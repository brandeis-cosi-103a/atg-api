package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableCollection;

import edu.brandeis.cosi.atg.api.cards.Card;

public interface GameDeck {

    int getNumAvailable(Card.Type cardType);
    ImmutableCollection<Card.Type> getCardTypes();
}
