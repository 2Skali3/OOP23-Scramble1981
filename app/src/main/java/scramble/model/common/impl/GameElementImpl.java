package scramble.model.common.impl;

import scramble.model.common.api.GameElement;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

/**
 * Implementation of the GameElement interface. Sets the ground for all game
 * objects development.
 *
 * @see GameElement
 */
public abstract class GameElementImpl extends HitBoxImpl implements GameElement {

    private final int width, height;
    private final PairImpl<Integer, Integer> position;

    /**
     * Class constructor.
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  game element width
     * @param height game element height
     */
    public GameElementImpl(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.position = new PairImpl<>(x, y);
        this.width = width;
        this.height = height;
    }

    /** {@inheritDoc} */
    @Override
    public void updatePosition(final PairImpl<Integer, Integer> newPosition) {
        this.position.setPair(newPosition);
        updateHitBox(newPosition.getFirstElement(), newPosition.getSecondElement());
    }

    /** {@inheritDoc} */
    @Override
    public PairImpl<Integer, Integer> getPosition() {
        return new PairImpl<Integer, Integer>(position.getFirstElement(), position.getSecondElement());
    }

    /** {@inheritDoc} */
    @Override
    public abstract BufferedImage getSprite();

    /**
     * Getter for the game element width.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter for the game element height.
     *
     * @return heigth
     */
    public int getHeight() {
        return height;
    }

    /**
     * Draws the spaceship hitbox.
     * 
     * @param g the graphic component
     */
    public void drawHitBox(final Graphics g) {
        g.drawRect(getHitBox().x, getHitBox().y, getHitBox().width, getHitBox().height);
    }
}
