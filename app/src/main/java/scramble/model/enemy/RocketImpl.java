package scramble.model.enemy;

import java.awt.image.BufferedImage;

import scramble.model.bullets.Bullet;
import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;
import scramble.utility.Constants;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.util.logging.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.random.RandomGenerator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of the Enemy interface. Used for the simple rocket NPC.
 */
public class RocketImpl extends GameElementImpl {

    private static final Logger LOG = Logger.getLogger(RocketImpl.class.getName());

    private final List<BufferedImage> sprites;
    private final List<BufferedImage> explosionSprites;
    private int currentSprite;
    private int currentExpSprite;
    private float speedY;
    private boolean hit;
    private AtomicInteger counterForExplosion;
    private final Timer startTimer;
    private final RandomGenerator randomStartDelay;
    private final TimerTask task;
    private int randomDelay;
    private RocketState state;

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
        loadSprites();
        this.currentSprite = 0;
        this.hit = false;
        this.speedY = 3.5f;
        this.state = RocketState.PREMOVE;
        startTimer = new Timer();
        randomStartDelay = RandomGenerator.getDefault();
        task = new TimerTask() {

            @Override
            public void run() {
                state = RocketState.MOVING;
            }
        };
        randomDelay = 1000 + randomStartDelay.nextInt(3000);

    }

    private void updateRocketPosition(int x, int y) {
        updatePosition(new PairImpl<Integer, Integer>(x, y));
    }

    public void move() {
        if (isHit()) {
            speedY = 0;
        }
        if (this.state.equals(RocketState.PREMOVE)) {
            updateRocketPosition(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED,
                    (int) (getPosition().getSecondElement()));
        } else if (this.state.equals(RocketState.MOVING)) {
            updateRocketPosition(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED,
                    (int) (getPosition().getSecondElement() - speedY));
        } else if (this.state.equals(RocketState.EXPLODED)) {
            updateRocketPosition(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED,
                    (int) getPosition().getSecondElement());
        }
        if (getPosition().getSecondElement() <= 0) {
            setExploded();
            this.counterForExplosion = new AtomicInteger(Constants.ROCKET_EXPLOSION_DURATION);
        }
    }

    @Override
    public BufferedImage getSprite() {
        currentSprite += 1;
        currentSprite = currentSprite % Constants.SPRITE_ROCKET;
        return sprites.get(currentSprite);
    }

    public BufferedImage getExplosionSprite() {
        currentExpSprite += 1;
        currentExpSprite = currentExpSprite % Constants.SPRITE_ROCKET_EXPLOSION;
        return explosionSprites.get(currentExpSprite);
    }

    public boolean checkCollisionBullet(final Set<Bullet> bullets) {
        return bullets.stream().anyMatch(this::hasCollided);
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
        this.state = RocketState.PREMOVE;
        startTimer.schedule(task, randomDelay);
    }

    public boolean isExploded() {
        return this.state.equals(RocketState.EXPLODED);
    }

    public void setExploded() {
        this.state = RocketState.EXPLODED;
    }

    public int getCounterForExplosion() {
        return counterForExplosion.get();
    }

    public void incrementCounterForExplosion() {
        this.counterForExplosion.incrementAndGet();
    }

    public static int getExplosionDuration() {
        return Constants.ROCKET_EXPLOSION_DURATION;
    }

    private void loadSprites() {
        for (int i = 1; i <= Constants.SPRITE_ROCKET; i++) {
            try (InputStream inputStream = getClass().getResourceAsStream("/rocket/rocket_frame" + i + "_shader.png")) {
                sprites.add(ImageIO.read(inputStream));
            } catch (IOException e) {
                LOG.severe("Error occurred while loading rocket sprites!");
                LOG.severe(e.toString());
            }
        }
        for (int i = 1; i <= Constants.SPRITE_ROCKET_EXPLOSION; i++) {
            try {
                explosionSprites
                        .add(ImageIO.read(getClass().getResource("/rocket/rocket_explosion" + i + "_sprite.png")));
            } catch (IOException e) {
                LOG.severe("Ops! couldn't load enemy_rocket_explosion_sprites");
                LOG.severe(e.toString());
            }
        }
    }

}