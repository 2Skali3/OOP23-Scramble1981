package scramble.view.compact;

import java.awt.Graphics;

import javax.swing.JPanel;

import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.common.impl.PairImpl;
import scramble.model.spaceship.SpaceShip;


/**
 * Class for the rappresentation of the Spaceship Panel.
 * 
 * @see GamePanel
 * @see JPanel
 */
public class SpaceShipPanel extends GamePanel {

    private static final long serialVersionUID = 1L;
    private static final int STARTER_POSITION_X = 50;
    private static final int STARTER_POSITION_Y = 50;
    private static final int SPACESHIP_WIDTH = 32;
    private static final int SPACESHIP_HEIGHT = 16;

    private final transient SpaceShip spaceship;

    /** Cosnstructor for the SpaceshipPanel class. */
    public SpaceShipPanel() {
        this.spaceship = new SpaceShip(STARTER_POSITION_X, STARTER_POSITION_Y, SPACESHIP_WIDTH, SPACESHIP_HEIGHT);
        this.setOpaque(false);
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        if (spaceship.getSprite() != null) {
            g.drawImage(spaceship.getSprite(), spaceship.getPosition().getFirstElement(),
                    spaceship.getPosition().getSecondElement(), spaceship.getWidth(), spaceship.getHeight(), null);
        }
        this.canNotBeRepaint();
    }

    /**
     * Method that executes the command sent to the spaceship.
     * 
     * @param command the command
     */
    public void sendCommand(final SpaceShipCommand command) {
        command.execute();
    }

    /**
     * Moves the spaceship, controlled by the player, on the game panel.
     * 
     * @param dx movement on the X axis
     * @param dy movement on the Y axis
     */
    public void moveSpaceship(final int dx, final int dy) {
        final PairImpl<Integer, Integer> location = spaceship.getPosition();
        final int shipX = location.getFirstElement();
        final int shipY = location.getSecondElement();

        if (shipX + dx < getWidth() / 2 && shipX + dx >= 0 && shipY + dy < getHeight() && shipY + dy >= 0) {
            spaceship.move(dx, dy);
        }
        this.canBeRepaint();
        repaint();
    }

}
