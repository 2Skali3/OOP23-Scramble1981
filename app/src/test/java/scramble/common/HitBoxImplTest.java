package scramble.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import scramble.model.common.impl.HitBoxImpl;
import scramble.utility.Constants;

import java.awt.Rectangle;

class HitBoxImplTest {

    @Test
    void testHasCollidedReturnsTrueWhenIntersect() {
        // Create two hitboxes with intersecting rectangles
        final HitBoxImpl hitBox1 = new HitBoxImpl(0, 0, Constants.SPACESHIP_STARTER_POSITION,
                Constants.SPACESHIP_STARTER_POSITION);
        final HitBoxImpl hitBox2 = new HitBoxImpl(Constants.SPACESHIP_STARTER_POSITION / 2,
                Constants.SPACESHIP_STARTER_POSITION / 2, Constants.SPACESHIP_STARTER_POSITION,
                Constants.SPACESHIP_STARTER_POSITION);

        // Check if the hasCollided method returns true
        assertTrue(hitBox1.hasCollided(hitBox2));
    }

    @Test
    void testUpdateHitboxUpdatesPositionCorrectly() {
        // Given
        final int initialX = 10;
        final int initialY = 20;
        final int width = 30;
        final int height = 40;
        final HitBoxImpl hitBox = new HitBoxImpl(initialX, initialY, width, height);

        // When
        final int newX = 50;
        final int newY = 60;
        hitBox.updateHitBox(newX, newY);

        // Then
        final Rectangle expected = new Rectangle(newX, newY, width, height);
        assertEquals(expected, hitBox.getHitBox());
    }

    @Test
    void testGetHitBoxReturnsSameDimensionsAndPosition() {
        // Given
        final int x = 10;
        final int y = 20;
        final int width = 30;
        final int height = 40;
        final HitBoxImpl hitBox = new HitBoxImpl(x, y, width, height);

        // When
        final Rectangle result = hitBox.getHitBox();

        // Then
        final Rectangle expected = new Rectangle(x, y, width, height);
        assertEquals(expected, result);
    }
}
