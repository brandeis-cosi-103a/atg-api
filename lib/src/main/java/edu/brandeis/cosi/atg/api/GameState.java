package edu.brandeis.cosi.atg.api;

/**
 * Represents the current state of the game.
 */
public interface GameState {

    /**
     * Gets the name of the current player.
     *
     * @return the name of the current player
     */
    public String getCurrentPlayerName();

    /**
     * Gets the hand of the current player.
     *
     * @return the hand of the current player
     */
    public Hand getCurrentPlayerHand();

    /**
     * Gets the amount of money the current player can spend this turn.
     *
     * This is the amount of money the player has earned by playing cards during this turn,
     * as opposed to the amount of money that the player has in their hand or deck.
     *
     * @return the amount of spendable money
     */
    public int getSpendableMoney();

    /**
     * Gets the number of buys available to the current player.
     *
     * @return the number of available buys
     */
    public int getAvailableBuys();

    /**
     * Gets the game deck.
     *
     * @return the game deck
     */
    public GameDeck getDeck();
}
