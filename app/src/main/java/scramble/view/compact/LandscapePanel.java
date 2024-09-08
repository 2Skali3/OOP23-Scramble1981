package scramble.view.compact;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import scramble.controller.input.impl.InputControlImpl;
import scramble.controller.map.MapController;
import scramble.model.map.api.MapColumn;
import scramble.model.map.impl.MapElement;
import scramble.model.map.util.LandUtils;
import scramble.utility.Constants;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * Class for the rappresentation of the Landscape Panel.
 *
 * @see GamePanel
 * @see JPanel
 */
public class LandscapePanel extends GamePanel {
    /** Numebr of columns on screen. */
    public static final int COLUMNS_ON_SCREEN = GameView.WINDOW_WIDTH / LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
    /** Number of columns that aren't seen on screen but are still loaded. */
    public static final int EXTRA_COLUMNS_LOADED = 50;
    /** Number of total columns loaded. */
    public static final int TOTAL_COLUMNS_LOADED = COLUMNS_ON_SCREEN + EXTRA_COLUMNS_LOADED;

    private static final int PIXEL_THRESHOLD_FOR_UPDATE = LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE
            * LandscapePanel.EXTRA_COLUMNS_LOADED;

    private static final long serialVersionUID = 1L;
    private static final MapController MAP_CONTROLLER = new MapController();

    private int landscapeX;
    private int counter;

    public int getLandscapeX() {
        return landscapeX;
    }

    private int starterX;
    private transient List<MapColumn> columns;

    private final Timer landscapeTimer;

    private void updateLandscape() {
        if (!InputControlImpl.isExplPause()) {
            this.landscapeX += Constants.LANDSCAPEX_SPEED;
            this.counter += Constants.LANDSCAPEX_SPEED;
            if (this.landscapeX / LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE
                    + LandscapePanel.TOTAL_COLUMNS_LOADED == MAP_CONTROLLER.getMapSize()) {
                this.landscapeX = 0;
            } else if (-(this.landscapeX - this.starterX) % LandscapePanel.PIXEL_THRESHOLD_FOR_UPDATE == 0) {
                this.fillColumns();
                this.counter = 0;
            }
        }
    }

    /**
     * Returns the landscape.
     *
     * @return a 2D list
     */
    public List<MapElement> getColumns() {
        final List<MapElement> returnColumns = new ArrayList<>();
        for (final MapColumn mc : this.columns) {
            returnColumns.addAll(mc.getCeilings());
            returnColumns.addAll(mc.getFloors());
        }

        return returnColumns;
        // return new ArrayList<>(columns);
    }

    /** Costructor of the class LandscapePanel. */
    public LandscapePanel() {
        this.counter = 0;
        this.fillColumns();
        this.landscapeX = 0;
        this.starterX = 0;
        this.setOpaque(false);
        this.landscapeTimer = new Timer(32, e -> updateLandscape());
    }

    private void fillColumns() {
        this.columns = MAP_CONTROLLER.getColumnsToDisplay();
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        // to-do: dividi in sottometodi privati
        for (final MapColumn column : this.columns) {
            int tempY = 0;
            column.updateHitBoxX(-this.landscapeX);
            for (final BufferedImage bi : column.getBIListCeiling()) {
                g.drawImage(bi, column.getX() - this.landscapeX, tempY, column.getBIListWidth(),
                        column.getBIListHeight(), null);
                tempY += column.getBIListHeight();
            }
            tempY = column.getStartYFloor();
            for (final BufferedImage bi : column.getBIListFloor()) {
                g.drawImage(bi, column.getX() - this.landscapeX, tempY, column.getBIListWidth(),
                        column.getBIListHeight(), null);
                tempY += column.getBIListHeight();
            }

            for (final MapElement c : column.getCeilings()) {
                g.drawImage(c.getSprite(),
                        c.getX() - this.landscapeX, c.getY(),
                        c.getWidth(), c.getHeight(),
                        null);
            }

            for (final MapElement c : column.getFloors()) {
                g.drawImage(c.getSprite(),
                        c.getX() - this.landscapeX, c.getY(),
                        c.getWidth(), c.getHeight(),
                        null);
            }
        }
        drawHitBox(g);
    }

    private void drawHitBox(final Graphics g) {
        g.setColor(Color.red);
        for (final MapColumn c : columns) {
            for (final MapElement me : c.getCeilings()) {
                final Rectangle temp = me.getHitBox();
                g.drawRect(temp.x, temp.y, temp.width, temp.height);
            }
            for (final MapElement me : c.getFloors()) {
                final Rectangle temp = me.getHitBox();
                g.drawRect(temp.x, temp.y, temp.width, temp.height);
            }
        }
    }

    /**
     * Resets starting position of the map.
     *
     * @param starterPosition self explanatory
     */
    public void reset(final int starterPosition) {
        MAP_CONTROLLER.resetToX(starterPosition);
        this.landscapeX = +starterPosition * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
        this.starterX = landscapeX;
        this.fillColumns();
    }

    /**
     * Getter for the MapController of the LandscapePanel.
     * 
     * @return the MapController
     */
    public static MapController getMapController() {
        return MAP_CONTROLLER;
    }

    public int getCurrentMapX() {
        return MAP_CONTROLLER.getCurrentMapX() + this.counter;
    }

    /** @inheritDoc */
    @Override
    void startTimer() {
        this.landscapeTimer.start();
    }

    /** @inheritDoc */
    @Override
    public void stopTimer() {
        this.landscapeTimer.stop();
    }

}
