package it.polimi.ingsw.model.command;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.playerstate.SelectedAggregateActionState;

public abstract class GrabCommand implements Command {
    protected Player player;

    public GrabCommand(Player player) {
        this.player = player;
    }

}
