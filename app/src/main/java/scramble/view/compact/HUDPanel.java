package scramble.view.compact;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import scramble.controller.mediator.impl.LogicControllerImpl;
import scramble.model.common.util.BufferedImageManager;
import scramble.model.scores.Scores;
import scramble.model.spaceship.FuelBar;
import scramble.utility.Constants;
import scramble.view.font.ScrambleFontUtil;

/**
 * This class is dedicated to painting the fuelBar.
 */
public final class HUDPanel extends GamePanel {

    private static final long serialVersionUID = 1L;
    private static final float FONT_SIZE = 16f;
    private static final int GAP_Y = 30;
    private static final int LIVES_DIM = 5;

    private final Font retroFont;
    private transient BufferedImage fuelBarFull;
    private transient BufferedImage fuelBarEmpty;
    private transient BufferedImage stageHud;
    private int stage;
    private final Timer fuelTimer;

    private static final List<Float> STAGE_BAR_PAR = new ArrayList<>(
            Arrays.asList(new Float[] { 0.16f, 0.33f, 0.5f, 0.66f, 0.83f }));

    private static final List<Integer> STAGES = new ArrayList<>(
            Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }));

    private static final int INDEX_FIVE = 5;

    private static final Logger LOG = Logger.getLogger(FuelBar.class.getName());

    private final transient FuelBar fuelBar;

    private static final int SEC = 800;

    /** Class constructor. */
    public HUDPanel() {
        loadImages();
        fuelBar = new FuelBar();
        setOpaque(false);
        retroFont = ScrambleFontUtil.loadFont(FONT_SIZE);

        this.fuelTimer = new Timer(SEC, e -> {
            changeStage();
            this.fuelBar.decreaseFuel(Constants.FUEL_DECREASE_AMOUNT);
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPanel(final Graphics g) {
        paintFuelBar(g);
        paintStageHud(g);
        paintScore(g);
        paintLives(g);
    }

    /**
     * Draws both fuel bars images on top of each other.
     *
     * @param g graphic component
     */
    private void paintFuelBar(final Graphics g) {

        final int width = fuelBarFull.getWidth() * Constants.FUELBAR_SCALE_FACTOR;
        final int height = fuelBarFull.getHeight() * Constants.FUELBAR_SCALE_FACTOR;

        // Calculates the amount of empty to draw over the full bar
        final int fullWidth = (int) (fuelBar.getFuelLevel() / 100.0 * width);

        // Coordinates of starting draw point
        final int x = (getWidth() - width) / 2;
        final int y = getHeight() - 64;

        // Draws the empty bar
        g.drawImage(fuelBarEmpty, x, y, x + width, y + height, 0, 0, fuelBarEmpty.getWidth(), fuelBarEmpty.getHeight(),
                null);

        // Draws the full bar from right to left
        g.drawImage(fuelBarFull, x, y, x + fullWidth, y + height,
                fuelBarFull.getWidth() - (fullWidth / Constants.FUELBAR_SCALE_FACTOR),
                0, fuelBarFull.getWidth(), fuelBarFull.getHeight(), null);

    }

    private void paintStageHud(final Graphics g) {
        final int widthHud = (int) (stageHud.getWidth() * Constants.STAGE_HUD_SCALE_FACTOR);
        final int heightHud = (int) (stageHud.getHeight() * Constants.STAGE_HUD_SCALE_FACTOR);

        final int x = (getWidth() - widthHud) / 2;
        final int y = 10;

        if (stage == 0) {
            g.drawImage(
                    stageHud,
                    x, y,
                    x + widthHud, y + heightHud,
                    0, 0, stageHud.getWidth(), stageHud.getHeight(), null);
        } else {
            g.drawImage(
                    BufferedImageManager.substitutePurpleWithRed(stageHud,
                            stageHud.getWidth() * STAGE_BAR_PAR.get(stage - 1)),
                    x, y,
                    x + widthHud, y + heightHud,
                    0, 0, stageHud.getWidth(), stageHud.getHeight(), null);
        }

    }

    private void paintScore(final Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(retroFont.deriveFont(FONT_SIZE));

        final int currentScore = Scores.getCurrentScore();

        final String scoreText = "Score: " + currentScore;
        g.drawString(scoreText, 10, GAP_Y);
    }

    private void paintLives(final Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(retroFont.deriveFont(FONT_SIZE));

        final int currentLives = LogicControllerImpl.getLives() + 1;

        final String scoreText = "UP: " + currentLives;
        g.drawString(scoreText, getWidth() - (getWidth() / LIVES_DIM), GAP_Y);
    }

    /**
     * Loads from resources the fuel bars images.
     */
    private void loadImages() {
        try {
            fuelBarFull = ImageIO.read(HUDPanel.class.getResource("/hud/fuel_bar.png"));
            fuelBarEmpty = ImageIO.read(HUDPanel.class.getResource("/hud/fuel_bar_empty.png"));
            stageHud = ImageIO.read(HUDPanel.class.getResource("/hud/stage_board.png"));
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

    /**
     *
     */
    public void changeStage() {
        final int pos = LandscapePanel.getMapController().getCurrentMapX();

        if (pos > LogicControllerImpl.getCheckPoints().get(1).getFirstElement()
                && pos < LogicControllerImpl.getCheckPoints().get(2).getFirstElement()) {
            stage = STAGES.get(0);
        } else if (pos > LogicControllerImpl.getCheckPoints().get(2).getFirstElement()
                && pos < LogicControllerImpl.getCheckPoints().get(3).getFirstElement()) {
            stage = STAGES.get(1);
        } else if (pos > LogicControllerImpl.getCheckPoints().get(3).getFirstElement()
                && pos < LogicControllerImpl.getCheckPoints().get(4).getFirstElement()) {
            stage = STAGES.get(2);
        } else if (pos > LogicControllerImpl.getCheckPoints().get(4).getFirstElement()
                && pos < LogicControllerImpl.getCheckPoints().get(INDEX_FIVE).getFirstElement()) {
            stage = STAGES.get(3);
        } else if (pos > LogicControllerImpl.getCheckPoints().get(INDEX_FIVE).getFirstElement()) {
            stage = STAGES.get(4);
        }

    }

    /** @inheritDoc */
    @Override
    public void startTimer() {
        fuelTimer.start();
    }

    /** @inheritDoc */
    @Override
    public void stopTimer() {
        fuelTimer.stop();
    }

    /** @inheritDoc */
    @Override
    public void restartTimer() {
        fuelTimer.restart();
    }

    /**
     * Reset the number of stages to the starting stage.
     */
    public void resetStage() {
        this.stage = 0;
    }

}
