package edu.brandeis.cosi.atg.api.actions;

/**
 * Represents an action where a player ends their turn.
 */
public final class EndTurnAction implements Action {

    /**
     * Gets the description of the end turn action.
     *
     * @return the description of the end turn action
     */
    public String getDescription() {
        return "End turn";
    }
}
