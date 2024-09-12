package scramble.spaceship;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import scramble.model.enemy.RocketImpl;
import scramble.model.spaceship.SpaceShip;
import scramble.utility.Constants;

/**
 * Test for {@link SpaceShip} class.
 */
class SpaceShipTest {

    /**
     * Test for the {@link SpaceShip} sprites to see if are all loaded correctly.
     */
    @Test
    void testLoadAllSpritesWithoutErrors() {
        final SpaceShip spaceShip = new SpaceShip(0, 0, Constants.SPACESHIP_WIDTH, Constants.SPACESHIP_HEIGHT);

        assertNotNull(spaceShip);
        assertEquals(0, spaceShip.getxSpeed());
        assertEquals(0, spaceShip.getySpeed());
        assertFalse(spaceShip.isHit());
        assertFalse(spaceShip.checkGroundCollision(new ArrayList<>()));
        assertFalse(spaceShip.checkEnemyCollision(new ArrayList<>()));
    }

    /**
     * Test for the {@link SpaceShip} class to see if the collision between the
     * {@link RocketImpl} are currectly detected.
     */
    @Test
    void testDetectsEnemyCollisionAccurately() {
        // Create a SpaceShip instance
        final SpaceShip spaceShip = new SpaceShip(0, 0, Constants.SPACESHIP_WIDTH, Constants.SPACESHIP_HEIGHT);

        // Create a list of RocketImpl instances (mocked)
        final List<RocketImpl> rockets = new ArrayList<>();
        final RocketImpl rocket1 = new RocketImpl(0, 0, Constants.ROCKET_WIDTH, Constants.ROCKET_HEIGHT);
        final RocketImpl rocket2 = new RocketImpl(100, 0, Constants.ROCKET_WIDTH, Constants.ROCKET_HEIGHT);
        rockets.add(rocket1);
        rockets.add(rocket2);

        assertTrue(rocket1.hasCollided(spaceShip));
        assertFalse(rocket2.hasCollided(spaceShip));

        // Test the checkEnemyCollision method
        assertTrue(spaceShip.checkEnemyCollision(rockets));
        assertTrue(spaceShip.isHit());
    }

    /**
     * Test for the movment of the {@link SpaceShip} based on the key pressed.
     */
    @Test
    void testSpaceshipMovementBasedOnDirection() {
        final SpaceShip spaceShip = new SpaceShip(0, 0, Constants.SPACESHIP_WIDTH, Constants.SPACESHIP_HEIGHT);

        spaceShip.setLeft(true);
        spaceShip.move();
        assertEquals(-Constants.SPACESHIP_SPEED, spaceShip.getxSpeed());
        assertEquals(0, spaceShip.getySpeed());

        spaceShip.setLeft(false);
        spaceShip.setRight(true);
        spaceShip.move();
        assertEquals(Constants.SPACESHIP_SPEED, spaceShip.getxSpeed());
        assertEquals(0, spaceShip.getySpeed());

        spaceShip.setRight(false);
        spaceShip.setAbove(true);
        spaceShip.move();
        assertEquals(0, spaceShip.getxSpeed());
        assertEquals(-Constants.SPACESHIP_SPEED, spaceShip.getySpeed());

        spaceShip.setAbove(false);
        spaceShip.setDown(true);
        spaceShip.move();
        assertEquals(0, spaceShip.getxSpeed());
        assertEquals(Constants.SPACESHIP_SPEED, spaceShip.getySpeed());

        spaceShip.setDown(false);
        spaceShip.setLeft(true);
        spaceShip.setAbove(true);
        spaceShip.move();
        assertEquals(-1, spaceShip.getxSpeed());
        assertEquals(-1, spaceShip.getySpeed());
    }
}
