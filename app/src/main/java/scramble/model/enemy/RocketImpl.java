package scramble.model.enemy;

import java.awt.image.BufferedImage;

import scramble.model.bullets.Bullet;
import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;
import scramble.model.spaceship.SpaceShip;
import scramble.utility.Constants;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.logging.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.random.RandomGenerator;

/**
 * Implementation of the Enemy interface. Used for the simple rocket NPC.
 */
public class RocketImpl extends GameElementImpl {

    private static final Logger LOG = Logger.getLogger(SpaceShip.class.getName());
    private static final int SPRITES = 5;
    private static final int EXP_SPRITES = 4;
    private static final int EXPLOSION_DURATION = 15;
    private final List<BufferedImage> sprites;
    private final List<BufferedImage> explosionSprites;
    private int currentSprite;
    private int currentExpSprite;
    private float speedY;
    private boolean hit;
    private boolean moving;
    private boolean premove;
    private boolean exploded;
    private int counterForExplosion = 0;
    private final Timer startTimer;
    private final RandomGenerator randomStartDelay;
    private final TimerTask task;
    private int randomDelay;

    /**
     * Class constructor.
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  Rocket width
     * @param height Rocket height
     */
    public RocketImpl(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.sprites = new ArrayList<>();
        this.explosionSprites = new ArrayList<>();
        for (int i = 1; i <= SPRITES; i++) {
            try {
                sprites.add(ImageIO.read(getClass().getResource("/rocket/rocket_frame" + i + "_shader.png")));
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
        }
        for (int i = 1; i <= EXP_SPRITES; i++) {
            try {
                explosionSprites
                        .add(ImageIO.read(getClass().getResource("/rocket/rocket_explosion" + i + "_sprite.png")));
            } catch (IOException e) {
                LOG.severe("Ops! couldn't load enemy_rocket_explosion_sprites");
                LOG.severe(e.toString());
            }
        }
        this.currentSprite = 0;
        this.hit = false;
        this.speedY = 3.5f;
        this.moving = false;
        this.exploded = false;
        startTimer = new Timer();
        randomStartDelay = RandomGenerator.of("Random");
        task = new TimerTask() {

            @Override
            public void run() {
                premove = false;
                moving = true;
            }
        };
        randomDelay = 1000 + randomStartDelay.nextInt(3000);
        
    }

    public void move() {
        if (isHit()) {
            speedY = 0;
        }
        if(premove){
            updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED, (int) (getPosition().getSecondElement())));
        }
        if (moving) {
            updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED,
                    (int) (getPosition().getSecondElement() - speedY)));
        }
        if (getPosition().getSecondElement() <= 0) {
            setExploded(true);
            this.counterForExplosion = EXPLOSION_DURATION;
        }
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
                return true;
            }
        }
        return false;
    }

    public void moveExplosion(final int explosionSpeedX) {

    }

    /**
     * Getter for hit.
     *
     * @return hit
     */
    public boolean isHit() {
        return hit;
    }

    public void setHit(final boolean hit) {
        this.hit = hit;
    }

    public void turnOnMove() {
        premove = true;
        startTimer.schedule(task, randomDelay);
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

}