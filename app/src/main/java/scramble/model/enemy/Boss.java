package scramble.model.enemy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import java.util.random.RandomGenerator;

import scramble.model.bullets.Bullet;
import scramble.model.common.impl.GameElementImpl;
import scramble.model.scores.Scores;
import scramble.utility.Constants;

/**
 * Extension of the abstract class {@link GameElementImpl} for the
 * implementation of the {@code Boss}.
 */
public class Boss extends GameElementImpl {

    private static final Logger LOG = Logger.getLogger(Rocket.class.getName());

    private final List<BufferedImage> sprites;
    private final List<BufferedImage> explosionSprites;

    private boolean hit;
    private boolean exploded;

    /**
     * Class Constructor.
     * 
     * @param x      x coord
     * @param y      y coord
     * @param width  width
     * @param height height
     */
    public Boss(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.sprites = new ArrayList<>();
        this.explosionSprites = new ArrayList<>();

        loadSprites();
    }

    /**
     * Getter for the sprite.
     * 
     * @return a static sprite
     */
    @Override
    public BufferedImage getSprite() {
        return sprites.get(0);
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
     * Setter for hit.
     * 
     * @param hit the new value
     */
    public void setHit(final boolean hit) {
        this.hit = hit;
    }

    /**
     * Getter for exploded.
     *
     * @return true if is exploded, false otherwise
     */
    public boolean isExploded() {
        return exploded;
    }

    /**
     * Setter for exploded.
     *
     * @param exploded the new value
     */
    public void setExploded(final boolean exploded) {
        this.exploded = exploded;
        Scores.incrementCurrentScore(Constants.BOSS_POINTS);
    }

    /**
     * Checks for collision with bullet.
     * 
     * @param bullet the bullet to check
     * @return true if collided
     */
    public boolean checkCollisionBullet(final Bullet bullet) {
        return this.hasCollided(bullet);
    }

    /**
     * Getter for exploding sprite.
     * 
     * @return a random sprite
     */
    public BufferedImage getExplosionSprite() {
        final RandomGenerator randG = RandomGenerator.of("Random");
        return explosionSprites.get(randG.nextInt(Constants.SPRITE_BOSS_EXPLOSION));
    }

    private void loadSprites() {
        try (InputStream inputStream = Boss.class
                .getResourceAsStream("/boss/mystery_shader.png")) {
            sprites.add(ImageIO.read(inputStream));
        } catch (IOException e) {
            LOG.severe("Error occurred while loading boss sprites!");
            LOG.severe(e.toString());
        }
        for (int i = 1; i <= Constants.SPRITE_BOSS_EXPLOSION; i++) {
            try {
                explosionSprites
                        .add(ImageIO
                                .read(Boss.class.getResource("/boss/ufo_explosion_frame" + i + ".png")));
            } catch (IOException e) {
                LOG.severe("Ops! couldn't load enemy_rocket_explosion_sprites");
                LOG.severe(e.toString());
            }
        }
    }

}
