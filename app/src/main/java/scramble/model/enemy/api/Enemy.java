package scramble.model.enemy.api;

/**
 * Interface common to every NPC in-game. Describes the enemy common logic.
 */
public interface Enemy {

    /**
     * Method to initialise the movement pattern of the enemy.
     */
    void startMovement();
}
