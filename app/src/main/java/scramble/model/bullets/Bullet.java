package scramble.model.bullets;

import java.io.IOException;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;

/**
 * This class handles the bullet model in the game. It is an implementation
 * of the game element that is controlled by the player.
 */
public class Bullet extends GameElementImpl {

    private static final Logger LOG = Logger.getLogger(Bullet.class.getName());
    private static final Integer XSPEED = 10;
    private static final Integer YSPEED = 0;
    private BufferedImage sprite;


    /**
     * Class construnctor.
     *
     * @param x starting position on the X axis
     * @param y starting position on the Y axis
     * @param width  the width of the bullet
     * @param height the height of the bullet
     */
    public Bullet(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        try {
            // Safe way to get resource
            sprite = ImageIO.read(Bullet.class.getResource("/bullets/bullet" + ".png"));
        } catch (IOException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
    }
    /**
     * Handles the bullet's movement.
     *
     */
    public void move() {
        updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() + XSPEED,
                                                      getPosition().getSecondElement() + YSPEED));
    }

    /** {@inheritDoc} */
    // Warning suppressed since returning a javadoc class
    @SuppressFBWarnings
    @Override
    public BufferedImage getSprite() {
        return sprite;
    }

}
