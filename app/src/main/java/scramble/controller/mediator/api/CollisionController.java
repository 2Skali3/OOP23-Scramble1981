package scramble.controller.mediator.api;

/**
 * Interface for handling game objects collisions.
 */
public interface CollisionController {

    /**
     * Check if a collision is happenning between landscape and spaceship.
     */
    void touchedGround();

}
