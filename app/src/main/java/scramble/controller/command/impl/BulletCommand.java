package scramble.controller.command.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import scramble.controller.command.api.Command;
import scramble.model.bullets.BulletType;
import scramble.model.spaceship.SpaceShip;
import scramble.view.compact.BulletsPanel;

/**
 * Implementation of Command interface. handles the player (bullet) commands and
 * its screen movements.
 */
public class BulletCommand implements Command {

    private final BulletsPanel gamePanel;
    private final SpaceShip spaceship;
    private final BulletType type;

    /**
     * Class constructor.
     *
     * @param panel     the game panel to update
     * @param type      the type of the bullet
     * @param spaceship
     */
    @SuppressFBWarnings
    public BulletCommand(final BulletsPanel panel, final BulletType type, final SpaceShip spaceship) {
        this.gamePanel = panel;
        this.type = type;
        this.spaceship = spaceship;
    }

    /**
     * Executes the command to shoot a bullet.
     *
     */
    @Override
    public void execute() {
        gamePanel.shootBullet(type, spaceship);
    }

}
