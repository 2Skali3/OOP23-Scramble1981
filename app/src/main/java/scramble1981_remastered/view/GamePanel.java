package scramble1981_remastered.view;

import scramble1981_remastered.model.command.SpaceShipCommand;
import scramble1981_remastered.model.common.impl.Point2DImpl;
import scramble1981_remastered.model.map.*;
import scramble1981_remastered.model.spaceShip.SpaceShip;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

public class GamePanel extends JPanel {

    private final static int COLUMN_WIDTH = 16;
    private final static int SCALE_FACTOR = 1; // Scaling factor for heights

    private List<String> map;
    private TileMap tileMap;
    private int scrollX = 0; // Current scroll position
    private Map<String, List<Map<String, Object>>> level;

    private SpaceShip spaceship;
    private LevelsBuilder lb;

    public void sendCommand(SpaceShipCommand command) {
        command.execute();
    }

    /*
     * private Queue<SpaceshipCommand> commandQueue = ...
     * private void checkQueue() {
     * while(true) {
     * if (!commandQueue.empty()) {
     * var command = commandQueue.pop();
     * command.execute();
     * } else {
     * semaphore.wait();
     * }
     * }
     * }
     * 
     * private void sendCommand(SpaceshipCommand command) {
     * commandQueue.push(command);
     * semaphore.signal();
     * }
     */

    public GamePanel() {
        tileMap = new TileMap();
        lb = new LevelsBuilder();
        map = LandscapeData.getAllStages();
        level = lb.decodeLandscape(map);
        spaceship = new SpaceShip(50, getHeight() / 2, 32, 16);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawLandscape(g, level);
        drawSpaceship(g);
    }

    private void drawBackground(Graphics g) {
        // Draw a black starry background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Optionally, add stars
        g.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            g.fillRect(x, y, 2, 2);
        }
    }

    private void drawLandscape(Graphics g, Map<String, List<Map<String, Object>>> data) {

        List<Map<String, Object>> landscapeBottom = data.get("landscape_bottom");
        List<Map<String, Object>> landscapeTop = data.get("landscape_top");

        int x = -scrollX;
        if (landscapeTop != null) {
            for (Map<String, Object> segment : landscapeTop) {
                int ypos = (int) segment.get("ypos");
                if (ypos >= 0) {
                    int tileId = (int) segment.get("outer_tile_id");
                    int innerTileId = (int) segment.get("inner_tile_id");

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
        for (Map<String, Object> segment : landscapeBottom) {
            int ypos = (int) segment.get("ypos");
            int tileId = (int) segment.get("outer_tile_id");
            int innerTileId = (int) segment.get("inner_tile_id");

            g.drawImage(tileMap.getTile(tileId), x, ypos * COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH,
                    null);

            int y = ypos + 1;
            while (y * SCALE_FACTOR < getHeight()) {
                g.drawImage(tileMap.getTile(innerTileId), x, y * COLUMN_WIDTH, COLUMN_WIDTH,
                        COLUMN_WIDTH, null);
                y++;
            }

            x += COLUMN_WIDTH;
        }
    }

    private void drawSpaceship(Graphics g) {
        BufferedImage shipSprite = spaceship.getSprite();
        if (shipSprite != null) {
            g.drawImage(shipSprite, spaceship.getPosition().getX(), spaceship.getPosition().getY(),
                    spaceship.getWidth(), spaceship.getHeight(), null);
        }
    }

    public void moveSpaceship(int dx, int dy) {
        Point2DImpl location = spaceship.getPosition();
        int shipX = location.getX();
        int shipY = location.getY();
        if (shipX + dx < getWidth() / 2 &&
                shipX + dx > 0 && shipY + dy < getHeight() &&
                shipY + dy > 0) {
            spaceship.move(dx, dy);
        }
        repaint();
    }

    public void scrollBackground() {
        scrollX += COLUMN_WIDTH / 2;
        repaint();
    }

}
