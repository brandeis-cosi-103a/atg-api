package edu.brandeis.cosi.atg.api.event;

import edu.brandeis.cosi.atg.api.Player;
import edu.brandeis.cosi.atg.api.decisions.Decision;

/**
 * Represents an event where a player makes a decision.
 */
public final class PlayerDecisionEvent implements Event {
    private Decision decision;
    private Player player;

    /**
     * Constructs an PlayerDecisionEvent with the specified decision and player.
     *
     * @param decision the decision made by the player
     * @param player the player who made the decision
     */
    public PlayerDecisionEvent(Decision decision, Player player) {
        this.decision = decision;
        this.player = player;
    }

    /**
     * Gets the description of the decision event.
     *
     * @return the description of the decision event
     */
    public String getDescription() {
        return player.getName() + " made decision: " + decision.getDescription();
    }

    /**
     * Gets the decision made by the player.
     *
     * @return the decision made by the player
     */
    public Decision getDecision() {
        return decision;
    }

    /**
     * Gets the player who made the decision.
     *
     * @return the player who made the decision
     */
    public Player getPlayer() {
        return player;
    }
}
