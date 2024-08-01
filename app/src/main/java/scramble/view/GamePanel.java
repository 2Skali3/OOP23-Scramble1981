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

public class GamePanel extends JPanel implements ActionListener {

    private MapStageFactoryImpl stageFactory;
    private List<List<BufferedImage>> columns;
    private List<MapStageImpl> stages;
    private int landScapeX;
    private Timer timer;

    public GamePanel()
    {
        this.landScapeX = 0;
        this.stageFactory = new MapStageFactoryImpl();
        this.stages = new ArrayList<>();
        this.columns = new LinkedList<>();
        this.fillStage();
        this.fillColumns();

        landScapeX = 0;
        timer = new Timer(16, this); // Approx 60 FPS
    }

    private void fillStage() {
        this.stages.add(this.stageFactory.prestage());
        this.stages.add(this.stageFactory.stage1());
    }

    private void fillColumns() {
        for (MapStageImpl stage : this.stages){
            for(int i = 0; i < stage.size(); i++){
                this.columns.add(Converter.convertMapStageImplToBufferedImage(stage.getColumn(i)));
            }
        }
    }

    public void startGame() {
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        List<BufferedImage> column;

        for(int x = 0; x < LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH; x++)
        {
            column = this.columns.get(x);
            for(int y = 0; y < LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT; y++)
                g.drawImage(column.get(y), (x * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) + landScapeX, y * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE, LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE, LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        landScapeX -= 2;

        if (landScapeX <= -LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE * columns.size()) {
            landScapeX = 0;
        }
        
        repaint();
    }
    
}