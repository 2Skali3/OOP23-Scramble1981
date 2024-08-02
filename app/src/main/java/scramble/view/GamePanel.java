package scramble.view;

import javax.swing.JPanel;

import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.LandscapeUtil;
import scramble.model.map.LevelsBuilder;
import scramble.model.map.TileMap;
import scramble.model.spaceship.SpaceShip;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Game panel that shows the landscape, player, enemies and fuel.
 */
public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int STARTX = 50;
    private static final int COLUMN_WIDTH = 16;
    private static final int SCALE_FACTOR = 1; // Scaling factor for heights

    private int scrollX; // Current scroll position
    private final transient TileMap tileMap;
    private final Map<String, List<Map<String, Object>>> level;
    private final transient SpaceShip spaceship;

    /**
     * Class constructor.
     */
    public GamePanel() {
        tileMap = new TileMap();
        level = new LevelsBuilder().decodeLandscape(LandscapeUtil.getAllStages());
        scrollX = 0;
        spaceship = new SpaceShip(STARTX, super.getHeight() / 2, 32, 16);
    }

    private void drawLandscape(final Graphics g, final Map<String, List<Map<String, Object>>> data) {

        final List<Map<String, Object>> landscapeBottom = data.get("landscape_bottom");
        final List<Map<String, Object>> landscapeTop = data.get("landscape_top");

        int x = -scrollX;
        if (landscapeTop != null) {
            for (final Map<String, Object> segment : landscapeTop) {
                final int ypos = (int) segment.get("ypos");
                if (ypos >= 0) {
                    final int tileId = (int) segment.get("outer_tile_id");
                    final int innerTileId = (int) segment.get("inner_tile_id");

                    // Disegna il segmento del soffitto
                    g.drawImage(tileMap.getTile(tileId), x, ypos * COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH, null);

                    int y = ypos - 1;
                    while (y * COLUMN_WIDTH >= 0) {
                        g.drawImage(tileMap.getTile(innerTileId), x, y * COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH,
                                null);
                        y--;
                    }
                }
                x += COLUMN_WIDTH;
            }
        }

        x = -scrollX;
        for (final Map<String, Object> segment : landscapeBottom) {
            final int ypos = (int) segment.get("ypos");
            final int tileId = (int) segment.get("outer_tile_id");
            final int innerTileId = (int) segment.get("inner_tile_id");

            g.drawImage(tileMap.getTile(tileId), x, ypos * COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH, null);

            int y = ypos + 1;
            while (y * SCALE_FACTOR < getHeight()) {
                g.drawImage(tileMap.getTile(innerTileId), x, y * COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH, null);
                y++;
            }

            x += COLUMN_WIDTH;
        }
    }

    private void drawSpaceship(final Graphics g) {
        final BufferedImage shipSprite = spaceship.getSprite();
        if (shipSprite != null) {
            g.drawImage(shipSprite, spaceship.getPosition().getFirstElement(),
                    spaceship.getPosition().getSecondElement(), spaceship.getWidth(), spaceship.getHeight(), null);
        }
    }

    /**
     * Method that executes the command sent to the spaceship.
     * 
     * @param command the command
     */
    public void sendCommand(final SpaceShipCommand command) {
        command.execute();
    }

    /**
     * Paints the game screen, basically overriding the method of the extended
     * class.
     * 
     * @param g the graphics component
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawLandscape(g, level);
        drawSpaceship(g);
    }

    /**
     * Moves the spaceship, controlled by the player, on the game panel.
     * 
     * @param dx movement on the X axis
     * @param dy movement on the Y axis
     */
    public void moveSpaceship(final int dx, final int dy) {
        final PairImpl<Integer, Integer> location = spaceship.getPosition();
        final int shipX = location.getFirstElement();
        final int shipY = location.getSecondElement();

        if (shipX + dx < getWidth() / 2 && shipX + dx > 0 && shipY + dy < getHeight() && shipY + dy > 0) {
            spaceship.move(dx, dy);
        }
        repaint();
    }

    /**
     * Handles scrolling of the game levels.
     */
    public void scrollBackground() {
        scrollX += COLUMN_WIDTH / 2;
        repaint();
    }

    /**
     * Getter for starting X scrolling position.
     * 
     * @return scrollX
     */
    public int getScrollX() {
        return scrollX;
    }

    /**
     * Getter for tilemap.
     * 
     * @return tileMap
     */
    public TileMap getTileMap() {
        return tileMap;
    }

    /**
     * Getter for the level.
     * 
     * @return level
     */
    public Map<String, List<Map<String, Object>>> getLevel() {
        return new HashMap<String, List<Map<String, Object>>>(level);
    }

    /**
     * Getter for the spaceship.
     * 
     * @return the spaceship
     */
    public SpaceShip getSpaceship() {
        return spaceship;
    }

}
