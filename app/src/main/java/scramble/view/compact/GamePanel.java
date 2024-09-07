package scramble.view.compact;

import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Abstract class for the rappresentation of a JPanel in the game.
 * This class extends javax.swing.JPanel.
 * 
 * @see JPanel
 */
public abstract class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Method for the update of the panel.
     * 
     * @param g
     */
    protected abstract void drawPanel(Graphics g);

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawPanel(g);
    }

    /**
     * Starts timer of the singular.
     */
    abstract void startTimer();

    /**
     * Stops timer of the singular panel.
     */
    public abstract void stopTimer();
}
