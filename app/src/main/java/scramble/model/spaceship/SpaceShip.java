package scramble.model.spaceship;

import scramble.model.common.impl.GameElementImpl;

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
public final class SpaceShip extends GameElementImpl {

    private static final Logger LOG = Logger.getLogger(SpaceShip.class.getName());

    private final List<BufferedImage> sprites;
    private static final int SPRITES = 8;

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
    }

    /**
     * Getter for the spaceship's sprites. Suppressed spotbugs warning since it's
     * returning a jdk class.
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
        getPosition().updateLocation(dx, dy);
    }

    /** {@inheritDoc} */
    // Warning suppressed since returning a javadoc class
    @SuppressFBWarnings
    @Override
    public BufferedImage getSprite() {
        // Needs to apply only once
        @SuppressFBWarnings
        final Random random = new Random();
        final int num = random.nextInt(8);

        return sprites.get(num);
    }
}
