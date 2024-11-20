package edu.brandeis.cosi.atg.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the current, immutable state of the game.
 *
 * When the {@link Engine} prompts a {@link Player} for a decision, it provides
 * a GameState representing the current state of the game. The Player can use
 * the GameState while making a decision.
 *
 * GameState is intentionally immutable: the only way players can modify the
 * state of the game is to make a decision when prompted by the Engine.
 */
public final class GameState {
    private final String currentPlayerName;
    private final int availableBuys;
    private final int spendableMoney;
    private final Hand currentPlayerHand;
    private final GameDeck deck;
    private final TurnPhase phase;

    /**
     * Represents the phase of a turn.
     */
    public enum TurnPhase {
        /** The phase of a turn that involves playing money */
        MONEY,
        /**
         * The phase of a turn that involves buying cards.
         *
         * The BUY phase is the last phase of a turn, so ending the BUY phase is
         * equivalent to ending the turn.
         */
        BUY,
        /**
         * The phase of a turn where the player discards their hand and draws a new
         * hand.
         */
        CLEANUP;
    }

    /**
     * Constructs a GameState with the specified parameters.
     *
     * @param currentPlayerName the name of the player whose turn it is
     * @param currentPlayerHand the hand of the current player, or null if the
     *                          GameState is intended for a player who is not the
     *                          current player
     * @param phase             the phase of the current turn
     * @param spendableMoney    the amount of money the player can spend this turn
     * @param availableBuys     the number of available buys
     * @param deck              the game deck
     */
    @JsonCreator
    public GameState(@JsonProperty("currentPlayerNmae") String currentPlayerName,
            @JsonProperty("currentPlayerHand") Hand currentPlayerHand,
            @JsonProperty("turnPhase") TurnPhase phase,
            @JsonProperty("spendableMoney") int spendableMoney,
            @JsonProperty("availableBuys") int availableBuys,
            @JsonProperty("deck") GameDeck deck) {
        this.availableBuys = availableBuys;
        this.currentPlayerHand = currentPlayerHand;
        this.currentPlayerName = currentPlayerName;
        this.deck = deck;
        this.spendableMoney = spendableMoney;
        this.phase = phase;
    }

    /**
     * Gets the name of the current player.
     *
     * @return the name of the current player
     */
    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    /**
     * Gets the hand of the current player.
     *
     * @return the hand of the current player
     */
    public Hand getCurrentPlayerHand() {
        return currentPlayerHand;
    }

    /**
     * Gets the amount of money the current player can spend this turn.
     *
     * This is the amount of money the player has earned by playing cards during
     * this turn, as opposed to the amount of money that the player has in their
     * hand or deck.
     *
     * @return the amount of spendable money
     */
    public int getSpendableMoney() {
        return spendableMoney;
    }

    /**
     * Gets the number of buys available to the current player.
     *
     * @return the number of available buys
     */
    public int getAvailableBuys() {
        return availableBuys;
    }

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
    public GameDeck getDeck() {
        return deck;
    }

    /**
     * Gets the phase of the current turn.
     *
     * @return the phase of the current turn
     */
    public TurnPhase getTurnPhase() {
        return phase;
    }
}
