package edu.brandeis.cosi.atg.api.decisions;

/**
 * Represents a decision that a player can make during the game.
 */
public sealed interface Decision permits BuyDecision, EndPhaseDecision, PlayCardDecision {

    /**
     * Gets the description of the decision.
     *
     * @return the description of the decision
     */
    public String getDescription();
}
