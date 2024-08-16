package scramble.model.command.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import scramble.model.command.api.Command;
import scramble.model.spaceship.Directions;
import scramble.view.compact.SpaceShipPanel;

/**
 * Implementation of Command Directionserface. handles the player (ship)
 * commands and
 * its screen movements.
 */
public final class SpaceShipCommand implements Command {

    private final SpaceShipPanel spaceShipPanel;
    private final Directions direction;

    /**
     * Class constructor.
     * 
     * @param spaceShipPanel the game panel to update
     * @param direction      the spaceship direction
     */
    @SuppressFBWarnings
    public SpaceShipCommand(final SpaceShipPanel spaceShipPanel, final Directions direction) {
        this.spaceShipPanel = spaceShipPanel;
        this.direction = direction;
    }

    @Override
    public void execute() {
        spaceShipPanel.moveSpaceship(direction);
    }

}
