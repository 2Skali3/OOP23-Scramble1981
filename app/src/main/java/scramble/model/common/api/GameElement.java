package scramble.model.common.api;

import java.awt.image.BufferedImage;

import scramble.model.common.impl.HitBox;
import scramble.model.common.impl.Point2DImpl;

/**
 * Key interface that acts as base for every interactable object in the game.
 * That includes the player, enemies and fuel.
 */
public interface GameElement {

    /**
     * Updates game element position.
     * 
     * @param newPosition the new position
     */
    void updatePosition(Point2DImpl newPosition);

    /**
     * Gets the current game element position.
     * 
     * @return current position
     */
    Point2DImpl getPosition();

    /**
     * Gets the game element hit box.
     * 
     * @return game element hit box
     */
    HitBox getHitBox();

    /**
     * Getter for a game element single sprite.
     * 
     * @return the game element image
     */
    BufferedImage getSprite();

    /**
     * Updates the game element sprite.
     * 
     * @param newSprite the new image
     */
    void updateSprite(BufferedImage newSprite);

}
