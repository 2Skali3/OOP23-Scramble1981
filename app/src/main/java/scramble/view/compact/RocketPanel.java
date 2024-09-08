package scramble.view.compact;

import java.awt.Graphics;
import javax.swing.Timer;

import scramble.model.common.impl.PairImpl;
import scramble.model.common.api.Pair;
import scramble.model.enemy.RocketImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Iterator;

public class RocketPanel extends GamePanel {

    private static final long serialVersionUID = 1L;

    private static final int ROCKET_HEIGHT = 34;
    private static final int ROCKET_WIDTH = 25;

    private transient List<RocketImpl> rockets;
    private transient List<RocketImpl> rocketsOnScreen;

    private final Timer updateTimer;
    private final Timer rocketSpawn;
    private final List<Pair<Integer, Integer>> flatPositions;

    private boolean st = false;

    private int mapX;

    public RocketPanel(final List<Pair<Integer, Integer>> flatFloorPosition) {

        // this.rocket = new RocketImpl(STARTER_POSITION_X, STARTER_POSITION_Y,
        // ROCKET_WIDTH, ROCKET_HEIGHT);
        this.rockets = new ArrayList<RocketImpl>();
        this.flatPositions = new ArrayList<>(flatFloorPosition);
        this.rocketsOnScreen = new ArrayList<>();
        this.fillRockets();

        this.setOpaque(false);

        updateTimer = new Timer(32, e -> update());
        // updateTimer.start();

        this.rocketSpawn = new Timer(64, e -> loadRockets());
        // this.rocketSpawn.start();
    }

    private void fillRockets() {
        int counter = 0;
        for (Pair<Integer, Integer> pos : flatPositions) {
            if (counter % 5 == 0 && pos.getFirstElement() > this.mapX) {
                this.rockets
                        .add(new RocketImpl(pos.getFirstElement(), pos.getSecondElement(), ROCKET_WIDTH,
                                ROCKET_HEIGHT));
            }
            counter++;
        }
    }

    public void resetRockets() {
        this.rocketsOnScreen.clear();
        this.rockets.clear();
        this.fillRockets();
    }

    @Override
    protected void drawPanel(final Graphics g) {
        for (RocketImpl rocket : rocketsOnScreen) {
            if (rocket.getSprite() != null) {
                if (rocket.isHit()) {
                    g.drawImage(rocket.getExplosionSprite(), rocket.getPosition().getFirstElement(),
                            rocket.getPosition().getSecondElement(), rocket.getWidth(), rocket.getHeight(), null);
                    rocket.setExploded(true);
                } else {
                    g.drawImage(rocket.getSprite(), rocket.getPosition().getFirstElement(),
                            rocket.getPosition().getSecondElement(), rocket.getWidth(), rocket.getHeight(), null);
                }
            }
            rocket.drawHitBox(g);
        }
    }

    public List<RocketImpl> getRockets() {
        return new ArrayList<>(rocketsOnScreen);
    }

    public void update() {
        if (Objects.nonNull(rocketsOnScreen)) {
            if (st) {
                System.out.println("Hello");
            }
            for (RocketImpl rocket : rocketsOnScreen) {
                rocket.move();
            }
        }
        checkForExplosion();
    }

    @Override
    void startTimer() {
        st = false;
        this.resetRockets();
        this.rocketSpawn.start();
        this.updateTimer.start();
    }

    @Override
    public void stopTimer() {
        st = true;
        this.rocketSpawn.stop();
        this.updateTimer.stop();
    }

    public void setMapX(final int x) {
        this.mapX = x;
    }

    private void loadRockets() {
        Iterator<RocketImpl> iterator = rockets.iterator();

        while (iterator.hasNext()) {
            RocketImpl r = iterator.next();
            if (r.getPosition().getFirstElement() <= mapX) {
                r.updatePosition(new PairImpl<Integer, Integer>(GameView.WINDOW_WIDTH,
                        r.getPosition().getSecondElement() - ROCKET_HEIGHT));
                rocketsOnScreen.add(r);
                rocketsOnScreen.get(rocketsOnScreen.size() - 1).turnOnMove();
                iterator.remove();

            }

        }
    }

    private void checkForExplosion() {
        int count;
        Iterator<RocketImpl> iterator = rocketsOnScreen.iterator();
        while (iterator.hasNext()) {
            RocketImpl r = iterator.next();
            if (r.isExploded()) {
                count = r.incrementCounterForExplosion();
                if (count == RocketImpl.getExplosionDuration()) {
                    iterator.remove();
                }
            }
        }
    }
}
