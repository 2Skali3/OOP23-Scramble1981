package scramble.controller.gameloop;

import scramble.controller.map.MapController;
import scramble.model.common.impl.PairImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the game loop.
 * It manages the checkpoints and the player lives.
 */
public class GameLoopController {

    private static final int MAX_STAGES = 1;

    private static final int Y_POSITION = 50;

    private int lives;
    private final List<PairImpl<Integer, Integer>> checkPoints;

    /**
     * Class constructor.
     * 
     * @param lives number of lives, added for deep copy
     */
    public GameLoopController(final int lives) {
        this.lives = lives;
        this.checkPoints = new ArrayList<>();
    }

    /** Decrement lives counter. */
    public void lostLife() {
        this.lives--;
    }

    /**
     * Checks if game is over.
     * 
     * @return true if lives are over
     */
    public boolean isGameOver() {
        return this.lives == 0;
    }

    /** Adds checkpoints. */
    public void addCheckPoints() {
        for (int i = 0; i < MAX_STAGES + 1; i++) {
            checkPoints.add(new PairImpl<Integer, Integer>(MapController.getStageStartingX().get(i), Y_POSITION));
        }
    }

    /**
     * Getter for lives.
     * 
     * @return number of lives remaining
     */
    public int getLives() {
        return this.lives;
    }

    /** Sets lives to MAX_LIVES in case game starts anew. */
    public void resetLives() {
        this.lives = 2;
    }
}
