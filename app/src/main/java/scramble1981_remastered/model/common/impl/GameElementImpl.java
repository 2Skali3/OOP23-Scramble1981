package scramble1981_remastered.model.common.impl;

import java.awt.Polygon;
import java.awt.image.BufferedImage;

import scramble1981_remastered.model.common.api.GameElement;

public class GameElementImpl implements GameElement {

    private Point2DImpl location;
    private int width, height;
    private final Polygon hitBox = new Polygon();

    public GameElementImpl(int x, int y, int width, int height) {
        this.location = new Point2DImpl(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void updatePosition(Point2DImpl newPosition) {
        this.location.setLocation(newPosition.getX(), newPosition.getY());
    }

    @Override
    public Point2DImpl getPosition() {
        return this.location;
    }

    public void initHitbox() {

    }

    @Override
    public Polygon getHitBox() {
        return this.hitBox;
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

    public Point2DImpl getLocation() {
        return location;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
