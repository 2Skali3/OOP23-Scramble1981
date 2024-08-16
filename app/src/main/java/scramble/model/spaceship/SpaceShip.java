package scramble.model.spaceship;

import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElement;

import java.io.IOException;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles the spaceship model in the game. It is an implementation
 * of the game element that is controlled by the player.
 */
public final class SpaceShip extends GameElementImpl implements Cloneable {

    private static final Logger LOG = Logger.getLogger(SpaceShip.class.getName());
    private static final int SPRITES = 8;
    private static final int EXP_SPRITES = 4;

    private final List<BufferedImage> sprites;
    private final List<BufferedImage> explosionSprites;
    private final Random random;
    private boolean hit;

    /**
     * Class construnctor.
     *
     * @param startX starting position on the X axis
     * @param startY starting position on the Y axis
     * @param width  the width of the spaceship
     * @param height the height of the spaceship
     */
    public SpaceShip(final int startX, final int startY, final int width, final int height) {
        super(startX, startY, width, height);
        this.sprites = new ArrayList<>();
        for (int i = 1; i <= SPRITES; i++) {
            try {
                sprites.add(ImageIO.read(getClass().getResource("/ship/ship" + i + ".png")));
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
        }
        this.explosionSprites = new ArrayList<>();
        for (int i = 1; i <= EXP_SPRITES; i++) {
            try {
                explosionSprites
                        .add(ImageIO.read(getClass().getResource("/ship/explosion/ship_explosion_frame" + i + ".png")));
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
        }
        random = new Random();
        hit = false;
    }

    /**
     * Getter for the spaceship's sprites.
     *
     * @return the list of spaceship's sprites
     */
    public List<BufferedImage> getSprites() {
        return new ArrayList<>(sprites);
    }

    /**
     * Handles the spaceship's movement.
     *
     * @param dx amount of movement on the X axis
     * @param dy amount of movement on the Y axis
     */
    public void move(final int dx, final int dy) {
        updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() + dx,
                getPosition().getSecondElement() + dy));
    }

    /** {@inheritDoc} */
    // Warning suppressed since returning a javadoc class
    @SuppressFBWarnings
    @Override
    public BufferedImage getSprite() {
        // Needs to apply only once
        @SuppressFBWarnings
<<<<<<< HEAD
        final Random random = new Random();
=======
>>>>>>> 434efcfa90ec5c6e76f3a35148a8bb2ec581ed36
        final int num = random.nextInt(SPRITES);

        return sprites.get(num);
    }

    /**
     * Check if the spaceship is colliding with the map.
     * 
     * @param map the map
     * @return true it has touched the map
     */
    public boolean checkGroundCollision(final List<MapElement> map) {
        for (final MapElement me : map) {
            if (hasCollided(me)) {
                hit = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns randomised image for explosion animation.
     * 
     * @return a sprite
     */
    public BufferedImage getExpSprite() {
        final int num = random.nextInt(EXP_SPRITES);
        // hit = false;
        return explosionSprites.get(num);
    }

    /**
     * Getter for hit.
     * 
     * @return hit
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * Setter for hit, the boolean recording collision status.
     * 
     * @param hit the new value
     */
    public void setHit(final boolean hit) {
        this.hit = hit;
    }

    @Override
    public SpaceShip clone() throws CloneNotSupportedException {
        return (SpaceShip) super.clone();
    }
}
