package edu.brandeis.cosi.atg.decisions;

/**
 * Represents a decision that a player can make during the game.
 * <br/>
 * <br/>
 * Decisions are the primary interface between the
 * {@link edu.brandeis.cosi.atg.engine.Engine} and a
 * {@link edu.brandeis.cosi.atg.player.Player}. The Engine presents a Player
 * with a list of possible decisions, and the Player chooses one. The Engine
 * then executes the chosen decision and updates the game state.
 * <br/>
 * <br/>
 * <strong>Valid Decision Types</strong>
 * <br/>
 * All implementations are defined as sealed subtypes:
 * <ul>
 * <li>{@link BuyDecision} - Buy a card from the supply.</li>
 * <li>{@link EndPhaseDecision} - End the current turn phase.</li>
 * <li>{@link GainCardDecision} - Gain a card to the discard pile (no
 * cost).</li>
 * <li>{@link PlayCardDecision} - Play a card from the hand.</li>
 * <li>{@link TrashCardDecision} - Trash (permanently remove) a card.</li>
 * </ul>
 */
public sealed interface Decision
        permits BuyDecision, EndPhaseDecision, GainCardDecision, PlayCardDecision,
        TrashCardDecision {
    /**
     * Gets the description of the decision.
     *
     * @return the description of the decision
     */
    public String getDescription();
}
