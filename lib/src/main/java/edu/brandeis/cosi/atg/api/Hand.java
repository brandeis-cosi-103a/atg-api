package edu.brandeis.cosi.atg.api;

import com.google.common.collect.ImmutableList;

import edu.brandeis.cosi.atg.api.cards.Card;

public interface Hand {
    public ImmutableList<Card> getAllCards();

    public ImmutableList<Card> getUnplayedCards();

    public ImmutableList<Card> getPlayedCards();

    public ImmutableList<Card> getPlayableCards();
}
