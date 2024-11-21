package edu.brandeis.cosi.atg.api;

/**
 * Represents the current state of the game.
 */
public interface GameState {

    /**
     * Represents the phase of a turn.
     */
    public enum TurnPhase {
        /** The phase of the turn that involves playing action cards */
        ACTION,
        /** The phase of a turn that involves reacting to another player's action */
        REACTION,
        /** The phase of a turn that involves playing money */
        MONEY,
        /**
         * The phase of a turn that involves buying cards.
         *
         * The BUY phase is the last phase of a turn, so ending the BUY phase is
         * equivalent to
         * ending the turn.
         */
        BUY,
        /**
         * A phase of a turn that involves discarding zero or more cards.
         *
         * This phase only occurs when a player takes an action that requires cards to
         * be discarded.
         */
        DISCARD,
        /**
         * A phase of a turn that involves gaining cards.
         *
         * This phase only occurs when a player takes an action that triggers a card
         * gain.
         */
        GAIN,
        /**
         * The phase of a turn where the player discards their hand and draws a new
         * hand.
         */
        CLEANUP;
    }

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
     * This is the amount of money the player has earned by playing cards during
     * this turn, as opposed to the amount of money that the player has in their
     * hand or deck.
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
     * Gets the number of actions available to the current player.
     *
     * @return the number of available actions
     */
    public int getAvailableActions();

    /**
     * Gets the game deck.
     *
     * @return the game deck
     */
    public GameDeck getDeck();

    /**
     * Gets the phase of the current turn.
     *
     * @return the phase of the current turn
     */
    public TurnPhase getTurnPhase();
}
