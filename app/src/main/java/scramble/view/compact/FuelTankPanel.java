package scramble.view.compact;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.swing.Timer;

import scramble.model.common.api.Pair;
import scramble.model.common.impl.PairImpl;
import scramble.model.enemy.RocketImpl;
import scramble.model.spaceship.FuelBar;
import scramble.model.tank.FuelTank;

/**
 * Class for the rappresentation of the FuelTank in the window.
 *
 * @see GamePanel
 * @see javax.swing.JFrame
 */
public class FuelTankPanel extends GamePanel {

    private static final long serialVersionUID = 1L;
    private transient List<FuelTank> fuelTanks;
    private transient List<FuelTank> tanksOnScreen;

    private final List<Pair<Integer, Integer>> flatPositions;
    private final Timer updateTimer;
    private final Timer refuelTimer;
    private static final int FUEL_TANK_HEIGHT = 32;
    private static final int FUEL_TANK_WIDTH = 32;

    private static final int SPAWN = 13;
    private static final int AVOID = 5;

    private int mapX;
    private final transient FuelBar fuelBar;

    /**
     * Constructor for the class {@code FuelTankPanel}.
     *
     * @param flatFloorPosition the list of spawn points for the tanks
     * @param fuelBar the fuelBar
     */
    public FuelTankPanel(final List<Pair<Integer, Integer>> flatFloorPosition, final FuelBar fuelBar) {

        this.fuelBar = fuelBar;
        initializeTank();
        this.flatPositions = new ArrayList<>(flatFloorPosition);

        fillTanks();
        this.setOpaque(false);

        updateTimer = new Timer(32, e -> update());
        this.refuelTimer = new Timer(64, e -> loadTanks());

    }
    private void initializeTank() {
        this.fuelTanks = new ArrayList<>();
        this.tanksOnScreen = new ArrayList<>();
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        for (final FuelTank tank : tanksOnScreen) {
            if (tank.getSprite() != null) {
                if (tank.isHit()) {
                    g.drawImage(tank.getExplosionSprite(), tank.getPosition().getFirstElement(),
                            tank.getPosition().getSecondElement(), tank.getWidth(), tank.getHeight(), null);
                    tank.setExploded(true);
                } else {
                    g.drawImage(tank.getSprite(), tank.getPosition().getFirstElement(),
                            tank.getPosition().getSecondElement(), tank.getWidth(), tank.getHeight(), null);
                }
            }
            tank.drawHitBox(g);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void startTimer() {
        updateTimer.start();
        refuelTimer.start();
    }

    /** {@inheritDoc} */
    @Override
    public void stopTimer() {
        updateTimer.stop();
        refuelTimer.stop();
    }

    /**
     * Method for resetting the {@link FuelTank} position.
     */
    public void resetTanks() {
        this.tanksOnScreen.clear();
        this.fuelTanks.clear();
        this.fillTanks();
    }

    /**
     * Getter for the {@link List} of {@link FuelTank}.
     *
     * @return a copy of the on screen tanks list
     */
    public List<FuelTank> getFuelTanks() {
        return new ArrayList<>(tanksOnScreen);
    }

    /**
     * Setter for mapX.
     *
     * @param x x-axis coordinate
     */
    public void setMapX(final int x) {
        this.mapX = x;
    }

    private void fillTanks() {
        int counter = 0;
        for (final Pair<Integer, Integer> pos : flatPositions) {
            if (counter % SPAWN == 0 && counter % AVOID != 0 && pos.getFirstElement() > this.mapX) {
                this.fuelTanks
                        .add(new FuelTank(pos.getFirstElement(), pos.getSecondElement(), FUEL_TANK_WIDTH,
                                FUEL_TANK_HEIGHT));
            }
            counter++;
        }
    }

    private void update() {
        if (Objects.nonNull(tanksOnScreen)) {
            for (final FuelTank tank : tanksOnScreen) {
                tank.move();
            }
        }
        checkForExplosion();
    }

    private void loadTanks() {
        final Iterator<FuelTank> iterator = fuelTanks.iterator();

        while (iterator.hasNext()) {
            final FuelTank ft = iterator.next();
            if (ft.getPosition().getFirstElement() <= mapX) {
                ft.updatePosition(new PairImpl<Integer, Integer>(GameView.WINDOW_WIDTH,
                        ft.getPosition().getSecondElement() - FUEL_TANK_HEIGHT));
                tanksOnScreen.add(ft);
                iterator.remove();
            }
        }
    }

    private void checkForExplosion() {
        int count;
        final Iterator<FuelTank> iterator = tanksOnScreen.iterator();
        while (iterator.hasNext()) {
            final FuelTank ft = iterator.next();
            if (ft.isExploded()) {
                count = ft.incrementCounterForExplosion();
                if (count == RocketImpl.getExplosionDuration()) {
                    fuelBar.increaseFuel(FuelTank.getFuelRefill());
                    iterator.remove();
                }
            }
        }
    }

}
