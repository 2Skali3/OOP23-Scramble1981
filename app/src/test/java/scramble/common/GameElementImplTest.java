package scramble.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import scramble.model.bullets.Bullet;
import scramble.model.bullets.BulletType;
import scramble.model.common.impl.GameElementImpl;
import scramble.model.common.impl.PairImpl;
import scramble.model.enemy.RocketImpl;
import scramble.model.spaceship.SpaceShip;
import scramble.model.tank.FuelTank;

import java.awt.image.BufferedImage;

class GameElementImplTest {

    private static final int UPDATED_X_POSITION = 50;
    private static final int UPDATED_Y_POSITION = 60;

    @Test
    void testConstructorInitializesCorrectly() {
        final int x = 10;
        final int y = 20;
        final int width = 30;
        final int height = 40;
        final GameElementImpl gameElement = new GameElementImpl(x, y, width, height) {
            @Override
            public BufferedImage getSprite() {
                return null;
            }
        };
        assertEquals(width, gameElement.getWidth());
        assertEquals(height, gameElement.getHeight());
        assertEquals(x, gameElement.getPosition().getFirstElement());
        assertEquals(y, gameElement.getPosition().getSecondElement());
    }

    @Test
    void testUpdatePositionUpdatesCorrectly() {
        // Setup
        final int x = 10;
        final int y = 20;
        final int width = 30;
        final int height = 40;
        final GameElementImpl gameElement = new GameElementImpl(x, y, width, height) {
            @Override
            public BufferedImage getSprite() {
                return null;
            }
        };

        // Execute
        final PairImpl<Integer, Integer> newPosition = new PairImpl<>(UPDATED_X_POSITION, UPDATED_Y_POSITION);
        gameElement.updatePosition(newPosition);

        // Verify
        assertEquals(newPosition.getFirstElement(), gameElement.getPosition().getFirstElement());
        assertEquals(newPosition.getSecondElement(), gameElement.getPosition().getSecondElement());
    }

    @Test
    void testGetSpriteImplementedInSubclasses() {

        final GameElementImpl spaceShip = new SpaceShip(0, 0, 100, 100);
        final GameElementImpl bullet = new Bullet(0, 0, BulletType.TYPE_BOMB);
        final GameElementImpl fuelTank = new FuelTank(0, 0, 100, 100);
        final GameElementImpl rocket = new RocketImpl(0, 0, 100, 100);
        assertNotNull(spaceShip.getSprite());
        assertNotNull(bullet.getSprite());
        assertNotNull(fuelTank.getSprite());
        assertNotNull(rocket.getSprite());

    }

}
