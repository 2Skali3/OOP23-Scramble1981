package scramble.view;

import javax.swing.JPanel;

import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.command.impl.BulletCommand;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.LandscapeUtil;
import scramble.model.map.LevelsBuilder;
import scramble.model.map.TileMap;
import scramble.model.spaceship.SpaceShip;
import scramble.model.bullets.Bullet;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Game panel that shows the landscape, player, enemies and fuel.
 */
public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int START = 50;
    private static final int COLUMN_WIDTH = 16;
    private static final int SCALE_FACTOR = 1; // Scaling factor for heights

    private static final int BULLET_WIDTH = 3;
    private static final int BULLET_HEIGHT = 3;

    private int scrollX; // Current scroll position
    private final transient TileMap tileMap;
    private final Map<String, List<Map<String, Object>>> level;
    private final transient SpaceShip spaceship;
    private final transient FuelBar fuelBar;
    private transient List<Bullet> bullets;

    /**
     * Class constructor.
     */
    public GamePanel() {
        tileMap = new TileMap();
        level = new LevelsBuilder().decodeLandscape(LandscapeUtil.getAllStages());
        fuelBar = new FuelBar();
        scrollX = 0;
        spaceship = new SpaceShip(START, START, 32, 16);
        bulletInit();
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
        final Rectangle hb = spaceship.getHitBox();
        g.setColor(Color.CYAN);
        g.drawRect(hb.x, hb.y, hb.width, hb.height);
        if (shipSprite != null) {
            g.drawImage(shipSprite, spaceship.getPosition().getFirstElement(),
                    spaceship.getPosition().getSecondElement(), spaceship.getWidth(), spaceship.getHeight(), null);
        }
    }
    private void drawBullet(final Graphics g, final Bullet bullet) {
        final BufferedImage bulletSprite = bullet.getSprite();
        if (bulletSprite != null) {
            g.drawImage(bulletSprite, bullet.getPosition().getFirstElement(),
                    bullet.getPosition().getSecondElement(), bullet.getWidth(), bullet.getHeight(), null);
        }
    }
    private void drawBullets(final Graphics g) {
        // for each bullet in bullet list, call drawBullet()
        for (final Bullet bullet : bullets) {
            drawBullet(g, bullet);
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
     * Method that executes the command sent to the spaceship.
     *
     * @param command the command
     */
    public void sendCommandBullet(final BulletCommand command) {
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
        drawBullets(g);
        fuelBar.paintFuelBar(g, getWidth());
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

        if (shipX + dx < getWidth() / 2 && shipX + dx >= 0 && shipY + dy < getHeight() && shipY + dy >= 0) {
            spaceship.move(dx, dy);
        }
        repaint();
    }

    /**
    * For each bullet, call bullet.move().
    */
    public void moveBullets() {
        final List<Bullet> bulletsToRemove = new ArrayList<>();
        for (final Bullet bullet : bullets) {
            bullet.move();
            // Check if the bullet is out from screen
            if (bullet.getPosition().getFirstElement() > getWidth()) {
                bulletsToRemove.add(bullet);
            }
        }
        // removes bullets that have gone off the screen
        bullets.removeAll(bulletsToRemove);
        repaint();
    }

    /**
     * Shoots a bullet from the spaceship's current position.
     *
     * This method calculates the initial position of the bullet based on
     * the spaceship's current position and size. It then creates a new
     * {@link Bullet} instance and adds it to the list of bullets in the game.
     * The bullet's start position is at the right edge of the spaceship, centered
     * vertically.
     */
    public void shootBullet(/*final int bullet_type*/) {
        //bullet_type should be an enum, not an int
        final PairImpl<Integer, Integer> location = spaceship.getPosition();
        final int bulletX = location.getFirstElement() + spaceship.getWidth();
        final int bulletY = location.getSecondElement() + spaceship.getHeight() / 2;

        /*
            create new Bullet class and append to List<Bullet>
            start position is (shipX+shipWidth, shipY+shipHeight/2)
        */
        final Bullet bullet = new Bullet(bulletX, bulletY, BULLET_WIDTH, BULLET_HEIGHT);
        bullets.add(bullet);
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

    private void bulletInit()  {
        this.bullets = new ArrayList<>();
    }

}
