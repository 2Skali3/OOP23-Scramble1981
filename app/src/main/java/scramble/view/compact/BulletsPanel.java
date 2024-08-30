package scramble.view.compact;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import scramble.model.bullets.Bullet;
import scramble.model.bullets.BulletType;
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
public class BulletsPanel extends GamePanel {

    private static final long serialVersionUID = 1L;

    private transient List<Bullet> bullets;

    /** Constructor for the SpaceshipPanel class. */
    public BulletsPanel() {
        bulletInit();
        this.setOpaque(false);
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        drawBullets(g);
        this.canNotBeRepaint();
    }

    /**
     * Getter for the bullets.
     *
     * @return the bullet list
     */
    public List<Bullet> getBullets() {
        return new ArrayList<>(bullets);
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
     * For each bullet, call bullet.move().
     */
    public void moveBullets() {
        final List<Bullet> bulletsToRemove = new ArrayList<>();
        for (final Bullet bullet : bullets) {
            bullet.moveByType();
            // Check if the bullet is out from screen
            if (bullet.getPosition().getFirstElement() > getWidth() /* || bullet.checkGroundCollision() */) { // aggiungere
                                                                                                              // il
                                                                                                              // secondo
                                                                                                              // caso
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
            if (bullet.isHit()) {
                g.drawImage(bullet.getExpSprite(), bullet.getPosition().getFirstElement(),
                        bullet.getPosition().getSecondElement(), bullet.getWidth(), bullet.getHeight(), null);
            } else {
                g.drawImage(bulletSprite, bullet.getPosition().getFirstElement(),
                        bullet.getPosition().getSecondElement(), bullet.getWidth(), bullet.getHeight(), null);
            }
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
     * @param type      the type of the bullet
     * @param spaceship spaceship
     */
    public void shootBullet(final BulletType type, final SpaceShip spaceship) {
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

}
