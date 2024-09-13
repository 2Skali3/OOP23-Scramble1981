package scramble.bullets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import scramble.model.bullets.Bullet;
import scramble.model.bullets.BulletType;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElement;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.LandPart;
import scramble.model.map.util.enums.TerrainType;
import scramble.utility.Constants;

class BulletTest {
    private static final int X_MAP = -20;
    private static final int X_BULLET = 50;
    private static final int Y_BULLET = 60;

    @Test
    void testBulletMovementBasedOnType() {
        // Setup
        final int x = X_BULLET;
        final int y = Y_BULLET;
        BulletType type = BulletType.TYPE_HORIZONTAL;
        Bullet bullet = new Bullet(x, y, type);

        // Action
        bullet.moveByType();

        // Assertion
        assertEquals(x + Constants.XSPEED_HORIZONTAL_BULLET, bullet.getPosition().getFirstElement());
        assertEquals(y, bullet.getPosition().getSecondElement());

        type = BulletType.TYPE_BOMB;
        bullet = new Bullet(x, y, type);

        // Action
        bullet.moveByType();

        // Assertion
        assertEquals(x + Constants.XSPEED_BOMB, bullet.getPosition().getFirstElement());
        assertEquals(y + Constants.YSPEED_BOMB, bullet.getPosition().getSecondElement());
    }

    @Test
    void testBulletCollisionWithMap() {
        // Setup
        final List<MapElement> map = new ArrayList<>();
        // final TerrainType terrainType, final LandBehaviour behaviour
        final MapElement mapElement = new MapElement(X_MAP, 0, LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                LandUtils.PIXEL_PER_LAND_SPRITE_SIDE, LandUtils.getSprite(LandPart.TOP_FLAT_FLOOR),
                TerrainType.GREENLAND, LandBehaviour.FLAT);
        map.add(mapElement);

        final Bullet bullet = new Bullet(0, 0, BulletType.TYPE_HORIZONTAL);

        // Assertion
        assertFalse(bullet.checkGroundCollision(map));

        // Trigger collision
        bullet.updatePosition(new PairImpl<>(mapElement.getPosition().getFirstElement(),
                mapElement.getPosition().getSecondElement()));
        assertTrue(bullet.checkGroundCollision(map));
    }

    @Test
    void testExplosionSpriteRsetrieval() {
        final Bullet bullet = new Bullet(0, 0, BulletType.TYPE_BOMB);
        final BufferedImage explosionSprite = bullet.getExpSprite();
        assertNotNull(explosionSprite);
    }

}
