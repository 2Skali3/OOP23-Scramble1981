package scramble1981_remastered.model.common.impl;

import java.awt.geom.Point2D;

public class Point2DImpl implements scramble1981_remastered.model.common.api.Point2D {

    private int x, y;

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

}
