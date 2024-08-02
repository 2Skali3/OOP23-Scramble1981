package scramble.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.util.logging.Logger;

/**
 * This class is in charge of the fuel bar part of the hud.
 * It loads two images, for empty and full bar.
 */
public final class FuelBar {
    private static final int MAX_FUEL = 100;
    private static final int SCALE_FACTOR = 2;
    private static final Logger LOG = Logger.getLogger(FuelBar.class.getName());

    private BufferedImage fuelBarFull;
    private BufferedImage fuelBarEmpty;
    private int fuelLevel; // shows the fuel level on the bar (varies from 1 to 100)

    /**
     * Class constructor.
     */
    public FuelBar() {
        loadImages();
        fuelLevel = MAX_FUEL; // Initially the fuel bar is full
        final Timer timer = new Timer(1000, e -> decreaseFuel(1));
        timer.start();
    }

    /**
     * Draws both fuel bars images on top of each other.
     * 
     * @param g          graphic component
     * @param panelWidth the Jpanel width
     */
    public void paintFuelBar(final Graphics g, final int panelWidth) {

        final int width = fuelBarFull.getWidth() * SCALE_FACTOR;
        final int height = fuelBarFull.getHeight() * SCALE_FACTOR;

        // Calculates the amount of empty to draw over the full bar
        final int fullWidth = (int) (fuelLevel / 100.0 * width);

        // Coordinates of starting draw point
        final int x = (panelWidth - width) / 2;
        final int y = 10;

        // Draws the empty bar
        g.drawImage(fuelBarEmpty, x, y, x + width, y + height, 0, 0, fuelBarEmpty.getWidth(), fuelBarEmpty.getHeight(),
                null);

        // Draws the full bar from right to left
        g.drawImage(fuelBarFull, x, y, x + fullWidth, y + height,
                fuelBarFull.getWidth() - (fullWidth / SCALE_FACTOR), 0,
                fuelBarFull.getWidth(), fuelBarFull.getHeight(),
                null);

    }

    /**
     * Loads from resources the fuel bars images.
     */
    private void loadImages() {
        try {
            fuelBarFull = ImageIO.read(getClass().getResource("/hud/fuel_bar.png"));
            fuelBarEmpty = ImageIO.read(getClass().getResource("/hud/fuel_bar_empty.png"));
        } catch (IOException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
    }

    /**
     * Decreases the fuel level.
     * 
     * @param amount the amount to subtract
     */
    public void decreaseFuel(final int amount) {
        fuelLevel -= amount;
        if (fuelLevel < 0) {
            fuelLevel = 0;
        }
    }

    /**
     * Increases the fuel level.
     * 
     * @param amount the amount to add
     */
    public void increaseFuel(final int amount) {
        fuelLevel += amount;
        if (fuelLevel > MAX_FUEL) {
            fuelLevel = MAX_FUEL;
        }
    }

    /**
     * Checks if fuel bar is empty.
     * 
     * @return True if fuel bar is empty
     */
    public boolean checkFuelZero() {
        return fuelLevel <= 0;
    }

    /**
     * Getter for the fuel threshold.
     * 
     * @return the current fuel level
     */
    public int getFuelLevel() {
        return fuelLevel;
    }
}
