package scramble1981_remastered.model.common.impl;

import scramble1981_remastered.model.common.api.Point2D;

public class Point2DImpl implements Point2D {

    private int x, y;

    public Point2DImpl(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updateLocation(int x, int y) {
        this.x += x;
        this.y += y;
    }

}
