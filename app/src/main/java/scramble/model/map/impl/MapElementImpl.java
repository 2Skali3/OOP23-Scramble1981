package scramble.model.map.impl;

import java.awt.image.BufferedImage;

import java.awt.Graphics2D;

import scramble.model.map.api.MapElement;

/**
 * Implementation of the interface MapElement.
 */
public class MapElementImpl implements MapElement {

    private final int height;
    private final BufferedImage sprite;

    /**
     * Constructor of the class MapElementImpl.
     * 
     * @param height the height of the MapElement
     * @param sprite the sprite (BufferedImage) of the MapElementImpl
     */
    public MapElementImpl(final int height, final BufferedImage sprite) {
        this.height = height;
        this.sprite = colonBufferedImage(sprite);
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
        return this.colonBufferedImage(this.sprite);
    }

    private BufferedImage colonBufferedImage(final BufferedImage sourceBufferedImage) {
        final BufferedImage clone = new BufferedImage(sourceBufferedImage.getWidth(), sourceBufferedImage.getHeight(),
                sourceBufferedImage.getType());
        final Graphics2D g2d = clone.createGraphics();
        g2d.drawImage(sourceBufferedImage, 0, 0, null);
        g2d.dispose();
        return clone;
    }
}
