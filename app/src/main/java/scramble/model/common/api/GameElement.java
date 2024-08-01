package scramble.model.common.api;

import java.awt.image.BufferedImage;

import scramble.model.common.impl.Point2DImpl;

import java.awt.Polygon;

public interface GameElement {

    void updatePosition(Point2DImpl newPosition);

    Point2DImpl getPosition();

    Polygon getHitBox();

    BufferedImage getSprite();

    void updateSprite(BufferedImage newSprite);

}
