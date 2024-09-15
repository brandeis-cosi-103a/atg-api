package edu.brandeis.cosi.atg.api.event;

import edu.brandeis.cosi.atg.api.Player;
import edu.brandeis.cosi.atg.api.actions.Action;

public final class ActionEvent implements Event {
    Action actionTaken;
    Player player;

    public ActionEvent(Action optionTaken, Player player) {
        this.actionTaken = optionTaken;
        this.player = player;
    }

    public String getDescription() {
        return player.getName() + " took action: " + actionTaken.getDescription();
    }
}
