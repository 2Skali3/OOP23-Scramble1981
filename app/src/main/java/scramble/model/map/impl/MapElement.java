package scramble.model.map.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import scramble.model.common.api.GameElement;
import scramble.model.common.api.HitBox;
import scramble.model.common.api.Pair;
import scramble.model.common.impl.HitBoxImpl;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.util.enums.TerrainType;

/**
 * Class {@code MapElement} is an implementation of the interface
 * {@link GameElement}.
 * 
 * <p>
 * A {@code MapElement} has:
 * <ul>
 * <li>space coordinate</li>
 * <li>a width and an height</li>
 * <li>an hitBox</li>
 * <li>a sprite</li>
 * </ul>
 * </p>
 * 
 * <p>
 * All the method of the {@link GameElement} interface has been implemented.
 * </p>
 * 
 * @see GameElement
 */
public class MapElement extends HitBoxImpl implements GameElement {

    private Pair<Integer, Integer> position;
    private final int width;
    private final int height;
    private BufferedImage sprite;
    private TerrainType terrainType;

    /**
     * Constructor of the class {@code MapElement}.
     * 
     * @param x      coordinate in the x-axis
     * @param y      coordinate in the y-axis
     * @param width  in the space
     * @param height in the space
     * @param sprite of the element
     */
    public MapElement(final int x, final int y, final int width, final int height, final BufferedImage sprite, final TerrainType terrainType) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
        this.position = new PairImpl<>(x, y);
        this.sprite = this.cloneBufferedImage(sprite);

    }

    /** {@inheritDoc} */
    @Override
    public void updatePosition(final PairImpl<Integer, Integer> newPosition) {
        this.position = new PairImpl<>(newPosition.getFirstElement(), newPosition.getSecondElement());
    }

    /** {@inheritDoc} */
    @Override
    public PairImpl<Integer, Integer> getPosition() {
        return new PairImpl<Integer, Integer>(this.position.getFirstElement(), this.position.getSecondElement());
    }

    public TerrainType getTerrainType() {
        return this.terrainType;
    }

    /**
     * Get the position of the {@code MapElement} in the x-axis.
     * 
     * @return the position of the {@code MapElement} in the x-axis
     */
    public int getX() {
        return this.position.getFirstElement();
    }

    /**
     * Get the position of the {@code MapElement} in the y-axis.
     * 
     * @return the position of the {@code MapElement} in the y-axis
     */
    public int getY() {
        return this.position.getSecondElement();
    }

    /** {@inheritDoc} */
    @Override
    public BufferedImage getSprite() {
        return this.cloneBufferedImage(this.sprite);
    }

    private BufferedImage cloneBufferedImage(final BufferedImage sourceBufferedImage) {
        final BufferedImage clone = new BufferedImage(sourceBufferedImage.getWidth(), sourceBufferedImage.getHeight(),
                sourceBufferedImage.getType());
        final Graphics2D g2d = clone.createGraphics();
        g2d.drawImage(sourceBufferedImage, 0, 0, null);
        g2d.dispose();
        return clone;
    }

    /**
     * Get the height of the {@code MapElement}.
     * 
     * @return the height of the {@code MapElement}
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Get the width of the {@code MapElement}.
     * 
     * @return the width of the {@code MapElement}
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Update the {@link HitBox} of the {@code MapElement}, and specify the
     * coordinate of it.
     * 
     * @param x coordinate in the x-axis
     * @param y coordinate in the y-axis
     */
    public void updateHitBoxPosition(final int x, final int y) {
        this.updateHitBox(x, y);
    }
}
