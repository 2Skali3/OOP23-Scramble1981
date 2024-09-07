package scramble.model.enemy;

import java.awt.image.BufferedImage;

import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;
import scramble.model.spaceship.SpaceShip;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.logging.Logger;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of the Enemy interface. Used for the simple rocket NPC.
 */
public class RocketImpl extends GameElementImpl implements Cloneable {

    private static final Logger LOG = Logger.getLogger(SpaceShip.class.getName());
    private static final int SPRITES = 5;

    private final List<BufferedImage> sprites;
    private int currentSprite;
    private boolean hit;

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
        for (int i = 1; i <= SPRITES; i++) {
            try {
                sprites.add(ImageIO.read(getClass().getResource("/rocket/rocket_frame" + i + "_shader.png")));
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
        }
        this.currentSprite=0;
        this.hit = false;
    }

    public void move() {

        updatePosition(new PairImpl<Integer, Integer>(getPosition().getFirstElement() - 4,
                getPosition().getSecondElement() - 1));
    }

    public List<BufferedImage> getSprites() {
        return new ArrayList<>(sprites);
    }

    @Override
    public BufferedImage getSprite() {
        final int num;
        if(currentSprite< SPRITES){
            num = currentSprite++;
        }
        else{
            currentSprite = 1;
            num = currentSprite;
        }
        return sprites.get(num);
    }

    public boolean checkCollision(final SpaceShip spaceship){
        if(hasCollided(spaceship)){
            this.hit = true;
            spaceship.setHit(true);
            return true;
        }
        return false;
    }
    @Override
    public RocketImpl clone() throws CloneNotSupportedException {
        return (RocketImpl) super.clone();
    }
}
