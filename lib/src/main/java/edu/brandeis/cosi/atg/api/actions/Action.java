package edu.brandeis.cosi.atg.api.actions;

/**
 * Represents an action that a player can take during the game.
 */
public sealed interface Action permits BuyAction, EndTurnAction, PlayCardAction {

    /**
     * Gets the description of the action.
     *
     * @return the description of the action
     */
    public String getDescription();
}
