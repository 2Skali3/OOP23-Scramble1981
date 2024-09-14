package scramble.controller.command.impl;

import java.util.logging.Logger;

import scramble.controller.command.api.Command;
import scramble.model.spaceship.Directions;
import scramble.view.compact.SpaceShipPanel;

/**
 * Implementation of Command Directionserface. handles the player (ship)
 * commands and
 * its screen movements.
 */
public final class SpaceShipCommand implements Command {

    private static final Logger LOG = Logger.getLogger(SpaceShipPanel.class.getName());

    private SpaceShipPanel spaceShipPanel;
    private final Directions direction;

    /**
     * Class constructor.
     * 
     * @param spaceShipPanel the game panel to update
     * @param direction      the spaceship direction
     */
    public SpaceShipCommand(final SpaceShipPanel spaceShipPanel, final Directions direction) {
        final SpaceShipPanel tmp;
        try {
            tmp = spaceShipPanel.clone();
            this.spaceShipPanel = tmp;

        } catch (CloneNotSupportedException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
        this.direction = direction;
    }

    @Override
    public void execute() {
        spaceShipPanel.moveSpaceship(direction);
    }

}
