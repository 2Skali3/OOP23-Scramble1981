package scramble1981_remastered.model.common.impl;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import scramble1981_remastered.model.common.api.GameElement;

public class GameElementImpl implements GameElement {

    private Point2DImpl location = new Point2DImpl();

    private int width, hight;

    public void GameElement(int x, int y, int width, int hight) {
        this.location.setLocation(x, y);
        this.width = width;
        this.hight = hight;
    }

    @Override
    public void updatePosition(Point2DImpl newPosition) {
        this.location.setLocation(newPosition.getX(), newPosition.getY());
    }

    @Override
    public Point2DImpl getPosition() {
        return this.location;
    }

    @Override
    public Polygon getHitBox() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSprite'");
    }

    @Override
    public BufferedImage getSprite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSprite'");
    }

    @Override
    public void updateSprite(BufferedImage newSprite) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSprite'");
    }

}
