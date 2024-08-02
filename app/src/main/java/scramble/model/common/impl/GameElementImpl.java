package scramble.model.common.impl;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import scramble.model.common.api.GameElement;

/**
 * Implementation of the GameElemnt interface. Sets the ground for all game
 * objects development.
 */
public class GameElementImpl implements GameElement {

    private final int width, height;
    private Rectangle hitbox;
    private final PairImpl<Float, Float> location;

    /**
     * Class constructor.
     * 
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  game element width
     * @param height game element height
     */
    public GameElementImpl(final float x, final float y, final int width, final int height) {
        this.location = new PairImpl<>(x, y);
        this.width = width;
        this.height = height;
        initHitbox();
    }

    private void initHitbox() {
        hitbox = new Rectangle(Math.round(this.location.getFirstElement()),
                Math.round(this.location.getSecondElement()), width, height);
    }

    /**
     * Updates the values of the hitbox.
     * 
     * @param x
     * @param y
     */
    protected void updateHitbox(final int x, final int y) {
        this.hitbox.x = (int) x;
        this.hitbox.y = (int) y;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressFBWarnings
    public Rectangle getHitbox() {

        return hitbox;
    }

    /** {@inheritDoc} */
    @Override
    public void updatePosition(final PairImpl<Integer, Integer> newPosition) {
        // this.location.setLocation(newPosition.getFirstElement(),
        // newPosition.getSecondElement());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressFBWarnings
    public PairImpl<Integer, Integer> getPosition() {
        return new PairImpl<Integer, Integer>(Math.round(location.getFirstElement()),
                Math.round(location.getSecondElement()));
    }

    /** {@inheritDoc} */
    @Override
    public BufferedImage getSprite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSprite'");
    }

    /** {@inheritDoc} */
    @Override
    public void updateSprite(final BufferedImage newSprite) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSprite'");
    }

    /**
     * Getter for the game elemnet width.
     * 
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter for the game elemnet height.
     * 
     * @return heigth
     */
    public int getHeight() {
        return height;
    }

}
