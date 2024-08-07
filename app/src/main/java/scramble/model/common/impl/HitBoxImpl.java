package scramble.model.common.impl;

import scramble.model.common.api.HitBox;
import java.awt.Rectangle;

/**
 * Implementation of the interface HitBox.
 * 
 * @see HitBox
 */
public class HitBoxImpl implements HitBox {

    private final Rectangle hitbox;

    /**
     * Constructor for the class HitboxImpl.
     * 
     * @param x      is the position of the hitbox on the x axis
     * @param y      is the position of the hitbox on the y axis
     * @param width  is the width of the hitbox
     * @param height is the height of the hitbox
     */
    public HitBoxImpl(final int x, final int y, final int width, final int height) {
        this.hitbox = new Rectangle(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCollided(final GameElementImpl obj) {
        return hitbox.intersects(obj.getHitBox());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHitBox(final int x, final int y) {
        hitbox.setLocation(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getHitBox() {
        return new Rectangle(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
}
