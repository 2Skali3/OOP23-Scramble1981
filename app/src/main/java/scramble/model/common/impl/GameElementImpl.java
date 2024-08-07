package scramble.model.common.impl;

import java.awt.image.BufferedImage;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import scramble.model.common.api.GameElement;

/**
 * Implementation of the GameElement interface. Sets the ground for all game
 * objects development.
 *
 * @see GameElement
 */
public class GameElementImpl extends HitBoxImpl implements GameElement {

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
    @SuppressFBWarnings
    public PairImpl<Integer, Integer> getPosition() {
        return this.position;
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

}
