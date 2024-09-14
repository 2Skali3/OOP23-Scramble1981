package scramble.controller.command.impl;

import scramble.controller.command.api.Command;
import scramble.model.bullets.BulletType;
import scramble.model.spaceship.SpaceShip;
import scramble.view.compact.BulletsPanel;

import java.util.logging.Logger;

/**
 * Implementation of Command interface. handles the player (bullet) commands and
 * its screen movements.
 */
public class BulletCommand implements Command {

    private static final Logger LOG = Logger.getLogger(BulletsPanel.class.getName());

    private BulletsPanel gamePanel;
    private SpaceShip spaceship;
    private final BulletType type;

    /**
     * Class constructor.
     *
     * @param panel     the game panel to update
     * @param type      the type of the bullet
     * @param spaceship
     */
    public BulletCommand(final BulletsPanel panel, final BulletType type, final SpaceShip spaceship) {
        final BulletsPanel tempPanel;
        final SpaceShip tempSpaceship;
        try {
            tempPanel = panel.clone();
            tempSpaceship = spaceship.clone();
            this.spaceship = tempSpaceship;
            this.gamePanel = tempPanel;
        } catch (CloneNotSupportedException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
        this.type = type;
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
