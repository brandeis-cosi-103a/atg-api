package edu.brandeis.cosi.atg.state;

/**
 * Represents the current, immutable state of the game.
 *
 * When the {@link edu.brandeis.cosi.atg.engine.Engine} prompts a
 * {@link edu.brandeis.cosi.atg.player.Player} for a decision, it provides
 * a GameState representing the current state of the game. The Player can use
 * the GameState while making a decision.
 *
 * GameState is intentionally immutable: the only way players can modify the
 * state of the game is to make a decision when prompted by the Engine.
 */
public record GameState(
        String currentPlayerName,
        Hand currentPlayerHand,
        GameState.TurnPhase phase,
        int availableActions,
        int spendableMoney,
        int availableBuys,
        CardStacks buyableCards) {
    public GameState {
        java.util.Objects.requireNonNull(currentPlayerName, "currentPlayerName must not be null");
        java.util.Objects.requireNonNull(phase, "phase must not be null");
        java.util.Objects.requireNonNull(buyableCards, "buyableCards must not be null");
        // currentPlayerHand can be null if this GameState is intended for a
        // non-current player.
    }

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
}
