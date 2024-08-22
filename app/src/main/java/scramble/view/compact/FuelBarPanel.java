package scramble.view.compact;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import scramble.model.spaceship.FuelBar;

/**
 * This class is dedicated to painting the fuelBar.
 */
public final class FuelBarPanel extends GamePanel {

    private static final long serialVersionUID = 1L;
    private static final int SCALE_FACTOR = 2;
    private transient BufferedImage fuelBarFull;
    private transient BufferedImage fuelBarEmpty;

    private static final Logger LOG = Logger.getLogger(FuelBar.class.getName());

    private final transient FuelBar fuelBar;

    /** Class constructor. */
    public FuelBarPanel() {
        loadImages();
        fuelBar = new FuelBar();
        setOpaque(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void drawPanel(final Graphics g) {
        paintFuelBar(g, getWidth());
    }

    /**
     * Draws both fuel bars images on top of each other.
     * 
     * @param g          graphic component
     * @param panelWidth the Jpanel width
     */
    private void paintFuelBar(final Graphics g, final int panelWidth) {

        final int width = fuelBarFull.getWidth() * SCALE_FACTOR;
        final int height = fuelBarFull.getHeight() * SCALE_FACTOR;

        // Calculates the amount of empty to draw over the full bar
        final int fullWidth = (int) (fuelBar.getFuelLevel() / 100.0 * width);

        // Coordinates of starting draw point
        final int x = (panelWidth - width) / 2;
        final int y = 10;

        // Draws the empty bar
        g.drawImage(fuelBarEmpty, x, y, x + width, y + height, 0, 0, fuelBarEmpty.getWidth(), fuelBarEmpty.getHeight(),
                null);

        // Draws the full bar from right to left
        g.drawImage(fuelBarFull, x, y, x + fullWidth, y + height, fuelBarFull.getWidth() - (fullWidth / SCALE_FACTOR),
                0, fuelBarFull.getWidth(), fuelBarFull.getHeight(), null);

    }

    /**
     * Loads from resources the fuel bars images.
     */
    private void loadImages() {
        try {
            fuelBarFull = ImageIO.read(FuelBarPanel.class.getResource("/hud/fuel_bar.png"));
            fuelBarEmpty = ImageIO.read(FuelBarPanel.class.getResource("/hud/fuel_bar_empty.png"));
        } catch (IOException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
    }

    /**
     * Getter for the fuel bar.
     * 
     * @return the fuel bar
     */
    public FuelBar getFuelBar() {
        return fuelBar;
    }

}
