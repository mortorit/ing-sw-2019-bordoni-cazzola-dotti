package it.polimi.ingsw.model.command;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.model.playerstate.*;

public class MoveCommand implements Command {
    private Player player;
    private Square newPosition;
    private MovableState currentState;
    private Square oldSquare;
    private PlayerState nextState;

    public MoveCommand(Player player, Square newPosition, SelectedAggregateActionState currentState) {
        this.player = player;
        this.newPosition = newPosition;
        this.currentState = currentState;
        if (currentState.getSelectedAggregateAction().isShoot() || currentState.getSelectedAggregateAction().isGrab())
            nextState = currentState;
        else
            nextState = new ManageTurnState();
    }

    public MoveCommand(Player player, Square newPosition, ReadyToShootState currentState) {
        this.player = player;
        this.newPosition = newPosition;
        this.currentState = currentState;
        if(!currentState.getSelectedWeapon().hasDamageToDo())
            nextState = new ManageTurnState();
        else
            nextState = currentState;
    }

    /**
     * This method move the player
     */
    @Override
    public void execute() {
        oldSquare = player.getPosition();
        player.move(newPosition);
        currentState.useMoves();
        player.changeState(nextState);
    }

    /**
     * This method move the player in the old position
     */
    @Override
    public void undo() {
        player.move(oldSquare);
        currentState.resetMoves();
        player.changeState(currentState);
    }
}
