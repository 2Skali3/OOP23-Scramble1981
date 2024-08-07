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
    private boolean isRepeintable = true;

    /**
     * Method for the repaint management. 
     * It tells to the GamePanel that can repaint itself.
     */
    public void canBeRepaint() {
        this.isRepeintable = true;
    }

    /**
     * Method for the repaint management. 
     * It tells to the GamePanel that can't repaint itself.
     */
    public void canNotBeRepaint() {
        this.isRepeintable = false;
    }

    /**
     * Method that tells if the panel is repaintable or not.
     * @return true if panel is repaintable, false instend.
     */
    public boolean isPanelRepeintable() {
        return this.isRepeintable;
    } 

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
}
