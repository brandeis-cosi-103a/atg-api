package edu.brandeis.cosi.atg.api.event;

import edu.brandeis.cosi.atg.api.Player;
import edu.brandeis.cosi.atg.api.actions.Action;

/**
 * Represents an event where a player takes an action.
 */
public final class ActionEvent implements Event {
    private Action actionTaken;
    private Player player;

    /**
     * Constructs an ActionEvent with the specified action and player.
     *
     * @param actionTaken the action taken by the player
     * @param player the player who took the action
     */
    public ActionEvent(Action actionTaken, Player player) {
        this.actionTaken = actionTaken;
        this.player = player;
    }

    /**
     * Gets the description of the action event.
     *
     * @return the description of the action event
     */
    public String getDescription() {
        return player.getName() + " took action: " + actionTaken.getDescription();
    }

    /**
     * Gets the action taken by the player.
     *
     * @return the action taken by the player
     */
    public Action getActionTaken() {
        return actionTaken;
    }

    /**
     * Gets the player who took the action.
     *
     * @return the player who took the action
     */
    public Player getPlayer() {
        return player;
    }
}
