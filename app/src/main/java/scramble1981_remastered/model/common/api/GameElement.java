package scramble1981_remastered.model.common.api;

import java.awt.image.BufferedImage;
import java.awt.Polygon;

public interface GameElement {

    void updatePosition(Position2D newPosition);

    Position2D getPosition();

    Polygon getHitBox();

    BufferedImage getSprite();

    void updateSprite(BufferedImage newSprite);

}
/*
 * loop[
 * 
 * check next
 * 
 * pos (ora, verso) -> new cord
 * 
 * newcord ->
 * 
 * update pos (setta le cord)
 * ]
 * 
 * 
 * Stati:
 * 
 * - aggiornare posizioni tutti obj a schermo
 */