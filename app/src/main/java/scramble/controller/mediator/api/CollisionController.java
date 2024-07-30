package scramble.controller.mediator.api;

import scramble.model.common.api.GameElement;

/**
 * Interface for handling game objects collisions.
 */
public interface CollisionController {

    /**
     * Check if a collision is happenning between two game objects.
     * 
     * @param e1 the first element
     * @param e2 the other one
     * 
     * @return has collided or not
     */
    boolean isColliding(GameElement e1, GameElement e2);
}
