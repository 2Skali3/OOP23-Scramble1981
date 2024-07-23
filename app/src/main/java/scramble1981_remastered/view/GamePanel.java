package scramble1981_remastered.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import scramble1981_remastered.model.map.MapUtils;
import scramble1981_remastered.model.map.impl.MapStageFactoryImpl;
import scramble1981_remastered.model.map.impl.MapStageImpl;

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
        this.stages.add(this.stageFactory.starterFlatLand());
        this.stages.add(this.stageFactory.stage1());
    }

    private void fillColumns() {
        for (MapStageImpl stage : this.stages) 
        {
            for(int i = 0; i < stage.size(); i++)
                this.columns.add(stage.getStageLandScapeColumn(i));
        }
    }

    public void startGame() {
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        List<BufferedImage> column;

        for(int x = 0; x < MapUtils.STAGE_WIDTH; x++)
        {
            column = this.columns.get(x);
            for(int y = 0; y < MapUtils.STAGE_HEIGHT; y++)
                g.drawImage(column.get(y), (x * MapUtils.MAP_SPRITE_PX) + landScapeX, y * MapUtils.MAP_SPRITE_PX, MapUtils.MAP_SPRITE_PX, MapUtils.MAP_SPRITE_PX, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        landScapeX -= 2;

        if (landScapeX <= -MapUtils.MAP_SPRITE_PX * columns.size()) {
            landScapeX = 0;
        }
        
        repaint();
    }
    
}

