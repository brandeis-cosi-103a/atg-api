package edu.brandeis.cosi.atg.api.actions;

public sealed interface Action permits BuyAction, EndTurnAction, PlayCardAction {
    public String getDescription();
}
