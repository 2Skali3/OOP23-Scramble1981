package scramble.model.common.impl;

import scramble.model.common.api.Point2D;

/**
 * Implementation of the Point2D interface.
 * Used for recognising the game objects position on the screen.
 */
public class Point2DImpl implements Point2D {

    private int x, y;

    /**
     * Class constructor.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Point2DImpl(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /** {@inheritDoc} */
    @Override
    public int getX() {
        return this.x;
    }

    /** {@inheritDoc} */
    @Override
    public int getY() {
        return this.y;
    }

    /** {@inheritDoc} */
    @Override
    public void setLocation(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the game element position on the screen.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void updateLocation(final int x, final int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
