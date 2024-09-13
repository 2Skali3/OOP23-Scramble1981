package scramble.view.compact;

import java.awt.Graphics;
import javax.swing.Timer;

import scramble.model.common.impl.PairImpl;
import scramble.model.common.api.Pair;
import scramble.model.enemy.RocketImpl;
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

    private static final long serialVersionUID = 1L;

    private static final int ROCKET_OFFSET = 5;

    private transient List<RocketImpl> rockets;
    private transient List<RocketImpl> rocketsOnScreen;

    private final Timer updateTimer;
    private final Timer rocketSpawn;

    private int mapX;

    /**
     * Class constructor {@code RocketPanel}.
     */
    public RocketPanel() {

        initializeRockets();
        this.fillRockets();

        this.setOpaque(false);

        updateTimer = new Timer(32, e -> update());
        // updateTimer.start();

        this.rocketSpawn = new Timer(64, e -> loadRockets());
        // this.rocketSpawn.start();
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
                        .add(new RocketImpl(pos.getFirstElement(), pos.getSecondElement(), Constants.ROCKET_WIDTH,
                                Constants.ROCKET_HEIGHT));
            }
            counter++;
        }
    }

    /** Resets all rockets and refill the list anew. */
    public void resetRockets() {

        // System.out.println("RocketPanel:\t" + mapX);
        this.rocketsOnScreen.clear();
        this.rockets.clear();
        this.fillRockets();
        this.loadRockets();
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        for (final RocketImpl rocket : rocketsOnScreen) {
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
    }

    /**
     * Getter for the list of rockets on the screen.
     *
     * @return a copy of the list
     */
    public List<RocketImpl> getRockets() {
        return new ArrayList<>(rocketsOnScreen);
    }

    private void update() {
        if (Objects.nonNull(rocketsOnScreen)) {
            for (final RocketImpl rocket : rocketsOnScreen) {
                rocket.move();
            }
        }
        checkForExplosion();
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

    private void loadRockets() {
        final Iterator<RocketImpl> iterator = rockets.iterator();

        while (iterator.hasNext()) {
            final RocketImpl r = iterator.next();
            if (r.getPosition().getFirstElement() <= mapX + GameView.WINDOW_WIDTH) {
                r.updatePosition(new PairImpl<Integer, Integer>(GameView.WINDOW_WIDTH,
                        r.getPosition().getSecondElement() - Constants.ROCKET_HEIGHT));
                rocketsOnScreen.add(r);
                rocketsOnScreen.get(rocketsOnScreen.size() - 1).turnOnMove();
                iterator.remove();

            }
        }
    }

    private void checkForExplosion() {
        int count;
        final Iterator<RocketImpl> iterator = rocketsOnScreen.iterator();
        while (iterator.hasNext()) {
            final RocketImpl r = iterator.next();
            if (r.isExploded()) {
                count = r.incrementCounterForExplosion();
                if (count == RocketImpl.getExplosionDuration()) {
                    if (r.isHit() && !r.isCrashed()) {
                        Scores.incrementCurrentScore(Constants.ROCKET_POINTS);
                    }
                    iterator.remove();
                }
            }
        }
    }

}
