package scramble.view.compact;

import java.awt.Graphics;
import javax.swing.Timer;

import scramble.model.common.impl.PairImpl;
import scramble.model.common.api.Pair;
import scramble.model.enemy.Boss;
import scramble.model.enemy.Rocket;
import scramble.model.scores.Scores;
import scramble.utility.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Iterator;

/**
 * Class for the rappresentation of the {@RocketImpl} in the window.
 *
 * @see GamePanel
 * @see JFrame
 */
public class RocketPanel extends GamePanel {

    private static final int BOSS_DIM = 48;
    private static final long serialVersionUID = 1L;
    private static final int ROCKET_OFFSET = 5;

    private final Timer updateTimer;
    private final Timer rocketSpawn;

    private transient List<Rocket> rockets;
    private transient List<Rocket> rocketsOnScreen;
    private transient List<Boss> bosses;

    private int mapX;

    /**
     * Class constructor {@code RocketPanel}.
     */
    public RocketPanel() {

        this.initializeRockets();
        this.fillRockets();
        this.initializeBosses();

        this.setOpaque(false);

        this.updateTimer = new Timer(32, e -> update());

        this.rocketSpawn = new Timer(64, e -> {
            this.loadRockets();
        });
    }

    /** Resets all rockets and refill the list anew. */
    public void resetRockets() {

        this.rocketsOnScreen.clear();
        this.rockets.clear();
        this.fillRockets();
        this.loadRockets();
        this.bosses = new ArrayList<>();

    }

    /**
     * Getter for the list of rockets on the screen.
     *
     * @return a copy of the list
     */
    public List<Rocket> getRockets() {
        return new ArrayList<>(rocketsOnScreen);
    }

    /** {@inheritDoc} */
    @Override
    public void startTimer() {
        this.rocketSpawn.start();
        this.updateTimer.start();
    }

    /** {@inheritDoc} */
    @Override
    public void stopTimer() {
        this.rocketSpawn.stop();
        this.updateTimer.stop();
    }

    /** {@inheritDoc} */
    @Override
    public void restartTimer() {
        this.rocketSpawn.restart();
        this.updateTimer.restart();
    }

    /**
     * Setter for MapX.
     *
     * @param x the new MapX
     */
    public void setMapX(final int x) {
        this.mapX = x;
    }

    /**
     * Getter for boss.
     * 
     * @return a single boss
     */
    public Boss getBoss() {
        return this.bosses.isEmpty() ? null : this.bosses.get(0);
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        for (final Rocket rocket : rocketsOnScreen) {
            if (rocket.getSprite() != null) {
                if (rocket.isHit()) {
                    g.drawImage(rocket.getExplosionSprite(), rocket.getPosition().getFirstElement(),
                            rocket.getPosition().getSecondElement(), rocket.getWidth(), rocket.getHeight(), null);
                    rocket.setExploded();
                } else {
                    g.drawImage(rocket.getSprite(), rocket.getPosition().getFirstElement(),
                            rocket.getPosition().getSecondElement(), rocket.getWidth(), rocket.getHeight(), null);
                }
            }
            rocket.drawHitBox(g);
        }

        if (!bosses.isEmpty()) {
            final Boss boss = bosses.get(0);
            if (boss.isHit()) {
                g.drawImage(bosses.get(0).getExplosionSprite(), boss.getPosition().getFirstElement(),
                        boss.getPosition().getSecondElement(),
                        boss.getWidth(), boss.getHeight(), null);
                if (!boss.isExploded()) {
                    boss.setExploded(true);
                }

            } else {
                g.drawImage(boss.getSprite(), boss.getPosition().getFirstElement(),
                        boss.getPosition().getSecondElement(),
                        boss.getWidth(), boss.getHeight(), null);
            }
        }

    }

    /**
     * Check if the boss is out of the screen.
     * 
     * @return {@code true} if is out of screen, {@code false} if is not spawned yet
     *         or is still on the screen
     */
    public boolean isBossOutOfScreen() {
        return !this.bosses.isEmpty() && this.bosses.get(0).getPosition().getFirstElement() < 0;
    }

    /** Initiliases boss list. */
    private void setUpBoss() {
        this.bosses.add(new Boss(GameView.WINDOW_WIDTH, GameView.WINDOW_HEIGHT / 2, BOSS_DIM, BOSS_DIM));
    }

    private void initializeBosses() {
        this.bosses = new ArrayList<>();
    }

    private void loadRockets() {
        final Iterator<Rocket> iterator = rockets.iterator();

        while (iterator.hasNext()) {
            final Rocket r = iterator.next();
            if (r.getPosition().getFirstElement() <= mapX + GameView.WINDOW_WIDTH) {
                r.updatePosition(new PairImpl<Integer, Integer>(GameView.WINDOW_WIDTH,
                        r.getPosition().getSecondElement() - Constants.ROCKET_HEIGHT));
                rocketsOnScreen.add(r);
                rocketsOnScreen.get(rocketsOnScreen.size() - 1).turnOnMove();
                iterator.remove();

            }

        }
        if (mapX >= Constants.BOSS_SPAWN_POINT && this.bosses.isEmpty()) {
            this.setUpBoss();
        }

    }

    private void checkForExplosion() {
        int count;
        final Iterator<Rocket> iterator = rocketsOnScreen.iterator();
        while (iterator.hasNext()) {
            final Rocket r = iterator.next();
            if (r.isExploded()) {
                count = r.incrementCounterForExplosion();
                if (count == Rocket.getExplosionDuration()) {
                    if (r.isHit() && !r.isCrashed()) {
                        Scores.incrementCurrentScore(Constants.ROCKET_POINTS);
                    }
                    iterator.remove();
                }
            }
        }
    }

    private void update() {
        if (Objects.nonNull(rocketsOnScreen)) {
            for (final Rocket rocket : rocketsOnScreen) {
                rocket.move();
            }
        }
        if (!bosses.isEmpty()) {
            final Boss boss = bosses.get(0);
            boss.updatePosition(new PairImpl<>(boss.getPosition().getFirstElement() - 1,
                    boss.getPosition().getSecondElement()));
        }
        checkForExplosion();
    }

    private void initializeRockets() {
        this.rockets = new ArrayList<>();
        this.rocketsOnScreen = new ArrayList<>();
    }

    private void fillRockets() {
        int counter = 0;
        final List<Pair<Integer, Integer>> spawnPosition = new ArrayList<>();
        spawnPosition.addAll(LandscapePanel.getMapController().getBrickFloorPosition());
        spawnPosition.addAll(LandscapePanel.getMapController().getFlatFloorPositions());
        for (final Pair<Integer, Integer> pos : spawnPosition) {
            if (counter % ROCKET_OFFSET == 0 && pos.getFirstElement() >= this.mapX + GameView.WINDOW_WIDTH
                    && pos.getFirstElement() < Constants.END_OF_ROCKET_SPAWN) {
                this.rockets
                        .add(new Rocket(pos.getFirstElement(), pos.getSecondElement(), Constants.ROCKET_WIDTH,
                                Constants.ROCKET_HEIGHT));
            }
            counter++;
        }
    }

}
