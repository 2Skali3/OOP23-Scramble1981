package scramble.view.compact;

import scramble.view.font.ScrambleFontUtil;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Timer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** This class shows a brief game completed overlay. */
public class GameOverPanel extends GamePanel {

    private static final int OVERLAY_DURATION = 240;
    private static final long serialVersionUID = 1L;
    private static final List<String> MESSAGES = new ArrayList<>(
            Arrays.asList("Congratulations,", "you have conquered", "our scramble system!"));
    private static final float FONT_SIZE = 26F;
    private static final int TEXT_SCALE = GameView.WINDOW_HEIGHT / 6;

    private final Font retroFont;
    private final Timer gameOverTimer;
    private boolean overlayOn;
    private int counter;

    /** Class constructor. */
    public GameOverPanel() {
        retroFont = ScrambleFontUtil.loadFont(FONT_SIZE);
        this.setOpaque(false); // Ensure background is transparent so stars are visible
        this.gameOverTimer = new Timer(32, e -> {
            display();
        });
    }

    /** {@inheritDoc} */
    @Override
    public void startTimer() {
        gameOverTimer.start();
        this.overlayOn = true;
    }

    /** {@inheritDoc} */
    @Override
    public final void stopTimer() {
        gameOverTimer.stop();
        this.overlayOn = false;
    }

    /** {@inheritDoc} */
    @Override
    public final void restartTimer() {
        gameOverTimer.restart();
    }

    /**
     * Getter for game over.
     * 
     * @return a boolean
     */
    public boolean isOverlayOn() {
        return this.overlayOn;
    }

    /** Sets Game over to false in case of new game. */
    public void newGame() {
        this.overlayOn = false;
    }

    /** {@inheritDoc} */
    @Override
    protected void drawPanel(final Graphics g) {
        g.setFont(retroFont);
        g.setColor(Color.YELLOW);
        final int messageWidth0 = g.getFontMetrics().stringWidth(MESSAGES.get(0));
        final int x0 = (GameView.WINDOW_WIDTH - messageWidth0) / 2;
        final int y0 = TEXT_SCALE * 2;
        final int messageWidth1 = g.getFontMetrics().stringWidth(MESSAGES.get(1));
        final int x1 = (GameView.WINDOW_WIDTH - messageWidth1) / 2;
        final int y1 = TEXT_SCALE * 3;
        final int messageWidth2 = g.getFontMetrics().stringWidth(MESSAGES.get(2));
        final int x2 = (GameView.WINDOW_WIDTH - messageWidth2) / 2;
        final int y2 = TEXT_SCALE * 4;
        g.drawString(MESSAGES.get(0), x0, y0);
        g.drawString(MESSAGES.get(1), x1, y1);
        g.drawString(MESSAGES.get(2), x2, y2);
    }

    private void display() {
        if (counter == OVERLAY_DURATION) {
            stopTimer();
            counter = 0;
        }
        counter++;
    }

}
