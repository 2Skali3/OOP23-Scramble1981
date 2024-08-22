package scramble.model.enemy.impl;

import java.awt.image.BufferedImage;

import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;
import scramble.model.enemy.api.Enemy;

/**
 * Implementation of the Enemy interface. Used for the simple rocket NPC.
 */
public class RocketImpl extends GameElementImpl implements Enemy {

    /**
     * Class constructor.
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  Rocket width
     * @param height Rocket height
     */
    public RocketImpl(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
    }

    /** {@inheritDoc} */
    @Override
    public void startMovement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startMovment'");
    }

    @Override
    public void updatePosition(PairImpl<Integer, Integer> newPosition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePosition'");
    }

    @Override
    public PairImpl<Integer, Integer> getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public BufferedImage getSprite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSprite'");
    }

}
