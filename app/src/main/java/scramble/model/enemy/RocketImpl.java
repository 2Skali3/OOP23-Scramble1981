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

/**
 * Implementation of the Enemy interface. Used for the simple rocket NPC.
 */
public class RocketImpl extends GameElementImpl {

    private static final Logger LOG = Logger.getLogger(RocketImpl.class.getName());
<<<<<<< HEAD

=======
    private static final int SPRITES = 5;
    private static final int EXP_SPRITES = 4;
    private static final int EXPLOSION_DURATION = 15;
    private static final int DELAY = 3000;
>>>>>>> 1cd6456da427bd6757fc70649e448d5b1c5693ab

    private final List<BufferedImage> sprites;
    private final List<BufferedImage> explosionSprites;
    private int currentSprite;
    private int currentExpSprite;
    private float speedY;
    private boolean hit;
<<<<<<< HEAD
    private int counterForExplosion = 0;
=======
    private boolean moving;
    private boolean premove;
    private boolean exploded;
    private int counterForExplosion;
>>>>>>> 1cd6456da427bd6757fc70649e448d5b1c5693ab
    private final Timer startTimer;
    private final TimerTask task;
<<<<<<< HEAD
    private int randomDelay;
    private RocketState state;
=======
    private final int randomDelay;
>>>>>>> 1cd6456da427bd6757fc70649e448d5b1c5693ab

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
<<<<<<< HEAD
        loadSprites();
        this.currentSprite = 0;
        this.hit = false;
        this.speedY = 3.5f;
        this.state = RocketState.PREMOVE;
=======
        for (int i = 1; i <= SPRITES; i++) {
            try {
                sprites.add(ImageIO.read(RocketImpl.class.getResource("/rocket/rocket_frame" + i + "_shader.png")));
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
        }
        for (int i = 1; i <= EXP_SPRITES; i++) {
            try {
                explosionSprites
                        .add(ImageIO.read(RocketImpl.class.getResource("/rocket/rocket_explosion" + i + "_sprite.png")));
            } catch (IOException e) {
                LOG.severe("Ops! couldn't load enemy_rocket_explosion_sprites");
                LOG.severe(e.toString());
            }
        }
        this.currentSprite = 0;
        this.hit = false;
        this.speedY = Constants.ROCKET_SPEED;
        this.moving = false;
        this.exploded = false;
>>>>>>> 1cd6456da427bd6757fc70649e448d5b1c5693ab
        startTimer = new Timer();
        final RandomGenerator randomStartDelay = RandomGenerator.of("Random");
        task = new TimerTask() {

            @Override
            public void run() {
                state = RocketState.MOVING;
            }
        };
        randomDelay = 1000 + randomStartDelay.nextInt(DELAY);

    }

    /** Handles movement logic for the rocket. */
    public void move() {
        if (isHit()) {
            speedY = 0;
        }
<<<<<<< HEAD
        if(this.state.equals(RocketState.PREMOVE)){
            updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED, (int) (getPosition().getSecondElement())));
        }else if (this.state.equals(RocketState.MOVING)) {
=======
        if (premove) {
            updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED,
                    getPosition().getSecondElement()));
        }
        if (moving) {
>>>>>>> 1cd6456da427bd6757fc70649e448d5b1c5693ab
            updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED,
                    (int) (getPosition().getSecondElement() - speedY)));
        } else if(this.state.equals(RocketState.EXPLODED)){
            updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() - Constants.LANDSCAPEX_SPEED, (int) getPosition().getSecondElement()));
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
     * Returns randomised image for explosion animation.
     *
     * @return a sprite
     */
    public BufferedImage getExplosionSprite() {
        currentExpSprite += 1;
        currentExpSprite = currentExpSprite % Constants.SPRITE_ROCKET_EXPLOSION;
        return explosionSprites.get(currentExpSprite);
    }

    /**
     * Check if the {@link Rocket} is collided with a {@Bullet}.
     *
     * @param bullets the {@link Set} of {@link Bullet} that needed to be checked
     *
     * @return {@code true} if is collided, false otherwise
     */
    public boolean checkCollisionBullet(final Set<Bullet> bullets) {
<<<<<<< HEAD
        return bullets.stream().anyMatch(this::hasCollided);
=======
        for (final Bullet b : bullets) {
            if (hasCollided(b)) {
                hit = true;
                return true;
            }
        }
        return false;
>>>>>>> 1cd6456da427bd6757fc70649e448d5b1c5693ab
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
     * @param hit
     */
    public void setHit(final boolean hit) {
        this.hit = hit;
    }

    /**
     * Method that allow the movement of the {@link Rocket}.
     */
    public void turnOnMove() {
        this.state = RocketState.PREMOVE;
        startTimer.schedule(task, randomDelay);
    }

    /**
     * Getter for exploded.
     *
     * @return exploded
     */
    public boolean isExploded() {
        return this.state.equals(RocketState.EXPLODED);
    }

<<<<<<< HEAD
    public void setExploded() {
        this.state = RocketState.EXPLODED;
=======
    /**
     * Setter for exploded.
     *
     * @param exploded the new value of {@code exploded}
     */
    public void setExploded(final boolean exploded) {
        this.exploded = exploded;
>>>>>>> 1cd6456da427bd6757fc70649e448d5b1c5693ab
    }

    /**
     * Getter for the counter of the explosion.
     *
     * @return the counter of the explosion
     */
    public int getCounterForExplosion() {
        return counterForExplosion;
    }

    /**
     * Increment the counter of the explosion.
     *
     * @return the counter of the explosion incremented by {@code 1}
     */
    public int incrementCounterForExplosion() {
        return this.counterForExplosion++;
    }

    /**
     * Getter for the explosion duration.
     *
     * @return the explosion duration
     */
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
