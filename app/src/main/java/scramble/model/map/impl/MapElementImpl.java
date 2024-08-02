package scramble.model.map.impl;

import java.awt.image.BufferedImage;

import scramble.model.map.api.MapElement;

/**
 * Implementation of the interface MapElement.
 */
public class MapElementImpl implements MapElement {

    private int height;
    private BufferedImage sprite;

    /**
     * Constructor of the class MapElementImpl.
     * @param height the height of the MapElement
     * @param sprite the sprite (BufferedImage) of the MapElementImpl
     */
    public MapElementImpl(final int height, final BufferedImage sprite) {
        this.height = height;
        this.sprite = sprite;
    }
    /**
     * @inheritDoc
     */
    @Override
    public int getHeight() {
        return this.height;
    }
    /**
     * @inheritDoc
     */
    @Override
    public BufferedImage getSprite() {
        return this.sprite;
    }
}