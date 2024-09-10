package scramble.model.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import scramble.model.bullets.Bullet;
import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;
import scramble.utility.Constants;

public class FuelTank extends GameElementImpl {

    private static final Logger LOG = Logger.getLogger(FuelTank.class.getName());
    private static final int SPRITES = 2;
    private static final int EXP_SPRITES = 4;
    private static final int EXPLOSION_DURATION = 15;
    private static final int FUEL_REFILL = 15;

    private final List<BufferedImage> sprites;
    private final List<BufferedImage> explosionSprites;

    private int currentSprite;
    private int currentExpSprite;
    private boolean hit;

    private boolean exploded;

    private int counterForExplosion = 0;

    public FuelTank(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.sprites = new ArrayList<>();
        this.explosionSprites = new ArrayList<>();
        for (int i = 1; i <= SPRITES; i++) {
            try {
                sprites.add(ImageIO.read(getClass().getResource("/fueltank/fuel_dump_" + i + ".png")));
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
        }
        for (int i = 1; i <= EXP_SPRITES; i++) {
            try {
                explosionSprites
                        .add(ImageIO.read(getClass().getResource("/fueltank/explosion_frame" + i + "_shader.png")));
            } catch (IOException e) {
                LOG.severe("Ops! couldn't load enemy_rocket_explosion_sprites");
                LOG.severe(e.toString());
            }
        }
        this.currentSprite = 0;
        this.hit = false;
        this.exploded = false;
    }

    public void move() {
        updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED,
                (int) (getPosition().getSecondElement())));
    }

    @Override
    public BufferedImage getSprite() {
        currentSprite += 1;
        currentSprite = currentSprite % SPRITES;
        return sprites.get(currentSprite);
    }

    public BufferedImage getExplosionSprite() {
        currentExpSprite += 1;
        currentExpSprite = currentExpSprite % EXP_SPRITES;
        return explosionSprites.get(currentExpSprite);
    }

    public boolean checkCollisionBullet(final Set<Bullet> bullets) {
        for (final Bullet b : bullets) {
            if (hasCollided(b)) {
                hit = true;
                // bullets.remove(b);
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for hit.
     *
     * @return hit
     */
    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public int getCounterForExplosion() {
        return counterForExplosion;
    }

    public int incrementCounterForExplosion() {
        return this.counterForExplosion++;
    }

    public static int getExplosionDuration() {
        return EXPLOSION_DURATION;
    }

    public static int getFuelRefill() {
        return FUEL_REFILL;
    }
}
