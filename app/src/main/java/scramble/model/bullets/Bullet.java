package scramble.model.bullets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
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
    private static final int XSPEED = 20;
    private int ySpeed;
    private static final int YSPEAD_BOMB = 20;
    private static final int SPRITES = 5;
    private final List<BufferedImage> sprites;
    private BufferedImage sprite;
    private final BulletType type;
    private static final Map<BulletType, PairImpl<Integer, Integer>> SIZE_MAP = Map.of(
        BulletType.TYPE_HORIZONTAL, new PairImpl<>(3, 3),
        BulletType.TYPE_BOMB, new PairImpl<>(21, 26));
    private boolean animationComplete;
    private int currentSpriteIndex;


    /**
     * Class construnctor.
     *
     * @param x starting position on the X axis
     * @param y starting position on the Y axis
     * @param type the type of the bullet
     */
    public Bullet(final int x, final int y, final BulletType type) {
        super(x, y, SIZE_MAP.get(type).getFirstElement(), SIZE_MAP.get(type).getSecondElement());
        this.sprites = new ArrayList<>();
        this.type = type;

        switch (type) {
            case TYPE_HORIZONTAL:
                try {
                    // Safe way to get resource
                    sprite = ImageIO.read(Bullet.class.getResource("/bullets/bullet" + ".png"));
                } catch (IOException e) {
                    LOG.severe("Ops!");
                    LOG.severe(e.toString());
                }
                break;

            case TYPE_BOMB:
                for (int i = 1; i <= SPRITES; i++) {
                    try {
                        sprites.add(ImageIO.read(Bullet.class.getResource("/bomb/bomb" + i + ".png")));
                    } catch (IOException e) {
                        LOG.severe("Ops!");
                        LOG.severe(e.toString());
                    }
                }
                break;

            default:
                break;
        }
    }

    /**
     * Handles the bullet's movement for different type of bullets.
     *
     */
    public void move3() {
        switch (type) {
            case TYPE_HORIZONTAL -> {
                ySpeed = 0;
                move();
            break;
        }
            case TYPE_BOMB -> {
                ySpeed = YSPEAD_BOMB;
                move();
            break;
        }
            default -> {

                break;
            }
        }
    }
    /**
     * Handles the bullet's movement.
     *
     */
    public void move() {
        updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() + XSPEED,
                                                      getPosition().getSecondElement() + ySpeed));
    }



    /** {@inheritDoc} */
    // Warning suppressed since returning a javadoc class
    @SuppressFBWarnings
    @Override
    public BufferedImage getSprite() {
        switch (type) {
            case TYPE_HORIZONTAL:
                return sprite;

            case TYPE_BOMB:
                return getNextBombSprite();

            default:
                // Throwing an exception for an unknown BulletType
                throw new IllegalArgumentException("Unknown BulletType: " + type);
        }
    }

    private BufferedImage getNextBombSprite() {
        if (animationComplete) {
            return sprites.get(SPRITES - 1); // Return the last sprite when the animation is complete
        }

        BufferedImage currentSprite = sprites.get(currentSpriteIndex);

        // Update the current sprite index
        if (currentSpriteIndex < SPRITES - 1) {
            currentSpriteIndex = currentSpriteIndex + 1;
        } else {
            animationComplete = true;
        }

        return currentSprite;
    }



}
