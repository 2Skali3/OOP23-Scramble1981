package scramble.view.compact;

import scramble.model.scores.Scores;
import scramble.view.font.ScrambleFontUtil;

import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The start menu with the game starts. Loopes through the Scoreboard as
 * well.
 */
public final class StartMenu extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(StartMenu.class.getName());

    private int sequenceStep;
    private final Font retroFont;
    private final transient Scores scores;

    private static final class FixedConstants {

        private static final int KONAMI_HEIGHT = 5;
        private static final int KONAMI_WIDTH = 75;
        private static final int GAP30 = 30;
        private static final int POINTS_WIDTH = 120;
        private static final int SCORE_HEIGHT = 150;
        private static final int SCORE_WIDTH = 200;
        private static final int SYSTEM_WIDTH = 220;
        private static final int INVADE_WIDTH = 250;
        private static final int SCRAMBLE_WIDTH = 140;
        private static final int PLAY_WIDTH = 50;
        private static final float FONT_SIZE1 = 21f;
        private static final float FONT_SIZE2 = 24f;
        private static final int TIMERS = 3000;
        private static final int SEC = 60;
        private static final int OFFSET = 25;

    }

    /**
     * Class constructor.
     */
    public StartMenu() {
        setLayout(new BorderLayout());
        setOpaque(false);
        scores = new Scores();
        retroFont = ScrambleFontUtil.loadFont(FixedConstants.FONT_SIZE2);

        final Timer animationTimer = new Timer(1000 / FixedConstants.SEC, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                // repaint();
            }
        });
        animationTimer.start();

        sequenceStep = 0;
        final Timer scoreSequenceTimer = new Timer(FixedConstants.TIMERS, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sequenceStep++;
                if (sequenceStep > 2) {
                    sequenceStep = 0;
                }
                // repaint();
            }
        });
        scoreSequenceTimer.start();

    }

    private void paintBoard(final Graphics g) {
        g.setFont(retroFont.deriveFont(FixedConstants.FONT_SIZE1));

        if (sequenceStep == 0) {
            g.setColor(Color.YELLOW);
            g.drawString("PLAY", getWidth() / 2 - FixedConstants.PLAY_WIDTH, getHeight() / 4);

            g.setColor(Color.CYAN);
            g.drawString("- SCRAMBLE -", getWidth() / 2 - FixedConstants.SCRAMBLE_WIDTH, getHeight() / 3);

            g.setColor(Color.ORANGE);
            g.drawString("HOW FAR CAN YOU INVADE", getWidth() / 2 - FixedConstants.INVADE_WIDTH, getHeight() / 2);
            g.drawString("OUR SCRAMBLE SYSTEM?", getWidth() / 2 - FixedConstants.SYSTEM_WIDTH,
                    getHeight() / 2 + FixedConstants.GAP30);

            g.setColor(Color.WHITE);
            g.drawString("KONAMI", getWidth() / 2 - FixedConstants.KONAMI_WIDTH,
                    getHeight() - (getHeight() / FixedConstants.KONAMI_HEIGHT));
        } else if (sequenceStep == 1) {
            g.setColor(Color.RED);
            final int offset = FixedConstants.OFFSET;
            int index = 1;
            g.drawString("- SCORE RANKING -", getWidth() / 2 - FixedConstants.SCORE_WIDTH, getHeight() / 3);
            g.setColor(Color.MAGENTA);
            for (final Integer i : scores.getScoresList()) {
                g.drawString(index + "TH    " + i.toString() + "  PTS", getWidth() / 2 - FixedConstants.SCORE_WIDTH,
                        getHeight() / 2 + index * offset);
                index++;
            }
        } else if (sequenceStep == 2) {
            g.setColor(Color.YELLOW);
            g.drawString("- SCORE TABLE -", getWidth() / 2 - FixedConstants.SCORE_HEIGHT, getHeight() / 4);
            try {
                g.drawImage(ImageIO.read(getClass().getResource("/elements/rocket.png")),
                        getWidth() / 2 - FixedConstants.POINTS_WIDTH, getHeight() / 2, 32, 32, null);
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
            g.setColor(Color.WHITE);
            g.drawString("   50 PTS", getWidth() / 2 - FixedConstants.POINTS_WIDTH,
                    getHeight() / 2 + FixedConstants.GAP30);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        paintBoard(g);
    }

}
