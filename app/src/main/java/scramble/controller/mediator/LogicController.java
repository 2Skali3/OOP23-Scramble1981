package scramble.controller.mediator;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import scramble.controller.input.InputControl;
import scramble.controller.map.MapController;
import scramble.model.bullets.Bullet;
import scramble.model.bullets.BulletType;
import scramble.model.common.impl.PairImpl;
import scramble.model.enemy.Boss;
import scramble.model.enemy.Rocket;
import scramble.model.spaceship.FuelBar;
import scramble.model.tank.FuelTank;
import scramble.view.compact.FuelTankPanel;
import scramble.view.compact.GameView;
import scramble.view.compact.RocketPanel;
import scramble.view.compact.SpaceShipPanel;
import scramble.utility.Constants;

/**
 * This class handles the Collision on the map.
 * It implements the interface designed and uses game view to handle
 * the game elements.
 */
public class LogicController {

    private static int lives;
    private static List<PairImpl<Integer, Integer>> checkPoints = new ArrayList<>();
    private final SpaceShipPanel spaceShipPanel;
    private final RocketPanel rocketPanel;
    private final FuelTankPanel fuelTankPanel;
    private final GameView gameView;
    private final Timer collisionTimer;
    private final Timer fuelCheckTimer;

    /**
     * Class constructor.
     *
     * @param gameView the calling class
     */
    public LogicController(final GameView gameView) {
        resetLives();
        this.gameView = new GameView(gameView);

        this.spaceShipPanel = this.gameView.getSpaceshipPanel();
        this.rocketPanel = this.gameView.getRocketPanel();
        this.fuelTankPanel = this.gameView.getFuelTankPanel();
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
                checkHorizontalBulletCollisions();
                checkBombBulletCollisions();
                touchedEnemy();
                checkEnemyCeilingCollision();

            }
        });

        fuelCheckTimer.start();
        collisionTimer.start();
    }

    /**
     * Getter for lives.
     *
     * @return number of lives remaining
     */
    public static int getLives() {
        return lives;
    }

    /** Sets lives to MAX_LIVES in case game starts anew. */
    public static void resetLives() {
        lives = Constants.MAX_LIVES;
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

    /* Check if a collision is happenning between landscape and spaceship. */
    private void touchedGround() {
        if (spaceShipPanel.getSpaceship().checkGroundCollision(gameView.getLandscapePanel().getColumns())) {
            iteraction();
        }
    }

    /* Check if a collision is happening between enemy and spaceship */
    private void touchedEnemy() {
        if (spaceShipPanel.getSpaceship().checkEnemyCollision(rocketPanel.getRockets())) {
            iteraction();
        }
    }

    /**
     * Behaviour for case when fuel is depleted.
     * 
     * @param fuelBar the fuel bar.
     */
    private void finishedFuel(final FuelBar fuelBar) {
        if (fuelBar.checkFuelZero()) {
            iteraction();
        }
    }

    private void timerLogic() {
        gameView.stopAllPanelTimers();
        InputControl.setPaused(true);
        final Timer delayTimer = new Timer(3500, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                startFuelCheckTimer();
                startCollisionTimer();
                InputControl.setPaused(false);
                spaceShipPanel.getSpaceship().setHit(false);
                if (isGameOver()) {
                    gameView.setStart();
                    resetLives();
                } else {
                    gameView.restartAllPanelTimers();
                    lostLife();
                    gameView.restartFromCheckPoint(gameView.returnToCheckPoint());
                }
                gameView.getRocketPanel().setMapX(gameView.getLandscapePanel().getCurrentMapX());
                gameView.getRocketPanel().resetRockets();
                gameView.getFuelTankPanel().setMapX(gameView.getLandscapePanel().getCurrentMapX());
                gameView.getFuelTankPanel().resetTanks();
            }
        });
        delayTimer.setRepeats(false);
        delayTimer.start();
    }

    /** Decrement lives counter. */
    private void lostLife() {
        lives--;
    }

    /**
     * Checks if game is over.
     *
     * @return true if lives are over
     */
    private boolean isGameOver() {
        return lives == 0;
    }

    /** Adds checkpoints. */
    private void addCheckPoints() {
        for (int i = 0; i < Constants.MAX_STAGES + 1; i++) {
            checkPoints.add(new PairImpl<>(MapController.getStageStartingX().get(i),
                    Constants.CHECKPOINT_Y_POSITION));
        }
    }

    private void checkHorizontalBulletCollisions() {
        final var bullets = gameView.getBulletsPanel();
        final List<Bullet> bulletsToRemove = bullets.getBullets()
                .stream()
                .filter(bullet -> bullet.getType() == BulletType.TYPE_HORIZONTAL
                        && (bullet.checkGroundCollision(gameView.getLandscapePanel().getColumns())
                                || checkBulletEnemyCollision(bullet) || checkBulletTankCollision()))
                .toList();
        bullets.removeBullets(bulletsToRemove);
    }

    private boolean checkBulletEnemyCollision(final Bullet bullet) {
        for (final Rocket rocket : rocketPanel.getRockets()) {
            if (rocket.checkSingleBullet(bullet)) {
                rocket.setHit(true);
                return true;
            }
        }
        final Boss boss = rocketPanel.getBoss();
        if (Objects.nonNull(boss) && boss.checkCollisionBullet(bullet)) {
            boss.setHit(true);
            return true;
        }
        return false;
    }

    private boolean checkEnemyCeilingCollision() {
        final var rockets = gameView.getRocketPanel().getRockets();
        for (final Rocket rocket : rockets) {
            if (rocket.checkCollisionCeiling(gameView.getLandscapePanel().getCeilingElements())) {
                rocket.setHit(true);
                rocket.setCrashed(true);
                return true;
            }
        }
        return false;
    }

    private boolean checkBulletTankCollision() {
        final var bullets = gameView.getBulletsPanel().getBullets();
        for (final FuelTank tank : fuelTankPanel.getFuelTanks()) {
            if (tank.checkCollisionBullet(bullets)) {
                tank.setDestroyed(true);
                return true;
            }
        }
        return false;
    }

    private void checkBombBulletCollisions() {
        final var bullets = gameView.getBulletsPanel();
        final List<Bullet> bulletsExploding = bullets.getBullets()
                .stream()
                .filter(bullet -> bullet.getType() == BulletType.TYPE_BOMB
                        && (bullet.checkGroundCollision(gameView.getLandscapePanel().getColumns())
                                || checkBulletEnemyCollision(bullet) || checkBulletTankCollision()))
                .toList();
        bullets.removeBullets(bulletsExploding);
        bullets.addExplodingBullets(bulletsExploding);
    }

    private void iteraction() {

        gameView.stopAllPanelTimers();
        stopFuelCheckTimer();
        stopCollisionTimer();
        spaceShipPanel.getSpaceship().setHit(true);
        timerLogic();

    }
}
