package scramble.model.enemy;

import java.awt.image.BufferedImage;

import scramble.model.bullets.Bullet;
import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElement;
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

/**
 * Implementation of the Enemy interface. Used for the simple rocket NPC.
 */
public class Rocket extends GameElementImpl {

    private static final Logger LOG = Logger.getLogger(Rocket.class.getName());

    private final List<BufferedImage> sprites;
    private final List<BufferedImage> explosionSprites;
    private final Timer startTimer;
    private final TimerTask task;
    private final int randomDelay;
    private int currentSprite;
    private int currentExpSprite;
    private float speedY;
    private boolean hit;
    private boolean crashed; // In the sense it hit the celing
    private int counterForExplosion;

    private RocketState state;

    /**
     * Class constructor.
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  Rocket width
     * @param height Rocket height
     */
    public Rocket(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.sprites = new ArrayList<>();
        this.explosionSprites = new ArrayList<>();
        loadSprites();
        this.currentSprite = 0;
        this.hit = false;
        this.crashed = false;
        this.speedY = Constants.ROCKET_SPEED;
        this.state = RocketState.PREMOVE;
        startTimer = new Timer();
        final RandomGenerator randomStartDelay = RandomGenerator.getDefault();
        task = new TimerTask() {

            @Override
            public void run() {
                state = RocketState.MOVING;
            }
        };
        randomDelay = 1000 + randomStartDelay.nextInt(Constants.MAXDELAY);

    }

    /** Handles rocket movement. */
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
            this.counterForExplosion = Constants.ROCKET_EXPLOSION_DURATION;
        }
    }

    /** {@inheritDoc} */
    @Override
    public BufferedImage getSprite() {
        currentSprite += 1;
        currentSprite = currentSprite % Constants.SPRITE_ROCKET;
        return sprites.get(currentSprite);
    }

    /**
     * Getter for exploding sprite.
     * 
     * @return a random sprite
     */
    public BufferedImage getExplosionSprite() {
        currentExpSprite += 1;
        currentExpSprite = currentExpSprite % Constants.SPRITE_ROCKET_EXPLOSION;
        return explosionSprites.get(currentExpSprite);
    }

    /**
     * Checks for collision with bullets.
     * 
     * @param bullets the list of on screen bullets
     * @return true if collided
     */
    public boolean checkCollisionBullet(final Set<Bullet> bullets) {
        return bullets.stream().anyMatch(this::hasCollided);
    }

    /**
     * Method that check if the {@code RocketImpl} has collided with the ceiling.
     * 
     * @param ceiling the {@link List} of {@link MapElement} of the ceiling
     * @return if the {@code RocketImpl} has collided or not
     */
    public boolean checkCollisionCeiling(final List<MapElement> ceiling) {
        return ceiling.stream().anyMatch(this::hasCollided);
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
     * Turns on movement of the rocket.
     */
    public void turnOnMove() {
        this.state = RocketState.PREMOVE;
        startTimer.schedule(task, randomDelay);
    }

    /**
     * Getter for exploded.
     * 
     * @return a boolean
     */
    public boolean isExploded() {
        return this.state.equals(RocketState.EXPLODED);
    }

    /**
     * Setter for exploded.
     */
    public void setExploded() {
        this.state = RocketState.EXPLODED;
    }

    /**
     * Getter for counter.
     * 
     * @return the counter int
     */
    public int getCounterForExplosion() {
        return counterForExplosion;
    }

    /**
     * Increment counter, used as a timer for explosion.
     * 
     * @return the counter incremented
     */
    public int incrementCounterForExplosion() {
        return this.counterForExplosion++;
    }

    /**
     * Getter for Rocket Explosion duration.
     * 
     * @return the rocket explosion duration
     */
    public static int getExplosionDuration() {
        return Constants.ROCKET_EXPLOSION_DURATION;
    }

    /**
     * Getter for crashed.
     * 
     * @return true if hit ceiling
     */
    public boolean isCrashed() {
        return crashed;
    }

    /**
     * Setter for crashed.
     * 
     * @param crashed the new value
     */
    public void setCrashed(final boolean crashed) {
        this.crashed = crashed;
    }

    private void loadSprites() {
        for (int i = 1; i <= Constants.SPRITE_ROCKET; i++) {
            try (InputStream inputStream = Rocket.class
                    .getResourceAsStream("/rocket/rocket_frame" + i + "_shader.png")) {
                sprites.add(ImageIO.read(inputStream));
            } catch (IOException e) {
                LOG.severe("Error occurred while loading rocket sprites!");
                LOG.severe(e.toString());
            }
        }
        for (int i = 1; i <= Constants.SPRITE_ROCKET_EXPLOSION; i++) {
            try {
                explosionSprites
                        .add(ImageIO
                                .read(Rocket.class.getResource("/rocket/rocket_explosion" + i + "_sprite.png")));
            } catch (IOException e) {
                LOG.severe("Ops! couldn't load enemy_rocket_explosion_sprites");
                LOG.severe(e.toString());
            }
        }
    }

    private void updateRocketPosition(final int x, final int y) {
        updatePosition(new PairImpl<Integer, Integer>(x, y));
    }

}
