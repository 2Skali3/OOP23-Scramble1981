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

class SpaceShipTest {

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
