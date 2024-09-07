package scramble.view.compact;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.Timer;

import javax.swing.JPanel;
import scramble.model.bullets.Bullet;
import scramble.model.bullets.BulletType;
import scramble.model.command.impl.BulletCommand;
import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.common.impl.PairImpl;
import scramble.model.common.impl.TimedLinkedListImpl;
import scramble.model.enemy.RocketImpl;
import scramble.model.spaceship.SpaceShip;
import scramble.utility.Constants;

/**
 * Class for the representation of the Spaceship Panel.
 *
 * @see GamePanel
 * @see JPanel
 */
public class BulletsPanel extends GamePanel {

    private static final long serialVersionUID = 1L;
    private static final int MAX_BOMB = 2;
    private transient Set<Bullet> bullets;
    private transient TimedLinkedListImpl<Bullet> explodingBullets;
    private Timer bulletTimer;

    /** Constructor for the SpaceshipPanel class. */
    public BulletsPanel() {
        bulletsInit();
        this.setOpaque(false);
        this.bulletTimer = new Timer(32, e -> this.updateBullets());

    }

    private void updateBullets() {
        for (Bullet b : bullets) {
            b.moveByType();
        }
        /*
         * for (Bullet b : explodingBullets.getList()) {
         * b.moveByType();
         * }
         */
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        drawBullets(g);
    }

    /**
     * Getter for the bullets.
     *
     * @return the bullet list
     */
    public Set<Bullet> getBullets() {
        return new HashSet<>(bullets);
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
     * Removes the specified bullets from the list of bullets and repaints the
     * panel.
     *
     * @param bulletsToRemove the list of bullets to be removed from the internal
     *                        list
     */
    public void removeBullets(final List<Bullet> bulletsToRemove) {
        bullets.removeAll(bulletsToRemove);
        // repaint();
    }

    /**
     * Adds the given list of bullets to the list of exploding bullets.
     * These bullets will be animated or processed as exploding bullets
     * with a specified duration for the explosion effect.
     *
     * @param bulletsToRemove the list of bullets that have collided and are
     *                        exploding
     */
    public void addExplodingBullets(final List<Bullet> bulletsToRemove) {
        explodingBullets.addAll(bulletsToRemove, 1000);
        // repaint();
    }

    /**
     * For each bullet, call bullet.move().
     */
    public void moveBullets() {
        final List<Bullet> bulletsToRemove = bullets.stream()
                .peek(b -> b.moveByType())
                .filter(b -> b.getPosition().getFirstElement() > getWidth())
                .toList();
        // removes bullets that have gone off the screen
        removeBullets(bulletsToRemove);
        List<Bullet> bulletsCopy = new ArrayList<>(explodingBullets.getList());
        bulletsCopy.stream().forEach(b -> b.moveExplosion(-Constants.LANDSCAPEX_SPEED));

        // repaint();
    }

    private void drawBullet(final Graphics g, final Bullet bullet) {
        final BufferedImage bulletSprite = bullet.getSprite();
        if (bulletSprite != null) {
            g.drawImage(bulletSprite, bullet.getPosition().getFirstElement(),
                    bullet.getPosition().getSecondElement(), bullet.getWidth(), bullet.getHeight(), null);
        }

    }

    private void drawExplodingBullet(final Graphics g, final Bullet bullet) {
        final BufferedImage bulletSprite = bullet.getExpSprite();
        if (bulletSprite != null) {
            g.drawImage(bulletSprite, bullet.getPosition().getFirstElement(),
                    bullet.getPosition().getSecondElement(), bullet.getWidth(), bullet.getHeight(), null);
        }
    }

    private void drawBullets(final Graphics g) {
        // for each bullet in bullet list, call drawBullet()
        bullets.stream().forEach(b -> drawBullet(g, b));
        List<Bullet> bulletsCopy = new ArrayList<>(explodingBullets.getList());
        bulletsCopy.stream().forEach(b -> drawExplodingBullet(g, b));
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
        if (type == BulletType.TYPE_BOMB) {
            // Check if there are already MAX_BOMBS bombs
            final long activeBombCount = bullets.stream()
                    .filter(b -> b.getType() == BulletType.TYPE_BOMB)
                    .count();

            if (activeBombCount >= MAX_BOMB) {
                // System.out.println("Cannot shoot more bombs. Maximum limit reached.");
                return; // Don't add new bomb if limit is reached
            }
        }

        final PairImpl<Integer, Integer> location = spaceship.getPosition();
        final int bulletX = location.getFirstElement() + spaceship.getWidth();
        final int bulletY = location.getSecondElement() + spaceship.getHeight() / 2;

        /*
         * create new Bullet class and append to List<Bullet>
         * start position is (shipX+shipWidth, shipY+shipHeight/2)
         */
        final Bullet bullet = new Bullet(bulletX, bulletY, type);
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

    private void bulletsInit() {
        this.bullets = new HashSet<>();
        this.explodingBullets = new TimedLinkedListImpl<>();
    }

    /** {@nheritDoc} */
    @Override
    void startTimer() {
        bulletTimer.start();
    }

    /** {@nheritDoc} */
    @Override
    public void stopTimer() {
        bulletTimer.stop();
    }

    
}
