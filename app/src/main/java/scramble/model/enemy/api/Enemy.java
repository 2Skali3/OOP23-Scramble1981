package scramble.model.enemy.api;

import scramble.model.common.api.GameElement;

/**
 * Interface common to every NPC in-game. Describes the enemy common logic.
 */
public interface Enemy extends GameElement{

    /**
     * Method to initialise the movement pattern of the enemy.
     */
    void startMovement();
}
