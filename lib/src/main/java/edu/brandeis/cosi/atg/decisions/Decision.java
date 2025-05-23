package edu.brandeis.cosi.atg.decisions;

/**
 * Represents a decision that a player can make during the game.
 *
 * Decisions are the primary interface between an
 * {@link edu.brandeis.cosi.atg.engine.Engine} and a
 * {@link edu.brandeis.cosi.atg.player.Player}. The Engine presents a Player
 * with a
 * list of possible
 * decisions, and the Player chooses one of them. The Engine then executes the
 * chosen decision.
 */
public sealed interface Decision permits BuyDecision, EndPhaseDecision, PlayCardDecision {
    /**
     * Gets the description of the decision.
     *
     * @return the description of the decision
     */
    public String getDescription();
}
