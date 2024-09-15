package edu.brandeis.cosi.atg.api;

public interface GameState {
    public String getCurrentPlayerName();

    public Hand getCurrentPlayerHand();

    public int getSpendableMoney();

    public int getAvailableBuys();

    public GameDeck getDeck();
}
