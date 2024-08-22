package scramble.view.compact;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import scramble.model.bullets.Bullet;
import scramble.model.bullets.BulletType;
import scramble.model.command.impl.BulletCommand;
import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.common.impl.PairImpl;
import scramble.model.spaceship.Directions;
import scramble.model.spaceship.SpaceShip;

/**
 * Class for the representation of the Spaceship Panel.
 *
 * @see GamePanel
 * @see JPanel
 */
public class SpaceShipPanel extends GamePanel {

    private static final Logger LOG = Logger.getLogger(SpaceShip.class.getName());

    private static final long serialVersionUID = 1L;
    private static final int STARTER_POSITION_X = 50;
    private static final int STARTER_POSITION_Y = 50;
    private static final int SPACESHIP_WIDTH = 32;
    private static final int SPACESHIP_HEIGHT = 16;

    private transient SpaceShip spaceship;
    private transient List<Bullet> bullets;
    private final Timer updateTimer;

    /** Cosnstructor for the SpaceshipPanel class. */
    public SpaceShipPanel() {
        this.spaceship = new SpaceShip(STARTER_POSITION_X, STARTER_POSITION_Y, SPACESHIP_WIDTH, SPACESHIP_HEIGHT);
        this.setOpaque(false);
        bulletInit();
        updateTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                update();
            }
        });
        updateTimer.start();
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {

        // TODO check isRepaintable() from GamePanel

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
        drawBullets(g);
        this.canNotBeRepaint();
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

        this.canBeRepaint();
        repaint();
    }

    /** Checks continuosly with a timer for spaceship movement. */
    public void update() {
        final PairImpl<Integer, Integer> location = spaceship.getPosition();
        final int shipX = location.getFirstElement();
        final int shipY = location.getSecondElement();
        final int xSpeed = spaceship.getxSpeed();
        final int ySpeed = spaceship.getySpeed();

        /*
         * if (shipX + xSpeed < getWidth() / 2 && shipX + xSpeed >= 0 && shipY + ySpeed
         * <= getHeight()
         * && shipY + ySpeed >= 0) {
         * spaceship.move();
         * }
         */

        final int minX = 0;
        final int maxX = getWidth() / 2;
        final int minY = 4;
        final int maxY = getHeight();

        // Condizioni per il movimento orizzontale (asse X)
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

    /**
     * For each bullet, call bullet.move().
     */
    public void moveBullets() {
        final List<Bullet> bulletsToRemove = new ArrayList<>();
        for (final Bullet bullet : bullets) {
            bullet.move3();
            // Check if the bullet is out from screen
            if (bullet.getPosition().getFirstElement() > getWidth()) { // aggiungere il secondo caso
                bulletsToRemove.add(bullet);
            }
        }
        // removes bullets that have gone off the screen
        bullets.removeAll(bulletsToRemove);
        repaint();
    }

    private void drawBullet(final Graphics g, final Bullet bullet) {
        final BufferedImage bulletSprite = bullet.getSprite();
        if (bulletSprite != null) {
            g.drawImage(bulletSprite, bullet.getPosition().getFirstElement(),
                    bullet.getPosition().getSecondElement(), bullet.getWidth(), bullet.getHeight(), null);
        }
    }

    private void drawBullets(final Graphics g) {
        // for each bullet in bullet list, call drawBullet()
        for (final Bullet bullet : bullets) {
            drawBullet(g, bullet);
        }
    }

    /**
     * Shoots a bullet from the spaceship's current position.
     *
     * This method calculates the initial position of the bullet based on
     * the spaceship's current position and size. It then creates a new
     * {@link Bullet} instance and adds it to the list of bullets in the game.
     * The bullet's start position is at the right edge of the spaceship, centered
     * vertically.
     * 
     * @param type the type of the bullet
     */
    public void shootBullet(final BulletType type) {
        // bullet_type should be an enum, not an int
        final PairImpl<Integer, Integer> location = spaceship.getPosition();
        final int bulletX = location.getFirstElement() + spaceship.getWidth();
        final int bulletY = location.getSecondElement() + spaceship.getHeight() / 2;

        /*
         * create new Bullet class and append to List<Bullet>
         * start position is (shipX+shipWidth, shipY+shipHeight/2)
         */
        final Bullet bullet = new Bullet(bulletX, bulletY, type);
        bullets.add(bullet);
        repaint();
    }

    /**
     * Method that executes the command sent to the spaceship.
     *
     * @param command the command
     */
    public void sendCommandBullet(final BulletCommand command) {
        command.execute();
    }

    private void bulletInit() {
        this.bullets = new ArrayList<>();
    }

    /** Stops for timer. */
    public void stopUpdateTimer() {
        updateTimer.stop();
    }

    /** Start the timer. */
    public void startUpdateTimer() {
        updateTimer.start();
    }
}
