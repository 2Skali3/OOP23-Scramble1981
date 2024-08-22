package scramble.controller.gameloop;

import scramble.controller.input.impl.InputControlImpl;
import scramble.controller.map.MapController;
import scramble.model.common.impl.PairImpl;
import scramble.model.spaceship.FuelBar;
import scramble.view.compact.GameView;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;

/**
 * This class handles the game loop.
 * It manages the checkpoints and the player lives.
 */
public class GameLoopController {

    private static final int MAX_STAGES = 1;

    private static final int Y_POSITION = 50;

    private GameView gameView;
    private int lives;
    private final List<PairImpl<Integer, Integer>> checkPoints;
    private Timer fuelCheckTimer;

    /**
     * Class constructor.
     * 
     * @param lives number of lives, added for deep copy
     */
    public GameLoopController(final int lives, final GameView gameView) {
        this.lives = lives;
        this.gameView = gameView;
        this.checkPoints = new ArrayList<>();
        fuelCheckTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishedFuel(gameView.getFuelBarPanel().getFuelBar());
            }
        });
        fuelCheckTimer.start();
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

    public void finishedFuel(final FuelBar fuelBar) {
        if (fuelBar.checkFuelZero()) {
            fuelCheckTimer.stop();
            InputControlImpl.setPaused(true);
            gameView.getSpaceshipPanel().stopUpdateTimer();
            gameView.getSpaceshipPanel().getSpaceship().setHit(true);
            final Timer delayTimer = new Timer(3500, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    if (isGameOver()) {
                        gameView.setStart();
                    } else {
                        gameView.getGLoopController().lostLife();
                        gameView.restartFromCheckPoint(gameView.returnToCheckPoint());
                    }
                    fuelCheckTimer.start();
                    InputControlImpl.setPaused(false);
                    gameView.getSpaceshipPanel().getSpaceship().setHit(false);
                }
            });
            delayTimer.setRepeats(false); // Il timer deve eseguire l'azione solo una volta
            delayTimer.start();
        }
    }
}
