package scramble.enemy;

import scramble.model.enemy.Rocket;
import scramble.model.bullets.Bullet;
import scramble.utility.Constants;
import scramble.model.common.impl.PairImpl;
import scramble.model.bullets.BulletType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.awt.image.BufferedImage;

class RocketImplTest {

    private Rocket makeRocketImpl(final int x, final int y, final int width, final int height) {
        return new Rocket(x, y, width, height);
    }

    @Test
    void moveRocketStateAndSpeedUpdatesPosition() {
        final Rocket rocket = makeRocketImpl(100, 100, 10, 10);

        // Arrange: setting initial state and invoking the entry point
        rocket.turnOnMove();
        rocket.move();

        // Assert: state-based check
        final PairImpl<Integer, Integer> position = rocket.getPosition();
        assertEquals(100 - Constants.LANDSCAPEX_SPEED, position.getFirstElement());
        assertEquals(100, position.getSecondElement());
    }

    @Test
    void checkSpriteCyclesThroughSprites() {
        final Rocket rocket = makeRocketImpl(100, 100, 10, 10);

        // Act: invoking the entry point
        final BufferedImage sprite1 = rocket.getSprite();
        final BufferedImage sprite2 = rocket.getSprite();

        // Assert: return-value check
        assertNotEquals(sprite1, sprite2);
    }

    @Test
    void checkCollisionBulletWithCollidingRockets() {
        final Rocket rocket = makeRocketImpl(100, 100, 10, 10);
        final Bullet bullet = new Bullet(100, 100, BulletType.TYPE_BOMB); 

        // Act: invoking the entry point
        final boolean result = rocket.checkSingleBullet(bullet);

        // Assert: return-value check
        assertTrue(result);
    }
}
