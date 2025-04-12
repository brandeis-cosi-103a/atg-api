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
    private final int availableActions;
    private final int spendableMoney;
    private final Hand currentPlayerHand;
    private final GameDeck deck;
    private final TurnPhase phase;

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
         * equivalent to ending the turn.
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
     * Constructs a GameState with the specified parameters.
     *
     * @param currentPlayerName the name of the player whose turn it is
     * @param currentPlayerHand the hand of the current player, or null if the
     *                          GameState is intended for a player who is not the
     *                          current player
     * @param phase             the phase of the current turn
     * @param availableActions  the number of available actions
     * @param spendableMoney    the amount of money the player can spend this turn
     * @param availableBuys     the number of available buys
     * @param deck              the game deck
     */
    @JsonCreator
    public GameState(@JsonProperty("currentPlayerNmae") String currentPlayerName,
            @JsonProperty("currentPlayerHand") Hand currentPlayerHand,
            @JsonProperty("turnPhase") TurnPhase phase,
            @JsonProperty("availableActions") int availableActions,
            @JsonProperty("spendableMoney") int spendableMoney,
            @JsonProperty("availableBuys") int availableBuys,
            @JsonProperty("deck") GameDeck deck) {
        this.availableBuys = availableBuys;
        this.availableActions = availableActions;
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
    public int getAvailableActions() {
        return availableActions;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GameState gameState = (GameState) o;

        if (availableBuys != gameState.availableBuys)
            return false;
        if (availableActions != gameState.availableActions)
            return false;
        if (spendableMoney != gameState.spendableMoney)
            return false;
        if (!currentPlayerName.equals(gameState.currentPlayerName))
            return false;
        if (currentPlayerHand != null ? !currentPlayerHand.equals(gameState.currentPlayerHand)
                : gameState.currentPlayerHand != null)
            return false;
        if (!deck.equals(gameState.deck))
            return false;
        return phase == gameState.phase;
    }

    @Override
    public int hashCode() {
        int result = currentPlayerName.hashCode();
        result = 31 * result + availableBuys;
        result = 31 * result + availableActions;
        result = 31 * result + spendableMoney;
        result = 31 * result + (currentPlayerHand != null ? currentPlayerHand.hashCode() : 0);
        result = 31 * result + deck.hashCode();
        result = 31 * result + phase.hashCode();
        return result;
    }
}
