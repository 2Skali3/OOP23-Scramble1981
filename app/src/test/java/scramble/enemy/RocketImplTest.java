package scramble.enemy;

import scramble.model.enemy.RocketImpl;
import scramble.model.bullets.Bullet;
import scramble.utility.Constants;
import scramble.model.common.impl.PairImpl;
import scramble.model.bullets.BulletType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class RocketImplTest {
    private RocketImpl MakeRocketImpl(int x, int y, int width, int height) {
        return new RocketImpl(x, y, width, height);
    }

    @Test
    public void Move_RocketStateAndSpeed_UpdatesPosition() {
        RocketImpl rocket = MakeRocketImpl(100, 100, 10, 10);
        
        // Arrange: setting initial state and invoking the entry point
        rocket.turnOnMove();
        rocket.move();
        
        // Assert: state-based check
        PairImpl<Integer, Integer> position = rocket.getPosition();
        assertEquals(100 - Constants.LANDSCAPEX_SPEED, position.getFirstElement());
        assertEquals(100, position.getSecondElement());
    }

    @Test
    public void GetSprite_CyclesThroughSprites_ReturnsNextSprite() {
        RocketImpl rocket = MakeRocketImpl(100, 100, 10, 10);
        
        // Act: invoking the entry point
        BufferedImage sprite1 = rocket.getSprite();
        BufferedImage sprite2 = rocket.getSprite();
        
        // Assert: return-value check
        assertNotEquals(sprite1, sprite2);
    }

    @Test
    public void CheckCollisionBullet_WithCollidingBullets_ReturnsTrue() {
        RocketImpl rocket = MakeRocketImpl(100, 100, 10, 10);
        Set<Bullet> bullets = new HashSet<>();
        Bullet bullet = new Bullet(100, 100, BulletType.TYPE_BOMB); // Assuming Bullet has a constructor with these parameters
        bullets.add(bullet);
        
        // Act: invoking the entry point
        boolean result = rocket.checkCollisionBullet(bullets);
        
        // Assert: return-value check
        assertTrue(result);
    }
}