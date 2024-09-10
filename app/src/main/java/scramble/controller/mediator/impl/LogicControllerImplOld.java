package scramble.controller.mediator.impl;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import scramble.controller.input.impl.InputControlImpl;
import scramble.controller.map.MapController;
import scramble.controller.mediator.api.LogicController;
import scramble.model.bullets.Bullet;
import scramble.model.bullets.BulletType;
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
public class LogicControllerImplOld implements LogicController {

    private int lives;
    private static List<PairImpl<Integer, Integer>> checkPoints = new ArrayList<>();
    private final SpaceShipPanel spaceShipPanel;
    private final GameView gameView;
    private final Timer collisionTimer;
    private final Timer fuelCheckTimer;
    // TODO implement me!
    // private final Timer keyPressedCheckTimer;

    /**
     * Class constructor.
     *
     * @param gameView the calling class
     */
    public LogicControllerImplOld(final GameView gameView) {
        this.lives = Constants.MAX_LIVES;
        this.gameView = new GameView(gameView);

        this.spaceShipPanel = gameView.getSpaceshipPanel();

        addCheckPoints();
        fuelCheckTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                finishedFuel(gameView.getHudPanel().getFuelBar());
            }
        });

        collisionTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                touchedGround();
                checkBulletCollisions();
            }
        });

        // TODO implement me!
        // keyPressedCheckTimer = new Timer(16, new ActionListener() {
        // @Override
        // public void actionPerformed(final ActionEvent e) {
        // checkKeyPressed();
        // }
        // });

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
    private void addCheckPoints() {
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
        this.lives = Constants.MAX_LIVES;
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

    /**
     * Getter for the list containing the checkpoint.
     * 
     * @return the checkpoint list
     */
    public static List<PairImpl<Integer, Integer>> getCheckPoints() {
        return new ArrayList<>(checkPoints);
    }

    /** {@inheritDoc} */
    @Override
    public void touchedGround() {
        if (spaceShipPanel.getSpaceship().checkGroundCollision(gameView.getLandscapePanel().getColumns())) {
            stopCollisionTimer();
            stopFuelCheckTimer();
            InputControlImpl.setPaused(true);
            spaceShipPanel.stopTimer();
            final Timer delayTimer = new Timer(3500, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    if (isGameOver()) {
                        gameView.getHudPanel().stopTimer();
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

    private void checkBulletCollisions() {
        final var bullets = gameView.getBulletsPanel();
        final List<Bullet> bulletsToRemove = new ArrayList<>();
        for (final Bullet bullet : bullets.getBullets()) {
            if (bullet.getType() == BulletType.TYPE_BOMB
                    && bullet.checkGroundCollision(gameView.getLandscapePanel().getColumns())) {
                bulletsToRemove.add(bullet);
                bullet.setHit(true);
                // System.out.println("Bullet hit set to true.");
            }
        }
        bullets.removeBullets(bulletsToRemove);
    }

    /** {@inheritDoc} */
    @Override
    public void finishedFuel(final FuelBar fuelBar) {
        if (fuelBar.checkFuelZero()) {
            stopFuelCheckTimer();
            stopCollisionTimer();
            InputControlImpl.setPaused(true);
            spaceShipPanel.stopTimer();
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

	@Override
	public void touchedEnemy() {
		if (spaceShipPanel.getSpaceship().checkEnemyCollision(rocketPanel.getRockets())) {
            //iteraction();
        }
	}

}
