package scramble1981_remastered.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import scramble1981_remastered.model.scores.Scores;
import scramble1981_remastered.view.font.ScrambleFont;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu extends JPanel {

    private Timer animationTimer;
    private Timer scoreSequenceTimer;
    private int sequenceStep = 0;
    private Font retroFont;
    private Scores scores;

    public StartMenu() {
        setLayout(new BorderLayout());

        scores = new Scores();
        retroFont = ScrambleFont.loadFont(24f);

        animationTimer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        animationTimer.start();

        scoreSequenceTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sequenceStep++;
                if (sequenceStep > 2) {
                    sequenceStep = 0;
                }
                repaint();
            }
        });
        scoreSequenceTimer.start();

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

    private void paintBoard(Graphics g) {
        g.setFont(retroFont.deriveFont(21f));

        if (sequenceStep == 0) {
            g.setColor(Color.YELLOW);
            g.drawString("PLAY", getWidth() / 2 - 50, getHeight() / 4);

            g.setColor(Color.CYAN);
            g.drawString("- SCRAMBLE -", getWidth() / 2 - 140, getHeight() / 3);

            g.setColor(Color.ORANGE);
            g.drawString("HOW FAR CAN YOU INVADE", getWidth() / 2 - 250, getHeight() / 2);
            g.drawString("OUR SCRAMBLE SYSTEM?", getWidth() / 2 - 220, getHeight() / 2 + 30);

            g.setColor(Color.WHITE);
            g.drawString("KONAMI", getWidth() / 2 - 75, getHeight() - 100);
        } else if (sequenceStep == 1) {
            g.setColor(Color.RED);
            int offset = 25;
            int index = 1;
            g.drawString("- SCORE RANKING -", getWidth() / 2 - 200, getHeight() / 3);
            g.setColor(Color.MAGENTA);
            for (Integer i : scores.getScores()) {
                g.drawString(index + "TH    " + i.toString() + "  PTS", getWidth() / 2 - 200,
                        getHeight() / 2 + index * offset);
                index++;
            }
        } else if (sequenceStep == 2) {
            g.setColor(Color.YELLOW);
            g.drawString("- SCORE TABLE -", getWidth() / 2 - 150, getHeight() / 4);
            try {
                g.drawImage(ImageIO.read(getClass().getResource("/elements/rocket.png")), getWidth() / 2 - 120,
                        getHeight() / 2, 32, 32, null);
            } catch (Exception e) {
                System.out.println("rocket png non trovata");
            }
            g.setColor(Color.WHITE);
            g.drawString("   50 PTS", getWidth() / 2 - 120, getHeight() / 2 + 30);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        paintBoard(g);
    }

}