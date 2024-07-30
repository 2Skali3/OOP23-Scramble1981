package scramble.view;

import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import scramble.model.map.LandscapeUtil;
import scramble.model.scores.Scores;
import scramble.view.font.ScrambleFontUtil;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The start menu with wich the game starts.
 * Loopes through the Scoreboard as well.
 */
public final class StartMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int GAP30 = 30;
    private static final int PTSW = 120;
    private static final int SCOREH = 150;
    private static final int SCOREV = 200;
    private static final int KONH = 25;
    private static final int KONW = 75;
    private static final int SYSW = 220;
    private static final int INVW = 250;
    private static final int SRBW = 140;
    private static final int PLAYW = 50;
    private static final float FSIZE1 = 21f;
    private static final float FSIZE2 = 24f;
    private static final int TIMERS = 3000;
    private static final int SEC = 60;
    private static final int OFFSET = 25;
    private static final Logger LOG = Logger.getLogger(LandscapeUtil.class.getName());

    private int sequenceStep;
    private final Font retroFont;
    private final transient Scores scores;

    /**
     * Class constructor.
     */
    public StartMenu() {
        setLayout(new BorderLayout());

        scores = new Scores();
        retroFont = ScrambleFontUtil.loadFont(FSIZE2);

        final Timer animationTimer = new Timer(1000 / SEC, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                repaint();
            }
        });
        animationTimer.start();

        sequenceStep = 0;
        final Timer scoreSequenceTimer = new Timer(TIMERS, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sequenceStep++;
                if (sequenceStep > 2) {
                    sequenceStep = 0;
                }
                repaint();
            }
        });
        scoreSequenceTimer.start();

    }

    private void paintBoard(final Graphics g) {
        g.setFont(retroFont.deriveFont(FSIZE1));

        if (sequenceStep == 0) {
            g.setColor(Color.YELLOW);
            g.drawString("PLAY", getWidth() / 2 - PLAYW, getHeight() / 4);

            g.setColor(Color.CYAN);
            g.drawString("- SCRAMBLE -", getWidth() / 2 - SRBW, getHeight() / 3);

            g.setColor(Color.ORANGE);
            g.drawString("HOW FAR CAN YOU INVADE", getWidth() / 2 - INVW, getHeight() / 2);
            g.drawString("OUR SCRAMBLE SYSTEM?", getWidth() / 2 - SYSW, getHeight() / 2 + GAP30);

            g.setColor(Color.WHITE);
            g.drawString("KONAMI", getWidth() / 2 - KONW, getHeight() - KONH);
        } else if (sequenceStep == 1) {
            g.setColor(Color.RED);
            final int offset = OFFSET;
            int index = 1;
            g.drawString("- SCORE RANKING -", getWidth() / 2 - SCOREV, getHeight() / 3);
            g.setColor(Color.MAGENTA);
            for (final Integer i : scores.getScoresList()) {
                g.drawString(index + "TH    " + i.toString() + "  PTS", getWidth() / 2 - SCOREV,
                        getHeight() / 2 + index * offset);
                index++;
            }
        } else if (sequenceStep == 2) {
            g.setColor(Color.YELLOW);
            g.drawString("- SCORE TABLE -", getWidth() / 2 - SCOREH, getHeight() / 4);
            try {
                g.drawImage(ImageIO.read(getClass().getResource("/elements/rocket.png")), getWidth() / 2 - PTSW,
                        getHeight() / 2, 32, 32, null);
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
            g.setColor(Color.WHITE);
            g.drawString("   50 PTS", getWidth() / 2 - PTSW, getHeight() / 2 + GAP30);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        paintBoard(g);
    }

}
