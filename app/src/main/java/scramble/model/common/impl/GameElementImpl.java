package scramble.model.common.impl;

import java.awt.image.BufferedImage;

import scramble.model.common.api.GameElement;

/**
 * Implementation of the GameElemnt interface.
 * Sets the ground for all game objects development.
 */
public class GameElementImpl implements GameElement {

    private final Point2DImpl location;
    private final int width, height;
    private final HitBox hitBox;

    /**
     * Class constructor.
     * 
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  game element width
     * @param height game element height
     */
    public GameElementImpl(final int x, final int y, final int width, final int height) {
        this.location = new Point2DImpl(x, y);
        this.hitBox = new HitBox();
        this.width = width;
        this.height = height;
    }

    /** {@inheritDoc} */
    @Override
    public void updatePosition(final Point2DImpl newPosition) {
        this.location.setLocation(newPosition.getX(), newPosition.getY());
    }

    /** {@inheritDoc} */
    @Override
    public Point2DImpl getPosition() {
        /*
         * try {
         * return (Point2DImpl) location.clone();
         * } catch (CloneNotSupportedException e) {
         * return new Point2DImpl(0, 0);
         * }
         */
        return location;
    }

    /**
     * Method for the hit box initialisation.
     */
    public void initHitbox() {

    }

    /** {@inheritDoc} */
    @Override
    public HitBox getHitBox() {
        try {
            return (HitBox) hitBox.clone();
        } catch (CloneNotSupportedException e) {
            return new HitBox();
        }
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
