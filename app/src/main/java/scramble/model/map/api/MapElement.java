package scramble.model.map.api;

import java.awt.image.BufferedImage;

/**
 * Interface for a Map element that contains an height in the space and a sprite
 * (BufferedImage).
 */
public interface MapElement {
    /**
     * Getter for the Height of the MapElement.
     * 
     * @return the height of the MapElement
     */
    int getHeight();

    /**
     * Getter that return the sprite of the MapElement.
     * 
     * @return the sprite of the MapElement
     */
    BufferedImage getSprite();
}
