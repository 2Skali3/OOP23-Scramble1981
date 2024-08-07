package scramble.model.command.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import scramble.model.command.api.Command;
import scramble.view.compact.SpaceShipPanel;

/**
 * Implementation of Command interface. handles the player (bullet) commands and
 * its screen movements.
 */
public class BulletCommand implements Command {

    private final SpaceShipPanel gamePanel;

    /**
     * Class constructor.
     *
     * @param panel the game panel to update
     */
    @SuppressFBWarnings
    public BulletCommand(final SpaceShipPanel panel) {
        this.gamePanel = panel;
    }

    /**
     * Executes the command to shoot a bullet.
     *
     */
    @Override
    public void execute() {
        gamePanel.shootBullet();
    }

}
