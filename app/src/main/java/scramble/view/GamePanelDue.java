package scramble.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import scramble.model.map.MapStageFactoryImpl;
import scramble.model.map.impl.MapStageImpl;
import scramble.model.map.utils.Converter;
import scramble.model.map.utils.LandscapeUtils;

import javax.swing.JPanel;

/**
 * Extension of the class JPanel and implementation of the interface
 * ActionListener.
 * 
 * @see JPanel
 * @see ActionListener
 */
public class GamePanelDue extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private final transient MapStageFactoryImpl stageFactory;
    private transient List<List<BufferedImage>> columns;
    private transient List<MapStageImpl> stages;
    private int landScapeX;
    private final Timer timer;

    /**
     * Constructor of GamePanel.
     */
    public GamePanelDue() {
        this.landScapeX = 0;
        this.stageFactory = new MapStageFactoryImpl();
        this.fillStage();
        this.fillColumns();
        landScapeX = 0;
        timer = new Timer(16, this); // Approx 60 FPS
    }

    private void fillStage() {
        this.stages = new ArrayList<>();
        this.stages.add(this.stageFactory.prestage());
        this.stages.add(this.stageFactory.stage1());
    }

    private void fillColumns() {
        reinitialiseColumns();
        for (final MapStageImpl stage : this.stages) {
            for (int i = 0; i < stage.size(); i++) {
                this.columns.add(Converter.convertMapStageImplToBufferedImage(stage.getColumn(i)));
            }
        }
    }

    private void reinitialiseColumns() {
        this.columns = new LinkedList<>();
    }

    /**
     * Method to start the game.
     */
    public void startGame() {
        timer.start();
    }

    /**
     * Method that insert the element into the window.
     * 
     * @param g
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        List<BufferedImage> column;
        for (int x = 0; x < LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH; x++) {
            column = this.columns.get(x);
            for (int y = 0; y < LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT; y++) {
                g.drawImage(column.get(y), x * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE + landScapeX,
                        y * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE, LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        null);
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        landScapeX -= 2;
        if (landScapeX <= -LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE * columns.size()) {
            landScapeX = 0;
        }
        repaint();
    }
}
