package scramble1981_remastered.view;

import scramble1981_remastered.model.command.SpaceShipCommand;
import scramble1981_remastered.model.common.impl.Point2DImpl;
import scramble1981_remastered.model.map.*;
import scramble1981_remastered.model.spaceShip.SpaceShip;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel {

    private static final int STAGES = 6;

    private int stage = 1;
    private List<String> currentStage;
    private TileMap tileMap;
    private TileTranslations tileTranslations;
    private final int columnWidth = 16;
    private final int scaleFactor = 1; // Scaling factor for heights
    private int scrollX = 0; // Current scroll position

    private SpaceShip spaceship;

    /*
    private Queue<SpaceshipCommand> commandQueue = ...
    private void checkQueue() {
        while(true) {
            if (!commandQueue.empty()) {
                var command = commandQueue.pop();
                command.execute();
            } else {
                semaphore.wait();
            }
        }
    }

    private void sendCommand(SpaceshipCommand command) {
        commandQueue.push(command);
        semaphore.signal();
    }
    */
    public void sendCommand(SpaceShipCommand command) {
        command.execute();
    }

    public GamePanel() {
        tileMap = new TileMap();
        tileTranslations = new TileTranslations();
        currentStage = LandscapeData.getDataForStage(stage);
        spaceship = new SpaceShip(50, getHeight() / 2, 32, 16);
        // TODO create new thread that launches checkQueue
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawLandscape(g, decodeLandscape(currentStage), 0);
        // drawLandscape(g, decodeLandscape(nextStage), stageTransitionX);
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

    private void drawLandscape(Graphics g, Map<String, List<Map<String, Object>>> data, int offsetX) {
        List<Map<String, Object>> landscapeBottom = data.get("landscape_bottom");
        List<Map<String, Object>> landscapeTop = data.get("landscape_top");

        int x = -scrollX;
        if (landscapeTop != null) {
            for (Map<String, Object> segment : landscapeTop) {
                int ypos = (int) segment.get("ypos");
                int tileId = (int) segment.get("outer_tile_id");
                int innerTileId = (int) segment.get("inner_tile_id");

                // Disegna il segmento del soffitto
                g.drawImage(tileMap.getTile(tileId), x, ypos * columnWidth, columnWidth, columnWidth, null);

                int y = ypos - 1;
                while (y * columnWidth > 0) {
                    g.drawImage(tileMap.getTile(innerTileId), x, y * columnWidth, columnWidth, columnWidth, null);
                    y--;
                }
                x += columnWidth;
            }
        }

        x = -scrollX;
        for (Map<String, Object> segment : landscapeBottom) {
            int ypos = (int) segment.get("ypos");
            int tileId = (int) segment.get("outer_tile_id");
            int innerTileId = (int) segment.get("inner_tile_id");

            g.drawImage(tileMap.getTile(tileId), x, ypos * columnWidth, columnWidth, columnWidth,
                    null);

            int y = ypos + 1;
            while (y * scaleFactor < getHeight()) {
                g.drawImage(tileMap.getTile(innerTileId), x, y * columnWidth, columnWidth,
                        columnWidth, null);
                y++;
            }

            x += columnWidth;
        }
    }

    private void drawSpaceship(Graphics g) {
        BufferedImage shipSprite = spaceship.getSprite();
        if (shipSprite != null) {
            g.drawImage(shipSprite, spaceship.getLocation().getX(), spaceship.getLocation().getY(),
                    spaceship.getWidth(), spaceship.getHeight(), null);
        } else {
            // Temporary placeholder
            g.setColor(Color.RED);
            g.fillRect(spaceship.getLocation().getX(), spaceship.getLocation().getY(), spaceship.getWidth(),
                    spaceship.getHeight());
        }
    }

    public Map<String, List<Map<String, Object>>> decodeLandscape(List<String> stageData) {

        List<Map<String, Object>> landscapeBottomData = new ArrayList<>();
        List<Map<String, Object>> landscapeTopData = new ArrayList<>();
        Map<String, List<Map<String, Object>>> landscapeData = new HashMap<>();

        int pointer = 0;

        while (pointer < stageData.size() && !isEndOfStage(stageData.get(pointer)) && stage != 6) {

            int firstCharPtr = (Integer.decode(stageData.get(pointer)) & 248) / 8;
            int firstChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 1)));
            int secondCharPtr = (Integer.decode(stageData.get(pointer + 2)) & 248) / 8;
            int secondChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 3)));

            // int groundObjectId = Integer.decode(stageData.get(pointer + 5));

            Map<String, Object> bottomSegment1 = createSegment(firstCharPtr, firstChar, stage > 3 ? 17 : 15,
                    "bottom");
            Map<String, Object> bottomSegment2 = createSegment(secondCharPtr, secondChar, stage > 3 ? 34 : 15,
                    "bottom");

            landscapeBottomData.add(bottomSegment1);
            landscapeBottomData.add(bottomSegment2);

            boolean hasCeiling = Integer.decode(stageData.get(pointer + 4)) != 0;
            if (hasCeiling) {
                int ceilingFirstCharPtr = (Integer.decode(stageData.get(pointer + 4)) & 248) / 8;
                int ceilingFirstChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 5)));
                int ceilingSecondCharPtr = (Integer.decode(stageData.get(pointer + 6)) & 248) / 8;
                int ceilingSecondChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 7)));

                Map<String, Object> topSegment1 = createSegment(ceilingFirstCharPtr, ceilingFirstChar,
                        stage > 3 ? 17 : 15, "top");
                Map<String, Object> topSegment2 = createSegment(ceilingSecondCharPtr, ceilingSecondChar,
                        stage > 3 ? 34 : 15, "top");
                landscapeTopData.add(topSegment1);
                landscapeTopData.add(topSegment2);

                pointer += 9;
            } else {
                pointer += 6;
            }

        }
        landscapeData.put("landscape_top", landscapeTopData);
        landscapeData.put("landscape_bottom", landscapeBottomData);

        return landscapeData;
    }

    private boolean isEndOfStage(String data) {
        return Integer.decode(data) == 255;
    }

    private Map<String, Object> createSegment(int ypos, int tileId, int inner, String position) {
        Map<String, Object> segment = new HashMap<>();
        segment.put("ypos", ypos);
        segment.put("outer_tile_id", tileId);
        segment.put("inner_tile_id", inner);
        segment.put("position", position);
        return segment;
    }

    public void scrollBackground() {
        scrollX += columnWidth / 2;
        if (scrollX >= getWidth()) {
            scrollX = 0;
            // stage = (stage % STAGES) + 1;
        }
        repaint();
    }

    public void moveSpaceship(int dx, int dy) {
        Point2DImpl location = spaceship.getLocation();
        if (location.getX() + dx < getWidth() / 2)
            spaceship.move(dx, dy);
        repaint();
    }

}
