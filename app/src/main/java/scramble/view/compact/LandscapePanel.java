package scramble.view.compact;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import scramble.model.common.api.Pair;
import scramble.model.map.MapStageFactoryImpl;
import scramble.model.map.impl.MapStageImpl;
import scramble.model.map.utils.Converter;
import scramble.model.map.utils.LandscapeUtils;

/**
 * Class for the rappresentation of the Landscape Panel.
 * 
 * @see GamePanel
 * @see JPanel
 */
public class LandscapePanel extends GamePanel {

    private static final long serialVersionUID = 1L;

    private static final MapStageFactoryImpl STAGE_FACTORY = new MapStageFactoryImpl();

    // Da cambiare quando quando modifica MapElement ----
    private transient List<Pair<List<BufferedImage>, List<BufferedImage>>> columns;

    private transient List<MapStageImpl> stages;

    private int landscapeX;

    /** Costructor of the class LandscapePanel. */
    public LandscapePanel() {
        // da Modificare
        this.fillStage();
        this.fillColumns();

        // Rimarranno
        this.landscapeX = 0;
        this.setOpaque(false);
    }

    private void fillStage() {
        this.stages = new ArrayList<>();
        this.stages.add(LandscapePanel.STAGE_FACTORY.prestage());
        this.stages.add(LandscapePanel.STAGE_FACTORY.stage1());
    }

    private void fillColumns() {
        this.reinitialiseColumns();
        for (final MapStageImpl stage : this.stages) {
            for (int i = 0; i < stage.size(); i++) {
                this.columns.add(Converter.convertMapStageImpl(stage.getColumn(i)));
            }
        }
    }

    private void reinitialiseColumns() {
        this.columns = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void drawPanel(final Graphics g) {

        landscapeX -= 2;
        if (this.isPanelRepeintable() && landscapeX <= -LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE * columns.size()) {
            landscapeX = 0;
        }

        // Da modificare con la modifica di MapElement
        List<BufferedImage> ceiling;
        List<BufferedImage> floor;
        for (int x = 0; x < LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH; x++) {
            ceiling = this.columns.get(x).getFirstElement();
            floor = this.columns.get(x).getSecondElement();

            for (int y = 0; y < ceiling.size(); y++) {
                g.drawImage(ceiling.get(y), x * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE + landscapeX,
                        y * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE, LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        null);
            }

            for (int y = 0; y < floor.size(); y++) {
                g.drawImage(floor.get(y),
                        x * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE + landscapeX,
                        (LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT - 1 - y)
                                * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        null);
            }
        }

        this.canNotBeRepaint();
    }

}
