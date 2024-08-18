package scramble.view.compact;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import scramble.model.bullets.Bullet;
import scramble.model.command.impl.BulletCommand;
import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.common.impl.PairImpl;
import scramble.model.spaceship.SpaceShip;

/**
 * Class for the representation of the Spaceship Panel.
 * 
 * @see GamePanel
 * @see JPanel
 */
public class SpaceShipPanel extends GamePanel {

    private static final long serialVersionUID = 1L;
    private static final int STARTER_POSITION_X = -50;
    private static final int STARTER_POSITION_Y = -50;
    private static final int SPACESHIP_WIDTH = 32;
    private static final int SPACESHIP_HEIGHT = 16;
    private static final int BULLET_WIDTH = 3;
    private static final int BULLET_HEIGHT = 3;

    private final transient SpaceShip spaceship;
    private transient List<Bullet> bullets;

    /** Cosnstructor for the SpaceshipPanel class. */
    public SpaceShipPanel() {
        this.spaceship = new SpaceShip(STARTER_POSITION_X, STARTER_POSITION_Y, SPACESHIP_WIDTH, SPACESHIP_HEIGHT);
        this.setOpaque(false);
        bulletInit();
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {

        // TODO check isRepaintable() from GamePanel

        if (spaceship.getSprite() != null) {
            g.drawImage(spaceship.getSprite(), spaceship.getPosition().getFirstElement(),
                    spaceship.getPosition().getSecondElement(), spaceship.getWidth(), spaceship.getHeight(), null);
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
        return spaceship;
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
        // repaint();
    }

    /**
     * For each bullet, call bullet.move().
     */
    public void moveBullets() {
        final List<Bullet> bulletsToRemove = new ArrayList<>();
        for (final Bullet bullet : bullets) {
            bullet.move();
            // Check if the bullet is out from screen
            if (bullet.getPosition().getFirstElement() > getWidth()) {
                bulletsToRemove.add(bullet);
            }
        }
        // removes bullets that have gone off the screen
        bullets.removeAll(bulletsToRemove);
        // repaint();
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
     */
    public void shootBullet(/* final int bullet_type */) {
        // bullet_type should be an enum, not an int
        final PairImpl<Integer, Integer> location = spaceship.getPosition();
        final int bulletX = location.getFirstElement() + spaceship.getWidth();
        final int bulletY = location.getSecondElement() + spaceship.getHeight() / 2;

        /*
         * create new Bullet class and append to List<Bullet>
         * start position is (shipX+shipWidth, shipY+shipHeight/2)
         */
        final Bullet bullet = new Bullet(bulletX, bulletY, BULLET_WIDTH, BULLET_HEIGHT);
        bullets.add(bullet);
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

    private void bulletInit() {
        this.bullets = new ArrayList<>();
    }

}
