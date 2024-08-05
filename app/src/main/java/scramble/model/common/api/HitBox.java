package scramble.model.common.api;

import java.awt.Rectangle;

/**
 * Interface for the class HitBox.
 */
public interface HitBox {

    /**
     * Updates the location of the rectangle used as hitbox.
     * Uses the centre of the rectangle as a pivot.
     * 
     * @return boolean hasCollided
     */
    boolean hasCollided();

    /**
     * Updates the location of the rectangle used as hitbox.
     * Uses the centre of the rectangle as a pivot.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    void updateHitBox(int x, int y);

    /**
     * Getter for the rectangle used as hitbox.
     * 
     * @return the hitbox
     */
    Rectangle getHitBox();
}
