package scramble.model.command.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import scramble.model.command.api.Command;
import scramble.view.compact.SpaceShipPanel;

/**
 * Implementation of Command interface. handles the player (ship) commands and
 * its screen movements.
 */
public final class SpaceShipCommand implements Command {

    private final SpaceShipPanel spaceShipPanel;
    private final int x;
    private final int y;

    /**
     * Class constructor.
     * 
     * @param spaceShipPanel the game panel to update
     * @param x     movement on the x axis
     * @param y     movement on the y axis
     */
    @SuppressFBWarnings
    public SpaceShipCommand(final SpaceShipPanel spaceShipPanel, final int x, final int y) {
        this.spaceShipPanel = spaceShipPanel;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        spaceShipPanel.moveSpaceship(x, y);
    }

}
