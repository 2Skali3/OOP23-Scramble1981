package scramble.view.compact;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import scramble.controller.input.impl.InputControlImpl;
import scramble.controller.map.MapController;
import scramble.model.map.api.MapColumn;
import scramble.model.map.impl.MapElement;
import scramble.model.map.utils.LandscapeUtils;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Class for the rappresentation of the Landscape Panel.
 *
 * @see GamePanel
 * @see JPanel
 */
public class LandscapePanel extends GamePanel {
    /** Numebr of columns on screen. */
    public static final int COLUMNS_ON_SCREEN = GameView.WINDOW_WIDTH / LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
    /** Number of columns that aren't seen on screen but are still loaded. */
    public static final int EXTRA_COLUMNS_LOADED = 20;
    /** Number of total columns loaded. */
    public static final int TOTAL_COLUMNS_LOADED = COLUMNS_ON_SCREEN + EXTRA_COLUMNS_LOADED;

    private static final int PIXEL_THRESHOLD_FOR_UPDATE = LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE
            * LandscapePanel.EXTRA_COLUMNS_LOADED;
    private static final int LANDSCAPEX_SPEED = 4;

    private static final long serialVersionUID = 1L;
    private static final MapController MAP_CONTROLLER = new MapController();
    private int landscapeX;
    private int starterX;
    private transient List<MapColumn> columns;

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
        this.fillColumns();
        this.landscapeX = 0;
        this.starterX = 0;
        this.setOpaque(false);
    }

    private void fillColumns() {
        this.columns = MAP_CONTROLLER.getColumnsToDisplay();
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        // to-do: dividi in sottometodi privati
        if (!InputControlImpl.isExplPause() && this.isPanelRepeintable()) {
            this.landscapeX += LandscapePanel.LANDSCAPEX_SPEED;
            if (this.landscapeX / LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE
                    + LandscapePanel.TOTAL_COLUMNS_LOADED == MAP_CONTROLLER.getMapSize()) {
                this.landscapeX = 0;
            } else if (-(this.landscapeX - this.starterX) % LandscapePanel.PIXEL_THRESHOLD_FOR_UPDATE == 0) {
                this.fillColumns();
            }
        }
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
        this.landscapeX = +starterPosition * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
        this.starterX = landscapeX;
        this.fillColumns();
        this.canBeRepaint();
    }

}
