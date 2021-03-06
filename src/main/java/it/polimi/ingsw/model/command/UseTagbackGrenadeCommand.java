package it.polimi.ingsw.model.command;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PowerUp;
import it.polimi.ingsw.model.playerstate.ShootedState;
import it.polimi.ingsw.view.commandmessage.CommandMessage;
import it.polimi.ingsw.view.commandmessage.CommandType;
import it.polimi.ingsw.view.commandmessage.PowerUpCommandMessage;

/**
 * This command represent the action of use a tagback grenade
 */
public class UseTagbackGrenadeCommand implements Command {
    private final PowerUp grenade;
    /**
     * This is the player doing the command
     */
    private final Player player;

    /**
     * This constructor create a command for use a tagback grenade
     *
     * @param player  is the player who use the tagback grenade
     * @param grenade is the grenade
     */
    public UseTagbackGrenadeCommand(Player player, PowerUp grenade) {
        this.grenade = grenade;
        this.player = player;
    }

    /**
     * This method execute the effect of the TagbackGrenade
     */
    @Override
    public void execute() {
        player.pay(grenade);
        player.getLastShooter().addMarks(PowerUp.TAGBACK_GRENADE_MARKS, player.getId());
        player.changeState(new ShootedState());
    }

    /**
     * This method throw an exception because after a TagbackGrenade you can't go back
     */
    @Override
    public void undo() {
        throw new IllegalUndoException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUndoable() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandMessage createCommandMessage() {
        return new PowerUpCommandMessage(CommandType.USE_TAGBACK_GRENADE, grenade.getType(), grenade.getColor());
    }
}
