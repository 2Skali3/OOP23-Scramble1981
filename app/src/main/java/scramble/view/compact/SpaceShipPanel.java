package scramble.view.compact;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import scramble.controller.command.impl.BulletCommand;
import scramble.controller.command.impl.SpaceShipCommand;
import scramble.model.common.impl.PairImpl;
import scramble.model.spaceship.Directions;
import scramble.model.spaceship.SpaceShip;
import scramble.utility.Constants;

/**
 * Class for the representation of the Spaceship Panel.
 *
 * @see GamePanel
 * @see JPanel
 */
public class SpaceShipPanel extends GamePanel implements Cloneable {

    private static final Logger LOG = Logger.getLogger(SpaceShip.class.getName());
    private static final long serialVersionUID = 1L;

    private final Timer updateTimer;

    private transient SpaceShip spaceship;

    /** Constructor for the SpaceshipPanel class. */
    public SpaceShipPanel() {
        this.spaceship = new SpaceShip(Constants.SPACESHIP_STARTER_POSITION,
                Constants.SPACESHIP_STARTER_POSITION, Constants.SPACESHIP_WIDTH,
                Constants.SPACESHIP_HEIGHT);
        this.setOpaque(false);
        updateTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                update();
            }
        });
        updateTimer.start();
    }

    /**
     * Getter for the spaceship.
     *
     * @return the spaceship
     */
    public SpaceShip getSpaceship() {
        SpaceShip temp = spaceship;
        try {
            temp = spaceship.clone();
            this.spaceship = temp;
        } catch (CloneNotSupportedException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
        return temp;
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
     * Sets the corrispective boolean for the player movement.
     * Also checks if repaintable.
     *
     * @param direction the direction
     */
    public void moveSpaceship(final Directions direction) {

        switch (direction) {
            case UP:
                spaceship.setAbove(true);
                break;
            case DOWN:
                spaceship.setDown(true);
                break;
            case LEFT:
                spaceship.setLeft(true);
                break;
            case RIGHT:
                spaceship.setRight(true);
                break;
            default:
        }
        // repaint();
    }

    /**
     * Method that executes the command sent to the spaceship.
     *
     * @param command the command
     */
    public void sendCommandBullet(final BulletCommand command) {
        command.execute();
    }

    /** @inheritDoc */
    @Override
    public void startTimer() {
        this.updateTimer.start();
    }

    /** @inheritDoc */
    @Override
    public void stopTimer() {
        this.updateTimer.stop();
    }

    /** @inheritDoc */
    @Override
    public void restartTimer() {
        updateTimer.restart();
    }

    /** @inheritdoc */
    @Override
    public SpaceShipPanel clone() throws CloneNotSupportedException {
        return (SpaceShipPanel) super.clone();
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {

        if (spaceship.getSprite() != null) {
            if (spaceship.isHit()) {
                g.drawImage(spaceship.getExpSprite(), spaceship.getPosition().getFirstElement(),
                        spaceship.getPosition().getSecondElement(), spaceship.getWidth(), spaceship.getHeight(), null);
            } else {
                g.drawImage(spaceship.getSprite(), spaceship.getPosition().getFirstElement(),
                        spaceship.getPosition().getSecondElement(), spaceship.getWidth(), spaceship.getHeight(), null);
            }
        }

        spaceship.drawHitBox(g);
    }

    /* Checks continuosly with a timer for spaceship movement. */
    private void update() {
        final PairImpl<Integer, Integer> location = spaceship.getPosition();
        final int shipX = location.getFirstElement();
        final int shipY = location.getSecondElement();
        final int xSpeed = spaceship.getxSpeed();
        final int ySpeed = spaceship.getySpeed();

        final int minX = 0;
        final int maxX = getWidth() / 2;
        final int minY = 0;
        final int maxY = getHeight();

        // Conditions for horizontal movement (on X axis)
        if (shipX + xSpeed >= minX && shipX + xSpeed <= maxX) {
            spaceship.move();
        } else if (shipX + xSpeed < minX) {
            spaceship.resetSpeedX();
            spaceship.updatePosition(new PairImpl<>(minX, shipY));
        } else if (shipX + xSpeed > maxX) {
            spaceship.resetSpeedX();
            spaceship.updatePosition(new PairImpl<>(maxX, shipY));
        }

        if (shipY + ySpeed >= minY && shipY + ySpeed <= maxY) {
            spaceship.move();
        } else if (shipY + ySpeed < minY) {
            spaceship.resetSpeedY();
            spaceship.updatePosition(new PairImpl<>(shipX, minY));
        } else if (shipY + ySpeed > maxY) {
            spaceship.resetSpeedY();
            spaceship.updatePosition(new PairImpl<>(shipX, maxY));
        }
    }
}
