package scramble.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This class is used to create a starry background.
 * It's shared by the start menu and the game panel.
 */
public class Background extends JPanel {

    private static final long serialVersionUID = 1L;

    private void drawBackground(final Graphics g) {
        // Draw a black starry background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Optionally, add stars
        g.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            final int x = (int) (Math.random() * getWidth());
            final int y = (int) (Math.random() * getHeight());
            g.fillRect(x, y, 2, 2);
        }
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
        drawBackground(g);
    }

}
