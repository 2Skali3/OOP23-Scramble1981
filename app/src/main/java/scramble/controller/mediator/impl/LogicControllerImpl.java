package scramble.controller.mediator.impl;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import scramble.controller.input.impl.InputControlImpl;
import scramble.controller.map.MapController;
import scramble.controller.mediator.api.LogicController;
import scramble.model.common.impl.PairImpl;
import scramble.model.spaceship.FuelBar;
import scramble.view.compact.GameView;
import scramble.view.compact.SpaceShipPanel;
import scramble.utility.Constants;

/**
 * This class handles the Collision on the map.
 * It implements the interface designed and uses game view to handle
 * the game elements.
 */
public class LogicControllerImpl implements LogicController {

    private int lives;
    private final List<PairImpl<Integer, Integer>> checkPoints;
    private final SpaceShipPanel spaceShipPanel;
    private final GameView gameView;
    private final Timer collisionTimer;
    private final Timer fuelCheckTimer;

    /**
     * Class constructor.
     * 
     * @param gameView the calling class
     */
    public LogicControllerImpl(final GameView gameView) {
        this.lives = Constants.MAX_LIVES;
        this.gameView = new GameView(gameView);

        this.spaceShipPanel = gameView.getSpaceshipPanel();

        this.checkPoints = new ArrayList<>();
        fuelCheckTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                finishedFuel(gameView.getFuelBarPanel().getFuelBar());
            }
        });

        collisionTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                touchedGround();
            }
        });

        fuelCheckTimer.start();
        collisionTimer.start();
    }

    /** Decrement lives counter. */
    private void lostLife() {
        this.lives--;
    }

    /**
     * Checks if game is over.
     * 
     * @return true if lives are over
     */
    private boolean isGameOver() {
        return this.lives == 0;
    }

    /** Adds checkpoints. */
    public void addCheckPoints() {
        for (int i = 0; i < Constants.MAX_STAGES + 1; i++) {
            checkPoints.add(new PairImpl<Integer, Integer>(MapController.getStageStartingX().get(i), 
            Constants.CHECKPOINT_Y_POSITION));
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

    /** Starts collision timer. */
    public void startCollisionTimer() {
        collisionTimer.start();
    }

    /** Starts check fuel timer. */
    public void startFuelCheckTimer() {
        fuelCheckTimer.start();
    }

    /** Stops collision timer. */
    public void stopCollisionTimer() {
        collisionTimer.stop();
    }

    /** Stops check fuel timer. */
    public void stopFuelCheckTimer() {
        fuelCheckTimer.stop();
    }

    /** {@inheritDoc} */
    @Override
    public void touchedGround() {
        if (spaceShipPanel.getSpaceship().checkGroundCollision(gameView.getLandscapePanel().getColumns())) {
            stopCollisionTimer();
            stopFuelCheckTimer();
            InputControlImpl.setPaused(true);
            spaceShipPanel.stopUpdateTimer();
            final Timer delayTimer = new Timer(3500, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    if (isGameOver()) {
                        gameView.setStart();
                        resetLives();
                    } else {
                        lostLife();
                        gameView.restartFromCheckPoint(gameView.returnToCheckPoint());
                    }
                    startCollisionTimer();
                    startFuelCheckTimer();
                    InputControlImpl.setPaused(false);
                    spaceShipPanel.getSpaceship().setHit(false);
                }
            });
            delayTimer.setRepeats(false); // Il timer deve eseguire l'azione solo una volta
            delayTimer.start();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void finishedFuel(final FuelBar fuelBar) {
        if (fuelBar.checkFuelZero()) {
            stopFuelCheckTimer();
            stopCollisionTimer();
            InputControlImpl.setPaused(true);
            spaceShipPanel.stopUpdateTimer();
            spaceShipPanel.getSpaceship().setHit(true);
            final Timer delayTimer = new Timer(3500, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    if (isGameOver()) {
                        gameView.setStart();
                        resetLives();
                    } else {
                        lostLife();
                        gameView.restartFromCheckPoint(gameView.returnToCheckPoint());
                    }
                    startFuelCheckTimer();
                    startCollisionTimer();
                    InputControlImpl.setPaused(false);
                    spaceShipPanel.getSpaceship().setHit(false);
                }
            });
            delayTimer.setRepeats(false); // Il timer deve eseguire l'azione solo una volta
            delayTimer.start();
        }
    }

}
