package scramble.model.common.api;

import java.awt.image.BufferedImage;

import scramble.model.common.impl.PairImpl;

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
    void updatePosition(PairImpl<Integer, Integer> newPosition);

    /**
     * Gets the current game element position.
     *
     * @return current position
     */
    PairImpl<Integer, Integer> getPosition();

    /**
     * Getter for a game element single sprite.
     *
     * @return the game element image
     */
    BufferedImage getSprite();

}
